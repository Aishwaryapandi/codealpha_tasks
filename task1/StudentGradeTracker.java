import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class StudentGradeTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Double> grades = new ArrayList<>();
        double sum = 0;
        double grade;
        
        System.out.println("Enter student grades. Type -1 to stop.");
        
        while (true) {
            System.out.print("Enter grade: ");
            if (scanner.hasNextDouble()) {  
                grade = scanner.nextDouble();
                
                if (grade == -1) {
                    break; 
                }
                
                grades.add(grade);
                sum += grade;
            } else {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next(); 
            }
        }
        
        if (!grades.isEmpty()) {
            double average = sum / grades.size();
            double highest = Collections.max(grades);
            double lowest = Collections.min(grades);

            System.out.println("\nSummary:");
            System.out.printf("Average Grade: %.2f\n", average);
            System.out.printf("Highest Grade: %.2f\n", highest);
            System.out.printf("Lowest Grade: %.2f\n", lowest);
        } else {
            System.out.println("No grades were entered.");
        }
        
        scanner.close();
    }
}
