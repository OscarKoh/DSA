package entity;

import TutorialGrp.TutorialGrp;
import adt.ArrayList;
import adt.ListInterface;
import java.io.Serializable;

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
    private ListInterface<TutorialGrp> tutorialGroup = new ArrayList<>();
    private ListInterface<Course> courseList = new ArrayList<>();

    public Programme(String programmeCode, String programmeName, int programmeDurationInMonths, String faculty) {
        this.programmeCode = programmeCode;
        this.programmeName = programmeName;
        this.programmeDurationInMonths = programmeDurationInMonths;
        this.faculty = faculty;
    }

    public int getProgrammeDurationInMonths() {
        return programmeDurationInMonths;
    }

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

    public ListInterface<TutorialGrp> getTutorialGroup() {
        return tutorialGroup;
    }

    public void setTutorialGroup(ListInterface<TutorialGrp> tutorialGroup) {
        this.tutorialGroup = tutorialGroup;
    }

    public ListInterface<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(ListInterface<Course> courseList) {
        this.courseList = courseList;
    }

    public boolean addCourse(Course course) {
        if (courseList.add(course)) {
            creditHour += course.getCreditHour();
            return true;
        }

        return false;
    }

    public boolean removeCourse(Course course) {
        if (courseList.remove(course)) {
            creditHour -= course.getCreditHour();
            return true;
        }

        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Programme)) {
            return false;
        }

        return ((Programme) o).getProgrammeCode().equals(programmeCode);
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
