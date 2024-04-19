/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

/**
 *
 * @author Ow Yong Qing
 */
import adt.ListInterface;
//import DAO.studentDAO;
import adt.ArrayList;
import entity.Course;
import entity.Programme;
import entity.RegisteredCourse;
import entity.Student;

public class ManageStudent {

    private static ListInterface<Student> studentList = new ArrayList<>();
//    private static ListInterface<Programme> programmeList = DataInitializer.initializeAllData();

//    private static studentDAO studentDao = new studentDAO("student.dat");
    public static ListInterface<Student> getStudentList() {
        return studentList;
    }

    public static boolean validateInput(String input) {
        return !input.isEmpty();
    }

    public static boolean validateName(String name) {
        String regex = "[a-zA-Z]+";
        return name.matches(regex);
    }

    public static boolean validateInputIC(String IC) {
        String regex = "\\d{6}-\\d{2}-\\d{4}";
        return IC.matches(regex);
    }

    public static boolean validateInputCN(String contact_number) {
        String regex = "\\d{3}-\\d{7}";
        return contact_number.matches(regex);
    }

    // Add this method to the ManageStudent class or any appropriate class where programmeList is accessible
    public static boolean validateProgrammeCode(String programmeCode, ListInterface<Programme> programmeList) {
        for (int i = 0; i < programmeList.size(); i++) {
            Programme prog = programmeList.get(i);
            if (prog.getProgrammeCode().equals(programmeCode)) {
                return true; // Programme code is valid
            }
        }
        return false; // Programme code is invalid
    }

    public static int getNextStudentId() {
        // If the list is empty, return 1 as the first student ID
        if (studentList.isEmpty()) {
            return 1;
        }

        // Retrieve the maximum student ID from the existing list
        int maxStudentID = retrieveMaxStudentID(studentList);

        // Return the next available ID
        return maxStudentID + 1;
    }

    private static int retrieveMaxStudentID(ListInterface<Student> studentList) {
        int maxID = 0;
        for (int i = 0; i < studentList.size(); i++) {
            Student student = studentList.get(i);
            int studentID = student.getStudentID();
            if (studentID > maxID) {
                maxID = studentID;
            }
        }
        return maxID;
    }

    public static boolean addStudent(int id, String name, String IC, String contact_number, String programme) {
        if (checkExistsInStudent(IC)) {
            System.out.println("This student already exists.\n");
            return false;
        }
        Student student = new Student(id, name, IC, contact_number, programme);
        studentList.add(student);
        Programme programmes = findProgrammeByCode(programme);
        programmes.getStudentList().add(student);
//        studentDao.saveToFile(studentList);
//        System.out.println("Student successfully added.");
        return true;
    }

    public static Programme findProgrammeByCode(String programme) {
        for (int i = 0; i < ManageProgramme.getProgrammeList().size(); i++) {
            if (ManageProgramme.getProgrammeList().get(i).getProgrammeCode().equalsIgnoreCase(programme)) {
                return ManageProgramme.getProgrammeList().get(i);
            }
        }
        return null;
    }

    public static boolean checkExistsInStudent(String stuIC) {
        String ic = "";
        for (int i = 0; i < stuIC.length(); i++) {
            char integer;

            if (Character.isDigit(stuIC.charAt(i))) {
                integer = stuIC.charAt(i);
                ic = ic + integer;
            }
        }

        for (int j = 0; j < studentList.size(); j++) {
            if (studentList.get(j).getIC().equals(ic)) {
                return true;
            }
        }
        return false;
    }

    public static boolean removeStudent(int studentId) {
        Student studentToRemove = getStudentById(studentId); // Retrieve the student to remove
        int index = studentList.search(studentToRemove);

        if (studentToRemove != null) {
            studentList.remove(index); // Remove the student from studentList

            Programme programme = findProgrammeByCode(studentToRemove.getProgramme()); // Find the corresponding programme
            int proStuIndex = programme.getStudentList().search(studentToRemove);
            if (programme != null) {

                programme.getStudentList().remove(proStuIndex); // Remove the student from the programme's student list
            }

            System.out.println(studentToRemove.getStudentID() + " removed successfully.");
            return true;
        } else {
            System.out.println("Student with ID " + studentId + " not found.");
            return false;
        }
    }
    
    public static boolean amendStudent(int studentId, String newName, String newContactNumber) {
        ListInterface<Student> studentList = getStudentList();

        int index = studentList.search(new Student(studentId, "", "", "", ""));
        if (index != -1) {
            Student student = studentList.get(index);
            if (newName != null && !newName.isEmpty()) {
                student.setName(newName);
            }
            if (newContactNumber != null && !newContactNumber.isEmpty()) {
                student.setContact_number(newContactNumber);
            }
//            if (newProgramme != null && !newProgramme.isEmpty()) {
//                student.setProgramme(newProgramme);
//            }
            return true;
        } else {
            return false;
        }
    }

    public static Course findCourseByCode(String courseCode) {
        for (int i = 0; i < ManageCourse.getCourseList().size(); i++) {
            if (ManageCourse.getCourseList().get(i).getCode().equalsIgnoreCase(courseCode)) {
                return ManageCourse.getCourseList().get(i);
            }
        }
        return null;
    }

    public static boolean addStudentCourse(Student student, String courseCode, String status) {
        Course course = findCourseByCode(courseCode);
        course.getRegisterStudentList().add(student);
        RegisteredCourse registerCourse = new RegisteredCourse(student.getStudentID(), course.getCode(), status);
        ManageRegistered.getRegisteredList().add(registerCourse);
        return true;
    }

