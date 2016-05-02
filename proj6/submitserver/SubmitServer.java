package submitserver;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

//must only use set and map classes
//number of students are limitless
//submissions are limitless
public class SubmitServer implements Cloneable {

   // map of project number to a map of hashsets of student,score
   private Map<Integer, Map> projectStudentMap = new LinkedHashMap<Integer, Map>();

   private static class Score {
      private int score;
      private int submissionNumber;

      // constructor
      public Score(int score, int submissionNumber) {
         this.score = score;
         this.submissionNumber = submissionNumber;
      }
   }

   // map of student to a set of sets

   // constructor to initialize a SubmitServer object able to store submissions
   // for the numProjects parameter (can be negative or 0)
   public SubmitServer(int numProjects) {

      for (int i = 1; i <= numProjects; i++) {
         projectStudentMap.put(i, new HashMap<String, Set<Score>>());
      }
   }

   // add a single submission for the parameters: name, projectNumber, and
   // score.

   public SubmitServer addSubmission(String name, int projectNumber, int score) {
      // validate if name is not empty

      // validate if score and projectNumber are in range
      if (name == null || name == "" || projectNumber < 1 || projectNumber > projectStudentMap.size() || score < 0
            || score > 100) {
         return this;
      }

      Map<String, Set<Score>> thisProjectMap = projectStudentMap.get(projectNumber);

      // add student and set to map if student does not exist
      if (!thisProjectMap.containsKey(name)) {
         thisProjectMap.put(name, new LinkedHashSet<Score>());
      }

      Set<Score> thisStudentSet = thisProjectMap.get(name);

      thisStudentSet.add(new Score(score, thisStudentSet.size() + 1));

      return this;
   }

   public int numSubmissions(String name, int projectNumber) {
      // validate projectNumber and student
      if (name == null || name == "" || projectNumber < 1 || projectNumber > projectStudentMap.size()) {
         return 0;
      }

      int counter = 0;
      Map<String, Set<Score>> thisProjectMap;

      thisProjectMap = projectStudentMap.get(projectNumber);

      for (Map.Entry<String, Set<Score>> entry : thisProjectMap.entrySet()) {
         if (entry.getKey().equals(name)) {
            counter += entry.getValue().size();
         }
      }

      return counter;
   }

   public int numSubmissions(int projectNumber) {
      // validate projectNumber and student
      if (projectNumber < 1 || projectNumber > projectStudentMap.size()) {
         return 0;
      }

      int counter = 0;
      Map<String, Set<Score>> thisProjectMap;

      thisProjectMap = projectStudentMap.get(projectNumber);

      for (Map.Entry<String, Set<Score>> entry : thisProjectMap.entrySet()) {
         counter += entry.getValue().size();

      }

      return counter;
   }

   public int numSubmissions(String name) {
      int counter = 0;
      Map<String, Set<Integer>> thisProjectMap;

      // loop through each project
      for (int i = 1; i <= projectStudentMap.size(); i++) {

         thisProjectMap = projectStudentMap.get(i);

         for (Map.Entry<String, Set<Integer>> entry : thisProjectMap.entrySet()) {
            // loop through each student and add the size of each student score
            // set to counter
            if (entry.getKey().equals(name)) {
               counter += entry.getValue().size();
            }
         }
      }
      return counter;
   }

   public int numSubmissions() {
      int counter = 0;
      Map<String, Set<Integer>> thisProjectMap;

      // loop through each project
      for (int i = 1; i <= projectStudentMap.size(); i++) {

         thisProjectMap = projectStudentMap.get(i);

         for (Map.Entry<String, Set<Integer>> entry : thisProjectMap.entrySet()) {
            // loop through each student and add the size of each student score
            // set to counter
            counter += entry.getValue().size();
         }
      }
      return counter;
   }

