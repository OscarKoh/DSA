/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import adt.ListInterface;
import adt.ArrayList;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Ow Yong Qing
 */
public class Student implements Serializable {

    private int studentID;
    private String name;
    private String IC;
    private String contact_number;
    private String programme;
    private TutorialGroup group;
    private ListInterface<Course> courses = new ArrayList<>();
    private ListInterface<RegisteredCourse> registeredCourses;
    private ListInterface<Team> teamList;

    public Student() {
    }

    public Student(int studentID, String name, String IC, String contact_number, String programme) {
        this.studentID = studentID;
        this.name = name;
        this.IC = IC;
        this.contact_number = contact_number;
        this.programme = programme;
        this.registeredCourses = new ArrayList<>();
        this.teamList = new ArrayList<>();
    }

    public String getProgramme() {
        return programme;
    }

    public void setProgramme(String programme) {
        this.programme = programme;
    }

    public ListInterface<Course> getCourses() {
        return courses;
    }

    public void setCourses(ListInterface<Course> courses) {
        this.courses = courses;
    }

    public ListInterface<RegisteredCourse> getRegisteredCourses() {
        return registeredCourses;
    }

    public void setRegisteredCourses(ListInterface<RegisteredCourse> registeredCourses) {
        this.registeredCourses = registeredCourses;
    }

    public ListInterface<Team> getTeamLists() {
        return teamList;
    }
    
    public void setGroup(TutorialGroup group){
        this.group = group;
    }

    public TutorialGroup getGroup() {
        return group;
    }

    public String getIC() {
        return IC;
    }

    public void setIC(String IC) {
        this.IC = IC;
    }

    public int getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }
    
    

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Student other = (Student) obj;
        return this.studentID == other.studentID;
    }

    @Override
    public int hashCode() {
        // Calculating the hash code based on all fields
        int result = studentID;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (IC != null ? IC.hashCode() : 0);
        result = 31 * result + (contact_number != null ? contact_number.hashCode() : 0);
        result = 31 * result + (programme != null ? programme.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("  %-10s %-30s %-15s %-13s %-30s", studentID, name, IC, contact_number, programme);
    }
}
