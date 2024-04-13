package boundary;


import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean exit = false;
        while (!exit) {
            System.out.println("\n===============================");
            System.out.println("        Main Menu              ");
            System.out.println("===============================");
            System.out.println("Choose an option:");
            System.out.println("1. Course Management System");
            System.out.println("2. Student Registration System");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    CourseManagement.courseManagement();
                    break;
                case 2:
                    StudentRegistration.studentRegistration();
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
        scanner.close();

    }
}
