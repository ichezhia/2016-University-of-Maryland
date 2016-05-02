package messageThreadRunnable;

// creates threads by implementing the Runnable interface (the preferred
// approach)

public class MessageWorker implements Runnable {

  private String msg;
  private int times;

  public MessageWorker(String msg, int times) {
    this.msg= msg;
    this.times= times;
  }

  public void print() {
    int i;

    for (i= 0; i < times; i++)
      System.out.println(msg);
  }

  public void run() {
    // random delay up to 1 sec. to cause variation in which thread runs first
    try {
      Thread.sleep((int) (Math.random() * 1000));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    print();
  }

  public static void main(String[] args) {
    // try changing to a larger value like 100000 to see execution switch
    // between threads
    int times= 100;
    MessageWorker msg1= new MessageWorker("Llamas are cute and fuzzy.", times);
    MessageWorker msg2= new MessageWorker("It hurts when a kangaroo jumps " +
                                          "on your head!", times);

    Thread tmsg1= new Thread(msg1);
    Thread tmsg2= new Thread(msg2);

    tmsg1.start();
    tmsg2.start();
  }

}
