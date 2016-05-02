package iterator;

// (c) Larry Herman, 2016.  You are allowed to use this code yourself, but
// not to provide it to anyone else.

public class Vehicle {

  private String name;
  private int numPassengers;

  public Vehicle(String name, int numPassengers) {
    this.name= name;
    this.numPassengers= numPassengers;
  }

  public String toString() {
    return "Name: " + name + ", numPassengers: " + numPassengers;
  }

  public boolean sameNames(Vehicle otherVehicle) {
    return name.equals(otherVehicle.name);
  }

  public boolean equals(Object obj) {
    Vehicle v;
    boolean result= false;

    if (this == obj)
      result= true;
    else {
      if (obj instanceof Vehicle) {
        v= (Vehicle) obj;
        result= name.equals(v.name) && numPassengers == v.numPassengers;
      }
    }
    return result;
  }

  // if we implement equals() we must implement hashCode() (this will be
  // covered later)
  public int hashCode() {
    return 0;
  }
}