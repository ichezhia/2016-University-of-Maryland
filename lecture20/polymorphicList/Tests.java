package polymorphicList;

import org.junit.*;
import static org.junit.Assert.*;

public class Tests {

  @Test public void testIntegerInsert() {
    List list= new EmptyList();
    int i, arr[]= new int[]{60, 30, 10, 20, 50, 40};

    for (i= 0; i < arr.length; i++)
      // list must be assigned the result of list.insert (i.e., insert() is
      // not a void method in the polymorphic list implementation)
      list= list.insert(arr[i]);

    assertTrue(list.find(30));
    assertFalse(list.find(100));
    assertTrue(list.toString().equals("60 30 10 20 50 40 "));
  }

  @Test public void testMax() {
    List list= new EmptyList();
    int i, arr[]= new int[]{40, 30, 70, 1000, 50, 80};

    for (i= 0; i < arr.length; i++)
      list= list.insert(arr[i]);

    try {
      assertEquals(80, list.getLastElement());
    } catch (ListIsEmptyException e) {
      System.out.println("List has no maximum, you fool, as it is empty.");
    }
  }

}
