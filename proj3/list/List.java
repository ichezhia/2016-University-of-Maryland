/*
 * Iniyan Chezhian
 * ichezhi1
 * 114167101
 * CMSC 132-0301
 * 
 * I pledge on my honor that I have not given or received any unauthorized
 * assistance on this assignment.
 */

package list;

import java.lang.Iterable;
import java.util.Iterator;
import java.util.Comparator;

import java.lang.IndexOutOfBoundsException;
import java.util.NoSuchElementException;

/*
 * Linked List data structure which stores elements of type T.
 * The elements must be Comparable.
 * The list is also Iterable.
 * Various operations can be performed on this list. Add, remove, get, etc.
 */
public class List<T extends Comparable<T>> implements Iterable<T> {

   // inner node class with data and next Node
   // data must be Comparable
   private static class Node<D extends Comparable<D>> {
      private D data;
      private Node<D> next;

      // constructor for Node with data D
      public Node(D data) {
         this.data = data;
      }
   }

   // head node is final, and data is always null
   // empty dummy head is used for this linked list
   private final Node<T> emptyHead = new Node<T>(null);

   // default constructor
   public List() {

   }

   // copy constructor - deep copy of parameter list
   public List(List<T> otherList) {
      // point current nodes to head for both lists
      Node<T> currentNewList = emptyHead;
      Node<T> currentOtherList = otherList.emptyHead;

      // while parameter list has a next value
      while (currentOtherList.next != null) {
         // make new node with next value of otherList, and add it to new list
         currentNewList.next = new Node<T>(currentOtherList.next.data);

         // point current to next node for both lists
         currentNewList = currentNewList.next;
         currentOtherList = currentOtherList.next;
      }
   }

   // inserts element to end of list
   public void insert(T newElt) {
      // point current to head
      Node<T> current = emptyHead;

      // prepare new node
      Node<T> newNode = new Node<T>(newElt);

      // point current to node before desired insertion
      while (current.next != null) {
         // point current to next node
         current = current.next;
      }

      // connect new node after previous node
      current.next = newNode;
   }

   // inserts element in list
   // increasing order of list elements is maintained
   // duplicates added adjacently
   public void sortedOrderInsert(T newElt) {
      // point current to head
      Node<T> current = emptyHead;

      // prepare new node
      Node<T> newNode = new Node<T>(newElt);

      // point current to node before desired insertion
      // keep moving current until end of list is reached or next data is > new
      // elements are Comparable, so compareTo is used
      while (current.next != null && !(current.next.data.compareTo(newElt) > 0)) {
         // point current to next node
         current = current.next;
      }

      // connect new node before next node
      newNode.next = current.next;
      // connect new node after previous node
      current.next = newNode;
   }

   // returns number of elements stored in list
   // empty list is 0 elements
   // must count each of multiple occurrences of same element
   public int size() {
      // point current to first element, even if its null
      Node<T> current = emptyHead.next;

      // start counter at 0, assuming empty list
      int counter = 0;

      while (current != null) {
         // increment counter, as current node has data
         counter++;

         // point current to next node
         current = current.next;
      }
      return counter;
   }

   // return element at position index
   // first element in list is 0, etc.
   // throw IndexOutOfBoundsException if index is invalid
   public T getElementAtIndex(int index) throws IndexOutOfBoundsException {
      // negative index is invalid
      if (index < 0) {
         throw new IndexOutOfBoundsException();
      }

      // point current to first element, even if its null
      Node<T> current = emptyHead.next;

      for (int i = 0; i < index; i++) {
         if (current.next == null) {
            // requested index is beyond list of elements
            throw new IndexOutOfBoundsException();
         }
         // point current to next node
         current = current.next;
      }
      // current points to node of desired index
      return (T) current.data;
   }

