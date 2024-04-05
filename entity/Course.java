package entity;

import TeachingAss.Tutor;
import Student.Student;
import adt.ArrayList;
import adt.ListInterface;
import java.io.Serializable;

/**
 *
 * @author Loo Suk Zhen
 */
public class Course implements Serializable {

    private String code;
    private String name;
    private ListInterface<Programme> programmesList = new ArrayList<>();
    private ArrayList<CourseType> courseTypes = new ArrayList<>();
    private String status;
    private int creditHour;
    private double fee;
    private ListInterface<Student> studentList = new ArrayList<>();
    private ListInterface<Tutor> tutorList = new ArrayList<>();

    public Course(String code, String name, ArrayList<CourseType> courseTypes, String status, int creditHour, double fee) {
        this.code = code;
        this.name = name;
        this.status = status;
        this.creditHour = creditHour;
        this.courseTypes = courseTypes;
        this.fee = fee;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public ArrayList<CourseType> getCourseTypes() {
        return courseTypes;
    }

    public void setCourseTypes(ArrayList<CourseType> courseTypes) {
        this.courseTypes = courseTypes;
    }

    public enum CourseType {
        LECTURE,
        TUTORIAL,
        PRACTICAL
    }

    public ListInterface<Programme> getProgrammesList() {
        return programmesList;
    }

    public void setProgrammesList(ListInterface<Programme> programmesList) {
        this.programmesList = programmesList;
    }

    public ListInterface<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(ListInterface<Student> studentList) {
        this.studentList = studentList;
    }

    public ListInterface<Tutor> getTutorList() {
        return tutorList;
    }

    public void setTutorList(ListInterface<Tutor> tutorList) {
        this.tutorList = tutorList;
    }

    public String getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }

    public int getCreditHour() {
        return creditHour;
    }

    public String getName() {
        return name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCreditHour(int creditHour) {
        this.creditHour = creditHour;
    }

    /**
     *
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Course)) {
            return false;
        }

        return ((Course) object).code.equals(code);
    }

    public String toString() {
        ArrayList<CourseType> courseTypes = getCourseTypes();
        StringBuilder courseTypesString = new StringBuilder();
        for (int j = 0; j < courseTypes.size(); j++) {
            courseTypesString.append(courseTypes.get(j));
            if (j < courseTypes.size() - 1) {
                courseTypesString.append(", ");
            }
        }
        return String.format("Course Code      : %s\n"
                + "Course Name      : %s\n"
                + "Course Status    : %s\n"
                + "Course Type      : %s\n"
                + "Credit Hour      : %s\n"
                + "Course Fee       : %.2f\n", code, name, status, courseTypesString.toString(), creditHour, fee);
    }

}
