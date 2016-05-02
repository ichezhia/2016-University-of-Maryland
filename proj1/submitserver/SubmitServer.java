package submitserver;

import java.util.ArrayList;
import java.util.List;

/*
 * SubmitServer will store submission results and allow the data to be
 * retrieved.
 * Cloneable is implemented to clone the object as the data attributes
 * (ArrayLists, String) are easily cloneable.
 */
public class SubmitServer implements Cloneable {

   // Private attribute that stores a list of students (name and scores)
   private List<Student> studentList = new ArrayList<Student>();

   // SubmitServer will store submission results and allow the data to be
   // retrieved.
   // Accepts a name and score to be added.
   // Returns a clone of the current object to allow for chained calls.
   public SubmitServer addSubmission(String name, int score) {

      // validate parameters
      if (name != null && name != "" && score >= 0 & score <= 100) {
         // checks if student exists - stores index in student list if exists
         int exists = this.studentExists(name);

         // student with name does not exist
         if (exists == -1) {
            // add student and score
            studentList.add(new Student(name, score));
         } else {
            // student exists, so add score to student of index
            (studentList.get(exists)).addScore(score);
         }
      }
      // return clone of current object to allow for chained calls
      return this.clone();
   }

   // returns number of submissions for a given student
   public int numSubmissions(String name) {
      // checks if student exists - stores index in student list if exists
      int exists = this.studentExists(name);

      // return -1 if name is invalid or student does not exist
      if (name == null || name == "" || exists == -1) {
         return -1;
      } else {
         // student does exists, so returns number of submissions for student
         return (studentList.get(exists)).getNumberSubmits();
      }
   }

   // returns total number of submissions on server for all students
   public int numSubmissions() {
      int counter = 0;

      for (int i = 0; i < studentList.size(); i++) {
         // number of submits for each student in student list is accumulated
         counter += (studentList.get(i)).getNumberSubmits();
      }

      return counter;
   }

   // returns the submission number for the highest score for given student
   public int bestSubmissionNumber(String name) {
      // checks if student exists - stores index in student list if exists
      int exists = this.studentExists(name);

      // return -1 if student does not exist
      if (this.studentExists(name) == -1) {
         return -1;
      } else {
         // student exists, so return submission number for the highest score
         return (studentList.get(exists)).getBestSubmitNumber();
      }
   }

   // returns highest score for given student
   public int bestSubmissionScore(String name) {
      // checks if student exists - stores index in student list if exists
      int exists = this.studentExists(name);

      // return -1 if student does not exist
      if (this.studentExists(name) == -1) {
         return -1;
      } else {
         // student exists, so return highest score
         return (studentList.get(exists)).getBestScore();
      }
   }

   // returns true if given student has at least one score more than zero
   // returns if student does not exist or has all zero scores
   public boolean satisfactory(String name) {
      // checks if student exists - stores index in student list if exists
      int exists = this.studentExists(name);

      // student does not exist
      if (this.studentExists(name) == -1) {
         return false;
      } else if ((studentList.get(exists)).getBestScore() > 0) {
         // student has best score above zero
         return true;
      } else {
         // student has all zero scores
         return false;
      }
   }

   // returns number of students who got extra credit
   // students who got 100 on their one and only submission
   public int gotExtraCredit() {
      int counter = 0;

      // goes through each student and checks if best score is 100
      // and student only has 1 submission
      for (int i = 0; i < studentList.size(); i++) {
         if ((studentList.get(i)).getBestScore() == 100 && (studentList.get(i)).getNumberSubmits() == 1) {
            counter++;
         }
      }
      // return accumulated amount of students
      return counter;
   }

   public boolean changeScore(String name, int submissionNumber, int newScore) {
      int exists = this.studentExists(name);

      if (name == null || name == "" || exists == -1) {
         return false;
      } else if (submissionNumber > (studentList.get(exists)).getNumberSubmits()) {
         // invalid submissionNumber
         return false;
      } else if (newScore < 0 || newScore > 100) {
         // invalid newScore
         return false;
      } else if (newScore == (studentList.get(exists)).getScore(submissionNumber - 1)) {
         return false;
      } else {
         (studentList.get(exists)).fixScore(submissionNumber - 1, newScore);
         return true;
      }
   }

   // searches to see if student with name already exists
   // returns index in student list if exists
   private int studentExists(String name) {

      // validate name
      if (name == null || name == "") {
         return -1;
      }

      for (int i = 0; i < studentList.size(); i++) {
         if (name.equals((studentList.get(i)).getName())) {
            // return index if name is equal
            return i;
         }
      }
      // student does not exist
      return -1;
   }

   // clone class to make clone to return in addSubmission method
   public SubmitServer clone() {
      try {
         return (SubmitServer) super.clone();
      } catch (CloneNotSupportedException e) {
         e.printStackTrace();
         throw new RuntimeException();
      }
   }
}
