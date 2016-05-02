package fibonacci;

/* This program prints the first 35 Fibonacci numbers, and also how many
 * recursive calls are required to calculate each of them, given the
 * straightforward, but inefficient, recursive function for computing
 * Fibonacci numbers used here.  Note this does not require using a
 * recursive helper method.
 */

public class Fibonacci {

  public static int callCounter= 0;

  public static int fib(int n) {
    callCounter++;
    if (n == 0 || n == 1)
      return 1;
    else return fib(n - 1) + fib(n - 2);
  }

  public static void main(String[] args) {
    int i, limit= 35;

    for (i= 0; i <= limit; i++) {
      System.out.print("fib(" + i +"): " + fib(i));
      System.out.println(", calls to compute: " + callCounter);
      callCounter= 0;
    }
  }

}
