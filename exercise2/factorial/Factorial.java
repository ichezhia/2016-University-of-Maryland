package factorial;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Factorial implements Iterable {

   private int lowerLimit, upperLimit, currentInt, currentFactorial;

   public Factorial(int lowerLimit, int upperLimit) {
      this.lowerLimit = lowerLimit;
      lowerLimit = currentInt;
      this.upperLimit = upperLimit;
      currentFactorial = 1;
   }

   public String toString() {
      String output = "";

      currentInt = 1;
      currentFactorial = 1;

      if (lowerLimit == 0 && upperLimit == 0) {
         // iterator only works with numbers >1
         // special case for 0 factorial
         return "1";
      } else if (lowerLimit <= upperLimit) {
         Iterator iter = iterator();

         while (iter.hasNext()) {
            output += iter.next() + " ";
         }

         return output.substring(0, output.length() - 1);
      } else {
         return "";
      }

   }

   public static void main(String args[]) {
      Factorial f = new Factorial(0, 0);

      System.out.println(f); // calls toString()
   }

   private class Iterate implements Iterator {

      public boolean hasNext() {
         if (currentInt > upperLimit) {
            return false;
         } else {
            return true;
         }
      }

      public Object next() {
         if (hasNext() == false) {
            NoSuchElementException e = new NoSuchElementException();
            throw e;
         } else {

            if (currentInt < lowerLimit) {
               for (int i = 1; i <= lowerLimit; i++) {
                  currentFactorial *= i;
               }
               currentInt = lowerLimit + 1;
               return currentFactorial;
            } else if (currentInt >= lowerLimit && currentInt <= upperLimit) {

               currentFactorial *= currentInt;
               currentInt++;
            }

         }

         return currentFactorial;
      }
   }

   public Iterator iterator() {
      try {
         return new Iterate();
      } catch (NoSuchElementException e) {
         return null;
      }
   }

}
