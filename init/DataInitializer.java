package init;

import control.ManageProgramme;
import control.ManageCourse;
import adt.ArrayList;
import entity.Course;

public class DataInitializer {

    public static void initializeAllData() {
        initializeProgramme();
        initializeCourse();

    }

    public static void initializeProgramme() {
        String[] programmeCodes = {
            "RAC",
            "RBF",
            "RSW",
            "RDS",
            "RET",
            "RES",
            "RRT", "DLS", "DMT", "DMA",
            "DME", "RRM"
        };
        String[] programmeNames = {
            "Diploma in Accounting",
            "Diploma in Banking and Finance",
            "Diploma in Software Engineering",
            "Bachelor of Computer Science In Data Science",
            "Diploma in Entrepreneurship",
            "Bachelor of Economics", "Diploma in Electronic Engineering",
            "Diploma in Event Management", "Diploma in Fashion Design",
            "Diploma in Finance & Investment", "Diploma in Food Science",
            "Bachelor of Retail Management"};
        int[] programmeDuration = new int[]{
            24, 24, 24, 24, 24, 24, 24, 24, 24, 24,
            24, 24
        };
        String[] faculty = {
            "FAFB", "FCCI", "FOAS", "FAFB", "FCCI", "FOBE", "FAFB", "FAFB", "FCCI", "FOCS",
            "FSSH", "FAFB"
        };
        for (int i = 0; i < programmeNames.length; i++) {
            ManageProgramme.addProgramme(programmeCodes[i], programmeNames[i], programmeDuration[i], faculty[i]);
        }
    }

    public static void initializeCourse() {
        // Course data
        String[] courseCodes = {
            "BACS2023", "BACS1053", "BACS2063", "BAIT1023", "BAIT1043",
            "BBBE2013", "BBDT2123", "BAIT2073", "BAIT1093",
            "BAIT2012"
        };
        String[] courseNames = {
            "Object-Oriented Programming", "Database Management", "Data Structure and Algorithms",
            "Web Design and Development", "System Analysis And Design", "Money And Banking",
            "Marketing Of Financial Services", "English For Career Preparation",
            "Mobile Application Development", "Introduction To Computer Security",};
       
        // Define course types for each course
        ArrayList<Course.CourseType>[] courseTypeArrays = new ArrayList[courseCodes.length];
        for (int i = 0; i < courseCodes.length; i++) {
            courseTypeArrays[i] = new ArrayList<>(); // Initialize ArrayList
            if (i < 5) {
                // Courses with lecture, tutorial, and practical
                courseTypeArrays[i].add(Course.CourseType.LECTURE);
                courseTypeArrays[i].add(Course.CourseType.TUTORIAL);
                courseTypeArrays[i].add(Course.CourseType.PRACTICAL);
            } else if (i == 6 && i == 7) {
                // Courses with lecture and tutorial
                courseTypeArrays[i].add(Course.CourseType.LECTURE);
                courseTypeArrays[i].add(Course.CourseType.TUTORIAL);
            } else if (i > 7) {
                // Courses with lecture, tutorial, and practical (repeated)
                courseTypeArrays[i].add(Course.CourseType.LECTURE);
                courseTypeArrays[i].add(Course.CourseType.TUTORIAL);
                courseTypeArrays[i].add(Course.CourseType.PRACTICAL);
            }
        }

        double[] fees = {
            449.00, 445.00, 700.00, 800.00, 560.00, 780.00, 903.00,
            670, 899, 995, 555, 677, 777
        };

        // Loop through each course and initialize it
        for (int i = 0; i < courseCodes.length; i++) {
            ManageCourse.addCourse(courseCodes[i], courseNames[i],  3, courseTypeArrays[i], fees[i]);
        }
    }

}
