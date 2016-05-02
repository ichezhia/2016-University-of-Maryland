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
 * EmptyTree is a Tree with no key or value. It is an empty tree. It can also
 * be the null right or left children for a NonEmptyTree.
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class EmptyTree<K extends Comparable<K>, V> implements Tree<K, V> {
   //singleton
   private static EmptyTree emptyTree = new EmptyTree();

   //private constructor
   private EmptyTree() {
   }

   public static EmptyTree getInstance() {
      return emptyTree;
   }

   public NonEmptyTree<K, V> addKeyWithValue(K keyToAdd, V valueToAdd) {
      // check if null parameters
      if (keyToAdd == null || valueToAdd == null) {
         throw new NullPointerException();
      }

      return new NonEmptyTree<K, V>(keyToAdd, valueToAdd, this, this);
   }

   //empty tree have a size of 0
   public int size() {
      return 0;
   }

   //empty tree does not have any key
   //return null value
   public V lookup(K lookUpKey) {
      return null;
   }

   public void pathFromRoot(K key, List<K> list) {
      //do nothing since empty tree has no elements
   }

   //compare this empty tree to another tree
   public boolean haveSameKeys(Tree<K, V> otherTree) {
      String tree1 = this.toString();
      String tree2 = otherTree.toString();

      return tree1.equals(tree2);
   }

   //empty tree has no elements and no more levels (no more children)
   public int numElementsAtLevel(int level) {
      return 0;
   }

   //no max in empty tree
   public K max() throws EmptyTreeException {
      throw new EmptyTreeException();
   }

   //no min in empty tree
   public K min() throws EmptyTreeException {
      throw new EmptyTreeException();
   }

   //key was not in parent tree
   //do nothing and return this, key was not found
   public Tree<K, V> deleteKeyAndValue(K keyToDelete) {
      return this;
   }

   //empty tree is empty String
   public String toString() {
      return "";
   }

   //helper method for toString has empty String for empty tree
   @Override
   public String toStringKeyValue() {
      return "";
   }
}