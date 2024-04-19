/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Boundary;

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
import init.DataInitializer;
import entity.Programme;
import adt.ArrayList;
import control.ManageCourse;
import control.ManageProgramme;
import static control.ManageRegistered.removeRegisteredCourse;
import java.util.stream.Collectors;

public class StudentRegistration {

    public static void main(String[] args) {
        DataInitializer.initializeAllData();
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
            System.out.println("9.  Maximum Credit Hours");
            System.out.println("10. Summary Report Students In Programme");
            System.out.println("11. Summary Report Students In Course");
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

                        System.out.println("-------------------");
                        System.out.println("Insert Successfully");
                        System.out.println("-------------------");

                        // Create and return a new Student object
                        ManageStudent.addStudent(id, name, IC, contact_number, programme);
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
                                        amend = ManageStudent.amendStudent(studentIdToAmend, newName, studentToAmend.getContact_number());
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
                                        studentToAmend.setContact_number(newContactNumber);
                                        amend = ManageStudent.amendStudent(studentIdToAmend, studentToAmend.getName(), newContactNumber);
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
                        System.out.println("\nEnter status: ");
                        String status = scanner.nextLine();
                        RegisteredCourse registerCourse = new RegisteredCourse(selectedStudent.getStudentID(), courseCode, status);
                        boolean success = ManageRegistered.addRegistered(registerCourse);
                        if (success) {
                            System.out.println("Registration successfully added.");
                        }

//                        ManageStudent.addStudentCourse(selectedStudent, courseCode, status);
                        System.out.println("\nDo you want to add another course? (yes/no)");
                        String continueChoice = scanner.nextLine().toLowerCase();
                        if (continueChoice.equals("no")) {
                            break;
                        }
                    }

                    System.out.println("---------------------------");
                    System.out.println("Courses added successfully.");
                    System.out.println("---------------------------");
                    break;

                case 7:
                    // Remove student from a course
                    System.out.println("\nRemove student from course...");
                    for (int i = 0; i < studentList.size(); i++) {
                        Student stud = studentList.get(i);
                        System.out.println((i + 1) + ". " + stud.getStudentID() + " - " + stud.getName());
                    }
                    System.out.println("Enter the student ID(enter the index number): ");
                    int studentIdToRemoveCourse = scanner.nextInt();
                    scanner.nextLine();

                    // Display the courses the student is registered for
                    ListInterface<RegisteredCourse> registeredCourses = ManageRegistered.getRegisteredCoursesForStudent(studentIdToRemoveCourse);
                    if (!registeredCourses.isEmpty()) {
                        System.out.println("Courses registered for student ID " + studentIdToRemoveCourse + ": ");
                        for (int i = 0; i < registeredCourses.size(); i++) {
                            {
                                RegisteredCourse course = registeredCourses.get(i);
                                System.out.println((i + 1) + ". " + course.getCode() + ", Status: " + course.getStatus());
                            }
                        }

                        // Prompt for the course to remove
                        System.out.println("Enter the index of the course to remove: ");
                        int courseIndexToRemove = scanner.nextInt();
                        scanner.nextLine();

                        // Validate the course index
                        if (courseIndexToRemove < 1 || courseIndexToRemove > registeredCourses.size()) {
                            System.out.println("Invalid course index.");
                            break;
                        }

                        // Remove the course from the student's record
                        removeRegisteredCourse(studentIdToRemoveCourse, courseIndexToRemove);
//                        if (courseRemoved) {
//                            System.out.println("Course " + courseCodeToRemove + " removed successfully.");
//                        } else {
//                            System.out.println("Course " + courseCodeToRemove + " could not be removed.");
//                        }
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
                    System.out.println("\nCalculating Maximum Credit Hours...");
                    System.out.printf("%-15s %-40s %-30s%n", "Student ID", "Student Name", "Maximum Credit Hours Allowed");
                    System.out.println("-------------- ---------------------------------------- ------------------------------");

                    for (int i = 0; i < studentList.size(); i++) {
                        Student student = studentList.get(i);
//                        int totalCreditHours = ManageStudent.calculateTotalCreditHours(student, courseList);
                        int maxCreditHours = ManageStudent.calculateMaxCreditHours(student, programmeList);

                        System.out.printf("%-15s %-50s %-30s%n", student.getStudentID(), student.getName(), maxCreditHours);
                    }
                    break;

                case 10:
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

                case 11:
                    // Ask the user for filtering criteria
                    System.out.println("Enter the course status:");
                    String status = scanner.nextLine(); // Consume newline character

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
                    System.out.println("Exiting...");
                    running = false; // Set running to false to exit the loop
                    break;

                default:
                    System.out.println("Invalid option!");
                    break;
            }
        }

        scanner.close();
    }
}
