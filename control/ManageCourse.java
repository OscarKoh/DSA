package control;

import DAO.courseDAO;
import entity.Programme;
import entity.Course;
import adt.ListInterface;
import adt.ArrayList;
import entity.Course.CourseType;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

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

    public static boolean isValidCourseCode(String courseCode) {
        String pattern = "^[A-Za-z]{4}\\d{4}$";
        // Compile the pattern into a regular expression object
        Pattern regex = Pattern.compile(pattern);
        return regex.matcher(courseCode).matches();
    }

    public static boolean addCourse(String courseCode, String courseName, int creditHour, ArrayList<Course.CourseType> courseTypes, double fee) {
        if (checkExistsInCourse(courseCode, courseName)) {
            System.out.println("This course already exists.\n");
            return false;
        }
        Course course = new Course(courseCode.toUpperCase(), courseName, courseTypes, creditHour, fee);
        courseList.add(course);
//        System.out.println("You are successfully added a new course.");
        courseDAO.saveToFile(courseList);
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
                courseDAO.saveToFile(courseList);
                return true;
            }
        }
        return false;
    }

    public static Course findCourse(String courseCode) {
        courseCode = courseCode.toUpperCase();

        for (int i = 0; i < courseList.size(); i++) {
            if (courseList.get(i).getCode().equals(courseCode)) {
                return courseList.get(i);
            }
        }
        return null;
    }

    public static boolean removeProgrammeFromCourse(int proNum, int courseNum) {
        Course course = courseList.get(courseNum - 1);
        Programme programme = course.getProgrammesList().get(proNum - 1);
        // Remove the course from the programme's course list
        int courseNumber = programme.getCourseList().search(course);
        programme.removeCourse(courseNumber);
        course.getProgrammesList().remove(proNum - 1);
        System.out.println("Programme removed from course successfully.");

        return true;
    }

    public static boolean addProgrammeToCourse(Programme programme, Course course) {
        if (course.getProgrammesList().search(programme) != -1) {
            System.out.println("Programme " + programme.getProgrammeName() + " is already associated with course " + course.getName() + "\n");
            return false;
        }
        if (programme.getCourseList().isFull()) {
            System.out.println("Maximum number of courses reached for programme " + programme.getProgrammeName()+"\n");
            return false;
        }
        if (course.getCreditHour() + programme.getCreditHour() > programme.getMaxTotalCreditHour()) {
            System.out.println("Adding " + programme.getProgrammeName() + " to " + course.getName() + " exceeds total credit hour limit." + "\n");
            return false;
        }
        course.getProgrammesList().add(programme);
        programme.addCourse(course);
        return true;

    }

    public static void amendCourseDetails(Programme programme, int num, String newCode, String newName,
            ArrayList<Course.CourseType> newTypesList, int newHours, double newFee) {
        Course courseToUpdate = programme.getCourseList().get(num - 1);

        // Ensure the course to update exists in the programme's course list
        if (courseToUpdate != null) {
            // Store the existing code, name, hours, fee, and types list
            String existingCode = courseToUpdate.getCode().toUpperCase();
            String existingName = courseToUpdate.getName();
            int existingHours = courseToUpdate.getCreditHour();
            double existingFee = courseToUpdate.getFee();
            ArrayList<Course.CourseType> existingTypesList;
            existingTypesList = courseToUpdate.getCourseTypes();
            // Calculate the difference in credit hours
            int hoursDifference = newHours - existingHours;

            // Check if changes are made
            boolean changesMade = !newCode.equalsIgnoreCase(existingCode)
                    || !newName.equalsIgnoreCase(existingName)
                    || newHours != existingHours
                    || Double.compare(newFee, existingFee) != 0
                    || !existingTypesList.equals(newTypesList);

            if (changesMade) {
                boolean codeExists = false;
                boolean nameExists = false;
                for (int i = 0; i < courseList.size(); i++) {
                    if (i != num - 1) { // Skip comparison for the course being updated
                        Course existingCourse = courseList.get(i);
                        if (!existingCourse.equals(courseToUpdate)) {
                            if (newCode.toUpperCase().equalsIgnoreCase(existingCourse.getCode().toUpperCase())) {
                                System.out.println("Error: This course code already exists in the system.");
                                codeExists = true;
                            } else if (newName.toUpperCase().equalsIgnoreCase(existingCourse.getName().toUpperCase())) {
                                System.out.println("Error: This course name already exists in the system.");
                                nameExists = true;
                            }
                        }
                    }
                }
                // Update course details
                if (!codeExists && !nameExists) {
                    courseToUpdate.setCode(newCode);
                    courseToUpdate.setName(newName);
                    courseToUpdate.setCourseTypes(newTypesList);
                    courseToUpdate.setCreditHour(newHours);
                    courseToUpdate.setFee(newFee);
                    int index = courseList.search(courseToUpdate);
                    courseList.amend(index, courseToUpdate);
                    courseDAO.saveToFile(courseList);
                    // Update the total credit hours of the programme
                    ManageProgramme.updateProgrammeCreditHours(hoursDifference, programme);
                    System.out.println("You have successfully amended the course details.");
                }
            } else {
                System.out.println("You did not make any updates.");
            }
        }
    }

    public static ListInterface<Course> availableCourses() {
        ListInterface<Course> availableCourses = new ArrayList<>();

        for (int i = 0; i < courseList.size(); i++) {
            Course course = courseList.get(i);

            // Check if the course has no associated programmes
            if (course.getProgrammesList() == null || course.getProgrammesList().isEmpty()) {
                availableCourses.add(course);
            }
        }
        return availableCourses;
    }

    public static ArrayList<Course.CourseType> courseType(String type, Course course) {
        ArrayList<Course.CourseType> newTypesList = new ArrayList<>();
        if (type.isEmpty()) {
            newTypesList = course.getCourseTypes(); // Keep the current value
        } else {
            String[] newTypesArray = type.split(",");
            newTypesList = new ArrayList<>();
            for (String newType : newTypesArray) {
                try {
                    Course.CourseType courseType = Course.CourseType.valueOf(newType.trim().toUpperCase());
                    newTypesList.add(courseType);
                } catch (IllegalArgumentException e) {
                    break;
                }
            }
        }
        return newTypesList;
    }

    public static void displayCourseDetails(Course course) {
        // Display course details
        System.out.println("\nCourse Details:");
        System.out.println("Course Code: " + course.getCode());
        System.out.println("Course Name: " + course.getName());
        ArrayList<Course.CourseType> courseTypes = course.getCourseTypes();
        if (courseTypes != null && !courseTypes.isEmpty()) {
            System.out.print("Course Type: ");
            ArrayList<CourseType> courseTypesList = course.getCourseTypes();
            StringBuilder courseTypesString = new StringBuilder();
            for (int j = 0; j < courseTypesList.size(); j++) {
                courseTypesString.append(courseTypesList.get(j));
                if (j < courseTypesList.size() - 1) {
                    courseTypesString.append(", ");
                }
            }
            System.out.println(courseTypesString);
        } 
        System.out.println("Credit Hour: " + course.getCreditHour());
        System.out.printf("Course Fee : RM%.2f\n", course.getFee()); // Format fee with 2 decimal places
    }

    public static String summaryReport(int minCreditHours) {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, d MMMM yyyy, h:mm a");
        String formattedDate = dateFormat.format(currentDate);
        StringBuilder reportBuilder = new StringBuilder();

        reportBuilder.append("\n==========================================================\n");
        reportBuilder.append(String.format("%-30s", "TUNKU ABDUL RAHMAN UNIVERSITY OF MANAGEMENT AND TECHNOLOGY\n"));
        reportBuilder.append("COURSE MANAGEMENT SUBSYSTEM\n\n");
        reportBuilder.append("Generated at: ").append(formattedDate).append("\n\n");
        reportBuilder.append("COURSE SUMMARY REPORT\n\n");
        reportBuilder.append(String.format("%-10s %-35s %-12s %s\n", "CODE", "COURSE NAME", "CREDIT HOUR", "FACULTIES/PROGRAMMES"));

        ListInterface<Course> sortedCourses = new ArrayList<>();
        for (int i = 0; i < courseList.size(); i++) {
            sortedCourses.add(courseList.get(i));
        }
        sortedCourses.sort("code");
        int totalCourses = sortedCourses.size();
        int[] programmeCountsPerCourse = new int[totalCourses];
        int[] facultyCountsPerCourse = new int[totalCourses];

        int includedCoursesCount = 0; // Counter for included courses

        for (int i = 0; i < totalCourses; i++) {
            Course course = sortedCourses.get(i);
            int programmeCount = course.getProgrammesList().size();
            programmeCountsPerCourse[i] = programmeCount;

            int uniqueFacultyCount = 0;
            ArrayList<String> uniqueFaculties = new ArrayList<>(); // Use a list to store unique faculties
            ListInterface<Programme> programmes = course.getProgrammesList();
            for (int j = 0; j < programmeCount; j++) {
                Programme programme = programmes.get(j);
                String faculty = programme.getFaculty();
                if (uniqueFaculties.search(faculty) == -1) {
                    uniqueFaculties.add(faculty);
                    uniqueFacultyCount++;
                }
            }
            facultyCountsPerCourse[i] = uniqueFacultyCount;

            if (course.getCreditHour() >= minCreditHours) {
                reportBuilder.append(String.format("%-10s %-40s %-14d %d / %d\n",
                        course.getCode(), course.getName(), course.getCreditHour(),
                        facultyCountsPerCourse[i], programmeCountsPerCourse[i]));
                includedCoursesCount++; // Increment the counter for included courses
            }
        }

        reportBuilder.append("\nTotal ").append(includedCoursesCount).append(" Courses "); // Use includedCoursesCount instead of totalCourses
        reportBuilder.append("\n----------------------------------------");
        // Find the maximum and minimum faculties offered
        int maxFaculties = 0; // Start with 0 since the minimum count is 0
        int minFaculties = Integer.MAX_VALUE;
        for (int i = 0; i < totalCourses; i++) {
            if (sortedCourses.get(i).getCreditHour() >= minCreditHours) {
                if (facultyCountsPerCourse[i] > maxFaculties) {
                    maxFaculties = facultyCountsPerCourse[i];
                }
                if (facultyCountsPerCourse[i] < minFaculties && facultyCountsPerCourse[i] > 0) { // Exclude counts of 0
                    minFaculties = facultyCountsPerCourse[i];
                }
            }
        }
        // Display course with highest faculties offered
        reportBuilder.append("\nHighest Faculties Offered:\n");
        for (int i = 0; i < totalCourses; i++) {
            if (sortedCourses.get(i).getCreditHour() >= minCreditHours && facultyCountsPerCourse[i] == maxFaculties) {
                Course course = sortedCourses.get(i);
                reportBuilder.append("->[").append(maxFaculties).append(" FACULTIES]")
                        .append("<").append(course.getCode()).append(">")
                        .append(" ").append(course.getName()).append("\n");
            }
        }

        reportBuilder.append("\nLowest Faculties Offered:\n");
        for (int i = 0; i < totalCourses; i++) {
            if (sortedCourses.get(i).getCreditHour() >= minCreditHours && facultyCountsPerCourse[i] == minFaculties
                    && minFaculties != maxFaculties) { // Check if faculties are not offered
                Course course = sortedCourses.get(i);
                reportBuilder.append("->[").append(minFaculties).append(" FACULTIES]")
                        .append("<").append(course.getCode()).append(">")
                        .append(" ").append(course.getName()).append("\n");
            }

        }
        if (minFaculties == maxFaculties) {
            reportBuilder.append("THERE IS NO LOWEST FACULTIES OFFERED\n");
        }
        reportBuilder.append("\n[NOTE:0 FACULTY OFFERED IS NOT COUNTED]");
        reportBuilder.append("\n----------------------------------------");
        // Find the maximum and minimum programmes offered
        int maxProgramme = 0; // Start with 0 since the minimum count is 0
        int minProgramme = Integer.MAX_VALUE;
        for (int i = 0; i < totalCourses; i++) {
            if (sortedCourses.get(i).getCreditHour() >= minCreditHours) {
                if (programmeCountsPerCourse[i] > maxProgramme) {
                    maxProgramme = programmeCountsPerCourse[i];
                }
                if (programmeCountsPerCourse[i] < minProgramme && programmeCountsPerCourse[i] > 0) { // Exclude counts of 0
                    minProgramme = programmeCountsPerCourse[i];
                }
            }
        }

        reportBuilder.append("\nHighest Programmes Offered:\n");
        for (int i = 0; i < totalCourses; i++) {
            if (programmeCountsPerCourse[i] == maxProgramme && sortedCourses.get(i).getCreditHour() >= minCreditHours) {
                Course course = sortedCourses.get(i);
                reportBuilder.append("->[").append(maxProgramme).append(" PROGRAMMES]").append("<")
                        .append(course.getCode()).append(">").append(" ").append(course.getName())
                        .append("\n");
            }
        }

        reportBuilder.append("\nLowest Programmes Offered:\n");
        for (int i = 0; i < totalCourses; i++) {
            if (programmeCountsPerCourse[i] == minProgramme && maxProgramme != minProgramme && sortedCourses.get(i).getCreditHour() >= minCreditHours) {
                Course course = sortedCourses.get(i);
                reportBuilder.append("->[").append(minProgramme).append(" PROGRAMMES]").append("<")
                        .append(course.getCode()).append(">").append(" ").append(course.getName())
                        .append("\n");
            }
        }

        if (maxProgramme == minProgramme) {
            reportBuilder.append("THERE IS NO LOWEST PROGRAMMES OFFERED\n");
        }
        reportBuilder.append("\n[NOTE:0 PROGRAMME OFFERED IS NOT COUNTED]");
        reportBuilder.append("\n----------------------------------------\n");
        reportBuilder.append("\nEND OF THE COURSE SUMMARY REPORT");

        return reportBuilder.toString();
    }

}
