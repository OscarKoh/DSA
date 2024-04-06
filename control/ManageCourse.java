package control;

import DAO.courseDAO;
import entity.Course;
import adt.ListInterface;
import adt.ArrayList;

/**
 *
 * @author Loo Suk Zhen
 */
public class ManageCourse {

    private static ListInterface<Course> courseList = new ArrayList<>();
    private static courseDAO courseDao = new courseDAO("course.dat");

    public static ListInterface<Course> getCourseList() {
        return courseList = courseDao.retrieveFromFile();
    }

    public static boolean addCourse(String courseCode, String courseName, int creditHour, ArrayList<Course.CourseType> courseTypes, double fee) {
        if (checkExistsInCourse(courseCode, courseName)) {
            System.out.println("This course already exists.\n");
            return false;
        }
        Course course = new Course(courseCode.toUpperCase(), courseName, courseTypes,, creditHour, fee);
        courseList.add(course);
//        System.out.println("You are successfully added a new course.");
        courseDao.saveToFile(courseList);
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
}
    
