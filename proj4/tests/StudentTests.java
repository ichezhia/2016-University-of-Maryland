package tests;

// (c) Larry Herman, 2016.  You are allowed to use this code yourself, but
// not to provide it to anyone else.

import java.util.List;
import recursiveMethods.RecursiveMethods;
import org.junit.*;
import static org.junit.Assert.*;

public class StudentTests {

   // tests parameters of replaceRange
   @Test
   public void test1() {
      List<Character> listC = sampleList1();

      RecursiveMethods.replaceRange(listC, 6, 4, 'z');
      RecursiveMethods.replaceRange(listC, -1, 0, 'z');
      RecursiveMethods.replaceRange(listC, -1, -1, 'z');
      RecursiveMethods.replaceRange(listC, 4, 4, 'z');
      RecursiveMethods.replaceRange(listC, 4, 5, 'z');

      assertEquals("c h a r", TestCode.listToStr(listC));
   }

   // tests functionality of replaceRange
   @Test
   public void test2() {
      List<Character> listC = sampleList1();

      RecursiveMethods.replaceRange(listC, 0, 0, 'z');

      assertEquals("z h a r", TestCode.listToStr(listC));
   }
   
   // tests functionality of replaceRange
   @Test
   public void test3() {
      List<Character> listC = sampleList1();

      RecursiveMethods.replaceRange(listC, 0, 1, 'z');

      assertEquals("z z a r", TestCode.listToStr(listC));
   }
   
   // tests functionality of replaceRange
   @Test
   public void test4() {
      List<Character> listC = sampleList1();

      RecursiveMethods.replaceRange(listC, 2, 3, 'z');

      assertEquals("c h z z", TestCode.listToStr(listC));
   }
   
   // tests functionality of replaceRange
   @Test
   public void test5() {
      List<Character> listC = sampleList1();

      RecursiveMethods.replaceRange(listC, 3, 3, 'z');

      assertEquals("c h a z", TestCode.listToStr(listC));
   }
   
   // tests functionality of areReversed
   @Test
   public void test6() {
      List<Character> listC = sampleList1();
      List<Character> listCReverse = sampleList1Reverse();
      List<Character> listRAH = sampleListRAH();
      List<Character> listAHC = sampleListAHC();
      List<Character> emptyList = emptyList();

      assertTrue(RecursiveMethods.areReversed(emptyList, emptyList));
      
      assertTrue(RecursiveMethods.areReversed(listC, listCReverse));
      assertTrue(RecursiveMethods.areReversed(listCReverse, listC));

      assertFalse(RecursiveMethods.areReversed(listC, listRAH));
      assertFalse(RecursiveMethods.areReversed(listC, listAHC));
      assertFalse(RecursiveMethods.areReversed(listRAH, listC));
      assertFalse(RecursiveMethods.areReversed(listAHC, listC));
      
      assertFalse(RecursiveMethods.areReversed(listCReverse, listRAH));
      assertFalse(RecursiveMethods.areReversed(listCReverse, listAHC));
      assertFalse(RecursiveMethods.areReversed(listRAH, listCReverse));
      assertFalse(RecursiveMethods.areReversed(listAHC, listCReverse));
      
      assertFalse(RecursiveMethods.areReversed(listAHC, listRAH));
      assertFalse(RecursiveMethods.areReversed(listRAH, listAHC));
   }
   
   // tests functionality of posOfMaxElt
   @Test
   public void test7() {
      List<Integer> list3 = sampleList3();
      List<Integer> list4 = sampleList4();
      List<Integer> list5 = sampleList5();
      List<Integer> list6 = sampleList6();
      List<Integer> list7 = sampleList7();
      List<Integer> list8 = sampleList8();
      List<Integer> list9 = sampleList9();

      List<Character> emptyList = emptyList();

      assertEquals(0, RecursiveMethods.posOfMaxElt(list9));
      assertEquals(4, RecursiveMethods.posOfMaxElt(list3));
      assertEquals(4, RecursiveMethods.posOfMaxElt(list4));
      assertEquals(0, RecursiveMethods.posOfMaxElt(list5));
      assertEquals(1, RecursiveMethods.posOfMaxElt(list6));
      assertEquals(1, RecursiveMethods.posOfMaxElt(list7));
      assertEquals(3, RecursiveMethods.posOfMaxElt(list8));
      assertEquals(-1, RecursiveMethods.posOfMaxElt(emptyList));
   }
   
   // tests functionality of changeAllFromTo
   @Test
   public void test8() {
      List<Integer> list3 = sampleList3();
      List<Integer> list4 = sampleList4();
      List<Integer> list5 = sampleList5();
      List<Integer> list6 = sampleList6();
      List<Integer> list7 = sampleList7();
      List<Integer> list8 = sampleList8();
      List<Integer> list9 = sampleList9();

      List<Character> emptyList = emptyList();

      assertEquals("2", TestCode.listToStr(RecursiveMethods.changeAllFromTo(list9, 10, 2)));
      assertEquals("10", TestCode.listToStr(RecursiveMethods.changeAllFromTo(list9, 1, 2)));
      
      assertEquals("10 15 30 40 50", TestCode.listToStr(RecursiveMethods.changeAllFromTo(list3, 20, 15)));
      assertEquals("10 20 30 30 30", TestCode.listToStr(RecursiveMethods.changeAllFromTo(list4, 50, 30)));
      assertEquals("1 1 30 40 50", TestCode.listToStr(RecursiveMethods.changeAllFromTo(list6, 60, 1)));
      assertEquals("10 15 30 40 50", TestCode.listToStr(RecursiveMethods.changeAllFromTo(list7, 100, 15)));
      assertEquals("10 20 35 35 50", TestCode.listToStr(RecursiveMethods.changeAllFromTo(list8, 100, 35)));
  
   }
   
   // private utility methods ////////////////////////////////////////////

   // character list
   private static List<Character> sampleList1() {
      return TestCode.makeList(new Character[] { 'c', 'h', 'a', 'r' });
   }

   // character list
   private static List<Character> sampleList1Reverse() {
      return TestCode.makeList(new Character[] { 'r', 'a', 'h', 'c' });
   }
   
   // character list
   private static List<Character> sampleListRAH() {
      return TestCode.makeList(new Character[] { 'r', 'a', 'h'});
   }
   
   // character list
   private static List<Character> sampleListAHC() {
      return TestCode.makeList(new Character[] { 'a', 'h', 'c'});
   }
   
   // empty list
   private static List<Character> emptyList() {
      return TestCode.makeList(new Character[] {});
   }
   
   // integer list
   private static List<Integer> sampleList3() {
      return TestCode.makeList(new Integer[] { 10, 20, 30, 40, 50 });
   }
   
   // integer list
   private static List<Integer> sampleList4() {
      return TestCode.makeList(new Integer[] { 10, 20, 30, 50, 50 });
   }
   
   // integer list
   private static List<Integer> sampleList5() {
      return TestCode.makeList(new Integer[] { 60, 20, 30, 40, 50 });
   }
   
   // integer list
   private static List<Integer> sampleList6() {
      return TestCode.makeList(new Integer[] { 60, 60, 30, 40, 50 });
   }
   
   // integer list
   private static List<Integer> sampleList7() {
      return TestCode.makeList(new Integer[] { 10, 100, 30, 40, 50 });
   }
   
   // integer list
   private static List<Integer> sampleList8() {
      return TestCode.makeList(new Integer[] { 10, 20, 100, 100, 50 });
   }
   
   // integer list
   private static List<Integer> sampleList9() {
      return TestCode.makeList(new Integer[] { 10});
   }
}