   public int bestSubmissionNumber(String name, int projectNumber) {
      // validate projectNumber and student
      if (name == null || name == "" || projectNumber < 1 || projectNumber > projectStudentMap.size()) {
         return 0;
      }

      int bestSubmissionNumber = 0;
      int bestSubmissionScore = 0;
      Map<String, Set<Score>> thisProjectMap;

      thisProjectMap = projectStudentMap.get(projectNumber);

      for (Map.Entry<String, Set<Score>> entry : thisProjectMap.entrySet()) {
         if (entry.getKey().equals(name)) {
            Set<Score> thisScoreSet = entry.getValue();

            for (Score score : thisScoreSet) {
               if (score.score >= bestSubmissionScore) {
                  bestSubmissionNumber = score.submissionNumber;
                  bestSubmissionScore = score.score;
               }
            }
         }
      }

      return bestSubmissionNumber;
   }

   public int bestSubmissionScore(String name, int projectNumber) {
      // validate projectNumber and student
      if (name == null || name == "" || projectNumber < 1 || projectNumber > projectStudentMap.size()) {
         return -1;
      }

      int bestSubmissionScore = 0;
      Map<String, Set<Score>> thisProjectMap;

      thisProjectMap = projectStudentMap.get(projectNumber);

      for (Map.Entry<String, Set<Score>> entry : thisProjectMap.entrySet()) {
         if (entry.getKey().equals(name)) {
            Set<Score> thisScoreSet = entry.getValue();

            for (Score score : thisScoreSet) {
               if (score.score >= bestSubmissionScore) {
                  bestSubmissionScore = score.score;
               }
            }
         }
      }

      return bestSubmissionScore;
   }

   public boolean satisfactory(String name, int projectNumber) {
      // validate projectNumber and student

      if (name == null || name == "" || projectNumber < 1 || projectNumber > projectStudentMap.size()) {
         return false;
      }

      Map<String, Set<Score>> thisProjectMap;

      thisProjectMap = projectStudentMap.get(projectNumber);

      for (Map.Entry<String, Set<Score>> entry : thisProjectMap.entrySet()) {
         if (entry.getKey().equals(name)) {
            Set<Score> thisScoreSet = entry.getValue();

            for (Score score : thisScoreSet) {
               if (score.score > 0) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   public boolean gotExtraCredit(String name, int projectNumber) {
      // validate projectNumber and student
      if (name == null || name == "" || projectNumber < 1 || projectNumber > projectStudentMap.size()) {
         return false;
      }

      Map<String, Set<Score>> thisProjectMap;

      thisProjectMap = projectStudentMap.get(projectNumber);

      for (Map.Entry<String, Set<Score>> entry : thisProjectMap.entrySet()) {
         if (entry.getKey().equals(name)) {
            Set<Score> thisScoreSet = entry.getValue();

            if (!(thisScoreSet.size() == 1)) {
               return false;
            }

            for (Score score : thisScoreSet) {
               if (score.score == 100) {
                  return true;
               }
            }
         }
      }
      return false;

   }

   public boolean changeScore(String name, int projectNumber, int submissionNumber, int newScore) {
      // validate projectNumber and student
      if (name == null || name == "" || projectNumber < 1 || projectNumber > projectStudentMap.size()) {
         return false;
      }

      Map<String, Set<Score>> thisProjectMap;

      thisProjectMap = projectStudentMap.get(projectNumber);

      for (Map.Entry<String, Set<Score>> entry : thisProjectMap.entrySet()) {
         if (entry.getKey().equals(name)) {
            Set<Score> thisScoreSet = entry.getValue();

            if (submissionNumber > thisScoreSet.size()) {
               // invalid submissionNumber
               return false;
            }

            for (Score score : thisScoreSet) {
               if (score.submissionNumber == submissionNumber) {
                  if (score.score != newScore) {
                     score.score = newScore;
                     return true;
                  } else {
                     return false;
                  }
               }
            }
         }
      }
      return false;
   }
}