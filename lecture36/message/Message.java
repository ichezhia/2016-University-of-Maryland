package message;

// doesn't use threads at all, but is the basis of the two other examples
// here that do

public class Message {

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

  public static void main(String[] args) {
    int times= 100;
    Message msg1= new Message("Llamas are cute and fuzzy.", times);
    Message msg2= new Message("It hurts when a kangaroo jumps on your head!",
                              times);

    msg1.print();
    msg2.print();
  }

}
