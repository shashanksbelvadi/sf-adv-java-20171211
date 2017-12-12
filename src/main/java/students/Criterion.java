package students;

@FunctionalInterface
public interface Criterion<E> {
  boolean test(E s);
//  boolean dosomethingbad(Date d);

  default Criterion<E> and(Criterion<E> second) {
    return s -> this.test(s) && second.test(s);
  }

  default Criterion<E> or(Criterion<E> second) {
    return s -> this.test(s) || second.test(s);
  }

  default Criterion<E> negate() {
    return s -> ! this.test(s);
  }

//  static <E> Criterion<E> and(Criterion<E> first, Criterion<E> second) {
//    return s -> first.test(s) && second.test(s);
//  }
//
//  static <E> Criterion<E> or(Criterion<E> first, Criterion<E> second) {
//    return s -> first.test(s) || second.test(s);
//  }
//
//  static <E> Criterion<E> negate(Criterion<E> crit) {
//    return s -> ! crit.test(s);
//  }
//
}
