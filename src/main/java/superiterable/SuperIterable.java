package superiterable;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class SuperIterable<E> implements Iterable<E> {
  private Iterable<E> self;

  public SuperIterable(Iterable<E> self) {
    this.self = self;
  }

  public SuperIterable<E> filter(Predicate<E> predicate) {
    List<E> out = new ArrayList<>();
    self.forEach(x -> { if (predicate.test(x)) out.add(x);});
    return new SuperIterable<>(out);
  }

  public <F> SuperIterable<F> map(Function<E, F> op) {
    List<F> out = new ArrayList<>();
    self.forEach(x -> out.add(op.apply(x)));
    return new SuperIterable<>(out);
  }

  public <F> SuperIterable<F> flatMap(Function<E, SuperIterable<F>> op) {
    List<F> out = new ArrayList<>();
    self.forEach(x -> op.apply(x).forEach(y -> out.add(y)));
    return new SuperIterable<>(out);
  }

  public SuperIterable<E> distinct() {
    List<E> out = new ArrayList<>();
    Set<E> seen = new HashSet<>();
    self.forEach(x -> { if (seen.add(x)) out.add(x);});
    return new SuperIterable<>(out);
  }

  @Override
  public Iterator<E> iterator() {
    return self.iterator();
  }
}
