package threads;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

class Consumer implements Runnable {
  private BlockingQueue<int[]> queue;

  public int[] counters = new int[10_000];

  public Consumer(BlockingQueue<int[]> queue) {
    this.queue = queue;
  }

  @Override
  public void run() {
    boolean shutdown = false;

    while ((! shutdown) || (! queue.isEmpty())) {
      int [] datablock;
      if (shutdown) {
        datablock = queue.poll();
      } else {
        try {
          datablock = queue.take();
        } catch (InterruptedException ie) {
          shutdown = true;
          continue;
        }
      }
      if (datablock[0] != datablock[1]) {
        System.out.println("!!! ERROR, got " + datablock[0] + " and " + datablock[1]);
      } else {
        counters[datablock[0]]++;
      }
    }
    System.out.println("Consumer shutting down...");
  }
}

class Producer implements Runnable {
  private BlockingQueue<int[]> queue;
  private Semaphore sem;

  public Producer(BlockingQueue<int[]> queue, Semaphore sem) {
    this.queue = queue;
    this.sem = sem;
  }

  @Override
  public void run() {
    try {
      for (int i = 0; i < 10_000; i++) {
        int[] data = {i, 0};
        if (i < 1_000) {
          Thread.sleep(1);
        }
        if (i == 99) {
          queue.put(new int[]{3,4});
        }
        if (i == 199) {
          queue.put(new int[]{199, 199});
        }
        data[1] = i;
        queue.put(data);
        data = null;
      }
    } catch (InterruptedException ie) {
      System.out.println("Unexpected interrupt to producer...?");
    }
    sem.release();
    System.out.println("Producer shutting down...");
  }
}

public class ProdCons {
  public static void main(String[] args) {
    BlockingQueue<int[]> queue = new ArrayBlockingQueue<int[]>(10);
    Semaphore sem = new Semaphore(-2);
    Runnable prod = new Producer(queue, sem);
    for (int i = 0; i < 3; i++) {
      new Thread(prod).start();
    }
    Consumer cons1 = new Consumer(queue);
    Consumer cons2 = new Consumer(queue);
    Consumer cons3 = new Consumer(queue);
    Consumer cons4 = new Consumer(queue);
    Thread t1 = new Thread(cons1);
    Thread t2 = new Thread(cons2);
    Thread t3 = new Thread(cons3);
    Thread t4 = new Thread(cons4);
    t1.start();
    t2.start();
    t3.start();
    t4.start();

    try {
      sem.acquire();
      System.out.println("Producers shut down, interrupting consumers");
      t1.interrupt();
      t2.interrupt();
      t3.interrupt();
      t4.interrupt();
      t1.join();
      t2.join();
      t3.join();
      t4.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println("Main sees producers and consumers finished.");

    for (int i = 0; i < 10_000; i++) {
      cons1.counters[i] += cons2.counters[i];
    }
    for (int i = 0; i < 10_000; i++) {
      cons1.counters[i] += cons3.counters[i];
    }
    for (int i = 0; i < 10_000; i++) {
      cons1.counters[i] += cons4.counters[i];
    }

    for (int i = 0; i < 10_000; i++) {
      if (cons1.counters[i] != 3) {
        System.out.println("!!!! PROBLEM count incorrect. Index "
            + i + " count is " + cons1.counters[i]);
      }
    }

    System.out.println("Main exiting.");
  }
}
