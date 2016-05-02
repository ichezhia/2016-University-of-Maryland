package tests;

// (c) Larry Herman, 2016.  You are allowed to use this code yourself, but
// not to provide it to anyone else.

import factorial.Factorial;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.*;
import static org.junit.Assert.*;

public class PublicTests {

  // The String class trim() method removes leading and trailing whitespace,
  // so the tests will pass even if your string returned by toString()
  // happens to have a blank space at its end.

  @Test public void testPublic1() {
    Factorial f= new Factorial(0, 0);

    assertEquals(f.toString().trim(), "1");
  }

  @Test public void testPublic2() {
    Factorial f= new Factorial(1, 1);

    assertEquals(f.toString().trim(), "1");
  }

  @Test public void testPublic3() {
    Factorial f= new Factorial(2, 2);

    assertEquals(f.toString().trim(), "2");
  }

  @Test public void testPublic4() {
    Factorial f= new Factorial(3, 3);

    assertEquals(f.toString().trim(), "6");
  }

  @Test public void testPublic5() {
    Factorial f= new Factorial(1, 4);

    assertEquals(f.toString().trim(), "1 2 6 24");
  }

  @Test public void testPublic6() {
    Factorial f= new Factorial(2, 5);

    assertEquals(f.toString().trim(), "2 6 24 120");
  }

  @Test public void testPublic7() {
    Factorial f= new Factorial(10, 10);

    assertEquals(f.toString().trim(), "3628800");
  }

  // This test tests the iterator explicitly, although it is also being
  // called indirectly (from the toString() method) in some tests above.
  @Test public void testPublic8() {
    Factorial f= new Factorial(5, 8);
    Iterator<Integer> iter= f.iterator();
    String answer= "";

    while (iter.hasNext())
      answer += iter.next() + " ";

    assertEquals(answer.trim(), "120 720 5040 40320");
  }

  @Test public void testPublic9() {
    Factorial f= new Factorial(10, 5);

    assertEquals(f.toString().trim(), "");
  }

  // Tests that the hasNext() method just returns false when the limits are
  // negative.
  @Test public void testPublic10() {
    assertFalse(new Factorial(-10, -5).iterator().hasNext());
  }

}
