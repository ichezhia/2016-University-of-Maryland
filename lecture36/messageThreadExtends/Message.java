package messageThreadExtends;

// creates threads by extending the Thread class; this is NOT the preferred
// approach for creating threads in Java; the MessageThreadRunnable example
// illustrates the preferred approach instead

public class Message extends Thread {

  private String msg;
  private int times;

  public Message(String msg, int times) {
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
    Message msg1= new Message("Llamas are cute and fuzzy.", times);
    Message msg2= new Message("It hurts when a kangaroo jumps on your head!",
                              times);

    msg1.start();
    msg2.start();
  }

}
