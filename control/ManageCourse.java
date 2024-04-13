package control;

import entity.Programme;
import entity.Course;
import adt.ListInterface;
import adt.ArrayList;
import entity.Course.CourseType;

/**
 *
 * @author Loo Suk Zhen
 */
public class ManageCourse {

    private static ListInterface<Course> courseList = new ArrayList<>();
//    private static courseDAO courseDao = new courseDAO("course.dat");
//
    public static ListInterface<Course> getCourseList() {
        return courseList;
    }
    

    public static boolean addCourse(String courseCode, String courseName, int creditHour, ArrayList<Course.CourseType> courseTypes, double fee) {
        if (checkExistsInCourse(courseCode, courseName)) {
            System.out.println("This course already exists.\n");
            return false;
        }
        Course course = new Course(courseCode.toUpperCase(), courseName, courseTypes, creditHour, fee);
        courseList.add(course);
//        System.out.println("You are successfully added a new course.");
//        courseDao.saveToFile(courseList);
        return true;
    }

    public static boolean checkExistsInCourse(String courseCode, String name) {
        String code = "";
        for (int j = 0; j < courseCode.length(); j++) {
            char capital;
            char integer;

            if (Character.isLetter(courseCode.charAt(j))) {
                capital = Character.toUpperCase(courseCode.charAt(j));
                code = code + capital;
            } else {
                integer = courseCode.charAt(j);
                code = code + integer;
            }
        }

        for (int i = 0; i < courseList.size(); i++) {
            if (courseList.get(i).getCode().equals(code)
                    || courseList.get(i).getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public static boolean removeCourse(int courseNum) {
        Course courseToRemove = courseList.get(courseNum - 1);

        for (int i = 0; i < courseList.size(); i++) {
            Course course = courseList.get(i);

            if (course.equals(courseToRemove)) {
                ListInterface<Programme> programmeList = course.getProgrammesList();

                for (int j = 0; j < programmeList.size(); j++) {
                    Programme programme = programmeList.get(j);
                    int courses = programme.getCourseList().search(courseToRemove);
                    // Remove the course from each programme's course list
                    programme.removeCourse(courses);
                }
                courseList.remove(i);
//                courseDao.saveToFile(courseList);
                return true;
            }
        }
        return false;
    }


    public static boolean addProgrammeToCourse(String progName, Course course) {
        Programme programme = ManageProgramme.findProgrammeByName(progName);
        if (course.getProgrammesList().search(programme) != -1) {
            System.out.println("Programme " + progName + " is already existed in course " + course.getName());
            return false;
        }
        course.getProgrammesList().add(programme);
        programme.addCourse(course);
//        courseDao.saveToFile(courseList);
        return true;
    }


    public static ListInterface<Course> availableCourses() {
        ListInterface<Course> availableCourses = new ArrayList<>();

        for (int i = 0; i < courseList.size(); i++) {
            Course course = courseList.get(i);

            // Check if the course has no associated programmes
            if (course.getProgrammesList().isEmpty()) {
                availableCourses.add(course);
            }
        }
        return availableCourses;
    }
}
