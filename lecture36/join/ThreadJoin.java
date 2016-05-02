package join;

// join() forces the calling thread to wait for another thread to stop
// running before proceeding

public class ThreadJoin implements Runnable {

   int myID, i;
   
   
   
  public ThreadJoin(int j) {
       myID = j;
}

public void run() {
   ThreadJoin t2= new ThreadJoin(2);

     Thread thread2= new Thread(t2);

     if(myID == 1) {
        thread2.start();
        try {
           thread2.join();
        } catch (InterruptedException e) {
           e.printStackTrace();
        }
     }
     
     
    for (i= 0; i < 2; i++) {
       try {
         Thread.sleep((long)(1000));
      } catch (InterruptedException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      System.out.println(myID + " " + i);
    }
  }

  public static void main(String[] args) {
    ThreadJoin t1= new ThreadJoin(1);

    Thread thread1= new Thread(t1);

    thread1.start();

    try {
      thread1.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println("Done!");
  }

}
