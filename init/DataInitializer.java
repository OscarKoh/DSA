package init;

import control.ManageProgramme;
import control.ManageCourse;
import adt.ArrayList;
import adt.ListInterface;
import entity.Course;
import entity.RegisteredCourse;
import control.ManageRegistered;
import static control.ManageProgramme.findProgrammeByName;
import control.ManageStudent;
import control.ManageStudentTeam;
import control.ManageTutorialGroup;
import control.ManageTutorialTeam;
import entity.Programme;
import entity.Student;
import entity.Team;
import entity.TutorialGroup;

public class DataInitializer {

    public static void initializeAllData() {
        initializeProgramme();
        initializeCourse();
        initializeStudent();
        initializeRegistration();
        initializeTutorialGroup();
        initializeStudentGroup();
        initializeTeamSubsytem();
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
        int[] maxTotalCreditHour = new int[]{
            24, 33, 33, 40, 24, 36, 30, 24, 36, 24,
            24, 34
        };
        for (int i = 0; i < programmeNames.length; i++) {
            ManageProgramme.addProgramme(programmeCodes[i], programmeNames[i], programmeDuration[i], faculty[i], maxTotalCreditHour[i]);
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

        // Loop through each course and initialize it
        for (int i = 0; i < courseCodes.length; i++) {
            ManageCourse.addCourse(courseCodes[i], courseNames[i], 3, courseTypeArrays[i], fees[i]);
        }
        ManageCourse.addProgrammeToCourse(findProgrammeByName(programmeNames[0]), ManageCourse.getCourseList().get(0));
        ManageCourse.addProgrammeToCourse(findProgrammeByName(programmeNames[0]), ManageCourse.getCourseList().get(1));
        ManageCourse.addProgrammeToCourse(findProgrammeByName(programmeNames[1]), ManageCourse.getCourseList().get(0));
        ManageCourse.addProgrammeToCourse(findProgrammeByName(programmeNames[1]), ManageCourse.getCourseList().get(2));
        ManageCourse.addProgrammeToCourse(findProgrammeByName(programmeNames[2]), ManageCourse.getCourseList().get(0));
        ManageCourse.addProgrammeToCourse(findProgrammeByName(programmeNames[2]), ManageCourse.getCourseList().get(2));
        ManageCourse.addProgrammeToCourse(findProgrammeByName(programmeNames[2]), ManageCourse.getCourseList().get(9));
    }

    public static void initializeStudent() {
        int[] stuID = {
            1001, 1002, 1003, 1004, 1005, 1006, 1007, 1008, 1009, 1010,
            1011, 1012, 1013, 1014, 1015, 1016, 1017, 1018, 1019, 1020,
            1021, 1022, 1023, 1024, 1025, 1026, 1027, 1028, 1029, 1030,
            1031, 1032, 1033, 1034, 1035, 1036, 1037, 1038, 1039, 1040,
            1041, 1042, 1043, 1044, 1045, 1046, 1047, 1048, 1049, 1050};

        String[] stuname = {
            "Eric Tsen", "Melissa Wong", "Yew Hong Yin", "Chan Pei Yee", "Cathy Loke",
            "Kelly Ong", "Kevin Kwa", "John Doe", "Steve Ong", "Stephie Yong",
            "Sean Ong", "Jaydon Wong", "Sarah Tan", "Daniel Lim", "Jessica Lee",
            "Jonathan Ng", "Michelle Tan", "Aaron Goh", "Rachel Lim", "Ken Tan",
            "Yvonne Ng", "Brian Lim", "Joanne Ooi", "Nicholas Chan", "Amanda Wong",
            "Marcus Lee", "Evelyn Goh", "Darren Tan", "Samantha Lim", "Gary Foo",
            "Emily Ong", "Chris Tan", "Rachel Goh", "Kelvin Lim", "Megan Ng",
            "Jeremy Tan", "Alice Ooi", "Brandon Lee", "Vanessa Ng", "Sarah Lee",
            "Edward Tan", "Rachel Ng", "David Lim", "Michelle Ooi", "Steven Tan",
            "Elena Lim", "Brian Tan", "Julia Ng", "Andrew Lim", "Emily Tan"
        };

        String[] stuIC = {
            "981025-14-5631", "750617-05-9202", "830926-06-1573", "690817-08-3414",
            "880530-11-8955", "091204-03-6726", "891211-09-4467", "630712-12-3788",
            "920309-02-5439", "561110-07-2891", "471013-10-7541", "840218-01-6323",
            "900315-14-2434", "880430-08-6245", "910611-03-8296", "950815-11-9757",
            "960112-04-1388", "930615-09-2469", "970320-02-5732", "940505-07-3161",
            "990727-12-8423", "981212-05-1394", "000701-10-7275", "960211-08-6246",
            "890413-01-7851", "921219-12-9469", "920701-12-1398", "930715-09-7327",
            "950713-05-9822", "960716-03-7863", "970811-11-3284", "910830-07-9325",
            "980407-02-1389", "940715-05-7458", "020920-01-8237", "961204-07-5646",
            "950813-02-1891", "950905-12-7322", "971109-08-2392", "950630-03-6313",
            "991128-06-2317", "980221-09-4126", "940407-01-4895", "940109-07-7614",
            "940610-12-2168", "970925-04-6714", "920314-07-6321", "931111-11-7834",
            "910122-12-7829", "001212-09-8712"
        };

        String[] stucontact_number = {
            "011-3498910", "012-4982380", "019-4582309", "012-3596079", "018-3498123",
            "012-7793245", "017-2487298", "012-1323223", "012-3476291", "018-1345323",
            "017-7793355", "012-7153447", "011-9874561", "013-6758492", "019-4562381",
            "017-4569231", "012-7124692", "016-8974521", "018-6537129", "011-9874523",
            "013-9876541", "019-4562317", "014-7852369", "017-6521471", "018-2152319",
            "018-1249272", "013-7896541", "012-8456239", "011-9874563", "011-1238271",
            "014-7896542", "016-7854213", "019-4569872", "012-7856941", "019-1498123",
            "018-7452369", "013-7342541", "016-9874523", "015-6978541", "014-7853641",
            "017-2130145", "012-9845623", "018-6934521", "017-6923471", "014-9865312",
            "011-9874123", "012-9845621", "019-2598679", "012-1498923", "019-1379837"
        };

        String[] stuProgramme = { // Change to array of programme codes
            "RAC", "RBF", "RSW", "RDS", "RET",
            "RES", "RRT", "DLS", "DMT", "DMA",
            "DME", "RRM", "RAC", "RBF", "RSW",
            "RDS", "RET", "RES", "RRT", "DLS",
            "DMT", "DMA", "DME", "RRM", "RAC",
            "RBF", "RSW", "RDS", "RET", "RES",
            "RRT", "DLS", "DMT", "DMA", "DME",
            "RRM", "RAC", "RBF", "RSW", "RDS",
            "RET", "RES", "RRT", "DLS", "DMT",
            "DMA", "DME", "RRM", "RAC", "RSW"
        };

        for (int i = 0; i < stuname.length; i++) {
            ManageStudent.addStudent(stuID[i], stuname[i], stuIC[i], stucontact_number[i], stuProgramme[i]);
        }
    }

    public static void initializeRegistration() {
        RegisteredCourse[] regCourses = {
            new RegisteredCourse(1001, "BACS2023", "Main"),
            new RegisteredCourse(1001, "BACS1053", "Resit"),
            new RegisteredCourse(1002, "BACS2063", "Elective"),
            new RegisteredCourse(1002, "BACS2023", "Resit"),
            new RegisteredCourse(1003, "BACS2063", "Elective"),
            new RegisteredCourse(1003, "BACS2023", "Repeat"),
            new RegisteredCourse(1003, "BAIT2012", "Main")};
//            new RegisteredCourse(1004, "BACS1053", "Repeat"),
//            new RegisteredCourse(1004, "BACS2063", "Main"),
//            new RegisteredCourse(1005, "BACS2023", "Resit"),
//            new RegisteredCourse(1006, "BBBE2013", "Repeat"),
//            new RegisteredCourse(1006, "BBDT2123", "Elective"),
//            new RegisteredCourse(1007, "BAIT2073", "Elective"),
//            new RegisteredCourse(1008, "BBDT2123", "Resit"),
//            new RegisteredCourse(1009, "BBDT2123", "Main"),
//            new RegisteredCourse(1010, "BBBE2013", "Resit"),
//            new RegisteredCourse(1011, "BAIT2073", "Elective"),
//            new RegisteredCourse(1012, "BBDT2123", "Main")};

        for (RegisteredCourse regCourse : regCourses) {
            ManageRegistered.addRegistered(regCourse);
        }
    }

    public static void initializeTutorialGroup() {
        String[] groupIds = {
            "RACG1", "RACG2", "RACG3",
            "RBFG1", "RBFG2", "RBFG3",
            "RSWG1", "RSWG2", "RSWG3",};

        String[] programmeCodes = {
            "RAC", "RAC", "RAC",
            "RBF", "RBF", "RBF",
            "RSW", "RSW", "RSW",};

        for (int i = 0; i < groupIds.length; i++) {
            TutorialGroup group = new TutorialGroup(groupIds[i], ManageTutorialGroup.getProgrammeByCode(programmeCodes[i]));
            ManageTutorialGroup.addTutorialGroup(group, group.getProgramme());
        }
    }

    public static void initializeStudentGroup() {
        ListInterface<Student> studentList = ManageStudent.getStudentList();
        ListInterface<TutorialGroup> groupList = ManageTutorialGroup.getGroupList();

        for (int i = 0; i < studentList.size(); i++) {
            ManageTutorialGroup.addStudentsToTutorialGroup(groupList.get(1), studentList.get(i));
        }

    }

    public static void initializeTeamSubsytem() {

        ListInterface<TutorialGroup> groupList = ManageTutorialGroup.getGroupList();
        ListInterface<Course> courseList = ManageCourse.getCourseList();
        ListInterface<Student> studentList = ManageStudent.getStudentList();

        Team t1 = new Team("RACG1OOPT1");
        Team t2 = new Team("RACG1OOPT2");
        Team t3 = new Team("RACG1DBT1");
        Team t4 = new Team("RACG2DBT2");

        TutorialGroup g1 = groupList.get(0);
        //TutorialGroup g2 = groupList.get(1);

        Course c1 = courseList.get(0);
        Course c2 = courseList.get(1);

        //Set Team size for course
        c1.setTeamSize(10);
        c2.setTeamSize(4);

        ManageTutorialTeam.createTeam(t1, g1, c1);
        ManageTutorialTeam.createTeam(t2, g1, c1);
        ManageTutorialTeam.createTeam(t3, g1, c2);
        ManageTutorialTeam.createTeam(t4, g1, c2);

        for (int i = 0; i < 12; i++) {
            Student student = studentList.get(i);
            if (i < 3) {
                ManageStudentTeam.addStudentToTeam(student, t1);
            } else if (i >= 3 && i < 6) {
                ManageStudentTeam.addStudentToTeam(student, t2);
            } else if (i >= 6 && i < 9) {
                ManageStudentTeam.addStudentToTeam(student, t3);
            } else {
                ManageStudentTeam.addStudentToTeam(student, t4);
            }
        }
    }

}
