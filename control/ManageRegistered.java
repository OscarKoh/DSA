/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import Entity.Course;
import Entity.RegisteredCourse;
import Entity.Student;
import adt.ArrayList;
import adt.ListInterface;
import static control.ManageStudent.findCourseByCode;
import static control.ManageStudent.getStudentById;
import java.util.Iterator;

/**
 *
 * @author Ow Yong Qing
 */
public class ManageRegistered {

    private static ListInterface<RegisteredCourse> registeredList = new ArrayList<>();
//    private static studentDAO studentDao = new studentDAO("student.dat");

    public static ListInterface<RegisteredCourse> getRegisteredList() {
        return registeredList;
    }

    public static boolean addRegistered(RegisteredCourse regCourses) {
        if (checkExistsInRegistered(regCourses)) {
            System.out.println("This registration already exists.");
            return false;
        }

        registeredList.add(regCourses);
        Course course = findCourseByCode(regCourses.getCode());
        Student student = getStudentById(regCourses.getStudentID());
        course.getRegisterStudentList().add(student);
        student.getRegisteredCourses().add(regCourses);
        return true;
    }

    public static boolean checkExistsInRegistered(RegisteredCourse regCourses) {
        Iterator<RegisteredCourse> iterator = registeredList.getIterator();
        while (iterator.hasNext()) {
            RegisteredCourse registeredCourse = iterator.next();
            if (registeredCourse.equals(regCourses)) {
                return true;
            }
        }
        return false;
    }

//    public static ListInterface<RegisteredCourse> getRegisteredCoursesForStudent(int studentID) {
//        ListInterface<RegisteredCourse> courses = new ArrayList<>();
//        for (int j = 0; j < registeredList.size(); j++) {
//            RegisteredCourse registeredCourse = registeredList.get(j);
//            if (registeredCourse.getStudentID() == studentID) {
//                courses.add(registeredCourse);
//            }
//        }
//        return courses;
//    }
    public static ListInterface<RegisteredCourse> getRegisteredCoursesStudent(int studentID) {
        ListInterface<RegisteredCourse> courses = new ArrayList<>();
        for (int j = 0; j < registeredList.size(); j++) {
            RegisteredCourse registeredCourse = registeredList.get(j);
            if (registeredCourse.getStudentID() == studentID) {
                courses.add(registeredCourse);
            }
        }
        return courses;
    }

    public static ListInterface<RegisteredCourse> getRegisteredCoursesForStudent(int index) {
        Student student = ManageStudent.getStudentList().get(index - 1);
        ListInterface<RegisteredCourse> registeredCourses = student.getRegisteredCourses();
        ListInterface<RegisteredCourse> filterReg = new ArrayList<>();
        for (int i = 0; i < registeredCourses.size(); i++) {
            if (registeredCourses.get(i).getStatus().equalsIgnoreCase("Main")
                    || registeredCourses.get(i).getStatus().equalsIgnoreCase("Elective")) {
                filterReg.add(registeredCourses.get(i));
            }
        }
        return filterReg;

    }

    public static boolean addStudentToCourse(RegisteredCourse regCourses) {
        // Check if the student is already registered for the course
        if (checkExistsInRegistered(regCourses)) {
            System.out.println("This student is already registered for the course.");
            return false;
        }

        // Add the provided course to the list
        registeredList.add(regCourses);

        // Print a success message
        System.out.println("Student successfully added to the course.");

        return true;
    }
//    
//     public static RegisteredCourse findCourseByCode(String courseCode){
//        for (int i = 0; i < registeredList.size(); i++) {
//            if(registeredList.get(i).getCode().equalsIgnoreCase(courseCode)){
//                return registeredList.get(i);
//            }
//        }
//        return null;
//    }

    public static boolean registerStudentCourse(int studentID) {
        for (int i = 0; i < registeredList.size(); i++) {
            if (registeredList.get(i).getStudentID() == studentID) {
                return true;
            }
        }
        return false;
    }

//    public static boolean removeRegisteredCourse(int studentId, String courseCodeToRemove) {
//        ListInterface<RegisteredCourse> registeredCourses = getRegisteredCoursesForStudent(studentId);
//
//        // Check if the student is registered for the course
//        boolean found = false;
//        int indexToRemove = -1;
//        for (int i = 0; i < registeredCourses.size(); i++) {
//            RegisteredCourse course = registeredCourses.get(i);
//            if (course.getCode().equals(courseCodeToRemove)) {
//                found = true;
//                indexToRemove = i;
//                break;
//            }
//        }
//
//        if (found) {
//            registeredCourses.remove(indexToRemove);
//            return true;
//        } else {
//            return false;
//        }
//    }
//    public static RegisteredCourse findRegCourse(int studentId, String courseCodeToRemove) {
//        RegisteredCourse registerCourse = null;
//        for (int i = 0; i < registeredList.size(); i++) {
//            if (registeredList.get(i).getStudentID() == studentId) {
//                if (registeredList.get(i).getCode().equalsIgnoreCase(courseCodeToRemove)) {
//                    registerCourse = registeredList.get(i);
//                }
//            }
//            return registerCourse;
//        }
//    }
    public static boolean removeRegisteredCourse(int stdChoice, int crsChoice) {
        Student student = ManageStudent.getStudentList().get(stdChoice - 1);
        ListInterface<RegisteredCourse> regCourseList = getRegisteredCoursesForStudent(stdChoice);
        RegisteredCourse regCourse = regCourseList.get(crsChoice - 1);
        Course course = findCourseByCode(regCourse.getCode());
        int position = course.getRegisterStudentList().search(student);

        for (int i = 0; i < registeredList.size(); i++) {
            if (registeredList.get(i).getStudentID() == student.getStudentID()
                    && registeredList.get(i).getCode().equalsIgnoreCase(regCourse.getCode())) {
                registeredList.remove(i);

            }
        }

        student.getRegisteredCourses().remove(crsChoice - 1);
        course.getRegisterStudentList().remove(position);
        System.out.println("remove successsfully");
        return true;
    }

}