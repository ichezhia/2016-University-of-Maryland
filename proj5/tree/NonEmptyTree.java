/*
 * Iniyan Chezhian
 * ichezhi1
 * 114167101
 * CMSC 132-0301
 * 
 * I pledge on my honor that I have not given or received any unauthorized
 * assistance on this assignment.
 */

package tree;

import java.util.List;

/*
 * Interface for polymorphic tree.
 * Trees consist of EmptyTree and NonEmptyTree, both implement Tree interface.
 * Generic class with two type parameters: K for Comparable keys, V for value.
 * NonEmptyTree is a tree which has key and values on this. It also has Tree
 * references of left and right children. K is comparable, because it is a
 * binary tree.
 */
@SuppressWarnings("unchecked")
public class NonEmptyTree<K extends Comparable<K>, V> implements Tree<K, V> {

   // key, value data and children
   private K key;
   private V value;
   private Tree<K, V> left;
   private Tree<K, V> right;

   // constructor for tree with key, value, and children
   public NonEmptyTree(K key, V value, Tree<K, V> left, Tree<K, V> right) {
      this.key = key;
      this.value = value;
      this.left = left;
      this.right = right;
   }

   //add keyToAdd with valueToAdd to current object tree. Key, value parameters.
   //if key already exists, replace value with valueToAdd (no duplicate keys)
   //throw NullPointerException if keyToAdd or valueToAdd is null
   public NonEmptyTree<K, V> addKeyWithValue(K keyToAdd, V valueToAdd) {
      int compareVal = keyToAdd.compareTo(key);

      if (compareVal == 0) {
         value = valueToAdd;
      } else if (compareVal < 0) {
         left = left.addKeyWithValue(keyToAdd, valueToAdd);
      } else if (compareVal > 0) {
         right = right.addKeyWithValue(keyToAdd, valueToAdd);
      }

      return this;

   }

   //return number of key value pairs in current object tree
   public int size() {
      // this current object parent is 1, then add the children
      return 1 + left.size() + right.size();
   }

   //find the value lookUpKey is associated with in current object tree and
   //return a reference to it
   //return null if no such key in tree
   public V lookup(K lookUpKey) {
      //compare lookUpKey to current object key
      int compareVal = lookUpKey.compareTo(key);

      if (compareVal < 0) {
         //want lesser key, so look left
         return left.lookup(lookUpKey);
      } else if (compareVal > 0) {
         //want greater key, so look right
         return right.lookup(lookUpKey);
      } else {
         // compareVal==0, key match, so return current object value
         return value;
      }
   }

   //if key is in tree, add keys in list from key to root order
   //do not modify list if key is not in tree
   public void pathFromRoot(K key, List<K> list) {
      if(lookup(key) == null) {
         //key does not exist in tree, so return here
         return;
      }
      
      //compare key with current object key
      int compareVal = key.compareTo(this.key);
     
      //add the current object key to list
      list.add(this.key);

      if (compareVal < 0) {
         //want lesser key, so look left
         left.pathFromRoot(key, list);
      } else if (compareVal > 0) {
         //want greater key, so look right
         right.pathFromRoot(key, list);
      }
   }

   //return true if current object tree have same keys
   //done by comparing in order toString comparison
   public boolean haveSameKeys(Tree<K, V> otherTree) {

      //convert both trees to strings
      String tree1 = this.toString();
      String tree2 = otherTree.toString();

      //compare the two strings which are in order
      return tree1.equals(tree2);
   }

   //return number of key/value pairs at level parameter
   public int numElementsAtLevel(int level) {
      if(level == 1) {
         //base case, at "root" node for current object
         return 1;
      } else {
         //recurse until level is 1
         return left.numElementsAtLevel(level-1) + right.numElementsAtLevel(level-1);
      }
   }

   //return max key in tree
   public K max() throws EmptyTreeException {
      try {
         //keep going right
         K max = right.max();
         return max;
      } catch (EmptyTreeException e) {
         //go right until reach empty tree, so return last key
         return key;
      }
   }

   //return min key in tree
   public K min() throws EmptyTreeException {
      try {
         //keep going left
         K min = left.min();
         return min;
      } catch (EmptyTreeException e) {
         //go left until reach empty tree, so return last key
         return key;
      }
   }

   //delete key and value for key parameter
   public Tree<K, V> deleteKeyAndValue(K keyToDelete) {
      //compare keyToDelete with current object key
      int compareVal = keyToDelete.compareTo(this.key);
      
      if (compareVal < 0) {
         //want lesser key, so look left
         left = left.deleteKeyAndValue(keyToDelete);
      } else if (compareVal > 0) {
         //want greater key, so look right
         right = right.deleteKeyAndValue(keyToDelete);
      } else if (compareVal == 0) {
         //found match
         try {
            //set value before key to avoid confusion
            value = lookup(right.min());

            //set new key
            key = right.min();

            //delete the old key
            right = right.deleteKeyAndValue(right.min());
         } catch (EmptyTreeException e) {
            //no more elements in tree left, so return an empty tree
            return left;
         }
      }

      //key not found, return current object tree
      return this;
   }
   
   //helper method for toString, make a string with in "key/value" format
   public String toStringKeyValue() {
      String retVal = "";
      retVal += key;
      retVal += "/";
      retVal += value;
      return retVal;
   }
   
   //overrides toString() Object class
   public String toString() {
      String retVal = toStringKeyValue();
      
      //check if left is non empty and add if nonempty tree
      if (!left.toStringKeyValue().equals("")) {
         retVal = left.toString() + " " + retVal;
      }

      //check if right is non empty tree and add if nonempty tree
      if (!right.toStringKeyValue().equals("")) {
         retVal = retVal + " " + right.toString();
      }
         
      return retVal;
   }
}