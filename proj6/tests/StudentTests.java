package tests;

import org.junit.*;

import submitserver.SubmitServer;

import static org.junit.Assert.*;

public class StudentTests {

   // test constructor for negative and zero parameter
   @Test
   public void test1() {
      SubmitServer server1 = new SubmitServer(-1);
      SubmitServer server2 = new SubmitServer(0);

      assertEquals(0, server1.numSubmissions());
      assertEquals(0, server2.numSubmissions());
   }

   // test addSubmission and numSubmissions(String, int)
   @Test
   public void test2() {
      SubmitServer server = new SubmitServer(2);

      assertEquals(0, server.numSubmissions("Student 1", 1));

      server.addSubmission("Student 1", 1, 80);
      server.addSubmission("Student 1", 1, 90);
      server.addSubmission("Student 1", 1, 100);
      server.addSubmission("Student 1", 1, 100);


      assertEquals(4, server.numSubmissions("Student 1", 1));

      server.addSubmission("Student 1", 2, 70);
      server.addSubmission("Student 1", 2, 80);      
      
      server.addSubmission("Student 2", 1, 85);
      server.addSubmission("Student 2", 1, 95);
      server.addSubmission("Student 2", 1, 93);
      
      //should not add
      server.addSubmission("", 1, 100);
      server.addSubmission("", 1, 100);


      assertEquals(3, server.numSubmissions("Student 2", 1));

      //equals 0
      assertEquals(0, server.numSubmissions("Student 2", 2));
      assertEquals(0, server.numSubmissions("", 1));
      assertEquals(0, server.numSubmissions("Fake", 1));
      assertEquals(0, server.numSubmissions("Student 2", 3));
   }
   
   // test addSubmission and numSubmissions(int)
   @Test
   public void test3() {
      SubmitServer server = new SubmitServer(2);

      assertEquals(0, server.numSubmissions(1));
      assertEquals(0, server.numSubmissions(2));


      server.addSubmission("Student 1", 1, 80);
      server.addSubmission("Student 1", 1, 90);
      server.addSubmission("Student 1", 1, 100);
      server.addSubmission("Student 1", 1, 100);

      assertEquals(4, server.numSubmissions(1));
      assertEquals(0, server.numSubmissions(2));
      
      server.addSubmission("Student 1", 2, 70);
      server.addSubmission("Student 1", 2, 80);      
      
      server.addSubmission("Student 2", 1, 85);
      server.addSubmission("Student 2", 1, 95);
      server.addSubmission("Student 2", 1, 93);
      
      //should not add
      server.addSubmission("", 1, 100);
      server.addSubmission("", 1, 100);


      assertEquals(7, server.numSubmissions(1));
      assertEquals(2, server.numSubmissions(2));
      
      //equals 0
      assertEquals(0, server.numSubmissions(-1));
      assertEquals(0, server.numSubmissions(0));
      assertEquals(0, server.numSubmissions(3));
   }
   
   // test addSubmission and numSubmissions(String)
   @Test
   public void test4() {
      SubmitServer server = new SubmitServer(2);

      assertEquals(0, server.numSubmissions("Student 1"));
      assertEquals(0, server.numSubmissions("Student 2"));
      assertEquals(0, server.numSubmissions(""));
      assertEquals(0, server.numSubmissions("Fake"));

      server.addSubmission("Student 1", 1, 80);
      server.addSubmission("Student 1", 1, 90);
      server.addSubmission("Student 1", 1, 100);
      server.addSubmission("Student 1", 1, 100);

      assertEquals(4, server.numSubmissions("Student 1"));
      assertEquals(0, server.numSubmissions("Student 2"));
      assertEquals(0, server.numSubmissions(""));
      assertEquals(0, server.numSubmissions("Fake"));
      
      server.addSubmission("Student 1", 2, 70);
      server.addSubmission("Student 1", 2, 80);      
      
      server.addSubmission("Student 2", 1, 85);
      server.addSubmission("Student 2", 1, 95);
      server.addSubmission("Student 2", 1, 93);
      
      //should not add
      server.addSubmission("", 1, 100);
      server.addSubmission("", 1, 100);


      assertEquals(6, server.numSubmissions("Student 1"));
      assertEquals(3, server.numSubmissions("Student 2"));
      
      //equals 0
      assertEquals(0, server.numSubmissions(""));
      assertEquals(0, server.numSubmissions("Fake"));

   }

   // test addSubmission and numSubmissions()
   @Test
   public void test5() {
      SubmitServer server = new SubmitServer(2);

      assertEquals(0, server.numSubmissions());

      server.addSubmission("Student 1", 1, 80);
      server.addSubmission("Student 1", 1, 90);
      server.addSubmission("Student 1", 1, 100);
      server.addSubmission("Student 1", 1, 100);

      assertEquals(4, server.numSubmissions());

      
      server.addSubmission("Student 1", 2, 70);
      server.addSubmission("Student 1", 2, 80);      
      
      server.addSubmission("Student 2", 1, 85);
      server.addSubmission("Student 2", 1, 95);
      server.addSubmission("Student 2", 1, 93);
      
      assertEquals(9, server.numSubmissions());

      //should not add
      server.addSubmission("", 1, 100);
      server.addSubmission("", 1, 100);


      assertEquals(9, server.numSubmissions());
   }
   