   // return element that is identical to parameter
   // return null if no such element
   public T contains(T element) {
      // point current to head
      Node<T> current = emptyHead;

      while (current.next != null) {
         // elements are Comparable, so compareTo is used
         if (current.next.data.compareTo(element) == 0) {
            // return identical element
            return (T) current.next.data;
         }
         // no match, so point current to next node
         current = current.next;
      }
      // default return
      return null;
   }

   // return string representation of current list
   // should add spaces between elements
   public String toString() {
      // point current to first element in list
      Node<T> current = emptyHead.next;

      // default String
      String returnValue = "";

      if (current == null) {
         // empty list
         return returnValue;
      } else {
         while (current != null) {
            // add data and space after data
            returnValue += current.data + " ";

            // point current to next node
            current = current.next;
         }
      }

      // remove last space after last data
      return returnValue.substring(0, returnValue.length() - 1);
   }

   // return index of first element identical to parameter
   public int indexOf(T element) {
      // point current to head
      Node<T> current = emptyHead;

      // start index at 0
      int index = 0;

      while (current.next != null) {
         // elements are Comparable, so compareTo is used
         if (current.next.data.compareTo(element) == 0) {
            // first identical element, so return current index
            return index;
         }
         // point current to next node
         current = current.next;

         // increment index
         index++;
      }

      // default case if element not found
      return -1;
   }

   // return index of last element identical to parameter
   public int lastIndexOf(T element) {
      // point current to head
      Node<T> current = emptyHead;

      // start index at 0
      int index = 0;

      // initialize to -1 assuming element does not exist
      int returnValue = -1;

      // current.next exists
      while (current.next != null) {
         // elements are Comparable, so compareTo is used
         if (current.next.data.compareTo(element) == 0) {
            // store the index
            returnValue = index;
         }
         // point current to next node
         current = current.next;

         // increment index
         index++;
      }

      // return last index match
      return returnValue;
   }

   // remove first element that is identical to element parameter
   // only remove one if there are multiple occurrences
   // return true if an element was removed
   // return false if an element was not removed
   public boolean removeElt(T element) {
      // point current to head
      Node<T> current = emptyHead;

      // if next node exists
      while (current.next != null) {
         // elements are Comparable, so compareTo is used
         if (current.next.data.compareTo(element) == 0) {
            // remove element from list by skipping over
            current.next = current.next.next;
            // element was removed
            return true;
         }
         // point current to next node
         current = current.next;
      }
      // element not found
      return false;
   }

   // remove the element from list with index parameter
   // throw IndexOutOfBoundsException if index parameter is invalid
   public void removeElementWithIndex(int index) throws IndexOutOfBoundsException {
      // negative index is invalid
      if (index < 0) {
         throw new IndexOutOfBoundsException();
      }

      // point current to head
      Node<T> current = emptyHead;

      // iterate through list until index is reached
      for (int i = 0; i < index; i++) {
         // point current to next node
         current = current.next;

         if (current.next == null) {
            // invalid index, no more elements
            throw new IndexOutOfBoundsException();
         }
      }
      // remove element from list
      current.next = current.next.next;
   }

   // reset list to contain no elements
   public void clear() {
      // first element is null, list is reset
      emptyHead.next = null;
   }

   // return new list, independent of current list consisting of elements from
   // fromIndex to toIndex parameters
   // throw IndexOutOfBoundsException if either index is invalid
   public List<T> subList(int fromIndex, int toIndex) throws IndexOutOfBoundsException {

      // validate index parameters
      // from must be <= to and indexes cannot be negative
      if (fromIndex > toIndex || fromIndex < 0 || toIndex < 0) {
         throw new IndexOutOfBoundsException();
      }

      // new list object
      List<T> subList = new List<T>();

      // point current to heads of both lists
      Node<T> currentSubList = subList.emptyHead;
      Node<T> currentList = emptyHead;

      // move current list pointer to node of fromIndex
      for (int i = 0; i < fromIndex; i++) {
         if (currentList.next == null) {
            // original list does not have enough elements to reach fromIndex
            throw new IndexOutOfBoundsException();
         }
         // point current to next node
         currentList = currentList.next;
      }

      // move current along fromIndex to toIndex and make new sub list
      for (int i = 0; i < toIndex - fromIndex + 1; i++) {
         if (currentList.next == null) {
            // not enough elements to reach toIndex
            throw new IndexOutOfBoundsException();
         }

         // make new sublist node with same data as original list
         currentSubList.next = new Node<T>(currentList.next.data);

         // point current to next node
         currentSubList = currentSubList.next;
         currentList = currentList.next;
      }

      // return new sub list
      return subList;
   }

