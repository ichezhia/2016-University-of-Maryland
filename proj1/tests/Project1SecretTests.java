package tests;

// (c) Larry Herman, 2016.  You are allowed to use this code yourself, but
// not to provide it to anyone else.

import submitserver.SubmitServer;
import org.junit.*;
import static org.junit.Assert.*;

public class Project1SecretTests {

  // Tests calling some methods with empty strings for names.
  @Test public void testSecret1() {
    SubmitServer server= new SubmitServer();

    server.addSubmission("Leanne Learner", 83);
    server.addSubmission("Schuyler Scholar", 92);
    server.addSubmission("Theresa Terrapin", 84);
    server.addSubmission("Schuyler Scholar", 0);
    server.addSubmission("Ursula Undergraduate", 77);
    server.addSubmission("Theresa Terrapin", 62);
    server.addSubmission("Theresa Terrapin", 55);
    server.addSubmission("Schuyler Scholar", 95);
    server.addSubmission("Leanne Learner", 93);
    server.addSubmission("Theresa Terrapin", 84);
    server.addSubmission("Schuyler Scholar", 93);
    server.addSubmission("Ursula Undergraduate", 57);

    assertEquals(-1, server.numSubmissions(""));
    assertEquals(-1, server.bestSubmissionNumber(""));
    assertEquals(-1, server.bestSubmissionScore(""));
    assertFalse(server.satisfactory(""));
  }

  // Tests calling some methods on an empty SubmitServer (that does not have
  // any submissions stored).
  @Test public void testSecret2() {
    SubmitServer server= new SubmitServer();

    assertEquals(-1, server.numSubmissions(""));
    assertEquals(-1, server.bestSubmissionNumber(""));
    assertEquals(-1, server.bestSubmissionScore(""));
    assertFalse(server.satisfactory(""));
  }

  // Tests that some methods are only matching names that are identical.
  @Test public void testSecret3() {
    SubmitServer server= new SubmitServer();

    server.addSubmission("Leanne Learner", 70);
    server.addSubmission("Leanne", 90);

    assertEquals(70, server.bestSubmissionScore("Leanne Learner"));
    assertEquals(90, server.bestSubmissionScore("Leanne"));
  }

  // Tests that some methods are properly enforcing case sensitivity of
  // names.
  @Test public void testSecret4() {
    SubmitServer server= new SubmitServer();

    server.addSubmission("Leanne Learner", 70);
    server.addSubmission("LeAnNe LeArNeR", 80);

    assertEquals(70, server.bestSubmissionScore("Leanne Learner"));
    assertEquals(80, server.bestSubmissionScore("LeAnNe LeArNeR"));
  }

  // Tests that names are not being searched for using reference comparision
  // for identical objects.
  @Test public void testSecret5() {
    SubmitServer server= new SubmitServer();
    String str= new String("Schuyler Scholar");

    server.addSubmission("Schuyler Scholar", 92);
    server.addSubmission("Theresa Terrapin", 84);

    assertEquals(92, server.bestSubmissionScore(str));
  }

  // Tests addSubmission()'s return value using chained calls.
  @Test public void testSecret6() {
    SubmitServer server= new SubmitServer();

    server.addSubmission("Junior Junior, Jr.", 87).
      addSubmission("Freddy Freshman", 84).
        addSubmission("Freddy Freshman", 55).
          addSubmission("Ursula Undergraduate", 77).
            addSubmission("Freddy Freshman", 68);

    assertEquals(5, server.numSubmissions());
  }

  // Tests adding submissions for a large number of students.
  @Test public void testSecret7() {
    SubmitServer server= new SubmitServer();
    int i;

    for (i= 0; i < 12345; i++)
      server.addSubmission("Student" + i, 50);

    assertEquals(12345, server.numSubmissions());
    for (i= 0; i < 12345; i++)
      assertEquals(1, server.numSubmissions("Student" + i));
  }

  // Tests calling bestSubmissionNumber() when a student has different
  // submissions with the same score.
  @Test public void testSecret8() {
    SubmitServer server= new SubmitServer();

    server.addSubmission("Paula Pupil", 74);
    server.addSubmission("Sammy Student", 68);
    server.addSubmission("Ursula Undergraduate", 0);
    server.addSubmission("Theresa Terrapin", 82);
    server.addSubmission("Leanne Learner", 91);
    server.addSubmission("Paula Pupil", 79);
    server.addSubmission("Schyuler Scholar", 0);
    server.addSubmission("Ursula Undergraduate", 0);
    server.addSubmission("Paula Pupil", 68);
    server.addSubmission("Schuyler Scholar", 30);
    server.addSubmission("Sammy Student", 85);
    server.addSubmission("Paula Pupil", 79);
    server.addSubmission("Ursula Undergraduate", 0);

    assertEquals(4, server.bestSubmissionNumber("Paula Pupil"));
  }

