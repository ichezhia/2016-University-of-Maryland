package maps;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/* This example illustrates using a more complex type of key for a map- not
 * just a single element, but a list of strings.  In the map, a list of
 * strings (containing a course name and section number) is used a a key,
 * and the associated value is a Department object, containing a
 * department's name and phone number.  In other words, each list of course
 * information maps to the department that that course is taught in.
 */

public class ListsAsKeys {

  public static void main(String[] args) {
    // mapping a list of Strings to a Department object
    HashMap<List<String>, Department> allCourses=
                                      new HashMap<List<String>, Department>();
    ArrayList<String> csCourse;
    ArrayList<String> mathCourse;
    ArrayList<String> target;
    Department csDept= new Department("Computer science", 4500);
    Department mathDept= new Department("Math", 5000);

    csCourse= new ArrayList<String>();
    csCourse.add("CMSC132");
    csCourse.add("0101");
    allCourses.put(csCourse, csDept);

    csCourse= new ArrayList<String>();
    csCourse.add("CMSC132");
    csCourse.add("0201");
    allCourses.put(csCourse, csDept);

    mathCourse= new ArrayList<String>();
    mathCourse.add("MATH100");
    mathCourse.add("0101");
    allCourses.put(mathCourse, mathDept);

    for (List<String> list : allCourses.keySet())
      System.out.println(list + " " + allCourses.get(list));

    // looking for a course
    target= new ArrayList<String>();
    target.add("MATH100");
    target.add("0101");
    if (allCourses.containsKey(target))
      System.out.println("Found target course.");
  }

}
