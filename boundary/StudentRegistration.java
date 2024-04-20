/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

//Ow Yong Qing
import entity.Course;
import entity.RegisteredCourse;
import entity.Student;
import adt.ListInterface;
import java.util.Scanner;
import control.ManageStudent;
import control.ManageRegistered;
import static control.ManageStudent.validateName;
import static control.ManageStudent.validateInputCN;
import static control.ManageStudent.validateInputIC;
import static control.ManageStudent.validateProgrammeCode;
import entity.Programme;
import control.ManageCourse;
import control.ManageProgramme;
import static control.ManageRegistered.removeRegisteredCourse;
import static control.ManageStudent.addStudent;
import java.util.InputMismatchException;

public class StudentRegistration {

    public static void studentRegistration() {
        Scanner scanner = new Scanner(System.in);
        ListInterface<Student> studentList = ManageStudent.getStudentList();
        ListInterface<Course> courseList = ManageCourse.getCourseList();

        ListInterface<RegisteredCourse> registeredList = ManageRegistered.getRegisteredList();
        ListInterface<Programme> programmeList = ManageProgramme.getProgrammeList();

        boolean running = true;
        while (running) {
            System.out.println("\n=====================================");
            System.out.println("    Student Registration Management    ");
            System.out.println("======================================");
            System.out.println("1.  Add new Student");
            System.out.println("2.  Remove a student");
            System.out.println("3.  View Student List");
            System.out.println("4.  Amend student details");
            System.out.println("5.  Search student for registered courses");
            System.out.println("6.  Add student to few courses");
            System.out.println("7.  Remove student from a course");
            System.out.println("8.  Student Bill");
//            System.out.println("9.  Maximum Credit Hours");
            System.out.println("9.  Summary Report Students In Programme");
            System.out.println("10. Summary Report Students In Course");
            System.out.println("0.  Exit");

            System.out.print("Enter your option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    //add student
                    int id;
                    String name,
                     IC,
                     contact_number,
                     programme;

                    while (true) {
                        System.out.println("\nAdding a new student...");
                        id = ManageStudent.getNextStudentId();

                        // Prompt for name
                        while (true) {
                            System.out.print("Enter name: ");
                            name = scanner.nextLine();

                            // Validate name (if needed)
                            if (!validateName(name)) {
                                System.out.println("Invalid name. Please try again.");
                                continue;
                            }
                            break;
                        }

                        // Prompt for IC
                        while (true) {
                            System.out.print("Enter IC: ");
                            IC = scanner.nextLine();

                            if (!validateInputIC(IC)) {
                                System.out.println("Invalid IC. Please try again.");
                                continue;
                            }
                            break;
                        }

                        //prompt for contact number
                        while (true) {
                            System.out.print("Enter contact number: ");
                            contact_number = scanner.nextLine();

                            if (!validateInputCN(contact_number)) {
                                System.out.println("Invalid contact number. Please try again.");
                                continue;
                            }
                            break;
                        }

                        System.out.println("Available programs:");
                        for (int i = 0; i < programmeList.size(); i++) {
                            Programme Programme = programmeList.get(i);
                            System.out.println(Programme.getProgrammeCode() + " - " + Programme.getProgrammeName());
                        }

                        // Prompt for programme code
                        System.out.println("Enter programme code: ");
                        programme = scanner.nextLine();

                        if (!validateProgrammeCode(programme, programmeList)) {
                            System.out.println("Invalid programme code. Please try again.");
                            continue;
                        }

                        // Create and return a new Student object
                        boolean success = addStudent(id, name, IC, contact_number, programme);
                        if (success) {
                            System.out.println("-------------------");
                            System.out.println("Insert Successfully");
                            System.out.println("-------------------");
                        }

                        break;
                    }
                    break;

                case 2:
                    //remove student
                    System.out.println("Available Student: ");
                    for (int i = 0; i < studentList.size(); i++) {
                        Student student = studentList.get(i);
                        System.out.println(student.getStudentID() + "\t" + student.getName());
                    }
                    int studentIdToRemove;
                    System.out.println("\nRemoving a student...");
                    System.out.print("Enter the student ID to remove: ");

                    studentIdToRemove = scanner.nextInt();
                    scanner.nextLine();
                    boolean removed = ManageStudent.removeStudent(studentIdToRemove);
                    if (removed) {
                        System.out.println("-----------------------------");
                        System.out.println("Student successfully removed.");
                        System.out.println("-----------------------------");
                    } else {
                        System.out.println("Student not found or could not be removed.");
                    }

                    break;

                case 3:
                    // View Student List
                    System.out.println("\nStudent List: ");
                    System.out.println("ID\tName\t\t\tIC\t\tContact Number\t\tProgramme");

                    for (int i = 0; i < studentList.size(); i++) {
                        //if programmme = programmeList.get(programmeChoice - 1)
                        //if student's programme equalTo programmeChoice 
                        // then print out the student
                        Student student = studentList.get(i);
                        System.out.printf("%-6d%-20s%-20s%-25s%-16s%n", student.getStudentID(), student.getName(), student.getIC(), student.getContact_number(), student.getProgramme());
                    }
//                    for (Student student : studentList.toArray()) {
//                        System.out.println(student.getStudentID() + "\t" + student.getName() + "\t" + student.getIC() + "\t" + student.getContact_number() + "\t" + student.getProgramme());
//                    }
                    break;

                case 4:
                    // Amend student details
                    System.out.println("\nAmending student details...");
                    System.out.println("Enter the student ID to amend details: ");
                    int studentIdToAmend = scanner.nextInt();
                    scanner.nextLine();

                    // Search for the student by ID
                    int index = -1;
                    for (int i = 0; i < studentList.size(); i++) {
                        if (studentList.get(i).getStudentID() == studentIdToAmend) {
                            index = i;
                            break;
                        }
                    }

                    if (index != -1) {
                        // Student found, proceed with amendment
                        Student studentToAmend = studentList.get(index);
                        System.out.println("Existing details: ");
                        System.out.println("Name: " + studentToAmend.getName());
                        System.out.println("IC: " + studentToAmend.getIC());
                        System.out.println("Contact Number: " + studentToAmend.getContact_number());

                        System.out.println("\nWhich detail do you want to amend?");
                        System.out.println("1. Name");
                        System.out.println("2. Contact Number");
                        System.out.println("Enter your choice: ");
                        int choice = scanner.nextInt();
                        scanner.nextLine(); // Consume newline character

                        boolean amend = false;
                        switch (choice) {
                            case 1:
                                // Amend name
                                boolean validateName = false;
                                while (!validateName) {
                                    System.out.println("\nOld name: " + studentToAmend.getName());
                                    System.out.println("\nEnter new name: ");
                                    String newName = scanner.nextLine().trim();
                                    if (!validateName(newName)) {
                                        System.out.println("Invalid name. Please try again.");
                                    } else {
                                        studentToAmend.setName(newName);
                                        amend = ManageStudent.amendStudentName(studentIdToAmend, newName);
                                        if (amend) {
                                            System.out.println("-------------------------------------");
                                            System.out.println("Student details successfully amended.");
                                            System.out.println("-------------------------------------");
                                            validateName = true; // Exit the loop if the name is valid
                                        } else {
                                            System.out.println("Failed to amend student details.");
                                        }
                                    }
                                }
                                break;
                            case 2:
                                // Amend contact number
                                boolean validContactNumber = false;
                                while (!validContactNumber) {
                                    System.out.println("\nOld contact number: " + studentToAmend.getContact_number());
                                    System.out.println("\nEnter new contact number: ");
                                    String newContactNumber = scanner.nextLine();

                                    // Validate the new contact number
                                    if (!validateInputCN(newContactNumber)) {
                                        System.out.println("Invalid contact number format. Please try again.");
                                    } else {
                                        amend = ManageStudent.amendStudentContact(studentIdToAmend, newContactNumber);
                                        if (amend) {
                                            System.out.println("-------------------------------------");
                                            System.out.println("Student details successfully amended.");
                                            System.out.println("-------------------------------------");
                                            validContactNumber = true; // Exit the loop if the contact number is valid
                                        } else {
                                            System.out.println("Failed to amend student details.");
                                        }
                                    }
                                }
                                break;
                            default:
                                System.out.println("Invalid choice.");
                                break;
                        }

                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 5:
                    //search student for registered courses
                    System.out.println("\nSearching for registered courses...");
                    for (int i = 0; i < studentList.size(); i++) {
                        Student stu = studentList.get(i);
                        System.out.println(stu.getStudentID() + " - " + stu.getName());
                    }
                    System.out.println("Enter the student ID to search for registered courses: ");
                    int studentIdToSearch = scanner.nextInt();
                    scanner.nextLine();

                    boolean exists = ManageRegistered.registerStudentCourse(studentIdToSearch);
                    if (exists) {
                        ListInterface<RegisteredCourse> courses = ManageRegistered.getRegisteredCoursesStudent(studentIdToSearch);
                        if (!courses.isEmpty()) {
                            System.out.println("Registered courses for student ID: " + studentIdToSearch + ": ");
                            for (int i = 0; i < courses.size(); i++) {
                                RegisteredCourse course = courses.get(i);
                                System.out.println("Course code: " + course.getCode() + ", Status: " + course.getStatus());
                            }
                        } else {
                            System.out.println("No registered courses found for the student.");
                        }
                    } else {
                        System.out.println("Student not found or not registered for any course.");
                    }
                    break;

                case 6:
                    // Add student to few courses
                    System.out.println("\nAdd student to courses...");
                    System.out.println("Programme List: ");
                    for (int i = 0; i < programmeList.size(); i++) {
                        Programme prog = programmeList.get(i);
                        System.out.println((i + 1) + ". " + prog.getProgrammeCode() + " - " + prog.getProgrammeName());
                    }

                    int programmeChoice;
                    while (true) {
                        try {
                            System.out.println("Select a programme(enter the index number): ");
                            programmeChoice = Integer.parseInt(scanner.nextLine());

                            if (programmeChoice < 1 || programmeChoice > programmeList.size()) {
                                System.out.println("Invalid program choice");
                                continue;
                            }
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a valid integer.");
                        }
                    }

                    Programme selectedProgramme = programmeList.get(programmeChoice - 1);

                    ListInterface<Course> availableCourses = selectedProgramme.getCourseList();
                    if (!availableCourses.isEmpty()) {
                        System.out.println("\nAvailable courses for " + selectedProgramme.getProgrammeName() + ": ");
                        for (int i = 0; i < availableCourses.size(); i++) {
                            Course course = availableCourses.get(i);
                            System.out.println((i + 1) + ". " + course.getCode() + " - " + course.getName());
                        }

                        ListInterface<Student> studentsInProgramme = selectedProgramme.getStudentList(); //here
                        //if (studentsInProgramme.isEmpty()) { //added 
                        //System.out.println("No registered found in programme."); //added
                        //} else { //added
                        System.out.println("\nStudents in " + selectedProgramme.getProgrammeName() + ": ");
                        for (int i = 0; i < studentsInProgramme.size(); i++) {
                            Student student = studentsInProgramme.get(i);
                            System.out.println((i + 1) + ". " + student.getName());
                        }
                        //}
                        //for (int i = 0; i < studentList.size(); i++) {
                        //Student student = studentList.get(i);
                        //System.out.println(student.getStudentID() + "\t" + student.getName() + "\t" + student.getIC() + "\t" + student.getContact_number() + "\t" + student.getProgramme());
                        //}
                        int studentChoice;
                        while (true) {
                            try {
                                System.out.println("Select a student(enter the index number): ");
                                studentChoice = Integer.parseInt(scanner.nextLine());

                                if (studentChoice < 1 || studentChoice > studentsInProgramme.size()) {
                                    System.out.println("Invalid student choice");
                                    continue;
                                }
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid input. Please enter a valid integer.");
                            }
                        }

                        Student selectedStudent = studentsInProgramme.get(studentChoice - 1);

                        while (true) {
                            System.out.println("\nEnter course code: ");
                            String courseCode = scanner.nextLine();

                            //validate course code
                            boolean isValidCourseCode = false;
                            for (int i = 0; i < availableCourses.size(); i++) {
                                Course course = availableCourses.get(i);
                                if (course.getCode().equalsIgnoreCase(courseCode)) {
                                    isValidCourseCode = true;
                                    break;
                                }
                            }

                            if (!isValidCourseCode) {
                                System.out.println("Invalid course code. Please enter a course from the available courses list.");
                                continue;
                            }

                            // Check if the student is already registered for the course
                            boolean alreadyRegisteredForCourse = false;
                            ListInterface<RegisteredCourse> studentRegisteredCourses = selectedStudent.getRegisteredCourses();
                            for (int i = 0; i < studentRegisteredCourses.size(); i++) {
                                RegisteredCourse registeredCourse = studentRegisteredCourses.get(i);
                                if (registeredCourse.getCode().equals(courseCode)) {
                                    alreadyRegisteredForCourse = true;
                                    break;
                                }
                            }

                            if (alreadyRegisteredForCourse) {
                                System.out.println("Student is already registered for course " + courseCode + ". Please enter another course.");
                                continue;
                            }

                            // Proceed with adding the course
                            System.out.println("\nEnter status (Main, Elective, Resit, Repeat): ");
                            String status = scanner.nextLine();

                            // Validate status
                            if (!(status.equals("Main") || status.equals("Elective") || status.equals("Resit") || status.equals("Repeat"))) {
                                System.out.println("Invalid status. Please enter 'Main', 'Elective', 'Resit', or 'Repeat'.");
                                continue;
                            }

                            // Convert the first letter to uppercase
                            status = status.substring(0, 1).toUpperCase() + status.substring(1).toLowerCase();

                            RegisteredCourse registerCourse = new RegisteredCourse(selectedStudent.getStudentID(), courseCode, status);
                            boolean success = ManageRegistered.addRegistered(registerCourse);
                            if (success) {
                                System.out.println("Registration successfully added.");
                            }

                            System.out.println("\nDo you want to add another course? (yes/no)");
                            String continueChoice = scanner.nextLine().toLowerCase();
                            if (continueChoice.equals("no")) {
                                break;
                            }
                        }
                        System.out.println("---------------------------");
                        System.out.println("Courses added successfully.");
                        System.out.println("---------------------------");
                    } else {
                        System.out.println("There is no available courses for this programme.");
                    }
                    break;

                case 7:
                    // Remove student from a course
                    System.out.println("\nRemove student from course...");
                    for (int i = 0; i < studentList.size(); i++) {
                        Student stud = studentList.get(i);
                        System.out.println((i + 1) + ". " + stud.getStudentID() + " - " + stud.getName());
                    }
                    int studentIdToRemoveCourse;
                    while (true) {
                        try {
                            System.out.println("Enter the student ID(enter the index number): ");
                            studentIdToRemoveCourse = Integer.parseInt(scanner.nextLine());
                            if (studentIdToRemoveCourse < 1 || studentIdToRemoveCourse > studentList.size()) {
                                System.out.println("Invalid student ID. Please enter a valid index.");
                                continue;
                            }
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid option. Please enter a valid integer.");
                        }
                    }

                    // Display the courses the student is registered for
                    ListInterface<RegisteredCourse> registeredCourses = ManageRegistered.getRegisteredCoursesForStudent(studentIdToRemoveCourse);
                    if (!registeredCourses.isEmpty()) {
                        System.out.println("Courses registered for student ID " + studentIdToRemoveCourse + ": ");
                        for (int i = 0; i < registeredCourses.size(); i++) {
                            RegisteredCourse course = registeredCourses.get(i);
                            System.out.println((i + 1) + ". " + course.getCode() + ", Status: " + course.getStatus());
                        }

                        // Prompt for the course to remove
                        int courseIndexToRemove;
                        while (true) {
                            try {
                                System.out.println("Enter the index of the course to remove: ");
                                String courseIndexInput = scanner.nextLine();
                                courseIndexToRemove = Integer.parseInt(courseIndexInput);
                                if (courseIndexToRemove > 0 && courseIndexToRemove <= registeredCourses.size()) {
                                    break; // Break out of the loop if the input is valid
                                } else {
                                    System.out.println("Invalid course index.");
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid input. Please enter a valid integer.");
                            }
                        }

                        // Remove the course from the student's record
                        removeRegisteredCourse(studentIdToRemoveCourse, courseIndexToRemove);
                    } else {
                        System.out.println("No registered courses found for the student.");
                    }
                    break;

                case 8:
                    //view student bill
                    System.out.println("\nView Student Bill");
                    System.out.println("==================");
                    System.out.println("Student List:");
                    for (int i = 0; i < studentList.size(); i++) {
                        Student student = studentList.get(i);
                        System.out.println((i + 1) + ". " + student.getStudentID() + " - " + student.getName());
                    }
                    System.out.print("\nEnter student ID: ");
                    int studentId = scanner.nextInt();
                    double bill = ManageStudent.calculateStudentBill(studentId);
                    if (bill > 0.0) {
                        System.out.println("Total Bill: RM " + bill);
                    }
                    break;

                case 9:
                    // Ask the user for filtering criteria
                    System.out.println("Enter filtering criteria:");
                    System.out.println("1. Minimum number of students");
                    System.out.println("2. Maximum number of courses");
                    System.out.print("Enter your choice: ");
                    int filterChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character

                    int minStudents = 0;
                    int maxCourses = 0;

                    switch (filterChoice) {
                        case 1:
                            System.out.print("Enter the minimum number of students: ");
                            minStudents = scanner.nextInt();
                            scanner.nextLine(); // Consume newline character
                            break;
                        case 2:
                            System.out.print("Enter the maximum number of courses: ");
                            maxCourses = scanner.nextInt();
                            scanner.nextLine(); // Consume newline character
                            break;
                        default:
                            System.out.println("Invalid choice.");
                            break;
                    }
                    System.out.println("\n                   Summary Report Students In Programme                                ");
                    System.out.println("----------------------------------------------------------------------------------------");
                    System.out.printf("%-50s  %-20s  %-20s%n", "Programme Name", "Number of Students", "Total Courses");
                    System.out.println("-------------------------------------------------  --------------------  ---------------");

                    // Generate and display the summary report based on the filtering criteria
                    for (int i = 0; i < programmeList.size(); i++) {
                        Programme pro = programmeList.get(i);
                        int numberOfStudents = ManageStudent.getNumberOfStudentsInProgramme(pro, studentList);
                        int totalCourses = ManageStudent.calculateNumberOfCoursesInProgramme(pro, studentList);

                        // Check if the current programme meets the filtering criteria
                        if ((minStudents == 0 || numberOfStudents >= minStudents) && (maxCourses == 0 || totalCourses <= maxCourses)) {
                            System.out.printf("%-60s  %-20d  %-20d%n", pro.getProgrammeName(), numberOfStudents, totalCourses);
                        }
                    }

                    System.out.println("\n                            End Of Report                                              ");
                    System.out.println("-----------------------------------------------------------------------------------------");
                    break;

                case 10:
                    // Ask the user for filtering criteria
                    String status;
                    while (true) {
                        System.out.println("Enter the course status (Main, Elective, Resit, Repeat): ");
                        status = scanner.nextLine().trim();

                        if (!status.equalsIgnoreCase("Main") && !status.equalsIgnoreCase("Elective")
                                && !status.equalsIgnoreCase("Resit") && !status.equalsIgnoreCase("Repeat")) {
                            System.out.println("Invalid status. Please enter again.");
                            continue;
                        }
                        break;
                    }

                    System.out.println("\n                            Summary Report Students In Course                           ");
                    System.out.println("----------------------------------------------------------------------------------------- ");
                    System.out.printf("%-15s  %-50s  %-20s%n", "Course Code", "Course Name", "Number of Students");
                    System.out.println("--------------  -------------------------------------------------  -----------------------");

                    // Call the method from ManageStudent class to generate and display the summary report
                    ManageStudent.generateSummaryReport(courseList, studentList, status);

                    System.out.println("\n                                  End Of Report                                         ");
                    System.out.println("------------------------------------------------------------------------------------------");
                    break;

                case 0:
                    running = true;
                    if (running) {
                        return; // Return from the method to go back to the main menu
                    }
                default:
                    System.out.println("Invalid option!");
                    break;
            }
        }

        scanner.close();
    }
}
