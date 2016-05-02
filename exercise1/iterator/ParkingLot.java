package iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ParkingLot {

   Vehicle vehicles[];
   int numVehicles;

   public ParkingLot(int size) {
      vehicles = new Vehicle[size];
      numVehicles = 0;
   }

   // just do nothing if the array is full
   public void addVehicle(Vehicle newVehicle) {
      if (numVehicles < vehicles.length)
         vehicles[numVehicles++] = newVehicle;
   }

   public Iterator<Vehicle> iterator() {
      try {
         return new MyIterator<Vehicle>(vehicles);
      } catch (NoSuchElementException e) {
         return null;
      }
   }
}