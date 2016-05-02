package setExample;

import java.util.Set;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Iterator;

/* This example shows some set versions and operations.  The same operations
 * in the test() method are all done three times, each with a set of the
 * three different types.  Notice the order that elements are iterated over
 * in each set type (no particular order for a HashSet, in order of
 * insertion for a LinkedHashSet, and in sorted order for a TreeSet).  The
 * sets all just have strings as elements (just a few names).
 */

public class SetExample {

  public static void main(String[] args) {
    System.out.println("** HashSet test **");
    test(new HashSet<String>());

    System.out.println("** LinkedHashSet test **");
    test(new LinkedHashSet<String>());

    System.out.println("** TreeSet test **");
    test(new TreeSet<String>());
  }

  // notice the parameter is a Set, which is an interface
  public static void test(Set<String> set) {
    ArrayList<String> elements;
    Iterator<String> iterator;
    String answer;

    // a simple set with three elements
    set.add("Tom");
    set.add("Frank");
    set.add("Beth");

    // we could have used a foreach loop
    System.out.println("Set contents are:");
    iterator= set.iterator();
    while (iterator.hasNext())
      System.out.println(iterator.next());

    // membership test
    if (set.contains("Frank"))
      System.out.println("Frank found");

    if (set.contains("Laura"))
      System.out.println("Laura found");

    // containsAll() test
    elements= new ArrayList<String>();
    elements.add("Frank");
    elements.add("Kathy");
    answer= "All";
    if (!set.containsAll(elements))
      answer= "Not all";
    System.out.println(answer + " elements in the ArrayList are in the set.");

    // adding all the elements in the parameter set to the current object set
    set.addAll(elements);
    System.out.println("After addAll(), the set contents are: " + set);
    System.out.println("\n");
  }

}
