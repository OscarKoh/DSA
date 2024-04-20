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
            System.out.println("13. Generate Courses Summary Report");
            System.out.println("14. Generate Programmes Summary Report");
            System.out.println("0. Exit");
            while (true) {
                try {
                    System.out.print("Enter your choice: ");
                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice) {
                        case 1: // Add new course
                            String code,
                             name;
                            int creditHour;
                            double fee;
                            while (true) {
                                try {
                                    System.out.print("Enter course code: ");
                                    code = scanner.nextLine();
                                    if (code.isEmpty()) {
                                        throw new IllegalArgumentException("Course code cannot be empty.");
                                    }
                                    if (!isValidCourseCode(code)) {
                                        throw new IllegalArgumentException("Course code must be 4 alphabets followed by 4 digits.");
                                    }
                                    break;
                                } catch (IllegalArgumentException e) {
                                    System.out.println("Invalid input: " + e.getMessage());
                                }
                            }

                            while (true) {
                                try {
                                    System.out.print("Enter course name: ");
                                    name = scanner.nextLine();
                                    if (name.isEmpty()) {
                                        throw new IllegalArgumentException("Course name cannot be empty.");
                                    }
                                    break;
                                } catch (IllegalArgumentException e) {
                                    System.out.println("Invalid input: " + e.getMessage());
                                }
                            }

                            ArrayList<Course.CourseType> typesList = new ArrayList<>();
                            while (true) {
                                try {
                                    System.out.print("Enter course types (comma-separated): ");
                                    String input = scanner.nextLine();
                                    String[] inputArray = input.split(",");
                                    for (String type : inputArray) {
                                        try {
                                            Course.CourseType courseType = Course.CourseType.valueOf(type.trim().toUpperCase());
                                            typesList.add(courseType);
                                        } catch (IllegalArgumentException e) {
                                            throw new IllegalArgumentException("Invalid input. Course type must be within lecture, tutorial, or practical.");
                                        }
                                    }
                                    break;
                                } catch (IllegalArgumentException e) {
                                    System.out.println("Invalid input: " + e.getMessage());
                                }
                            }

                            while (true) {
                                try {
                                    System.out.print("Enter credit hour: ");
                                    creditHour = scanner.nextInt();
                                    scanner.nextLine(); // Consume newline character
                                    if (creditHour <= 0) {
                                        throw new IllegalArgumentException("Course credit hour must be a positive integer.");
                                    }
                                    break;
                                } catch (InputMismatchException e) {
                                    System.out.println("Invalid input. Please enter a valid integer.");
                                    scanner.nextLine(); // Clear the invalid input
                                }
                            }

                            while (true) {
                                try {
                                    System.out.print("Enter fee of course: ");
                                    fee = scanner.nextDouble();
                                    scanner.nextLine();
                                    if (fee <= 0) {
                                        throw new IllegalArgumentException("Course fee must be a positive number.");
                                    }
                                    break;
                                } catch (InputMismatchException e) {
                                    System.out.println("Invalid input. Please enter a valid number.");
                                    scanner.nextLine(); // Clear the invalid input
                                }
                            }

                            boolean added = ManageCourse.addCourse(code, name, creditHour, typesList, fee);
                            if (added) {
                                System.out.println("You have successfully added a new course.");
                            }
                            break;

                        case 2: // Add new programme
                            String programmeCode,
                             programmeName,
                             facultyInput;
                            int duration,
                             totalCreditHours;
                            while (true) {
                                try {
                                    System.out.print("Enter programme code: ");
                                    programmeCode = scanner.nextLine();
                                    if (programmeCode.isEmpty()) {
                                        throw new IllegalArgumentException("Programme code cannot be empty.");
                                    }
                                    if (!isValidProgrammeCode(programmeCode)) {
                                        throw new IllegalArgumentException("Programme code must be 3 alphabets only.");
                                    }
                                    break;
                                } catch (IllegalArgumentException e) {
                                    System.out.println("Invalid input: " + e.getMessage());
                                }
                            }

                            while (true) {
                                try {
                                    System.out.print("Enter programme name: ");
                                    programmeName = scanner.nextLine();
                                    if (programmeName.isEmpty()) {
                                        throw new IllegalArgumentException("Programme name cannot be empty.");
                                    }
                                    break;
                                } catch (IllegalArgumentException e) {
                                    System.out.println("Invalid input: " + e.getMessage());
                                }
                            }

                            while (true) {
                                try {
                                    System.out.print("Enter faculty: ");
                                    facultyInput = scanner.nextLine();
                                    if (facultyInput.isEmpty()) {
                                        throw new IllegalArgumentException("Faculty cannot be empty.");
                                    }
                                    if (!isValidFaculty(facultyInput)) {
                                        throw new IllegalArgumentException("Faculty must be 4 alphabets only.");
                                    }
                                    break;
                                } catch (IllegalArgumentException e) {
                                    System.out.println("Invalid input: " + e.getMessage());
                                }
                            }

                            while (true) {
                                try {
                                    System.out.print("Enter duration in months: ");
                                    duration = scanner.nextInt();
                                    scanner.nextLine();
                                    if (duration <= 0) {
                                        throw new IllegalArgumentException("Programme duration must be a positive value.");
                                    }
                                    break;
                                } catch (InputMismatchException e) {
                                    System.out.println("Invalid input. Please enter a valid integer.");
                                    scanner.nextLine(); // Clear the invalid input
                                }
                            }

                            while (true) {
                                try {
                                    System.out.print("Enter maximum total credit hours of programme: ");
                                    totalCreditHours = scanner.nextInt();
                                    scanner.nextLine();
                                    if (totalCreditHours <= 0) {
                                        throw new IllegalArgumentException("Programme total credit hours must be a positive value.");
                                    }
                                    break;
                                } catch (InputMismatchException e) {
                                    System.out.println("Invalid input. Please enter a valid integer.");
                                    scanner.nextLine(); // Clear the invalid input
                                }
                            }

                            boolean successful = ManageProgramme.addProgramme(programmeCode, programmeName, duration, facultyInput, totalCreditHours);
                            if (successful) {
                                System.out.println("You have successfully added a programme.");
                            }
                            break;

                        case 3:
                            // Remove course
                            System.out.println("\nAvailable Courses:");
                            for (int i = 0; i < courseList.size(); i++) {
                                System.out.println((i + 1) + ". " + courseList.get(i).getName());
                            }

                            int courseNumToRemove;
                            while (true) {
                                System.out.print("Enter the number of the course you want to remove: ");
                                try {
                                    courseNumToRemove = scanner.nextInt();
                                    scanner.nextLine();

                                    if (courseNumToRemove < 1 || courseNumToRemove > courseList.size()) {
                                        System.out.println("Invalid course number.");
                                        continue;
                                    }
                                    break;
                                } catch (InputMismatchException e) {
                                    System.out.println("Invalid input. Please enter a valid integer.");
                                    scanner.nextLine(); // Clear the invalid input
                                }
                            }

                            boolean remove = removeCourse(courseNumToRemove);
                            if (remove) {
                                System.out.println("You have removed the course successfully, and it has also been removed from programmes.");
                            } else {
                                System.out.println("Failed to remove the course.");
                            }
                            break;

                        case 4:
                            // Remove programme
                            System.out.println("Available Programmes:");
                            for (int i = 0; i < programmeList.size(); i++) {
                                System.out.println((i + 1) + ". " + programmeList.get(i).getProgrammeName());
                            }
                            int proNumToBeRemove;
                            while (true) {
                                System.out.print("Enter the number of the programme you want to remove: ");
                                try {
                                    proNumToBeRemove = scanner.nextInt();
                                    scanner.nextLine();

                                    if (proNumToBeRemove < 1 || proNumToBeRemove > programmeList.size()) {
                                        System.out.println("Invalid programme number.");
                                        continue; // Restart the loop
                                    }
                                    break;
                                } catch (InputMismatchException e) {
                                    System.out.println("Invalid input. Please enter a valid integer.");
                                    scanner.nextLine(); // Clear the invalid input
                                }
                            }
                            boolean removePro = removeProgramme(proNumToBeRemove);
                            if (removePro) {
                                System.out.println("You have removed the programme successfully, and it has also been removed from courses.");
                            } else {
                                System.out.println("Failed to remove the programme.");
                            }

                            break;

                        case 5://Add Programme to Courses
                            boolean loopPro = false;
                            while (!loopPro) {
                                System.out.println();
                                System.out.println("Available Programmes:");
                                for (int i = 0; i < programmeList.size(); i++) {
                                    System.out.println((i + 1) + ". " + programmeList.get(i).getProgrammeName());
                                }
                                while (true) {
                                    System.out.print("Enter the number of the programme you want to add: ");
                                    try {
                                        int proNum = scanner.nextInt();
                                        scanner.nextLine();

                                        if (proNum > 0 && proNum <= programmeList.size()) {
                                            Programme programme = programmeList.get(proNum - 1);
                                            boolean addMoreCourses = true;
                                            System.out.println("Available Courses:");
                                            for (int i = 0; i < courseList.size(); i++) {
                                                System.out.println((i + 1) + ". " + courseList.get(i).getName());
                                            }
                                            while (addMoreCourses) {
                                                System.out.print("Enter the number of the course you want to add to the programme: ");
                                                try {
                                                    int courseAddToPro = scanner.nextInt();
                                                    scanner.nextLine(); // Consume newline character

                                                    if (courseAddToPro > 0 && courseAddToPro <= courseList.size()) {
                                                        Course course = courseList.get(courseAddToPro - 1);
                                                        boolean success = addProgrammeToCourse(programme, course);
                                                        if (success) {
                                                            System.out.println("Programme " + programme.getProgrammeName() + " successfully added to course " + course.getName() + "\n");
                                                        }
                                                    } else {
                                                        System.out.println("Invalid course number.");
                                                        continue;
                                                    }
                                                } catch (InputMismatchException e) {
                                                    System.out.println("Invalid input. Please enter a valid integer.");
                                                    scanner.nextLine(); // Clear the invalid input
                                                    continue;
                                                }
                                                System.out.print("Do you want to add more to another courses? (Y/N): ");
                                                char addAnotherCourseInput = scanner.nextLine().toUpperCase().charAt(0);
                                                addMoreCourses = (addAnotherCourseInput == 'Y');
                                            }
                                        } else {
                                            System.out.println("Invalid programme number.");
                                            continue;
                                        }
                                    } catch (InputMismatchException e) {
                                        System.out.println("Invalid input. Please enter a valid integer.");
                                        scanner.nextLine(); // Clear the invalid input
                                        continue;
                                    }
                                    System.out.print("Return to main menu? (Y/N): ");
                                    char returnToMainMenu = scanner.nextLine().toUpperCase().charAt(0);
                                    loopPro = (returnToMainMenu != 'N');
                                    break;
                                }
                            }
                            break;

                        case 6:
                            // Add new course to programmes
                            boolean loopToAddCourse = true;
                            ListInterface<Course> availableCourses = availableCourses();
                            if (!availableCourses.isEmpty()) {
                                while (loopToAddCourse) {
                                    System.out.println();
                                    System.out.println("Available Courses:");
                                    for (int i = 0; i < availableCourses.size(); i++) {
                                        System.out.println((i + 1) + ". " + availableCourses.get(i).getName());
                                    }
                                    while (true) {
                                        System.out.print("Enter the number of the course you want to add to a programme: ");
                                        try {
                                            int courseNumAddToProgramme = scanner.nextInt();
                                            scanner.nextLine();

                                            if (courseNumAddToProgramme > 0 && courseNumAddToProgramme <= availableCourses.size()) {
                                                Course newCourse = availableCourses.get(courseNumAddToProgramme - 1);
                                                boolean addMoreProgrammes = true;
                                                System.out.println("Available Programmes:");
                                                for (int i = 0; i < programmeList.size(); i++) {
                                                    System.out.println((i + 1) + ". " + programmeList.get(i).getProgrammeName());
                                                }
                                                while (addMoreProgrammes) {
                                                    System.out.print("Enter the number of the programme you want to add the course to: ");
                                                    try {
                                                        int programmeNumToAddCourse = scanner.nextInt();
                                                        scanner.nextLine();

                                                        if (programmeNumToAddCourse > 0 && programmeNumToAddCourse <= programmeList.size()) {
                                                            Programme selectedProgramme = programmeList.get(programmeNumToAddCourse - 1);
                                                            boolean success = addProgrammeToCourse(selectedProgramme, newCourse);
                                                            if (success) {
                                                                System.out.println("Programme " + selectedProgramme.getProgrammeName() + " successfully added to course " + newCourse.getName() + "\n");
                                                            }
                                                        } else {
                                                            System.out.println("Invalid programme number.");
                                                            continue;
                                                        }
                                                    } catch (InputMismatchException e) {
                                                        System.out.println("Invalid input. Please enter a valid integer.");
                                                        scanner.nextLine(); // Clear the invalid input
                                                        continue;
                                                    }
                                                    System.out.print("Do you want to add more to another programme? (Y/N): ");
                                                    char addAnotherCourseInput = scanner.nextLine().toUpperCase().charAt(0);
                                                    addMoreProgrammes = (addAnotherCourseInput == 'Y');
                                                }
                                                break; // Exit the loop for adding courses to programmes
                                            } else {
                                                System.out.println("Invalid course number.");
                                            }
                                        } catch (InputMismatchException e) {
                                            System.out.println("Invalid input. Please enter a valid integer.");
                                            scanner.nextLine(); // Clear the invalid input
                                        }
                                    }
                                    System.out.print("Return to main menu? (Y/N): ");
                                    char returnToMainMenu = scanner.nextLine().toUpperCase().charAt(0);
                                    loopToAddCourse = (returnToMainMenu == 'N');
                                    if (!loopToAddCourse) {
                                        break; // Exit the loop for returning to the main menu
                                    }
                                }
                            } else {
                                System.out.println("There are no new courses.");
                            }
                            break;

                        case 7: // Remove programme from course
                            boolean loopCourse = true;
                            System.out.println("Available Courses:");

                            for (int i = 0; i < courseList.size(); i++) {
                                System.out.println((i + 1) + ". " + courseList.get(i).getName());
                            }

                            while (loopCourse) {
                                try {
                                    System.out.print("Enter the number of the course you want to remove a programme from: ");
                                    int courseNumToRemovePro = scanner.nextInt();
                                    scanner.nextLine();

                                    if (courseNumToRemovePro > 0 && courseNumToRemovePro <= courseList.size()) {
                                        Course courseToRemoveProgramme = courseList.get(courseNumToRemovePro - 1);
                                        if (!courseToRemoveProgramme.getProgrammesList().isEmpty()) {
                                            boolean loopProgramme = true;
                                            System.out.println("\nAvailable Programmes for " + courseToRemoveProgramme.getName() + ":");
                                            for (int i = 0; i < courseToRemoveProgramme.getProgrammesList().size(); i++) {
                                                System.out.println((i + 1) + ". " + courseToRemoveProgramme.getProgrammesList().get(i).getProgrammeName());
                                            }
                                            while (loopProgramme) {
                                                try {
                                                    System.out.print("Enter the number of the programme you want to remove: ");
                                                    int programmeNumberToRemove = scanner.nextInt();
                                                    scanner.nextLine();

                                                    if (programmeNumberToRemove > 0 && programmeNumberToRemove <= courseToRemoveProgramme.getProgrammesList().size()) {
                                                        removeProgrammeFromCourse(programmeNumberToRemove, courseNumToRemovePro);
                                                        loopProgramme = false;
                                                        loopCourse = false;
                                                    } else {
                                                        System.out.println("Invalid programme number.");
                                                    }
                                                } catch (InputMismatchException e) {
                                                    System.out.println("Invalid input. Please enter a valid integer.");
                                                    scanner.nextLine(); // Consume invalid input
                                                }
                                            }
                                        } else {
                                            System.out.println("There is no programme for this course.\n");
                                            break; // Exit loopCourse while loop
                                        }
                                    } else {
                                        System.out.println("Invalid course number.");
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("Invalid input. Please enter a valid integer.");
                                    scanner.nextLine(); // Consume invalid input
                                }
                            }
                            break;

                        case 8: // Remove course from programme
                            boolean loopMore = true;

                            System.out.println("Available Programmes:");
                            for (int i = 0; i < programmeList.size(); i++) {
                                System.out.println((i + 1) + ". " + programmeList.get(i).getProgrammeName());
                            }
                            while (loopMore) {
                                try {
                                    System.out.print("Enter the number of the programme you want to remove a course from: ");
                                    int programNum = scanner.nextInt();
                                    scanner.nextLine();

                                    if (programNum > 0 && programNum <= programmeList.size()) {
                                        Programme pro = programmeList.get(programNum - 1);
                                        if (!pro.getCourseList().isEmpty()) {
                                            boolean loopProgramme = true;
                                            System.out.println("\nAvailable Courses for " + pro.getProgrammeName() + ":");
                                            for (int i = 0; i < pro.getCourseList().size(); i++) {
                                                System.out.println((i + 1) + ". " + pro.getCourseList().get(i).getName());
                                            }
                                            while (loopProgramme) {
                                                try {
                                                    System.out.print("Enter the number of the course you want to remove: ");
                                                    int courseNumberToRemove = scanner.nextInt();
                                                    scanner.nextLine();

                                                    if (courseNumberToRemove > 0 && courseNumberToRemove <= pro.getCourseList().size()) {
                                                        ManageProgramme.removeCourseProgramme(courseNumberToRemove, programNum);
                                                        loopMore = false;
                                                        loopProgramme = false;
                                                    } else {
                                                        System.out.println("Invalid course number.");
                                                    }
                                                } catch (InputMismatchException e) {
                                                    System.out.println("Invalid input. Please enter a valid integer.");
                                                    scanner.nextLine(); // Consume invalid input
                                                }
                                            }
                                        } else {
                                            System.out.println("There are no courses for this programme.\n");
                                            break; // Exit loopMore while loop
                                        }
                                    } else {
                                        System.out.println("Invalid programme number.");
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("Invalid input. Please enter a valid integer.");
                                    scanner.nextLine(); // Consume invalid input
                                }
                            }
                            break;

                        case 9: // Amend course details
                            boolean loop = true;

                            System.out.println();
                            System.out.println("Available Programmes:");
                            for (int i = 0; i < programmeList.size(); i++) {
                                System.out.println((i + 1) + ". " + programmeList.get(i).getProgrammeName());
                            }
                            while (loop) {
                                System.out.print("Enter the number of the programme you want to select: ");
                                int programChoice;

                                try {
                                    programChoice = scanner.nextInt();
                                    scanner.nextLine(); // Consume newline character
                                } catch (InputMismatchException e) {
                                    System.out.println("Invalid input. Please enter a valid number.");
                                    scanner.nextLine(); // Clear the invalid input
                                    continue;
                                }

                                if (programChoice > 0 && programChoice <= programmeList.size()) {
                                    Programme selectedProgramme = programmeList.get(programChoice - 1);
                                    ListInterface<Course> coursesForProgram = selectedProgramme.getCourseList();
                                    if (!coursesForProgram.isEmpty()) {
                                        // Display available courses for the selected program
                                        System.out.println("Available Courses for " + selectedProgramme.getProgrammeName() + ":");
                                        for (int i = 0; i < coursesForProgram.size(); i++) {
                                            System.out.println((i + 1) + ". " + coursesForProgram.get(i).getName());
                                        }
                                        boolean courseLoop = true;
                                        while (courseLoop) {
                                            System.out.print("Enter the number of the course you want to amend: ");
                                            int courseNumberToAmend;
                                            try {
                                                courseNumberToAmend = scanner.nextInt();
                                                scanner.nextLine(); // Consume newline character
                                            } catch (InputMismatchException e) {
                                                System.out.println("Invalid input. Please enter a valid number.");
                                                scanner.nextLine(); // Clear the invalid input
                                                continue;
                                            }

                                            if (courseNumberToAmend > 0 && courseNumberToAmend <= coursesForProgram.size()) {
                                                Course courseToAmend = coursesForProgram.get(courseNumberToAmend - 1);
                                                displayCourseDetails(courseToAmend);
                                                // Prompt user for new values
                                                // Validate and prompt for new course code
                                                String newCode;
                                                do {
                                                    System.out.print("Enter new course code (press Enter to keep current value): ");
                                                    newCode = scanner.nextLine().trim();
                                                    if (!newCode.isEmpty() && !isValidCourseCode(newCode)) {
                                                        System.out.println("Invalid course code. Course code must be four alphabets followed by four digits.");
                                                    }
                                                    if (newCode.isEmpty()) {
                                                        newCode = courseToAmend.getCode(); // Keep the current value
                                                    }
                                                } while (!isValidCourseCode(newCode));

                                                // Prompt for new course name
                                                System.out.print("Enter new course name (press Enter to keep current value): ");
                                                String newName = scanner.nextLine();
                                                if (newName.isEmpty()) {
                                                    newName = courseToAmend.getName(); // Keep the current value
                                                }

                                                // Prompt for new course types
                                                String newTypesInput;
                                                ArrayList<Course.CourseType> newTypesList;
                                                boolean invalidType;
                                                do {
                                                    System.out.print("Enter new course types (comma-separated, press Enter to keep current value): ");
                                                    newTypesInput = scanner.nextLine().trim();
                                                    newTypesList = ManageCourse.courseType(newTypesInput, courseToAmend);

                                                    // Check if any invalid course types were entered
                                                    invalidType = false;
                                                    if (newTypesList.isEmpty()) {
                                                        invalidType = true;
                                                        System.out.println("Invalid course types. Please enter valid course types.");
                                                    }

                                                } while (invalidType);

                                                // Prompt for new credit hours
                                                int newCreditHours;
                                                do {
                                                    System.out.print("Enter new credit hours (press Enter to keep current value): ");
                                                    try {
                                                        String creditHoursInput = scanner.nextLine().trim();
                                                        if (creditHoursInput.isEmpty()) {
                                                            newCreditHours = courseToAmend.getCreditHour(); // Keep the current value
                                                            break;
                                                        }
                                                        newCreditHours = Integer.parseInt(creditHoursInput);
                                                        if (newCreditHours <= 0) {
                                                            System.out.println("Credit hours must be a positive integer.");
                                                        }
                                                    } catch (InputMismatchException e) {
                                                        System.out.println("Invalid input. Please enter a valid integer value.");
                                                        newCreditHours = -1;
                                                    }
                                                } while (newCreditHours <= 0);

                                                // Prompt for new fee
                                                double newFee;
                                                do {
                                                    System.out.print("Enter new fee (press Enter to keep current value): ");
                                                    try {
                                                        String feeInput = scanner.nextLine().trim();
                                                        if (feeInput.isEmpty()) {
                                                            newFee = courseToAmend.getFee(); // Keep the current value
                                                            break;
                                                        }
                                                        newFee = Double.parseDouble(feeInput);
                                                        if (newFee < 0) {
                                                            System.out.println("Fee cannot be negative.");
                                                        }
                                                    } catch (InputMismatchException e) {
                                                        System.out.println("Invalid input. Please enter a valid numeric value.");
                                                        newFee = -1;
                                                    }
                                                } while (newFee < 0);

                                                // Amend the course details
                                                amendCourseDetails(selectedProgramme, courseNumberToAmend, newCode, newName, newTypesList, newCreditHours, newFee);
                                                courseLoop = false;
                                                loop = false;
                                            } else {
                                                System.out.println("Invalid course number.");
                                            }
                                        }
                                    } else {
                                        System.out.println("There are no courses available for this programme.\n");
                                        break;
                                    }
                                } else {
                                    System.out.println("Invalid programme choice.");
                                }
                            }
                            break;

                        case 10:
                            // List all courses for a Programme
                            boolean loopAgain = true;

                            System.out.println("Available Programmes:");
                            for (int i = 0; i < programmeList.size(); i++) {
                                System.out.println((i + 1) + ". " + programmeList.get(i).getProgrammeName());
                            }
                            while (loopAgain) {
                                try {
                                    System.out.print("Enter the number of the programme you want to list courses for: ");
                                    int progNum = scanner.nextInt();
                                    if (progNum > 0 && progNum <= programmeList.size()) {
                                        Programme selectedProgramme = programmeList.get(progNum - 1);
                                        String result = listAllCoursesForProgramme(selectedProgramme);
                                        System.out.println(result); // Print the result returned by listAllCoursesForProgramme
                                        loopAgain = false;
                                    } else {
                                        throw new IllegalArgumentException("Invalid programme number.");
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("Invalid input. Please enter a valid integer.");
                                    scanner.nextLine(); // Clear the invalid input
                                } catch (IllegalArgumentException e) {
                                    System.out.println(e.getMessage());
                                }
                            }
                            break;

                        case 11:
                            while (true) {
                                try {
                                    System.out.print("Enter the course codes separated by commas (e.g. BAIT6789):");
                                    String input = scanner.nextLine().toUpperCase();

                                    if (input.trim().isEmpty()) {
                                        throw new IllegalArgumentException("Error: Input cannot be empty.");
                                    }

                                    String[] courseCodes = input.split(",");
                                    Iterator<String> iterator = Arrays.asList(courseCodes).iterator(); // Convert array to list and get iterator

                                    while (iterator.hasNext()) {
                                        String courseCode = iterator.next().trim(); // Trim to remove whitespace
                                        Course course = ManageCourse.findCourse(courseCode);
                                        if (course != null) {
                                            ManageCourse.displayCourseDetails(course);
                                            System.out.println();
                                        } else {
                                            System.out.println("Course with code " + courseCode + " not found.");
                                        }
                                    }
                                    break;
                                } catch (IllegalArgumentException e) {
                                    System.out.println(e.getMessage());
                                }
                            }
                            break;
                            
                        case 12:
                            System.out.print("Enter faculty:");
                            String faculty = scanner.nextLine();
                            String coursesTakenByFaculty = listCoursesTakenByFaculties(faculty);
                            System.out.println(coursesTakenByFaculty);
                            break;
                            
                        case 13:
                            while (true) {
                                try {
                                    System.out.print("Enter the minimum number of credit hour to include in the report: ");
                                    int minCreditHour = scanner.nextInt();

                                    scanner.nextLine(); // Consume newline character
                                    System.out.println(summaryReport(minCreditHour));
                                    break;
                                } catch (InputMismatchException e) {
                                    System.out.println("Invalid input. Please enter a valid integer.");
                                    scanner.nextLine(); // Clear the invalid input
                                }
                            }
                            break;
                        case 14:
                            while (true) {
                                try {
                                    System.out.print("Enter the minimum total fee of a programme to include in the report: ");
                                    double totalFee = scanner.nextDouble();
                                    scanner.nextLine(); // Consume newline character
                                    System.out.println(prosummaryReport(totalFee));
                                    break;
                                } catch (InputMismatchException e) {
                                    System.out.println("Invalid input. Please enter a valid number.");
                                    scanner.nextLine(); // Clear the invalid input
                                }
                            }
                            break;
                        case 0:
                            exits = true;
                            if (exits) {
                                return; // Return from the method to go back to the main menu
                            }
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
