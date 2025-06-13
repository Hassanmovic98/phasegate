import java.util.Scanner;
import java.util.Scanner;

public class StudentGrade {
    public static void main(String[] args) {


	System.out.println ("Welcome to semi colon Africa StudentGrade System");

        Scanner input = new Scanner(System.in);

        System.out.print("what are the numbers of students: ");
        int numofStudents = input.nextInt();

        System.out.print("what are the numbers of subjects offerred: ");
        int numofSubjects = input.nextInt();

        int[][] scores = new int[numofStudents][numofSubjects];
        int[] studentTotals = new int[numofStudents];
        double[] studentAverages = new double[numofStudents];

        for (int studentIndex = 0; studentIndex < numofStudents; studentIndex++) {
            System.out.println("collecting scores of students " + (studentIndex + 1));
            int totalScore = 0;

            for (int subjectIndex = 0; subjectIndex < numofSubjects; subjectIndex++) {
                int score = -1;

                for (int attempt = 0; score < 0 || score > 100; attempt++) {
                   System.out.print("Please enter the score for Subjects " + (subjectIndex + 1) + ": ");

                    score = input.nextInt();

                    if (score < 0 || score > 100) {
                        System.out.println("invalid score, Student score can only be between 0 and 100.");
                    }
                }

                scores[studentIndex][subjectIndex] = score;
                totalScore += score;
            }

            studentTotals[studentIndex] = totalScore;
            studentAverages[studentIndex] = (double) totalScore / numofSubjects;
        }

        System.out.println("Students Grade Report");

        int classTotal = 0;
        int lowestScore = studentTotals[0];
        int highestScore = studentTotals[0];
        int leastStudent = 0;
        int topStudent = 0;

        for (int studentIndex = 0; studentIndex < numofStudents; studentIndex++) {
            int total = studentTotals[studentIndex];
            double average = studentAverages[studentIndex];
            System.out.printf("Student %d Total: %d, Average: %.2f%n", studentIndex + 1, total, average);
	    


            classTotal += studentTotals[studentIndex];

            if (studentTotals[studentIndex] > highestScore) {
                highestScore = studentTotals[studentIndex];
                topStudent = studentIndex;
            }

            if (studentTotals[studentIndex] < lowestScore) {
                lowestScore = studentTotals[studentIndex];
                leastStudent = studentIndex;
            }
        }

        double classAverage = (double) classTotal / (numofStudents * numofSubjects);

        System.out.println("The Class Average is : " + String.format("%.2f", classAverage));

        System.out.println("Best Performing Student is : Student " + (topStudent + 1)
                + " (Total: " + highestScore + ")");
        System.out.println("Lowest Performing Student is: Student " + (leastStudent + 1)
                + " (Total: " + lowestScore + ")");
    }
}

	 

	  
	