  // Tests calling satisfactory() on a student with no satisfactory
  // submissions.
  @Test public void testSecret9() {
    SubmitServer server= new SubmitServer();

    server.addSubmission("Paula Pupil", 74);
    server.addSubmission("Sammy Student", 68);
    server.addSubmission("Ursula Undergraduate", 0);
    server.addSubmission("Theresa Terrapin", 82);
    server.addSubmission("Leanne Learner", 91);
    server.addSubmission("Paula Pupil", 79);
    server.addSubmission("Schyuler Scholar", 0);
    server.addSubmission("Ursula Undergraduate", 0);
    server.addSubmission("Paula Pupil", 68);
    server.addSubmission("Schuyler Scholar", 0);
    server.addSubmission("Sammy Student", 85);
    server.addSubmission("Paula Pupil", 79);
    server.addSubmission("Ursula Undergraduate", 0);

    assertFalse(server.satisfactory("Schuyler Scholar"));
  }

  // Tests calling gotExtraCredit() when someone's first submission got 100,
  // but they had more than one submission, and when someone had just one
  // submission, but it didn't get 100.
  @Test public void testSecret10() {
    SubmitServer server= new SubmitServer();

    server.addSubmission("Paula Pupil", 100);
    server.addSubmission("Sammy Student", 100);
    server.addSubmission("Ursula Undergraduate", 100);
    server.addSubmission("Theresa Terrapin", 100);
    server.addSubmission("Coolidge Park", 99);
    server.addSubmission("Leanne Learner", 100);
    server.addSubmission("Schyuler Scholar", 100);
    server.addSubmission("Paula Pupil", 100);

    assertEquals(5, server.gotExtraCredit());
  }

  // Tests calling changeScore() with the name of a nonexistent student.
  @Test public void testSecret11() {
    SubmitServer server= new SubmitServer();

    server.addSubmission("Theresa Terrapin", 90);

    assertFalse(server.changeScore("Alan Alpaca", 1, 70));
    assertEquals(1, server.numSubmissions());
  }

  // Tests calling changeScore() with a too-small negative score.
  @Test public void testSecret12() {
    SubmitServer server= new SubmitServer();

    server.addSubmission("Theresa Terrapin", 90);
    server.changeScore("Theresa Terrapin", 1, -10);

    assertEquals(90, server.bestSubmissionScore("Theresa Terrapin"));
  }

  // Tests calling changeScore() with a too-large score greater than 100.
  @Test public void testSecret13() {
    SubmitServer server= new SubmitServer();

    server.addSubmission("Theresa Terrapin", 90);
    server.changeScore("Theresa Terrapin", 1, 101);

    assertEquals(90, server.bestSubmissionScore("Theresa Terrapin"));
  }

  // Tests calling changeScore() with the name of an existing student, but
  // with a submission number that's too large (greater than the number of
  // submissions that that student made).
  @Test public void testSecret14() {
    SubmitServer server= new SubmitServer();

    server.addSubmission("Theresa Terrapin", 50);
    server.addSubmission("Theresa Terrapin", 60);
    server.addSubmission("Theresa Terrapin", 70);
    server.addSubmission("Theresa Terrapin", 80);

    assertFalse(server.changeScore("Theresa Terrapin", 5, 90));
    assertEquals(80, server.bestSubmissionScore("Theresa Terrapin"));
  }

  // Tests calling changeScore() on different submissions for students.
  @Test public void testSecret15() {
    SubmitServer server= new SubmitServer();

    server.addSubmission("Paula Pupil", 70);
    server.addSubmission("Sammy Student", 90);
    server.addSubmission("Paula Pupil", 80);
    server.addSubmission("Sammy Student", 80);
    server.addSubmission("Paula Pupil", 90);
    server.addSubmission("Sammy Student", 70);

    server.changeScore("Paula Pupil", 1, 95);
    server.changeScore("Sammy Student", 3, 95);

    assertEquals(1, server.bestSubmissionNumber("Paula Pupil"));
    assertEquals(3, server.bestSubmissionNumber("Sammy Student"));
    assertEquals(95, server.bestSubmissionScore("Paula Pupil"));
    assertEquals(95, server.bestSubmissionScore("Sammy Student"));
  }

  // Tests calling changeScore() with a score that's the same as the
  // student's current score.
  @Test public void testSecret16() {
    SubmitServer server= new SubmitServer();

    server.addSubmission("Theresa Terrapin", 90);

    assertFalse(server.changeScore("Theresa Terrapin", 1, 90));
    assertEquals(1, server.numSubmissions("Theresa Terrapin"));
  }

  // Tests calling changeScore() with the name of an existing student, but
  // with submission numbers that are too small.
  @Test public void testSecret17() {
    SubmitServer server= new SubmitServer();

    server.addSubmission("Theresa Terrapin", 50);
    server.addSubmission("Theresa Terrapin", 60);
    server.addSubmission("Theresa Terrapin", 70);
    server.addSubmission("Theresa Terrapin", 80);

    assertFalse(server.changeScore("Theresa Terrapin", 0, 90));
    assertFalse(server.changeScore("Theresa Terrapin", -1, 90));
    assertEquals(80, server.bestSubmissionScore("Theresa Terrapin"));
  }

}
