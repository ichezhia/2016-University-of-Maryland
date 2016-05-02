package iterator;

// (c) Larry Herman, 2016.  You are allowed to use this code yourself, but
// not to provide it to anyone else.

import java.util.Iterator;

public class Driver {

  public static void main(String args[]) {
    ParkingLot lot= new ParkingLot(5);
    Iterator<Vehicle> iter;

    lot.addVehicle(new Vehicle("Dodge", 4));
    lot.addVehicle(new Vehicle("Tesla", 2));
    lot.addVehicle(new Vehicle("Ford", 6));
    lot.addVehicle(new Vehicle("Volkswagen", 5));
    lot.addVehicle(new Vehicle("Harley Davidson", 1));

    iter= lot.iterator();

    while (iter.hasNext())
      System.out.println(iter.next());
  }

}
