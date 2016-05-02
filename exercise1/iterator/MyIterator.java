package iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyIterator<T> implements Iterator<Vehicle> {

   int index;
   Vehicle[] vehicleArray;

   public MyIterator(Vehicle[] vehicles) {
      index = 0;
      vehicleArray = vehicles;
   }

   @Override
   public boolean hasNext() {
      if (index >= vehicleArray.length) {
         return false;
      } else if (vehicleArray[index] == null) {
         return false;
      } else {
         return true;
      }
   }

   @Override
   public Vehicle next() {
      if (hasNext() == false) {
         NoSuchElementException e = new NoSuchElementException();
         throw e;
      } else {
         return vehicleArray[index++];
      }
   }
}