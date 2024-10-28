import java.util.ArrayList;
import java.util.Scanner;

public class GradeTracker {
    private ArrayList<Double> grades;

    public GradeTracker() {
        grades = new ArrayList<>();
    }

    public void addGrade(double grade) {
        grades.add(grade);
        System.out.println("Grade " + grade + " added.");
    }

    public double calculateAverage() {
        if (grades.isEmpty()) {
            System.out.println("No grades available to calculate average.");
            return 0;
        }
        double sum = 0;
        for (double grade : grades) {
            sum += grade;
        }
        return sum / grades.size();
    }

    public void displayGrades() {
        if (grades.isEmpty()) {
            System.out.println("No grades recorded.");
            return;
        }
        System.out.println("Grades:");
        for (double grade : grades) {
            System.out.println(grade);
        }
    }

    public static void main(String[] args) {
        GradeTracker tracker = new GradeTracker();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nGrade Tracker Menu:");
            System.out.println("1. Add Grade");
            System.out.println("2. Calculate Average");
            System.out.println("3. Display Grades");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter the grade to add: ");
                    double grade = scanner.nextDouble();
                    tracker.addGrade(grade);
                    break;
                case 2:
                    double average = tracker.calculateAverage();
                    if (average != 0) {
                        System.out.println("Average grade: " + average);
                    }
                    break;
                case 3:
                    tracker.displayGrades();
                    break;
                case 4:
                    System.out.println("Exiting the program.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);

        scanner.close();
    }
}

