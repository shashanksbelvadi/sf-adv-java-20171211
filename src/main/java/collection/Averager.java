package collection;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;
import java.util.stream.DoubleStream;

class Average {
  private double sum;
  private long count;

  public Average() {}

  public void include(double d) {
    sum += d;
    count++;
  }

  public void merge(Average other) {
    sum += other.sum;
    count += other.count;
  }

  public double get() {
    return sum / count;
  }

  public void use(Consumer<Double> op) {
    op.accept(get());
  }
}

public class Averager {
  public static void main(String[] args) {
    long start = System.nanoTime();
    DoubleStream.generate(() -> ThreadLocalRandom.current().nextDouble(-Math.PI, +Math.PI))
        .limit(200_000_000L)
//        .parallel()
        .map(x -> Math.sin(x))
        .collect(()->new Average(), (a, d) -> a.include(d), (a1, a2) -> a1.merge(a2))
        .use(x -> System.out.println("Average is " + x));
    long time = System.nanoTime() - start;
    System.out.println("Time was " + (time / 1_000_000_000.0) + " seconds");
  }
}
