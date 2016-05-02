package maps;

// used by the ClassesImpMaps class as well as the ListsAsKeys class

public class Department {

  private String departmentName;
  private int phoneExtension;

  public Department(String department, int phoneExtension) {
    this.departmentName= department;
    this.phoneExtension= phoneExtension;
  }

  public String getDepartmentName() {
    return departmentName;
  }

  public void setDepartmentName(String department) {
    this.departmentName= department;
  }

  public int getPhoneExtension() {
    return phoneExtension;
  }

  public void setPhoneExtension(int phoneExtension) {
    this.phoneExtension= phoneExtension;
  }

  public String toString() {
    return "Department: " + departmentName + " phone extension: " +
           phoneExtension;
  }

}
