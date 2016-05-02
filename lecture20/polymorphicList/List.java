package polymorphicList;

/* For examples of how the methods work, see the driver in main() in the
 * NonEmptyList class, as well as the JUnit tests in Tests.java.
 */

public interface List {

  public NonEmptyList insert(int value);
  public boolean find(int value);
  public int getLastElement() throws ListIsEmptyException;

}
