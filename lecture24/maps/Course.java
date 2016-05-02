package maps;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

/* This example illustrates using a more complex type of value for a map-
 * not just a single element, but a set of strings.  In the map, an Integer
 * (representing a course number) is used as a key to a set of strings that
 * stores the names of the students who are enrolled in that course.  In
 * other words, each course number maps to the students who are enrolled.
 */

public class Course {

  private Map<Integer, Set<String>> allSections=
                                    new HashMap<Integer, Set<String>>();

  public void addStudent(Integer sectionNumber, String name) {
    Set<String> section= allSections.get(sectionNumber);

    if (section == null) {
      section= new TreeSet<String>();
      allSections.put(sectionNumber, section);
    }
    section.add(name);
  }

  public boolean removeStudent(String name) {
    Set<String> section;

    for (Integer sectionNum : allSections.keySet()) {
      section= allSections.get(sectionNum);
      if (section.contains(name)) {
        section.remove(name);
        if (section.isEmpty())
          allSections.remove(sectionNum);
        return true;
      }
    }

    return false;
  }

  public void printAllStudents() {
    Set<String> section;

    for (Integer sectionNum : allSections.keySet()) {
      section= allSections.get(sectionNum);
      for (String name : section)
        System.out.println(name);
    }
  }

  public static void main(String[] args) {
    Course course= new Course();

    course.addStudent(201, "Jose");
    course.addStudent(101, "Mary");
    course.addStudent(101, "Kelly");
    course.printAllStudents();
  }

}
