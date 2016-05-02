package maps;

import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TreeMap;

/* This example shows some map versions and operations.  The same operations
 * in the test() method are all done three times, each with a map of the
 * three different types.  Notice the order that elements are iterated over
 * in each map type (no particular order for a HashMap, in order of
 * insertion for a LinkedHashMap, and in sorted order for a TreeMap).  The
 * maps all have keys (strings representing employee names) that map to the
 * department (a Department object) that that employee works in, perhaps in
 * a trendy new boutique.  A Department contains a name and a phone number.
 * In other words, each employee name maps to the department that that
 * employee works in.
 */

public class ClassesImpMaps {

  public static void main(String[] args) {
    System.out.println("** HashMap test **");
    test(new HashMap<String, Department>());

    System.out.println("** LinkedHashMap test **");
    test(new LinkedHashMap<String, Department>());

    System.out.println("** TreeMap test **");
    test(new TreeMap<String, Department>());
  }

  // notice the parameter is a Map, which is an interface
  public static void test(Map<String, Department> map) {
    Department shoeDepartment;

    // adding several (key, value) pairs to the map
    map.put("Mary", new Department("Electronics", 5000));
    map.put("Peter", new Department("Music", 4500));
    shoeDepartment= new Department("Shoes", 6000);
    map.put("Zoe", shoeDepartment);
    map.put("Bob", shoeDepartment);

    // printing the contents
    for (String name : map.keySet())
      // finding the dept that maps to the name
      System.out.println(name + " " + map.get(name));

    // membership test
    if (map.containsKey("Mary"))
      System.out.println("Mary found");

    if (!map.containsKey("Bob"))
      System.out.println("Bob not found");

    // getting all the values
    System.out.println("All departments");
    for (Department dept : map.values())
      System.out.println(dept);
    System.out.println("\n");
  }

}
