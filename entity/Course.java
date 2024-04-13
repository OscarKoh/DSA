package entity;

import Student.Student;
import adt.ArrayList;
import adt.ListInterface;
import java.io.Serializable;
import java.lang.reflect.Field;

/**
 *
 * @author Loo Suk Zhen
 */
public class Course implements Serializable {

    private String code;
    private String name;
    private ListInterface<Programme> programmesList = new ArrayList<>();
    private ArrayList<CourseType> courseTypes = new ArrayList<>();
    private int creditHour;
    private double fee;
    private ListInterface<Student> registerStudentList = new ArrayList<>();
    private ListInterface<Tutor> tutorList = new ArrayList<>();

    public Course(){
        
    }
    public Course(String code, String name, ArrayList<CourseType> courseTypes, int creditHour, double fee) {
        this.code = code;
        this.name = name;
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

    public ListInterface<Student> getRegisterStudentList() {
        return registerStudentList;
    }

    public void setRegisterStudentList(ListInterface<Student> registerStudentList) {
        this.registerStudentList = registerStudentList;
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

    public int compareTo(Course anotherCourse, String... attributeNames) throws Exception {
        int result = 0;

        for (String attributeName : attributeNames) {
            try {
                Field field = getClass().getDeclaredField(attributeName);

                if (!Comparable.class.isAssignableFrom(field.getType())) {
                    if (attributeName.equals("fee")) {
                        double thisFee = this.fee;
                        double otherFee = anotherCourse.fee;
                        result = Double.compare(thisFee, otherFee);
                        if (result != 0) {
                            return result; // Return comparison result if fees are not equal
                        }
                    } else {
                        throw new IllegalArgumentException("Attribute is not comparable: " + attributeName);
                    }
                } else {
                    result = ((Comparable<Object>) field.get(this))
                            .compareTo((Comparable<Object>) field.get(anotherCourse));
                    if (result != 0) {
                        return result; // Return comparison result if attributes are not equal
                    }
                }
            } catch (NoSuchFieldException e) {
                throw new IllegalArgumentException("Invalid attribute name: " + attributeName, e);
            }
        }

        return result; // All attributes are equal
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
                + "Course Type      : %s\n"
                + "Credit Hour      : %s\n"
                + "Course Fee       : %.2f\n", code, name, courseTypesString.toString(), creditHour, fee);
    }

}
