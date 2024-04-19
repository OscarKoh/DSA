package entity;

import adt.ArrayList;
import adt.ListInterface;
import java.io.Serializable;
import java.lang.reflect.Field;

/**
 *
 * @author Loo Suk Zhen
 */
public class Programme implements Serializable {

    private int programmeDurationInMonths;
    private int creditHour = 0;
    private String programmeCode;
    private String programmeName;
    private String faculty;
    //private int maxTotalCreditHour;
    private ListInterface<TutorialGroup> groupList = new ArrayList<>();
    private ListInterface<Course> courseList = new ArrayList<>(6);
    private ListInterface<Student> studentList = new ArrayList<>();

    public Programme() {

    }

//    public Programme(String programmeCode, String programmeName, int programmeDurationInMonths, String faculty, int maxTotalCreditHour) {
//        this.programmeCode = programmeCode;
//        this.programmeName = programmeName;
//        this.programmeDurationInMonths = programmeDurationInMonths;
//        this.faculty = faculty;
//        this.maxTotalCreditHour = maxTotalCreditHour;
//    }

    public Programme(String programmeCode, String programmeName, int programmeDurationInMonths, String faculty) {
        this.programmeCode = programmeCode;
        this.programmeName = programmeName;
        this.programmeDurationInMonths = programmeDurationInMonths;
        this.faculty = faculty;
        //this.maxTotalCreditHour = maxTotalCreditHour;
    }

    public int getProgrammeDurationInMonths() {
        return programmeDurationInMonths;
    }

//    public int getMaxTotalCreditHour() {
//        return maxTotalCreditHour;
//    }
//
//    public void setMaxTotalCreditHour(int maxTotalCreditHour) {
//        this.maxTotalCreditHour = maxTotalCreditHour;
//    }

    public void setProgrammeDurationInMonths(int programmeDurationInMonths) {
        this.programmeDurationInMonths = programmeDurationInMonths;
    }

    public int getCreditHour() {
        return creditHour;
    }

    public void setCreditHour(int creditHour) {
        this.creditHour = creditHour;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getProgrammeCode() {
        return programmeCode;
    }

    public void setProgrammeCode(String programmeCode) {
        this.programmeCode = programmeCode;
    }

    public String getProgrammeName() {
        return programmeName;
    }

    public void setProgrammeName(String programmeName) {
        this.programmeName = programmeName;
    }

    public ListInterface<TutorialGroup> getGroupLists() {
        return groupList;
    }

    public void setTutorialGroup(ListInterface<TutorialGroup> groupList) {
        this.groupList = groupList;
    }

    public ListInterface<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(ListInterface<Course> courseList) {
        this.courseList = courseList;
    }

    public ListInterface<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(ListInterface<Student> studentList) {
        this.studentList = studentList;
    }

    public boolean addCourse(Course course) {
        courseList.add(course);
        creditHour += course.getCreditHour();
        return true;
    }

    public boolean removeCourse(int courseNum) {
        Course courseToRemove = courseList.get(courseNum);
        if (courseToRemove != null) {
            courseList.remove(courseNum);
            creditHour -= courseToRemove.getCreditHour(); // Update credit hour
            return true; // Return true if removal was successful
        }

        return false; // Return false if removal failed
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Programme)) {
            return false;
        }

        return ((Programme) o).getProgrammeCode().equals(programmeCode);
    }

    public int compareTo(Programme anotherProgramme, String... attributeNames) throws Exception {
        int result = 0;

        for (String attributeName : attributeNames) {
            try {
                Field field = getClass().getDeclaredField(attributeName);

                if (!Comparable.class.isAssignableFrom(field.getType())) {
                    if (attributeName.equals("creditHour")) {
                        int thisCreditHour = this.creditHour;
                        int otherCreditHour = anotherProgramme.creditHour;
                        result = Integer.compare(thisCreditHour, otherCreditHour);
                        if (result != 0) {
                            return result; // Return comparison result if fees are not equal
                        }
                    } else {
                        throw new IllegalArgumentException("Attribute is not comparable: " + attributeName);
                    }
                } else {

                    result = ((Comparable<Object>) field.get(this))
                            .compareTo((Comparable<Object>) field.get(anotherProgramme));

                    if (result != 0) {
                        return result; // Attributes are not equal, return comparison result
                    }
                }
            } catch (NoSuchFieldException e) {
                throw new IllegalArgumentException("Invalid attribute name: " + attributeName, e);
            }
        }

        return result; // All attributes are equal
    }

    @Override
    public String toString() {
        return String.format("Programme Code      : %s\n"
                + "Programme Name      : %s\n"
                + "Faculty             : %s\n"
                + "Duration            : %d months\n"
                + "Total Credit hour   : %d", programmeCode, programmeName, faculty, programmeDurationInMonths, creditHour);
    }

}
