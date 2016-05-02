package polymorphicList;

/* For examples of how the methods work, see the driver in main() below, as
 * well as the JUnit tests in Tests.java.
 */

public class NonEmptyList implements List {

  // note there's no head reference in a list represented by a NonEmptyList

  private int data;
  private List next;

  public NonEmptyList(int value, List next) {
    data= value;
    this.next= next;
  }

  // very important- what is this method doing and how is it working?
  // which insert() is being called?
  public NonEmptyList insert(int value) {
    next= next.insert(value);

    return this;
  }

  public boolean find(int value) {
    if (data == value)
      return true;
    else return next.find(value);
  }

  public int getLastElement() throws ListIsEmptyException {
    try {
      return next.getLastElement();
    } catch (ListIsEmptyException e) {
      return data;
    }
  }

  public String toString() {
    return data + " " + next;  // calls toString() on next
  }

  public static void main(String[] args) {
    List list= new EmptyList();  // note how a list is initialized!

    list= list.insert(3);  // note that insert() is not a void method, and its
                           // return value has to be assigned back to the list!
    list= list.insert(9);
    list= list.insert(7);
    list= list.insert(4);
    list= list.insert(2);

    System.out.println("The list is " + list);  // calls toString()

    System.out.println("list.find(3) returns " + list.find(3) + ".");
    System.out.println("list.find(2) returns " + list.find(2) + ".");
    System.out.println("list.find(8) returns " + list.find(8) + ".");

    try {
      System.out.println("The list's last element is " +
                         list.getLastElement() + ".");
    } catch (ListIsEmptyException liee) {
      liee.printStackTrace();
    }
  }

}
