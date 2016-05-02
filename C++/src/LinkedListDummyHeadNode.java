

/*
 * Uses a dummy head node, rather than head being null, to represent an
 * empty list.  The method nsertBeforeIthPosition() in this list is simpler
 * than the version in the MyLinkedList class from lecture as a result.
 * Exercise- see if any of the other methods in the MyLinkedList class would
 * be simpler if a dummy head node was used (try rewriting some of the
 * methods in that class that have special cases for an empty list in this
 * list class version to see).
 */

public class LinkedListDummyHeadNode {

  private static class Node {

    private Object data;
    private Node next;

    public Node(Object data) {
      this.data= data;
      next= null;
    }

  }

  private final Node head;  // list head reference; is now final

  // the value in the first Node isn't part of a list
  public LinkedListDummyHeadNode() {
    head= new Node(null);  // a constructor can initialize a final field
  }

  // where does this add an element?
  //
  // note this method is now slightly different; all the methods have to
  // take into account that there is a dummy head node, and not treat it as
  // a node with data
  public void add(Object data) {
    Node n= new Node(data);

    n.next= head.next;
    head.next= n;
  }

  // prints all the elements in the current object list, ending with a newline
  public void printList() {
    Node current= head.next;  // need to skip the first node!

    // note this prints an extra blank space after the last element- we
    // don't care about that here, but how could it be avoided if desired
    // (without calling any String class methods)?
    while (current != null) {
      System.out.print(current.data + " ");
      current= current.next;
    }

    System.out.println();
  }

  // returns a reference to the last element in the current object list, or
  // null if there is no such element- compare to the version in the first
  // MyLinkedList linked list example, which uses head being null to
  // represent an empty list rather than a dummy head node
  public Object getLastElement() {
    Node current= head;
    Object result= null;

    while (current.next != null)
      current= current.next;

    return current.data;  // note that the first node's data is null
  }

  // adds the element newElement immediately prior to the element "element"
  // in the current object list- this version is simpler than the one in the
  // first MyLinkedList linked list example, which uses head being null to
  // represent an empty list rather than a dummy head node
  public void insertElementBefore(Object element, Object newElement) {
    Node current= head, n;

    while (current.next != null && !current.next.data.equals(element))
      current= current.next;

    n= new Node(newElement);
    n.next= current.next;
    current.next= n;
  }

  public static void main(String[] args) {
    LinkedListDummyHeadNode list= new LinkedListDummyHeadNode();

    list.add("Richard");
    list.add("Ahmed");
    list.add("Joan");
    list.add("Jyna");
    list.add("Jesse");
    list.add("Manaswi");
    list.add("Steve");

    System.out.println("List originally is:");
    list.printList();

    list.insertElementBefore("Ahmed", "Xavier");
    System.out.println("\nThe list after inserting Xavier prior to Ahmed:");
    list.printList();

    System.out.println("\nThe list's last element is " +
                       list.getLastElement() + ".");
  }

}



/*
 * 
 * 
 * 
 * 
 * 
 * 
 */
/*
//
#include <stdlib.h>
#include <stdio.h>
#include "repository.h"

typedef struct dummy_node {
   int key;
   int data;
   struct dummy_node *next;
} node;

unsigned long int counter;

node empty_head;

void Repository_init();
int Repository_update(int key, int data);
int Repository_delete(int key);
int Repository_get(int key, int *data);
void Repository_print(int print_elements);


void Repository_init() {
   counter = 0;

   empty_head.next = NULL;
}


int Repository_update( int key, int data) {
   node *new;
   node *curr;

   curr = &empty_head;

   while(curr->next != NULL && curr->next->key < key) {
      point curr to next
      curr = curr->next;
      counter++;
   }

   if(curr->next == NULL || curr->next->key > key) {
      getting new node ready
      new = malloc(sizeof(node));
      if (new == NULL) return -1;

      new->key = key;
      new->data = data;

      connect new node before next node
      new->next = curr->next;
      connect new node after previous node
      curr->next = new;
      return 1;
   } else {

      curr->next->data = data;
   
      return 0;
   }  
}

int Repository_delete(int key) {
   
   node *delete;
   node *curr;

   curr = &empty_head;

   while(curr->next != NULL && curr->next->key <= key) {

      if (curr->next->key == key) {
         delete = curr->next;
         curr->next = delete->next;
         free(delete);
         return 1;
      }

      point curr to next
      curr = curr->next;
      counter++;
   }

   return 0;
}

int Repository_get(int key, int *data) {
   node *curr;

   curr = &empty_head;

   while(curr->next != NULL && curr->next->key <= key) {

      if (curr->next->key == key) {

         *data = curr->next->data;
         return 1;
      }

      point curr to next
      curr = curr->next;
      counter++;

   }
   return 0;
}

void Repository_print(int print_elements) {

   int size = 0;

   if (print_elements == 0) {
      print size of list and counter

      node *curr;

      curr = empty_head.next;

      while(curr != NULL) {
         size++;
         curr = curr->next;
      
         printf("Sorted repository has %d elements with %lu steps performed.", size, counter);
      }

   } else {
      print all list

      node *curr;

      curr = empty_head.next;
      
      printf("Elements:\t");
      

      while(curr != NULL) {
         size++;
         printf("{%d, %d}", curr->key, curr->data);

         curr = curr->next;
      }  
      
      printf("Repo has %d elements with %lu steps.\n",size, counter);
      

   }
}
*/