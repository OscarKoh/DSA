package boundary;

import Boundary.TeamManagementUI;
import init.DataInitializer;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        DataInitializer.initializeAllData();
        Scanner scanner = new Scanner(System.in);

        boolean exit = false;
        while (!exit) {
            System.out.println("\n===============================");
            System.out.println("        Main Menu              ");
            System.out.println("===============================");
            System.out.println("Choose an option:");
            System.out.println("1. Course Management System");
            System.out.println("2. Student Registration System");
            System.out.println("4. Assignment Team Management System");
            System.out.println("5. Exit");

            while (true) {
                try {
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
                        case 4:
                            TeamManagementUI.teamManagementSystem();

                        case 5:
                            System.exit(0);

                        default:
                            System.out.println("Invalid choice. Please enter a valid option.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    scanner.nextLine(); // Clear the invalid input
                    continue;
                }
                break;
            }

        }

    }
}
