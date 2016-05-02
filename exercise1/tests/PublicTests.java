package tests;

// (c) Larry Herman, 2016.  You are allowed to use this code yourself, but
// not to provide it to anyone else.

import iterator.Vehicle;
import iterator.ParkingLot;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.*;
import static org.junit.Assert.*;

public class PublicTests {

  // Checks the value of calling hasNext() on a new iterator over a nonempty
  // ParkingLot, which should be true.
  @Test public void testPublic1() {
    ParkingLot lot11= sampleParkingLot1();
    Iterator<Vehicle> iter= lot11.iterator();

    assertTrue(iter.hasNext());
  }

  // Checks the value returned by the first call to next() on an iterator.
  @Test public void testPublic2() {
    ParkingLot lot11= sampleParkingLot1();
    Iterator<Vehicle> iter= lot11.iterator();

    assertEquals("Name: Dodge, numPassengers: 4", iter.next().toString());
  }

  // Checks the values returned by the first two calls to next() on an
  // iterator.
  @Test public void testPublic3() {
    ParkingLot lot11= sampleParkingLot1();
    Iterator<Vehicle> iter= lot11.iterator();

    assertEquals("Name: Dodge, numPassengers: 4" +
                 "Name: Tesla, numPassengers: 2",
                 iter.next().toString() + iter.next());
  }

  // Checks the values returned by multiple iterations on an iterator.
  @Test public void testPublic4() {
    ParkingLot lot11= sampleParkingLot1();
    Iterator<Vehicle> iter= lot11.iterator();

    assertEquals("Name: Dodge, numPassengers: 4" +
                 "Name: Tesla, numPassengers: 2" +
                 "Name: Ford, numPassengers: 6" +
                 "Name: Volkswagen, numPassengers: 5" +
                 "Name: Harley Davidson, numPassengers: 1",
                 iter.next().toString() + iter.next() + iter.next() +
                 iter.next() + iter.next());
  }

  // Checks the value of hasNext() on a new iterator over an empty
  // ParkingLot, which should be false.
  @Test public void testPublic5() {
    ParkingLot lot11= new ParkingLot(10);
    Iterator<Vehicle> iter= lot11.iterator();

    assertFalse(iter.hasNext());
  }

  // Checks the values returned by multiple iterations on an iterator, until
  // hasNext() returns false, which is also testing that hasNext() properly
  // returns false when it should, and the iterator doesn't have any errors
  // in this case.
  @Test public void testPublic6() {
    ParkingLot lot11= sampleParkingLot1();
    Iterator<Vehicle> iter= lot11.iterator();
    String str= "";

    while (iter.hasNext())
      str += iter.next();

    assertEquals("Name: Dodge, numPassengers: 4" +
                 "Name: Tesla, numPassengers: 2" +
                 "Name: Ford, numPassengers: 6" +
                 "Name: Volkswagen, numPassengers: 5" +
                 "Name: Harley Davidson, numPassengers: 1", str);
  }

  // The same as the previous test, but in this case the number of Vehicles
  // being stored in the ParkingLot is different from the ParkingLot's
  // capacity (array size), so this is checking that hasNext() is
  // implemented correctly and testing the right condition.
  @Test public void testPublic7() {
    ParkingLot lot11= sampleParkingLot2();
    Iterator<Vehicle> iter= lot11.iterator();
    String str= "";

    while (iter.hasNext())
      str += iter.next();

    assertEquals("Name: Dodge, numPassengers: 4" +
                 "Name: Tesla, numPassengers: 2" +
                 "Name: Ford, numPassengers: 6" +
                 "Name: Volkswagen, numPassengers: 5" +
                 "Name: Harley Davidson, numPassengers: 1" + 
                 "Name: Ford, numPassengers: 8", str);
  }

  // Calls the iterator's next() method extra times, so that only
  // alternating vehicles from the ParkingLot should be returned.
  @Test public void testPublic8() {
    ParkingLot lot11= sampleParkingLot1();
    Iterator<Vehicle> iter= lot11.iterator();
    String str= "";

    while (iter.hasNext()) {
      str += iter.next();
      if (iter.hasNext())
        iter.next();  // calling next(), but ignoring its return value
    }

    assertEquals("Name: Dodge, numPassengers: 4" +
                 "Name: Ford, numPassengers: 6" +
                 "Name: Harley Davidson, numPassengers: 1", str);
  }

  // Tests two iterators iterating over the same ParkingLot at the same time
  // (they check whether there are two cars with the same brand in the same
  // ParkingLot).
  @Test public void testPublic9() {
    ParkingLot lot11;
    Iterator<Vehicle> iter1;
    Iterator<Vehicle> iter2;
    Vehicle vehicle1, vehicle2;
    boolean same;

    // first check for a ParkingLot without two vehicles with the same brand
    iter1= sampleParkingLot1().iterator();
    same= false;
    while (iter1.hasNext() && !same) {
      vehicle1= iter1.next();

      iter2= sampleParkingLot1().iterator();
      while (iter2.hasNext() && !same) {
        vehicle2= iter2.next();
        if (!vehicle1.equals(vehicle2) && vehicle1.sameNames(vehicle2))
          same= true;
      }
    }

    assertFalse(same);

    // Now check for a ParkingLot that does have two vehicles with the same
    // brand
    iter1= sampleParkingLot2().iterator();
    same= false;
    while (iter1.hasNext() && !same) {
      vehicle1= iter1.next();

      iter2= sampleParkingLot2().iterator();
      while (iter2.hasNext() && !same) {
        vehicle2= iter2.next();
        if (!vehicle1.equals(vehicle2) && vehicle1.sameNames(vehicle2))
          same= true;
      }
    }

    assertTrue(same);
  }

  // Verifies that an iterator properly throws a NoSuchElementException when
  // next() is called more times than the number of Vehicles in a
  // ParkingLot.
  @Test(expected= NoSuchElementException.class) public void testPublic10() {
    ParkingLot lot11= sampleParkingLot1();
    Iterator<Vehicle> iter= lot11.iterator();

    while (true)
      iter.next();
  }

  // private utility methods ////////////////////////////////////////////

  private ParkingLot sampleParkingLot1() {
    ParkingLot lot= new ParkingLot(5);

    lot.addVehicle(new Vehicle("Dodge", 4));
    lot.addVehicle(new Vehicle("Tesla", 2));
    lot.addVehicle(new Vehicle("Ford", 6));
    lot.addVehicle(new Vehicle("Volkswagen", 5));
    lot.addVehicle(new Vehicle("Harley Davidson", 1));

    return lot;
  }

  private ParkingLot sampleParkingLot2() {
    ParkingLot lot= new ParkingLot(10);

    lot.addVehicle(new Vehicle("Dodge", 4));
    lot.addVehicle(new Vehicle("Tesla", 2));
    lot.addVehicle(new Vehicle("Ford", 6));
    lot.addVehicle(new Vehicle("Volkswagen", 5));
    lot.addVehicle(new Vehicle("Harley Davidson", 1));
    lot.addVehicle(new Vehicle("Ford", 8));

    return lot;
  }

}
