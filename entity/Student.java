/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import adt.ListInterface;
import adt.ArrayList;
import java.io.Serializable;

/**
 *
 * @author Ow Yong Qing
 */
public class Student implements Serializable {
    private int studentID;
    private String name;
    private String IC;
    private String contact_number;
    private ListInterface<Course> courses = new ArrayList<>();
    
    public Student(){
    }
    
    public Student(int studentID, String name, String IC, String contact_number){
        this.studentID = studentID;
        this.name = name;
        this.IC = IC;
        this.contact_number = contact_number;
    }

    public ListInterface<Course> getCourses() {
        return courses;
    }

    public void setCourses(ListInterface<Course> courses) {
        this.courses = courses;
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
    public String toString() {
        return String.format("  %-10s %-30s %-15s %-13s", studentID, name, IC, contact_number);
    }   
}