   // test addSubmission and bestSubmissionNumber(String, int)
   @Test
   public void test6() {
      SubmitServer server = new SubmitServer(2);

      assertEquals(0, server.bestSubmissionNumber("Student 1", 1));
      assertEquals(0, server.bestSubmissionNumber("Student 1", 2));
      assertEquals(0, server.bestSubmissionNumber("Student 1", 3));
      assertEquals(0, server.bestSubmissionNumber("Student 2", 1));
      assertEquals(0, server.bestSubmissionNumber("Student 2", 2));
      assertEquals(0, server.bestSubmissionNumber("Student 2", 3));
      assertEquals(0, server.bestSubmissionNumber("", 1));
      assertEquals(0, server.bestSubmissionNumber("Fake", 1));
      
      server.addSubmission("Student 1", 1, 80);
      server.addSubmission("Student 1", 1, 90);
      server.addSubmission("Student 1", 1, 100);
      assertEquals(3, server.bestSubmissionNumber("Student 1", 1));
      server.addSubmission("Student 1", 1, 100);

      assertEquals(4, server.bestSubmissionNumber("Student 1", 1));
      assertEquals(0, server.bestSubmissionNumber("Student 1", 2));
      assertEquals(0, server.bestSubmissionNumber("Student 1", 3));
      assertEquals(0, server.bestSubmissionNumber("Student 2", 1));
      assertEquals(0, server.bestSubmissionNumber("Student 2", 2));
      assertEquals(0, server.bestSubmissionNumber("Student 2", 3));
      
      server.addSubmission("Student 1", 2, 70);
      server.addSubmission("Student 1", 2, 80);      
      
      server.addSubmission("Student 2", 1, 85);
      server.addSubmission("Student 2", 1, 95);
      server.addSubmission("Student 2", 1, 93);
      
      assertEquals(4, server.bestSubmissionNumber("Student 1", 1));
      assertEquals(2, server.bestSubmissionNumber("Student 1", 2));
      assertEquals(0, server.bestSubmissionNumber("Student 1", 3));
      assertEquals(2, server.bestSubmissionNumber("Student 2", 1));
      assertEquals(0, server.bestSubmissionNumber("Student 2", 2));
      assertEquals(0, server.bestSubmissionNumber("Student 2", 3));

      //should not add
      server.addSubmission("", 1, 100);
      server.addSubmission("", 1, 100);

      assertEquals(0, server.bestSubmissionNumber("", 1));
   }
   
   // test addSubmission and bestSubmissionScore(String, int)
   @Test
   public void test7() {
      SubmitServer server = new SubmitServer(2);


      assertEquals(-1, server.bestSubmissionScore("", 1));
      
      server.addSubmission("Student 1", 1, 80);
      server.addSubmission("Student 1", 1, 90);
      server.addSubmission("Student 1", 1, 100);
      assertEquals(100, server.bestSubmissionScore("Student 1", 1));
      server.addSubmission("Student 1", 1, 100);

      assertEquals(100, server.bestSubmissionScore("Student 1", 1));
      assertEquals(0, server.bestSubmissionScore("Student 1", 2));
      assertEquals(-1, server.bestSubmissionScore("Student 1", 3));

      
      server.addSubmission("Student 1", 2, 70);
      server.addSubmission("Student 1", 2, 80);      
      
      server.addSubmission("Student 2", 1, 85);
      server.addSubmission("Student 2", 1, 95);
      server.addSubmission("Student 2", 1, 93);
      
      assertEquals(100, server.bestSubmissionScore("Student 1", 1));
      assertEquals(80, server.bestSubmissionScore("Student 1", 2));
      assertEquals(-1, server.bestSubmissionScore("Student 1", 3));
      assertEquals(95, server.bestSubmissionScore("Student 2", 1));
      assertEquals(0, server.bestSubmissionScore("Student 2", 2));
      assertEquals(-1, server.bestSubmissionScore("Student 2", 3));

      //should not add
      server.addSubmission("", 1, 100);
      server.addSubmission("", 1, 100);

      assertEquals(-1, server.bestSubmissionScore("", 1));
   }
   
