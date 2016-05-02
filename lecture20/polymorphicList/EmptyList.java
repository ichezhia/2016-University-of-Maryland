package polymorphicList;

/* For examples of how the methods work, see the driver in main() in the
 * NonEmptyList class, as well as the JUnit tests in Tests.java.
 *
 * There's no constructor, because there's nothing for a constructor to do-
 * an EmptyList has no fields.  (The compiler will still generate a no-arg
 * default constructor anyway.)
 */

public class EmptyList implements List {

  public NonEmptyList insert(int value) {
    return new NonEmptyList(value, this);
  }

  public boolean find(int value) {
    return false;
  }

  public int getLastElement() throws ListIsEmptyException {
    throw new ListIsEmptyException();
  }

  public String toString() {
    return "";
  }

}
