package init;

import control.ManageProgramme;
import control.ManageCourse;
import adt.ArrayList;
import entity.Course;
import entity.RegisteredCourse;
import control.ManageRegistered;
import control.ManageStudent;

public class DataInitializer {

    public static void initializeAllData() {
        initializeProgramme();
        initializeCourse();
        initializeStudent();
        initializeRegistration();
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
            if (i <= 5) {
                // Courses with lecture, tutorial, and practical
                courseTypeArrays[i].add(Course.CourseType.LECTURE);
                courseTypeArrays[i].add(Course.CourseType.TUTORIAL);
                courseTypeArrays[i].add(Course.CourseType.PRACTICAL);
            } else if (i == 6 || i == 7) {
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

    public static void initializeStudent() {
        int[] stuID = {
            1001, 1002, 1003, 1004, 1005, 1006, 1007, 1008, 1009, 1010, 1011, 1012
        };

        String[] stuname = {
            "Eric Tsen",
            "Melissa Wong",
            "Yew Hong Yin",
            "Chan Pei Yee",
            "Cathy Loke",
            "Kelly Ong",
            "Kevin Kwa",
            "John Doe",
            "Steve Ong",
            "Stephie Yong",
            "Sean Ong",
            "Jaydon Wong"
        };

        String[] stuIC = {
            "981025-14-5638",
            "750617-05-9203",
            "830926-06-1574",
            "690817-08-3412",
            "480530-11-8956",
            "571204-03-6721",
            "891211-09-4465",
            "630712-12-3789",
            "720309-02-5436",
            "561110-07-2894",
            "471013-10-7542",
            "840218-01-6329"
        };

        String[] stucontact_number = {
            "011-3498910",
            "012-4982380",
            "019-4582309",
            "012-3596079",
            "018-3498123",
            "012-7793245",
            "017-2487298",
            "012-7793223",
            "012-3476291",
            "018-1345623",
            "017-7793355",
            "012-7153647"
        };

        String[] stuProgramme = { // Change to array of programme codes
            "RAC", "RDS", "RSW", "RDS", "RRT", "RET", "DLS", "DMA", "DMA", "RRM", "DME", "RAC"
        };
        
        for (int i = 0; i < stuname.length; i++) {
            ManageStudent.addStudent(stuID[i], stuname[i], stuIC[i], stucontact_number[i], stuProgramme[i]);
        }
    }

    public static void initializeRegistration() {
        RegisteredCourse[] regCourses = {
            new RegisteredCourse(1001, "BBBE2013", "Main"),
            new RegisteredCourse(1001, "BBDT2123", "Elective"),
            new RegisteredCourse(1001, "BAIT2073", "Resit"),
            new RegisteredCourse(1002, "BACS1053", "Main"),
            new RegisteredCourse(1002, "BACS2063", "Resit"),
            new RegisteredCourse(1003, "BACS2063", "Elective"),
            new RegisteredCourse(1003, "BAIT1093", "Repeat"),
            new RegisteredCourse(1003, "BAIT2012", "Repeat"),
            new RegisteredCourse(1004, "BACS1053", "Repeat"),
            new RegisteredCourse(1004, "BACS2063", "Main"),
            new RegisteredCourse(1005, "BACS2023", "Resit"),
            new RegisteredCourse(1006, "BBBE2013", "Repeat"),
            new RegisteredCourse(1006, "BBDT2123", "Elective"),
            new RegisteredCourse(1007, "BAIT2073", "Elective"),
            new RegisteredCourse(1008, "BBDT2123", "Resit"),
            new RegisteredCourse(1009, "BBDT2123", "Main"),
            new RegisteredCourse(1010, "BBBE2013", "Resit"),
            new RegisteredCourse(1011, "BAIT2073", "Elective"),
            new RegisteredCourse(1012, "BBDT2123", "Main")};

        for (RegisteredCourse regCourse : regCourses) {
            ManageRegistered.addRegistered(regCourse);
        }
    }

}
