/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.ArrayList;
import adt.ListInterface;
import entity.Course;
import entity.Programme;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 *
 * @author Loo Suk Zhen
 */
public class ManageProgramme {

    private static ListInterface<Programme> programmeList = new ArrayList<>();
//    private static programmeDAO programmeDao = new programmeDAO("programme.dat");
//

    public static ListInterface<Programme> getProgrammeList() {
        return programmeList;
    }

    public static boolean isValidProgrammeCode(String programmeCode) {
        String pattern = "^[A-Za-z]{3}$";
        // Compile the pattern into a regular expression object
        Pattern regex = Pattern.compile(pattern);
        return regex.matcher(programmeCode).matches();
    }

    public static boolean isValidFaculty(String faculty) {
        String pattern = "^[A-Za-z]{4}$";
        // Compile the pattern into a regular expression object
        Pattern regex = Pattern.compile(pattern);
        return regex.matcher(faculty).matches();
    }

    public static boolean addProgramme(String programmeCode, String programmeName, int duration, String faculty, int maxCreditHour) {
        if (contains(programmeCode, programmeName)) {
            System.out.println("This programme already exists.\n");
            return false;
        }
        Programme programme = new Programme(programmeCode.toUpperCase(), programmeName,
                duration, faculty, maxCreditHour);
        programmeList.add(programme);
//        programmeDao.saveToFile(programmeList);
        return true;
    }

    public static boolean contains(String programmeCode, String name) {
        programmeCode = programmeCode.toUpperCase();

        for (int i = 0; i < programmeList.size(); i++) {
            if (programmeList.get(i).getProgrammeCode().equals(programmeCode)
                    || programmeList.get(i).getProgrammeName().equals(name)) {
                return true;
            }
        }
        // The programme with the given programme code is not found in the programme list, so return false.
        return false;
    }

    public static void updateProgrammeCreditHours(int hoursDifference, Programme programme) {
        int currentCreditHours = programme.getCreditHour();
        programme.setCreditHour(currentCreditHours + hoursDifference);
    }

    public static Programme findProgrammeByName(String progName) {
        for (int i = 0; i < programmeList.size(); i++) {
            if (programmeList.get(i).getProgrammeName().equals(progName)) {
                return programmeList.get(i);
            }
        }
        return null;
    }

    public static boolean removeProgramme(int proNum) {
        Programme programmeToRemove = programmeList.get(proNum - 1);

        for (int i = 0; i < programmeList.size(); i++) {
            Programme programme = programmeList.get(i);
            if (programme.equals(programmeToRemove)) {
                ListInterface<Course> courseList = programme.getCourseList();
                for (int j = 0; j < courseList.size(); j++) {
                    Course course = courseList.get(j);
                    int programmes = course.getProgrammesList().search(programmeToRemove);
                    // Remove the course from each programme's course list
                    course.getProgrammesList().remove(programmes);
                }
                programmeList.remove(i);
//                programmeDao.saveToFile(programmeList);
                return true;
            }
        }
        return false;
    }

    public static boolean removeCourseProgramme(int courseNum, int proNum) {
        Programme programme = programmeList.get(proNum - 1);
        Course course = programme.getCourseList().get(courseNum - 1);
        int programmeNum = course.getProgrammesList().search(programme);
        course.getProgrammesList().remove(programmeNum);
        programme.removeCourse(courseNum - 1);
        System.out.println("Course removed from programme successfully.");
        return true;
    }

    public static void displayProgrammeDetails(Programme programme) {
        System.out.println("\nProgramme Details:");
        System.out.println("Programme Code: " + programme.getProgrammeCode());
        System.out.println("Programme Name: " + programme.getProgrammeName());
        System.out.println("Faculty: " + programme.getFaculty());
        System.out.println("Programme Duration In Months: " + programme.getProgrammeDurationInMonths());
    }

    public static String listAllCoursesForProgramme(Programme programme) {
        ListInterface<Course> programmeCourses = programme.getCourseList();
        if (programmeCourses == null || programmeCourses.isEmpty()) {
            return "No courses found for the programme: " + programme.getProgrammeName();
        }

        programmeCourses.sort("code"); // Sort by course code

        StringBuilder allCourses = new StringBuilder();
        int maxCourseNameLength = 0;

        allCourses.append("\nProgramme: ").append(programme.getProgrammeName()).append("\n\n");

        // Find the maximum length of course name for aligning purposes
        for (int i = 0; i < programmeCourses.size(); i++) {
            String courseName = programmeCourses.get(i).getName();
            maxCourseNameLength = Math.max(maxCourseNameLength, courseName.length());
        }

        allCourses.append(String.format("%-10s %-" + (maxCourseNameLength + 5) + "s %-12s\n", "CODE", "COURSE NAME", "CREDIT HOUR"));

        for (int i = 0; i < programmeCourses.size(); i++) {
            Course currentCourse = programmeCourses.get(i);
            String courseName = currentCourse.getName();
            String courseCode = currentCourse.getCode();
            int creditHour = currentCourse.getCreditHour();

            // Format the course name with padding for alignment
            String formattedCourseName = String.format("%-" + (maxCourseNameLength + 11) + "s", courseName);

            allCourses.append(courseCode).append("   ").append(formattedCourseName)
                    .append(creditHour).append("\n");
        }
        allCourses.append("\nTotal Credit Hours: ").append(programme.getCreditHour()).append("\n");
        return allCourses.toString();
    }

