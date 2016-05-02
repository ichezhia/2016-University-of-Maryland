/*
 * Iniyan Chezhian
 * ichezhi1
 * 114167101
 * CMSC 132-0301
 * 
 * I pledge on my honor that I have not given or received any unauthorized
 * assistance on this assignment.
 */

package tests;

import org.junit.*;
import list.List;
import static org.junit.Assert.*;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class StudentTests {

   // tests constructor to make empty list
   // tests if lists are empty
   // tests size is 0
   // multiple lists are made
   @Test
   public void test1() {
      List<String> testList1 = new List<String>();
      List<Integer> testList2 = new List<Integer>();

      assertNotNull(testList1);
      assertNotNull(testList2);

      assertEquals("", testList1.toString());
      assertEquals("", testList2.toString());

      assertEquals(0, testList1.size());
      assertEquals(0, testList2.size());
   }

   // tests sortedOrderInsert
   // tests toString
   // tests size
   // tests copy constructor

   @Test
   public void test2() {
      List<Integer> testList1 = new List<Integer>();

      testList1.sortedOrderInsert(10);
      testList1.sortedOrderInsert(20);
      testList1.sortedOrderInsert(20);
      testList1.sortedOrderInsert(5);

      assertNotNull(testList1);
      assertEquals("5 10 20 20", testList1.toString());
      assertEquals(4, testList1.size());

      List<Integer> testList2 = new List<Integer>(testList1);

      assertNotNull(testList1);
      assertEquals("5 10 20 20", testList1.toString());
      assertEquals(4, testList2.size());
   }

   //tests adding multiple of same element
   //tests toString
   //tests size
   //tests getElementAtIndex
   @Test
   public void test3() {
      List<Integer> testList1 = new List<Integer>();

      testList1.sortedOrderInsert(10);
      testList1.sortedOrderInsert(20);
      testList1.sortedOrderInsert(20);
      testList1.sortedOrderInsert(30);
      testList1.sortedOrderInsert(40);

      assertNotNull(testList1);
      assertEquals("10 20 20 30 40", testList1.toString());
      assertEquals(5, testList1.size());
      
      assertEquals(10, (int) testList1.getElementAtIndex(0));
      assertEquals(20, (int) testList1.getElementAtIndex(1));
      assertEquals(20, (int) testList1.getElementAtIndex(2));
      assertEquals(30, (int) testList1.getElementAtIndex(3));
      assertEquals(40, (int) testList1.getElementAtIndex(4));
   }
   
   
   //tests getElementAtIndex at index -1
   @Test(expected= IndexOutOfBoundsException.class) public void test4() {
      List<Integer> testList1 = new List<Integer>();

      testList1.sortedOrderInsert(10);
      testList1.sortedOrderInsert(20);

      testList1.getElementAtIndex(-1);
    }
   
   //tests getElementAtIndex at index greater than elements
   @Test(expected= IndexOutOfBoundsException.class) public void test5() {
      List<Integer> testList1 = new List<Integer>();

      testList1.sortedOrderInsert(10);
      testList1.sortedOrderInsert(20);

      testList1.getElementAtIndex(2);
    }
   
   //tests contains for existant and non-existant elements
   @Test
   public void test6() {
      List<Integer> testList1 = new List<Integer>();

      testList1.sortedOrderInsert(10);
      testList1.sortedOrderInsert(20);
      testList1.sortedOrderInsert(20);
      testList1.sortedOrderInsert(30);
      testList1.sortedOrderInsert(40);

      assertNotNull(testList1);
      assertEquals("10 20 20 30 40", testList1.toString());
      assertEquals(5, testList1.size());
      
      assertEquals(10, (int) testList1.contains(10));
      assertEquals(20, (int) testList1.contains(20));
      assertNull(testList1.contains(50));
   }
   
   //tests indexOf and lastIndexOf
   @Test
   public void test7() {
      List<Integer> testList1 = new List<Integer>();

      testList1.sortedOrderInsert(10);
      testList1.sortedOrderInsert(20);
      testList1.sortedOrderInsert(20);
      testList1.sortedOrderInsert(20);
      testList1.sortedOrderInsert(30);

      assertEquals(0, testList1.indexOf(10));
      assertEquals(1, testList1.indexOf(20));
      assertEquals(3, testList1.lastIndexOf(20));
   }
   
   //tests remove element
   //tests toString and size
   @Test
   public void test8() {
      List<Integer> testList1 = new List<Integer>();

      testList1.sortedOrderInsert(10);
      testList1.sortedOrderInsert(20);
      testList1.sortedOrderInsert(20);

      assertEquals("10 20 20", testList1.toString());
      assertEquals(3, testList1.size());
      
      assertFalse(testList1.removeElt(0));
      assertEquals("10 20 20", testList1.toString());
      assertEquals(3, testList1.size());

      assertTrue(testList1.removeElt(10));
      assertEquals("20 20", testList1.toString());
      assertEquals(2, testList1.size());

      assertTrue(testList1.removeElt(20));
      assertEquals("20", testList1.toString());
      assertEquals(1, testList1.size());

      assertTrue(testList1.removeElt(20));
      assertEquals("", testList1.toString());
      assertEquals(0, testList1.size());
   }
   
   //tests removeElementWithIndex
   @Test
   public void test9() {
      List<Integer> testList1 = new List<Integer>();

      testList1.sortedOrderInsert(10);
      testList1.sortedOrderInsert(20);
      testList1.sortedOrderInsert(20);
      testList1.sortedOrderInsert(30);

      assertEquals("10 20 20 30", testList1.toString());
      
      testList1.removeElementWithIndex(1);
      assertEquals("10 20 30", testList1.toString());
      
      testList1.removeElementWithIndex(0);
      assertEquals("20 30", testList1.toString());

      testList1.removeElementWithIndex(0);
      assertEquals("30", testList1.toString());

      testList1.removeElementWithIndex(0);
      assertEquals("", testList1.toString());
   }
   
   //tests removeElementWithIndex for IndexOutofBoundsException
   @Test(expected= IndexOutOfBoundsException.class) public void test10() {
      List<Integer> testList1 = new List<Integer>();

      testList1.sortedOrderInsert(10);
      testList1.sortedOrderInsert(20);

      testList1.removeElementWithIndex(-1);
   }
   
   //tests removeElementWithIndex for IndexOutofBoundsException
   @Test(expected= IndexOutOfBoundsException.class) public void test11() {
      List<Integer> testList1 = new List<Integer>();

      testList1.sortedOrderInsert(10);
      testList1.sortedOrderInsert(20);

      testList1.removeElementWithIndex(2);
   }
   
   //tests clear list then adding elements
   @Test
   public void test12() {
      List<Integer> testList1 = new List<Integer>();

      testList1.sortedOrderInsert(10);
      testList1.sortedOrderInsert(20);
      testList1.sortedOrderInsert(30);

      assertEquals("10 20 30", testList1.toString());
      assertEquals(3, testList1.size());
      
      testList1.clear();
      
      assertEquals("", testList1.toString());
      assertEquals(0, testList1.size());
      
      testList1.sortedOrderInsert(10);
      testList1.sortedOrderInsert(20);

      assertEquals("10 20", testList1.toString());
      assertEquals(2, testList1.size());
   }

   //tests sublist
   @Test
   public void test13() {
      List<Integer> testList1 = new List<Integer>();

      testList1.sortedOrderInsert(10);
      testList1.sortedOrderInsert(20);
      testList1.sortedOrderInsert(30);

      assertEquals("10 20 30", testList1.toString());
      
      List<Integer> testList2 = testList1.subList(0, 0); 
      assertEquals("10", testList2.toString());

      List<Integer> testList3 = testList1.subList(0, 1); 
      assertEquals("10 20", testList3.toString());
      
      List<Integer> testList4 = testList1.subList(0, 2); 
      assertEquals("10 20 30", testList4.toString());

      List<Integer> testList5 = testList1.subList(1, 1); 
      assertEquals("20", testList5.toString());
      
      List<Integer> testList6 = testList1.subList(1, 2); 
      assertEquals("20 30", testList6.toString());
   }
   
   //tests invalid index for sublist
   @Test(expected= IndexOutOfBoundsException.class) public void test14() {
      List<Integer> testList1 = new List<Integer>();

      testList1.sortedOrderInsert(10);
      testList1.sortedOrderInsert(20);
      
      List<Integer> testList2 = testList1.subList(-1, 0);
   }
   
   //tests invalid index for sublist
   @Test(expected= IndexOutOfBoundsException.class) public void test15() {
      List<Integer> testList1 = new List<Integer>();

      testList1.sortedOrderInsert(10);
      testList1.sortedOrderInsert(20);
      
      List<Integer> testList2 = testList1.subList(1, 0);
   }
   
   //tests invalid index for sublist
   @Test(expected= IndexOutOfBoundsException.class) public void test16() {
      List<Integer> testList1 = new List<Integer>();

      testList1.sortedOrderInsert(10);
      testList1.sortedOrderInsert(20);
      
      List<Integer> testList2 = testList1.subList(-6, -5);
   }
   
   //tests invalid index for sublist
   @Test(expected= IndexOutOfBoundsException.class) public void test17() {
      List<Integer> testList1 = new List<Integer>();

      testList1.sortedOrderInsert(10);
      testList1.sortedOrderInsert(20);
      
      List<Integer> testList2 = testList1.subList(2, 2);
   }
   
   //tests invalid index for sublist
   @Test(expected= IndexOutOfBoundsException.class) public void test18() {
      List<Integer> testList1 = new List<Integer>();

      testList1.sortedOrderInsert(10);
      testList1.sortedOrderInsert(20);
      
      List<Integer> testList2 = testList1.subList(2, 3);
   }
   
   //tests iterator
   //tests hasNext, next, and remove
   @Test
   public void test19() {
      List<Integer> testList1 = new List<Integer>();

      testList1.sortedOrderInsert(10);
      testList1.sortedOrderInsert(10);
      testList1.sortedOrderInsert(10);

      Iterator<Integer> it = testList1.iterator();
      
      while(it.hasNext()) {
         
         assertEquals(10,(int)it.next());
         it.remove();
      }
   }
   
   //tests iterator next for NoSuchElementException
   @Test(expected= NoSuchElementException.class) public void test20() {
      List<Integer> testList1 = new List<Integer>();

      testList1.sortedOrderInsert(10);
      testList1.sortedOrderInsert(10);

      Iterator<Integer> it = testList1.iterator();
      
      while(it.hasNext()) {
         assertEquals(10,(int)it.next());
         it.remove();
      }
      
      it.next();
   }
   
   //tests iterator next for IllegalStateException
   @Test(expected= IllegalStateException.class) public void test21() {
      List<Integer> testList1 = new List<Integer>();

      testList1.sortedOrderInsert(10);
      testList1.sortedOrderInsert(20);

      Iterator<Integer> it = testList1.iterator();
      
      it.next();

      it.remove();
      it.remove();
   }
   
   // tests lengthComparator for same size list
   @Test
   public void test22() {
      List<String> testList1 = new List<String>();
      testList1.insert("c");
      testList1.insert("h");

      List<String> testList2 = new List<String>();
      testList2.insert("c");
      testList2.insert("h");

      Comparator<List<String>> comparator = testList1.lengthComparator();

      assertEquals(0, comparator.compare(testList1, testList2));
   }
   
   // tests lengthComparator
   // first longer than second, return positive
   @Test
   public void test23() {
      List<String> testList1 = new List<String>();
      testList1.insert("c");
      testList1.insert("h");

      List<String> testList2 = new List<String>();
      testList2.insert("c");

      Comparator<List<String>> comparator = testList1.lengthComparator();

      assertTrue(comparator.compare(testList1, testList2) > 0);
   }
   
   // tests lengthComparator
   // first smaller than second, return negative
   @Test
   public void test24() {
      List<String> testList1 = new List<String>();
      testList1.insert("c");

      List<String> testList2 = new List<String>();
      testList2.insert("c");
      testList2.insert("h");

      Comparator<List<String>> comparator = testList1.lengthComparator();

      assertTrue(comparator.compare(testList1, testList2) < 0);
   }
   
   // tests orderComparator case, return 0
   @Test
   public void test25() {
      List<String> testList1 = new List<String>();
      testList1.insert("c");
      testList1.insert("h");
      testList1.insert("i");
      testList1.insert("n");

      List<String> testList2 = new List<String>();
      testList2.insert("c");
      testList2.insert("h");
      testList2.insert("i");
      testList2.insert("n");

      Comparator<List<String>> comparator = testList1.orderComparator();

      assertEquals(0, comparator.compare(testList1, testList2));
   }

   // tests orderComparator case, return negative
   @Test
   public void test26() {
      List<String> testList1 = new List<String>();
      testList1.insert("c");
      testList1.insert("h");
      testList1.insert("i");
      testList1.insert("n");

      List<String> testList2 = new List<String>();
      testList2.insert("l");
      testList2.insert("l");
      testList2.insert("a");
      testList2.insert("m");
      testList2.insert("a");

      Comparator<List<String>> comparator = testList1.orderComparator();

      assertTrue(comparator.compare(testList1, testList2) < 0);
   }

   // tests orderComparator case, return positive
   @Test
   public void test27() {
      List<String> testList1 = new List<String>();
      testList1.insert("c");
      testList1.insert("h");
      testList1.insert("i");
      testList1.insert("n");

      List<String> testList2 = new List<String>();
      testList2.insert("b");
      testList2.insert("u");
      testList2.insert("g");

      Comparator<List<String>> comparator = testList1.orderComparator();

      assertTrue(comparator.compare(testList1, testList2) > 0);
   }

   // tests orderComparator case, return positive
   @Test
   public void test28() {
      List<String> testList1 = new List<String>();
      testList1.insert("c");
      testList1.insert("h");
      testList1.insert("i");
      testList1.insert("n");

      List<String> testList2 = new List<String>();
      testList2.insert("c");
      testList2.insert("h");
      testList2.insert("i");
      testList2.insert("m");
      testList2.insert("p");

      Comparator<List<String>> comparator = testList1.orderComparator();

      System.out.println(comparator.compare(testList1, testList2));
      assertTrue(comparator.compare(testList1, testList2) > 0);
   }

   // tests orderComparator case, return negative
   @Test
   public void test29() {
      List<String> testList1 = new List<String>();
      testList1.insert("c");
      testList1.insert("h");
      testList1.insert("i");
      testList1.insert("n");

      List<String> testList2 = new List<String>();
      testList2.insert("c");
      testList2.insert("h");
      testList2.insert("i");
      testList2.insert("n");
      testList2.insert("s");

      Comparator<List<String>> comparator = testList1.orderComparator();

      assertTrue(comparator.compare(testList1, testList2) < 0);
   }
}
