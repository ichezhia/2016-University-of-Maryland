package join;

// illustrates that threads will run, and therefore can terminate, in any
// order, and that the original (main) method is also running in a thread

public class ThreadNoJoin implements Runnable {

  public void run() {
    int i;

    for (i= 0; i < 3; i++) {
      try {
        // random delay up to 1 sec. before every iteration
        Thread.sleep((int) (Math.random() * 1000));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      System.out.println(i);
    }
  }

  public static void main(String[] args) {
    Thread thread1= new Thread(new ThreadNoJoin());
    Thread thread2= new Thread(new ThreadNoJoin());

    thread1.start();
    thread2.start();

    System.out.println("Done!");
  }

}