    public static String listCoursesTakenByFaculties(String faculty) {
        ListInterface<Course> coursesTaken = new ArrayList<>();

        for (int i = 0; i < programmeList.size(); i++) {
            Programme programme = programmeList.get(i);

            if (programme != null && programme.getFaculty().equalsIgnoreCase(faculty)) {
                // Retrieve the list of courses for this programme
                ListInterface<Course> courses = programme.getCourseList();

                for (int j = 0; j < courses.size(); j++) {
                    Course course = courses.get(j);
                    if (course != null && coursesTaken.search(course) == -1) {
                        coursesTaken.add(course); // Add the course to coursesTaken list
                    }
                }
            }
        }
        // Sort the courses by fee
        coursesTaken.sort("fee");
        coursesTaken.reverse();

        StringBuilder allCourses = new StringBuilder();
        allCourses.append("\nList of Courses taken by ").append(faculty).append("\n\n");

        allCourses.append(String.format("%-10s %-40s %3s\n", "CODE", "COURSE NAME", "FEE(RM)"));

        for (int i = 0; i < coursesTaken.size(); i++) {
            Course course = coursesTaken.get(i);
            String courseName = course.getName();
            String courseCode = course.getCode();
            double courseFee = course.getFee();

            // Append course details with formatting for alignment
            allCourses.append(courseCode).append("   ").append(courseName);
            int spacesNeeded = 49 - (courseCode.length() + courseName.length());
            for (int s = 0; s < spacesNeeded; s++) {
                allCourses.append(" ");
            }
            allCourses.append(String.format("%.2f", courseFee)).append("\n");
        }
        return allCourses.toString();
    }

