package threads;

public class Stopper {
  public static void main(String[] args) throws Throwable {
    boolean [] stop = { false };

    new Thread(() -> {
      while (! stop[0])
        ;
      System.out.println("Stopping!!!!");
    }).start();
    System.out.println("Job started...");
    Thread.sleep(1_000);
    System.out.println("About to set stop flag");
    stop[0] = true;
    System.out.println("Flag set to " + stop[0] + ", main exiting...");
  }
}
