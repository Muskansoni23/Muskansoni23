import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private List<Double> grades;

    public Student(String name) {
        this.name = name;
        this.grades = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addGrade(double grade) {
        grades.add(grade);
    }

    public List<Double> getGrades() {
        return grades;
    }

    public double calculateAverage() {
        double sum = 0;
        for (double grade : grades) {
            sum += grade;
        }
        return grades.isEmpty() ? 0 : sum / grades.size();
    }

    public double getHighestGrade() {
        return grades.stream().max(Double::compare).orElse(0.0);
    }

    public double getLowestGrade() {
        return grades.stream().min(Double::compare).orElse(0.0);
    }
}
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GradeTracker {
    private List<Student> students;

    public GradeTracker() {
        students = new ArrayList<>();
    }

    public void addStudent(String name) {
        students.add(new Student(name));
    }

    public Student findStudent(String name) {
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(name)) {
                return student;
            }
        }
        return null;
    }

    public void displayAllStudents() {
        for (Student student : students) {
            System.out.println("Name: " + student.getName() + ", Average: " + student.calculateAverage());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GradeTracker tracker = new GradeTracker();
        String command;

        do {
            System.out.println("Enter command (add/view/exit): ");
            command = scanner.nextLine().toLowerCase();

            switch (command) {
                case "add":
                    System.out.println("Enter student name: ");
                    String name = scanner.nextLine();
                    tracker.addStudent(name);
                    System.out.println("Student added.");
                    break;

                case "view":
                    System.out.println("Enter student name to view grades: ");
                    String studentName = scanner.nextLine();
                    Student student = tracker.findStudent(studentName);
                    if (student != null) {
                        System.out.println("Grades for " + student.getName() + ": " + student.getGrades());
                        System.out.println("Average: " + student.calculateAverage());
                        System.out.println("Highest: " + student.getHighestGrade());
                        System.out.println("Lowest: " + student.getLowestGrade());
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case "exit":
                    System.out.println("Exiting...");
                    break;

                default:cn
                    System.out.println("Invalid command.");
                    break;
            }
        } while (!command.equals("exit"));

        scanner.close();
    }
}


   