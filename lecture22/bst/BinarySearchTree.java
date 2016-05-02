package bst;

/* Example illustrating a few methods for a (standard, nonpolymorphic)
 * binary search tree.  First figure out the shape of the the tree that's
 * created by createExampleTree().
 */

public class BinarySearchTree {

  private static class Node {

    private int data;
    private Node left, right;

    public Node(int data) {
      this.data= data;
      this.left= this.right= null;
    }

  }

  // reference fields are initialized to null if not initialized otherwise,
  // so the initialization is just for clarity, to show explicitly that an
  // empty tree is represented by null
  private Node root= null;

  // insert() returns false if inserting its parameter value is not possible
  // because it's already present in the tree, and true otherwise
  public boolean insert(int value) {
    Node travel= root, prev= null, newNode;
    boolean result= true;

    while (travel != null && travel.data != value) {
      prev= travel;
      if (value < travel.data)
        travel= travel.left;
      else travel= travel.right;
    }

    if (travel != null)
      result= false;
    else
      if (root == null)  // tree is empty
        root= new Node(value);
      else
        if (value < prev.data)
          prev.left= new Node(value);
        else prev.right= new Node(value);

    return result;
  }

  // note that this toString() recursive helper method is performing a
  // traversal (what kind of traversal is it?)
  private String toString(Node travel) {
    String result= "", leftStr, rightStr;

    if (travel != null) {
      leftStr= toString(travel.left);
      rightStr= toString(travel.right);
      result= leftStr + (leftStr.equals("") ? "" : " ") + travel.data +
                        (rightStr.equals("") ? "" : " ") + rightStr;
    }

    return result;
  }

  // why in this case do we want to have a recursive helper method, with a
  // different parameter list, which is called by toString() below?  (In
  // other words, why not just make this toString() method be recursive, and
  // not have a helper method at all?)
  public String toString() {
    return toString(root);
  }

  public static BinarySearchTree createExampleTree() {
    BinarySearchTree tree= new BinarySearchTree();

    // not printing or using the return values of the insert() calls here...
    tree.insert(40);
    tree.insert(20);
    tree.insert(60);
    tree.insert(10);
    tree.insert(30);
    tree.insert(50);
    tree.insert(50);  // note duplicate insertion attempt
    tree.insert(70);

    return tree;
  }

  public static void main(String[] args) {
    BinarySearchTree bst= createExampleTree();

    System.out.println(bst);  // calls toString()
  }

}