    public static Student getStudentById(int studentId) {
        // Iterate through the list of students to find the one with the matching ID
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getStudentID() == studentId) {
                return studentList.get(i);
            }
        }
        // If no student with the given ID is found, return null or throw an exception
        return null; // Or throw an exception indicating that the student was not found
    }

    public static double calculateStudentBill(int studentId) {
        // Get the student object
        Student student = ManageStudent.getStudentById(studentId);
        if (student == null) {
            System.out.println("Student not found.");
            return 0.0;
        }

        // Get the list of registered courses for the student
        ListInterface<RegisteredCourse> registeredCourses = student.getRegisteredCourses();

        // Initialize total bill
        double totalBill = 0.0;

        // Print header for details
        System.out.println("===========================");
        System.out.println("Course Details:");

        // Iterate through each registered course
        for (int i = 0; i < registeredCourses.size(); i++) {
            RegisteredCourse regCourse = registeredCourses.get(i);

            // Find the corresponding course object
            Course course = findCourseByCode(regCourse.getCode());
            if (course != null) {
                // Add the course fee to the total bill
                totalBill += course.getFee();

                // Print course details
                System.out.println("Course Code: " + course.getCode());
                System.out.println("Course Name: " + course.getName());
                System.out.println("Fee: RM " + course.getFee());
                System.out.println("Status: " + regCourse.getStatus());
                System.out.println("---------------------------");
            }
        }

        return totalBill;
    }

    public static int calculateTotalCreditHours(Student student, ListInterface<Course> courseList) {
        int totalCreditHours = 0;
        ListInterface<RegisteredCourse> registeredCourses = student.getRegisteredCourses();
        for (int i = 0; i < registeredCourses.size(); i++) {
            RegisteredCourse registeredCourse = registeredCourses.get(i);
            Course course = getCourseByCode(courseList, registeredCourse.getCode());
            if (course != null) {
                totalCreditHours += course.getCreditHour();
            }
        }
        return totalCreditHours;
    }

    // Method to get course by code
    private static Course getCourseByCode(ListInterface<Course> courseList, String courseCode) {
        for (int i = 0; i < courseList.size(); i++) {
            Course course = courseList.get(i);
            if (course.getCode().equals(courseCode)) {
                return course;
            }
        }
        return null; // Course not found
    }

    public static Programme getProgrammeByCode(ListInterface<Programme> programmeList, String programmeCode) {
        for (int i = 0; i < programmeList.size(); i++) {
            Programme programme = programmeList.get(i);
            if (programme.getProgrammeCode().equals(programmeCode)) {
                return programme;
            }
        }
        return null; // Programme not found
    }

    public static int calculateMaxCreditHours(Student student, ListInterface<Programme> programmeList) {
        int maxCreditHours = 0;
        Programme programme = getProgrammeByCode(programmeList, student.getProgramme());
        if (programme != null) {
            ListInterface<Course> programmeCourses = programme.getCourseList();
            for (int i = 0; i < programmeCourses.size(); i++) {
                Course course = programmeCourses.get(i);
                maxCreditHours += course.getCreditHour();
            }
        }
        return maxCreditHours;
    }

    public static int getNumberOfStudentsInProgramme(Programme programme, ListInterface<Student> studentList) {
        int count = 0;
        for (int i = 0; i < studentList.size(); i++) {
            Student student = studentList.get(i);
            if (student.getProgramme().equals(programme.getProgrammeCode())) {
                count++;
            }
        }
        return count;
    }

    public static int calculateNumberOfCoursesInProgramme(Programme programme, ListInterface<Student> studentList) {
        int totalCourses = 0;

        for (int i = 0; i < studentList.size(); i++) {
            Student student = studentList.get(i);
            if (student.getProgramme().equals(programme.getProgrammeCode())) {
                ListInterface<RegisteredCourse> registeredCourses = student.getRegisteredCourses();
                totalCourses += registeredCourses.size();
            }
        }

        return totalCourses;
    }

    public static void generateSummaryReport(ListInterface<Course> courseList, ListInterface<Student> studentList, String status) {
        // Iterate through each course
        for (int i = 0; i < courseList.size(); i++) {
            Course course = courseList.get(i);

            // Initialize the count of students with the specified status for the current course
            int numberOfStudentsWithStatus = 0;

            // Iterate through each student
            for (int j = 0; j < studentList.size(); j++) {
                Student student = studentList.get(j);

                // Check if the student is registered for the current course
                ListInterface<RegisteredCourse> studentRegisteredCourses = student.getRegisteredCourses();
                for (int k = 0; k < studentRegisteredCourses.size(); k++) {
                    RegisteredCourse registeredCourse = studentRegisteredCourses.get(k);
                    if (registeredCourse.getCode().equals(course.getCode())) {
                        // If the registered course matches the current course, check its status
                        if (registeredCourse.getStatus().equalsIgnoreCase(status)) {
                            // If the status matches the specified status, increment the count
                            numberOfStudentsWithStatus++;
                            // No need to continue checking this student's other registered courses for this course
                            break;
                        }
                    }
                }
            }

            // Check if the current course meets the filtering criteria
            if (numberOfStudentsWithStatus > 0) {
                // If the course meets the criteria, print its details in the summary report
                System.out.printf("%-15s  %-60s  %-20d%n", course.getCode(), course.getName(), numberOfStudentsWithStatus);
            }
        }
    }
}