   // return an iterator
   public Iterator<T> iterator() {
      return new ListIterator<T>();
   }

   // generic Iterator inner class
   public class ListIterator<T> implements Iterator<T> {
      // point current to head
      Node current = emptyHead;

      // previous is null for now since we start at head
      Node previous = null;

      // default is false - cannot remove until next is called
      boolean isRemoveable = false;

      // return true if list has more elements that can be returned by next()
      // otherwise return false
      @Override
      public boolean hasNext() {
         // return true if next is not null, and false if next is null
         return current.next != null;
      }

      // return next data element in list
      // start at first element in list
      // throw NoSuchElementException if there are no more elements in list
      @Override
      public T next() throws NoSuchElementException {
         // move pointers to next node
         previous = current;
         current = current.next;

         if (current != null) {
            // next can be called
            // so can remove()
            isRemoveable = true;

            // return data
            return (T) current.data;
         } else {
            // current node is null
            throw new NoSuchElementException();
         }
      }

      // remove element that was just returned by next()
      // throw IllegalStateException if next has never been called
      // also throw if element was already removed once after next()
      @Override
      public void remove() throws IllegalStateException {
         if (isRemoveable) {
            // remove node by skipping over this node in list
            previous.next = current.next;
            current = previous;

            // reset
            isRemoveable = false;
         } else {
            // not removable
            throw new IllegalStateException();
         }
      }
   }

   // return Comparator that compares two lists using compare()
   // compares length of two lists
   public Comparator<List<T>> lengthComparator() {
      return new lengthComparator();
   }

   // inner class Comparator
   // compares length of two lists
   public class lengthComparator implements Comparator<List<T>> {
      @Override
      public int compare(List<T> list1, List<T> list2) {
         // return difference in size of two list
         return list1.size() - list2.size();
      }
   }

   // return Comparator that compares two lists using compare()
   // compares lists based on contents
   public Comparator<List<T>> orderComparator() {
      return new orderComparator();
   }

   // inner class Comparator
   public class orderComparator implements Comparator<List<T>> {
      // How to Order Compare:
      // 1.if lists contain same elements in same order, then return 0
      // 2.if are not identical, and first nonmatch of first is less than second
      // return negative
      // 3.if are not identical, and first nonmatch of first is greater than
      // second return negative
      // 4.if lists have identical elements, return negative if first is shorter
      // and return positive if first list is longer
      @Override
      public int compare(List<T> list1, List<T> list2) {
         //assume lists are equal
         int returnValue = 0;

         //point current to head nodes
         Node<T> current1 = list1.emptyHead;
         Node<T> current2 = list2.emptyHead;

         //while both lists have next elements and returnValue is still 0
         while (current1.next != null && current2.next != null && returnValue == 0) {

            // elements are Comparable, so compareTo is used
            //store comparison as returnValue
            //if equal, returnValue is 0, otherwise exit while loop
            returnValue = current1.next.data.compareTo(current2.next.data);

            //point current to next nodes
            current1 = current1.next;
            current2 = current2.next;
         }

         if (returnValue != 0) {
            // return element comparison
            return returnValue;
         } else {
            // equal for number of elements in both
            // return size comparison
            return list1.size() - list2.size();
         }
      }
   }
}