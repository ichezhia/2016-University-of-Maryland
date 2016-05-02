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

import tree.Tree;
import tree.EmptyTree;
import tree.EmptyTreeException;
import org.junit.*;

import static org.junit.Assert.*;

import java.util.ArrayList;

@SuppressWarnings("unchecked")
public class StudentTests {



   // tests addKeyWithValue and addKeyWithValue for adding duplicate keys
   //tests toString
   @Test
   public void test1() {
      Tree<Integer, String> tree = EmptyTree.getInstance();
      tree = tree.addKeyWithValue(new Integer(10), "1");
      tree = tree.addKeyWithValue(new Integer(5), "1");
      tree = tree.addKeyWithValue(new Integer(30), "1");
      tree = tree.addKeyWithValue(new Integer(2), "1");
      tree = tree.addKeyWithValue(new Integer(25), "1");
      tree = tree.addKeyWithValue(new Integer(45), "1");
      tree = tree.addKeyWithValue(new Integer(23), "1");

      tree = tree.addKeyWithValue(new Integer(24), "0");
      tree = tree.addKeyWithValue(new Integer(24), "1");

      assertEquals("2/1 5/1 10/1 23/1 24/1 25/1 30/1 45/1", tree.toString());

      tree = tree.deleteKeyAndValue(new Integer(30));

      assertEquals("2/1 5/1 10/1 23/1 24/1 25/1 45/1", tree.toString());
   }

   // tests addKeyWithValue for NullPointerException
   @Test(expected = NullPointerException.class)
   public void test2() {
      Tree<Integer, String> tree = EmptyTree.getInstance();
      tree = tree.addKeyWithValue(null, "1");
   }

   // tests addKeyWithValue for NullPointerException
   @Test(expected = NullPointerException.class)
   public void test3() {
      Tree<Integer, String> tree = EmptyTree.getInstance();
      tree = tree.addKeyWithValue(new Integer(10), null);
   }
   
   // tests size on empty and while adding and deleting
   @Test
   public void test4() {
      Tree<Integer, String> tree = EmptyTree.getInstance();
      assertEquals(0, tree.size());

      tree = tree.addKeyWithValue(new Integer(10), "1");
      assertEquals(1, tree.size());

      tree = tree.addKeyWithValue(new Integer(5), "1");
      assertEquals(2, tree.size());

      tree = tree.addKeyWithValue(new Integer(30), "1");
      tree = tree.addKeyWithValue(new Integer(2), "1");
      tree = tree.addKeyWithValue(new Integer(25), "1");
      tree = tree.addKeyWithValue(new Integer(45), "1");
      tree = tree.addKeyWithValue(new Integer(23), "1");
      tree = tree.addKeyWithValue(new Integer(24), "1");

      assertEquals(8, tree.size());

      tree = tree.deleteKeyAndValue(new Integer(30));

      assertEquals(7, tree.size());
   }
   
   
   // tests lookup on empty and while adding and deleting
   @Test
   public void test5() {
      Tree<Integer, String> tree = EmptyTree.getInstance();
      assertNull(tree.lookup(new Integer(1)));

      tree = tree.addKeyWithValue(new Integer(10), "1");
      assertEquals("1", tree.lookup(new Integer(10)));

      tree = tree.addKeyWithValue(new Integer(5), "11");
      assertNull(tree.lookup(new Integer(1)));
      assertEquals("11", tree.lookup(new Integer(5)));

      tree = tree.addKeyWithValue(new Integer(30), "10");

      assertEquals("10", tree.lookup(new Integer(30)));

      tree = tree.deleteKeyAndValue(new Integer(30));

      assertNull(tree.lookup(new Integer(30)));
   }
   
   // Tests pathFromRoot() on a tree in different situations
   @Test public void test6() {
      ArrayList<Integer> list= new ArrayList<Integer>();      
      Tree<Integer, String> tree = EmptyTree.getInstance();
      tree.pathFromRoot(10, list);
      assertEquals("", TestCode.listToStr(list));

      tree = tree.addKeyWithValue(new Integer(10), "1");
      ArrayList<Integer> list2= new ArrayList<Integer>();      
      tree.pathFromRoot(10, list2);
      assertEquals("10", TestCode.listToStr(list2));

      tree = tree.addKeyWithValue(new Integer(5), "1");
      assertEquals(2, tree.size());

      tree = tree.addKeyWithValue(new Integer(30), "1");
      tree = tree.addKeyWithValue(new Integer(2), "1");
      tree = tree.addKeyWithValue(new Integer(25), "1");
      tree = tree.addKeyWithValue(new Integer(45), "1");
      tree = tree.addKeyWithValue(new Integer(23), "1");
      tree = tree.addKeyWithValue(new Integer(24), "1");

      ArrayList<Integer> list3= new ArrayList<Integer>();      
      tree.pathFromRoot(24, list3);
      assertEquals("10 30 25 23 24", TestCode.listToStr(list3));
      
      tree = tree.deleteKeyAndValue(new Integer(23));

      ArrayList<Integer> list4= new ArrayList<Integer>();      
      tree.pathFromRoot(24, list4);
      assertEquals("10 30 25 24", TestCode.listToStr(list4));
      
      //not in tree
      tree.pathFromRoot(20, list4);
      assertEquals("10 30 25 24", TestCode.listToStr(list4));

   }
   
   //simple toString test
   @Test
   public void test7() {
      Tree<Integer, String> tree = EmptyTree.getInstance();
      tree = tree.addKeyWithValue(new Integer(10), "1");

      assertEquals("10/1", tree.toString());

      tree = tree.deleteKeyAndValue(new Integer(10));

      assertEquals("", tree.toString());

   }
   
   // tests numElementsAtLevel and min and max
   @Test
   public void test8() {
      Tree<Integer, String> tree = EmptyTree.getInstance();
      assertEquals(0, tree.numElementsAtLevel(1));
      assertEquals(0, tree.numElementsAtLevel(2));


      tree = tree.addKeyWithValue(new Integer(10), "1");
      assertEquals(1, tree.numElementsAtLevel(1));
      assertEquals(0, tree.numElementsAtLevel(2));

      tree = tree.addKeyWithValue(new Integer(5), "1");
      assertEquals(1, tree.numElementsAtLevel(2));

      tree = tree.addKeyWithValue(new Integer(30), "1");
      assertEquals(2, tree.numElementsAtLevel(2));

      
      tree = tree.addKeyWithValue(new Integer(2), "1");
      tree = tree.addKeyWithValue(new Integer(25), "1");
      tree = tree.addKeyWithValue(new Integer(45), "1");
      tree = tree.addKeyWithValue(new Integer(23), "1");
      tree = tree.addKeyWithValue(new Integer(24), "1");

      assertEquals(1, tree.numElementsAtLevel(1));
      assertEquals(2, tree.numElementsAtLevel(2));
      assertEquals(3, tree.numElementsAtLevel(3));
      assertEquals(1, tree.numElementsAtLevel(4));
      assertEquals(1, tree.numElementsAtLevel(5));
      
      try {
         assertEquals(2, (int) tree.min());
      } catch (EmptyTreeException e) {
      }
      try {
         assertEquals(45, (int) tree.max());
      } catch (EmptyTreeException e) {
      }


}
   
}