    public static String prosummaryReport(double minTotalFee) {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, d MMMM yyyy, h:mm a");
        String formattedDate = dateFormat.format(currentDate);

        // Copy the original programmeList to a temporary list for sorting
        ListInterface<Programme> sortedProgrammes = new ArrayList<>();
        for (int i = 0; i < programmeList.size(); i++) {
            // Assuming programmeList is a valid list containing Programme objects
            sortedProgrammes.add(programmeList.get(i));
        }

        // Sort the temporary list by credit hours
        sortedProgrammes.sort("creditHour");
        // Reverse the order to get descending
        sortedProgrammes.reverse();

        // Generate report header
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("==========================================================\n");
        reportBuilder.append("TUNKU ABDUL RAHMAN UNIVERSITY OF MANAGEMENT AND TECHNOLOGY\n\n");
        reportBuilder.append("PROGRAMME SUMMARY REPORT\n\n");
        reportBuilder.append("Generated at: ").append(formattedDate).append("\n\n");
        reportBuilder.append(String.format("%-20s%-50s%-15s%-20s%-15s\n", "PROGRAMME CODE", "PROGRAMME NAME",
                "TOTAL COURSES", "TOTAL CREDIT HOURS", "TOTAL FEE(RM)"));

        // Initialize variables for highest and lowest values
        double highestTotalFee = Double.MIN_VALUE;
        double lowestTotalFee = Double.MAX_VALUE;
        int highestTotalCourses = Integer.MIN_VALUE;
        int lowestTotalCourses = Integer.MAX_VALUE;
        int includedProgramsCount = 0;

        for (int i = 0; i < sortedProgrammes.size(); i++) {
            Programme programme = sortedProgrammes.get(i);
            ListInterface<Course> coursesOffered = programme.getCourseList();

            // Calculate total courses offered, total credit hours, and total fee
            int totalCoursesOffered = coursesOffered.size();
            int totalCreditHours = programme.getCreditHour();
            double totalFee = 0.00;
            for (int j = 0; j < totalCoursesOffered; j++) {
                Course course = coursesOffered.get(j);
                totalFee += course.getFee();
            }

            // Update highest and lowest values
            highestTotalFee = Math.max(highestTotalFee, totalFee);
            if (totalFee >= minTotalFee) {
                lowestTotalFee = Math.min(lowestTotalFee, totalFee);
                // Find the lowest number of courses offered among programmes with fees greater than or equal to minTotalFee
                lowestTotalCourses = Math.min(lowestTotalCourses, totalCoursesOffered);
                includedProgramsCount++;
            }
            highestTotalCourses = Math.max(highestTotalCourses, totalCoursesOffered);

            // Append programme details
            if (totalFee >= minTotalFee) {
                reportBuilder.append(String.format("%-20s%-55s%-18d%-16d%-15.2f\n", programme.getProgrammeCode(),
                        programme.getProgrammeName(), totalCoursesOffered, totalCreditHours, totalFee));
            }
        }

        reportBuilder.append("\nTotal ").append(includedProgramsCount).append(" Programmes."); // Use includedCoursesCount instead of totalCourses
        reportBuilder.append("\n----------------------------------------");
        // Append highest courses offered
        reportBuilder.append("\nHighest Courses Offered:\n");
        for (int i = 0; i < sortedProgrammes.size(); i++) {
            Programme programme = sortedProgrammes.get(i);
            ListInterface<Course> coursesOffered = programme.getCourseList();
            int totalCoursesOffered = coursesOffered.size();
            if (totalCoursesOffered == highestTotalCourses) {
                reportBuilder.append("->[").append(highestTotalCourses).append(" Courses]").append("<")
                        .append(programme.getProgrammeCode()).append(">").append(" ")
                        .append(programme.getProgrammeName()).append("\n");
            }
        }

        // Append lowest courses offered
        ListInterface<Programme> pro = new ArrayList<>();

        reportBuilder.append("\nLowest Courses Offered:\n");
        for (int i = 0; i < sortedProgrammes.size(); i++) {
            if (sortedProgrammes.get(i).getCourseList().size() == lowestTotalCourses && highestTotalCourses != lowestTotalCourses) {
                Programme programme = sortedProgrammes.get(i);
                pro.add(programme);
                reportBuilder.append("->[").append(lowestTotalCourses).append(" Courses]").append("<")
                        .append(programme.getProgrammeCode()).append(">").append(" ")
                        .append(programme.getProgrammeName()).append("\n");
            }
        }
        // Check if no programs with the lowest course count were found
        if (highestTotalCourses == lowestTotalCourses) {
            reportBuilder.append("THERE IS NO LOWEST COURSE OFFERED.\n");
        }

        reportBuilder.append("\n[NOTE:0 COURSE OFFERED IS NOT COUNTED]");
        reportBuilder.append("\n----------------------------------------");
        // Append highest total fee
        reportBuilder.append("\nHighest Total Fee(RM):\n");
        for (int i = 0; i < sortedProgrammes.size(); i++) {
            Programme programme = sortedProgrammes.get(i);
            ListInterface<Course> coursesOffered = programme.getCourseList();
            double totalFee = 0.0;
            for (int j = 0; j < coursesOffered.size(); j++) {
                totalFee += coursesOffered.get(j).getFee();
            }
            if (totalFee == highestTotalFee) {
                reportBuilder.append("->[RM").append(String.format("%.2f", highestTotalFee)).append("]").append("<")
                        .append(programme.getProgrammeCode()).append(">").append(" ").append(programme.getProgrammeName())
                        .append("\n");
            }
        }
        reportBuilder.append("\nLowest Total Fee(RM):\n");

        if (lowestTotalFee != Double.MAX_VALUE) {
            for (int i = 0; i < sortedProgrammes.size(); i++) {
                Programme programme = sortedProgrammes.get(i);
                ListInterface<Course> coursesOffered = programme.getCourseList();
                double totalFee = 0.0;
                for (int j = 0; j < coursesOffered.size(); j++) {
                    totalFee += coursesOffered.get(j).getFee();
                }
                if (totalFee == lowestTotalFee) {
                    reportBuilder.append("->[RM").append(String.format("%.2f", lowestTotalFee)).append("]").append("<")
                            .append(programme.getProgrammeCode()).append(">").append(" ").append(programme.getProgrammeName())
                            .append("\n");
                    break; // Only append the lowest once
                }
            }
        } else {
            reportBuilder.append("THERE IS NO LOWEST TOTAL FEE.");
        }

        // Append lowest total fee
        reportBuilder.append("\n[NOTE:0.00 FEE IS NOT COUNTED]");
        reportBuilder.append("\n----------------------------------------\n");
        reportBuilder.append("\nEND OF PROGRAMME SUMMARY REPORT\n");
        return reportBuilder.toString();
    }

}