   // test addSubmission and satisfactory(String, int)
   @Test
   public void test8() {
      SubmitServer server = new SubmitServer(2);

      assertFalse(server.satisfactory("Student 1", 1));
      assertFalse(server.satisfactory("Student 1", 2));
      assertFalse(server.satisfactory("Student 1", 3));
      assertFalse(server.satisfactory("Student 2", 1));
      assertFalse(server.satisfactory("Student 2", 2));
      assertFalse(server.satisfactory("Student 2", 3));
      assertFalse(server.satisfactory("", 1));
      assertFalse(server.satisfactory("", 3));
      assertFalse(server.satisfactory("Fake", 1));
      
      server.addSubmission("Student 1", 1, 80);
      server.addSubmission("Student 1", 1, 90);
      server.addSubmission("Student 1", 1, 100);
      assertTrue(server.satisfactory("Student 1", 1));
      server.addSubmission("Student 1", 1, 100);

      assertTrue(server.satisfactory("Student 1", 1));
      assertFalse(server.satisfactory("Student 1", 2));
      assertFalse(server.satisfactory("Student 1", 3));
      assertFalse(server.satisfactory("Student 2", 1));
      assertFalse(server.satisfactory("Student 2", 2));
      assertFalse(server.satisfactory("Student 2", 3));
      assertFalse(server.satisfactory("", 1));
      assertFalse(server.satisfactory("", 3));
      assertFalse(server.satisfactory("Fake", 1));
      
      server.addSubmission("Student 1", 2, 70);
      server.addSubmission("Student 1", 2, 80);      
      
      server.addSubmission("Student 2", 1, 0);
      server.addSubmission("Student 2", 1, 0);
      server.addSubmission("Student 2", 1, 0);
      
      assertTrue(server.satisfactory("Student 1", 1));
      assertTrue(server.satisfactory("Student 1", 2));
      assertFalse(server.satisfactory("Student 1", 3));
      assertFalse(server.satisfactory("Student 2", 1));
      assertFalse(server.satisfactory("Student 2", 2));
      assertFalse(server.satisfactory("Student 2", 3));
      assertFalse(server.satisfactory("", 1));
      assertFalse(server.satisfactory("", 3));
      assertFalse(server.satisfactory("Fake", 1));

      //should not add
      server.addSubmission("", 1, 100);
      server.addSubmission("", 1, 100);

      assertFalse(server.satisfactory("", 3));
   }
   
   // test addSubmission and gotExtraCredit(String, int)
   @Test
   public void test9() {
      SubmitServer server = new SubmitServer(2);

      assertFalse(server.gotExtraCredit("Student 1", 1));
      assertFalse(server.gotExtraCredit("Student 1", 2));
      assertFalse(server.gotExtraCredit("Student 1", 3));
      assertFalse(server.gotExtraCredit("Student 2", 1));
      assertFalse(server.gotExtraCredit("Student 2", 2));
      assertFalse(server.gotExtraCredit("Student 2", 3));
      assertFalse(server.gotExtraCredit("", 1));
      assertFalse(server.gotExtraCredit("", 3));
      assertFalse(server.gotExtraCredit("Fake", 1));
      
      server.addSubmission("Student 1", 1, 80);
      server.addSubmission("Student 1", 1, 90);
      server.addSubmission("Student 1", 1, 100);

      assertFalse(server.gotExtraCredit("Student 1", 1));
      
      server.addSubmission("Student 1", 2, 100);
      server.addSubmission("Student 1", 2, 100);      
      
      assertFalse(server.gotExtraCredit("Student 1", 2));

      
      server.addSubmission("Student 2", 1, 100);
      
      
      assertTrue(server.gotExtraCredit("Student 2", 1));


      //should not add
      server.addSubmission("", 1, 100);

      assertFalse(server.gotExtraCredit("", 1));
   }
   
// test changeScore()
   @Test
   public void test10() {
      SubmitServer server = new SubmitServer(2);

      assertFalse(server.changeScore("Student 1", 1, 1,100));
      assertFalse(server.changeScore("Student 1", 1, 1,-1));
      assertFalse(server.changeScore("Student 1", 1, 1,101));
      
      server.addSubmission("Student 1", 1, 80);
      server.addSubmission("Student 1", 1, 95);
      server.addSubmission("Student 1", 1, 90);
     
      assertFalse(server.changeScore("Student 1", 1, 3,90));
      
      assertEquals(2, server.bestSubmissionNumber("Student 1", 1));
      
      assertTrue(server.changeScore("Student 1", 1, 3,100));
      
      assertEquals(3, server.bestSubmissionNumber("Student 1", 1));
      
      server.addSubmission("Student 1", 1, 100);

      assertEquals(4, server.bestSubmissionNumber("Student 1", 1));

      
     
      
      server.addSubmission("Student 2", 1, 99);
      
      assertTrue(server.changeScore("Student 2", 1, 1,100));
      assertTrue(server.gotExtraCredit("Student 2", 1));


      //should not add
      server.addSubmission("", 1, 100);
      server.addSubmission("", 1, 100);

      assertEquals(0, server.bestSubmissionNumber("", 1));
   }
   
   
}
