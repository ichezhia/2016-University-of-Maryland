/*
 * Iniyan Chezhian
 * ichezhi1
 * 114167101
 * CMSC 132-0301
 * 
 * I pledge on my honor that I have not given or received any unauthorized
 * assistance on this assignment.
 */

package recursiveMethods;

import java.util.ArrayList;
import java.util.List;

//methods that can be called on List objects
public class RecursiveMethods {

   public static <T> void replaceRange(List<T> list, int from, int to, T elt) {
      // validate index
      // no negative indexes
      if (from >= 0 && to >= 0) {
         replaceRange(list, from, to, elt, from);
      }
   }

   private static <T> void replaceRange(List<T> list, int from, int to, T elt, int current) {

      if (current > to || current >= list.size()) {
         return;
      }

      list.set(current, elt);

      replaceRange(list, from, to, elt, current + 1);
   }

   public static <T> boolean areReversed(List<T> list1, List<T> list2) {
      // check to see if same length, false if not
      if (list1.size() != list2.size()) {
         return false;
      }

      return areReversed(list1, list2, 0);
   }

   private static <T> boolean areReversed(List<T> list1, List<T> list2, int i) {
      if (i == list1.size()) {
         return true;
      }

      if (list1.get(i).equals(list2.get(list2.size() - 1 - i))) {
         return areReversed(list1, list2, ++i);
      }

      return false;
   }

   public static <T extends Comparable<T>> int posOfMaxElt(List<T> list) {
      if (list.size() == 0) {
         return -1;
      } else {
         return posOfMaxElt(list, -1, list.get(0), 0);
      }
   }

   private static <T extends Comparable<T>> int posOfMaxElt(List<T> list, int maxI, T maxElt, int i) {

      if (i == list.size()) {
         return maxI;
      }

      if (list.get(i).compareTo(maxElt) >= 0) {
         return posOfMaxElt(list, i, list.get(i), ++i);
      } else {
         return posOfMaxElt(list, maxI, maxElt, ++i);
      }
   }

   public static <T> List<T> changeAllFromTo(List<T> list, T oldElt, T newElt) {

      List<T> returnList = new ArrayList<T>();
      return changeAllFromTo(list, returnList, oldElt, newElt, 0);
   }

   private static <T> List<T> changeAllFromTo(List<T> list, List<T> returnList, T oldElt, T newElt, int i) {

      if (i == list.size()) {
         return returnList;
      }

      if (list.get(i).equals(oldElt)) {
         returnList.add(newElt);

      } else {
         returnList.add(list.get(i));
      }

      return changeAllFromTo(list, returnList, oldElt, newElt, ++i);
   }
}
