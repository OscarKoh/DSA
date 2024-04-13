package boundary;

import adt.ArrayList;
import adt.ListInterface;
import control.ManageCourse;
import static control.ManageCourse.addProgrammeToCourse;
import static control.ManageCourse.amendCourseDetails;
import static control.ManageCourse.availableCourses;
import static control.ManageCourse.displayCourseDetails;
import static control.ManageCourse.isValidCourseCode;
import static control.ManageCourse.removeCourse;
import static control.ManageCourse.removeProgrammeFromCourse;
import static control.ManageCourse.summaryReport;
import control.ManageProgramme;
import static control.ManageProgramme.isValidFaculty;
import static control.ManageProgramme.isValidProgrammeCode;
import static control.ManageProgramme.listAllCoursesForProgramme;
import static control.ManageProgramme.prosummaryReport;
import static control.ManageProgramme.removeProgramme;
import entity.Course;
import entity.Programme;
import init.DataInitializer;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author Loo Suk Zhen
 */
public class CourseManagement {

    public static void courseManagement() {
        DataInitializer.initializeAllData();
        ListInterface<Programme> programmeList = ManageProgramme.getProgrammeList();
        ListInterface<Course> courseList = ManageCourse.getCourseList();

        Scanner scanner = new Scanner(System.in);

        boolean exits = false;
        while (!exits) {
            System.out.println("\n===============================");
            System.out.println("       Course Management       ");
            System.out.println("===============================");
            System.out.println("Choose an operation:");
            System.out.println("1. Add New Course");
            System.out.println("2. Add New Programme");
            System.out.println("3. Remove Course");
            System.out.println("4. Remove Programme");
            System.out.println("5. Add Programme to Courses");
            System.out.println("6. Add New Course to Programmes");
            System.out.println("7. Remove Programme from Course");
            System.out.println("8. Remove Course from Programme");
            System.out.println("9. Amend Course Details From a Programme");
            System.out.println("10. List all courses for a Programme");
            System.out.println("11. Search courses in a Semester");
            System.out.println("12. List Courses taken by Faculty");
            System.out.println("13. Generate Summary Report");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: //add new course
                    String code,
                     name;
                    int creditHour;
                    double fee;
                    while (true) {
                        System.out.print("Enter course code: ");
                        code = scanner.nextLine();
                        if (code.isEmpty()) {
                            break;
                        } 
                        else{
                            break;
                        }
                    }
                    while (true) {
                        System.out.print("Enter course name: ");
                        name = scanner.nextLine();
                        if (!name.isEmpty()) {
                            break;
                        } else {
                            System.out.println("Invalid input. Course name cannot be empty.");
                        }
                    }

                    ArrayList<Course.CourseType> typesList = new ArrayList<>();
                    while (true) {
                        System.out.print("Enter course types (comma-separated): ");
                        String input = scanner.nextLine();
                        String[] inputArray = input.split(",");
                        boolean validInput = true;
                        for (String type : inputArray) {
                            try {
                                Course.CourseType courseType = Course.CourseType.valueOf(type.trim().toUpperCase());
                                typesList.add(courseType);
                            } catch (IllegalArgumentException e) {
                                validInput = false;
                                System.out.println("Invalid.Course type must within lecture,tutorial, practical.");
                                break;
                            }
                        }
                        if (validInput) {
                            break;
                        }
                    }
                    while (true) {
                        System.out.print("Enter credit hour: ");
                        creditHour = scanner.nextInt();
                        scanner.nextLine(); // Consume newline character
                        if (creditHour != 0) {
                            break;
                        } else {
                            System.out.println("Invalid input. Course credit hour cannot be 0.");
                        }
                    }

                    while (true) {
                        System.out.print("Enter fee of course: ");
                        fee = scanner.nextDouble();
                        scanner.nextLine();
                        if (fee != 0) {
                            break;
                        } else {
                            System.out.println("Invalid input. Course fee cannot be 0.");
                        }
                    }

                    boolean added = ManageCourse.addCourse(code, name, creditHour, typesList, fee);
                    if (added) {
                        System.out.println("You are successfully added a new course.");
                    }
                    continue;

                case 2: //add new programme
                    String programmeCode,
                     programmeName,
                     facultyInput;
                    int duration;
                    while (true) {
                        System.out.print("Enter programme code: ");
                        programmeCode = scanner.nextLine();
                        if (!programmeCode.isEmpty()) {
                            break;
                        } else {
                            System.out.println("Invalid input. Programme code cannot be empty.");
                        }
                    }
                    while (true) {
                        System.out.print("Enter programme name: ");
                        programmeName = scanner.nextLine();
                        if (!programmeName.isEmpty()) {
                            break;
                        } else {
                            System.out.println("Invalid input. Programme name cannot be empty.");
                        }
                    }
                    while (true) {
                        System.out.print("Enter faculty: ");
                        facultyInput = scanner.nextLine();
                        if (!facultyInput.isEmpty()) {
                            break;
                        } else {
                            System.out.println("Invalid input. Faculty cannot be empty.");
                        }
                    }
                    while (true) {
                        System.out.print("Enter duration in months: ");
                        duration = scanner.nextInt();
                        scanner.nextLine();
                        if (duration != 0) {
                            break;
                        } else {
                            System.out.println("Invalid input. Programme duration cannot be 0.");
                        }
                    }

                    boolean successful = ManageProgramme.addProgramme(programmeCode, programmeName, duration, facultyInput);
                    if (successful) {
                        System.out.println("You are successfully added a programme.");
                    }
                    continue;
                case 3://remove course
                    System.out.println("\nAvailable Courses:");
                    for (int i = 0; i < courseList.size(); i++) {
                        System.out.println((i + 1) + ". " + courseList.get(i).getName());
                    }
                    System.out.print("Enter the number of the course you want to remove: ");
                    int courseNumToRemove = scanner.nextInt();
                    scanner.nextLine();

                    boolean remove = removeCourse(courseNumToRemove);
                    if (remove) {
                        System.out.println("You have removed successfully, this course will also remove from programmes.");
                    } else {
                        System.out.println("Failed to remove course.");
                    }
                    continue;

                case 4://remove programme
                    System.out.println("Available Programme:");
                    for (int i = 0; i < programmeList.size(); i++) {
                        System.out.println((i + 1) + ". " + programmeList.get(i).getProgrammeName());
                    }
                    System.out.print("Enter the number of the programme you want to remove: ");
                    int proNumToBeRemove = scanner.nextInt();
                    scanner.nextLine();
                    boolean removePro = removeProgramme(proNumToBeRemove);
                    if (removePro) {
                        System.out.println("You have removed successfully, this programme will also remove from courses.");
                    } else {
                        System.out.println("Failed to remove programme.");
                    }
                    continue;

                case 5://Add Programme to Courses
                    boolean loopPro = false;
                    while (!loopPro) {
                        System.out.println();
                        System.out.println("Available Programmes:");
                        for (int i = 0; i < programmeList.size(); i++) {
                            System.out.println((i + 1) + ". " + programmeList.get(i).getProgrammeName());
                        }
                        System.out.print("Enter the number of the programme you want to add: ");
                        int proNum = scanner.nextInt();
                        scanner.nextLine();

                        if (proNum > 0 && proNum <= programmeList.size()) {
                            String programme = programmeList.get(proNum - 1).getProgrammeName();
                            boolean addMoreCourses = true;
                            while (addMoreCourses) {
                                System.out.println("Available Courses:");
                                for (int i = 0; i < courseList.size(); i++) {
                                    System.out.println((i + 1) + ". " + courseList.get(i).getName());
                                }
                                System.out.print("Enter the number of the course you want to add to the programme: ");
                                int courseAddToPro = scanner.nextInt();
                                scanner.nextLine(); // Consume newline character

                                if (courseAddToPro > 0 && courseAddToPro <= courseList.size()) {
                                    Course course = courseList.get(courseAddToPro - 1);
                                    boolean success = addProgrammeToCourse(programme, course);
                                    if (success) {
                                        System.out.println("Programme successfully added to Course.");
                                    }
                                } else {
                                    System.out.println("Invalid course number.");
                                }
                                System.out.print("Do you want to add more to another courses? (Y/N): ");
                                char addAnotherCourseInput = scanner.nextLine().toUpperCase().charAt(0);
                                addMoreCourses = (addAnotherCourseInput == 'Y');
                            }
                        } else {
                            System.out.println("Invalid programme number.");
                        }
                        System.out.print("Return to main menu? (Y/N): ");
                        char returnToMainMenu = scanner.nextLine().toUpperCase().charAt(0);
                        loopPro = (returnToMainMenu != 'N');
                    }
                    continue;

                case 6:
                    // add new course to programmes
                    boolean loopToAddCourse = true;
                    while (loopToAddCourse) {
                        ListInterface<Course> availableCourses = availableCourses();
                        if (!availableCourses.isEmpty()) {
                            System.out.println();
                            System.out.println("Available Courses:");
                            for (int i = 0; i < availableCourses.size(); i++) {
                                System.out.println((i + 1) + ". " + availableCourses.get(i).getName());
                            }
                            System.out.print("Enter the number of the course you want to add to a programme: ");
                            int courseNumAddToProgramme = scanner.nextInt();
                            scanner.nextLine(); // Consume newline character
                            if (courseNumAddToProgramme > 0 && courseNumAddToProgramme <= availableCourses.size()) {
                                Course newCourse = availableCourses.get(courseNumAddToProgramme - 1);
                                boolean addMoreProgrammes = true;
                                while (addMoreProgrammes) {
                                    System.out.println("Available Programmes:");
                                    for (int i = 0; i < programmeList.size(); i++) {
                                        System.out.println((i + 1) + ". " + programmeList.get(i).getProgrammeName());
                                    }
                                    System.out.print("Enter the number of the programme you want to add the course to: ");
                                    int programmeNumToAddCourse = scanner.nextInt();
                                    scanner.nextLine(); // Consume newline character
                                    if (programmeNumToAddCourse > 0 && programmeNumToAddCourse <= programmeList.size()) {
                                        String selectedProgramme = programmeList.get(programmeNumToAddCourse - 1).getProgrammeName();
                                        boolean success = addProgrammeToCourse(selectedProgramme, newCourse);
                                        if (success) {
                                            System.out.println("Course added to the programme successfully.");
                                        }
                                    } else {
                                        System.out.println("Invalid programme number.");
                                    }
                                    System.out.print("Do you want to add more to another programme? (Y/N): ");
                                    char addAnotherCourseInput = scanner.nextLine().toUpperCase().charAt(0);
                                    addMoreProgrammes = (addAnotherCourseInput == 'Y');
                                }
                            } else {
                                System.out.println("Invalid course number.");
                            }
                            System.out.print("Return to main menu? (Y/N): ");
                            char returnToMainMenu = scanner.nextLine().toUpperCase().charAt(0);
                            loopToAddCourse = (returnToMainMenu == 'N');
                        }
                    }
                    continue;
                case 7:

                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println(
                            "Invalid choice. Please enter a valid option.");

                    break;
            }

            break;
        }

        scanner.close();
            
     }
}
