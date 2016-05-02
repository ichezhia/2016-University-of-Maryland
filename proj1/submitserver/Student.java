package submitserver;

import java.util.List;
import java.util.ArrayList;

/*
 * The Student class is used by the SubmitServer to make Student objects.
 * Contains attributes of name and list of scores.
 */
public class Student {

   // Name of student and ArrayList of score Integers.
   private String studentName;
   List<Integer> studentScoreList = new ArrayList<Integer>();

   // constructor
   Student(String name, int score) {
      this.studentName = name;
      this.addScore(score);
   }

   // setter for studentName
   public void setName(String name) {
      this.studentName = name;
   }

   // getter for studentName
   public String getName() {
      return studentName;
   }

   // adds a score to the studentScoreList
   public void addScore(int score) {
      studentScoreList.add(new Integer(score));
   }

   // replaces a score at an index of studentScoreList
   public void fixScore(int index, int score) {
      studentScoreList.set(index, new Integer(score));
   }

   // returns score at an index of studentScoreList
   public int getScore(int index) {
      return studentScoreList.get(index);
   }

   // iterates through studentScoreList to return the bestScore
   public int getBestScore() {
      int bestScore = 0;

      for (int i = 0; i < studentScoreList.size(); i++) {
         if (studentScoreList.get(i) > bestScore) {
            bestScore = studentScoreList.get(i);
         }
      }
      return bestScore;
   }

   // iterates through studentScoreList to find the bestScore
   // then returns the submit number of that bestScore
   public int getBestSubmitNumber() {
      int bestScore = 0;
      int index = 0;

      for (int i = 0; i < studentScoreList.size(); i++) {
         //compare to bestScore so far
         if (studentScoreList.get(i) >= bestScore) {
            //if current index score is greater, store the score and index
            bestScore = studentScoreList.get(i);
            index = i;
            //new index is stored, because latest highest score index is required
         }
      }
      return index + 1;
   }

   // returns the size of the studentScoreList,
   // which is the number of submissions for the student
   public int getNumberSubmits() {
      return studentScoreList.size();
   }

}
