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
    private String email;
    private String contact_number;
//    private String program;
    private String status;
    private double bill;
//    private ListInterface<Programme> programmesList = new Entity.ArrayList<>();
    private ListInterface<Course> courses = new ArrayList<>();
    
    public Student(){
    }
    
    public Student(int studentID, String name, String IC, String email, String contact_number, String status, double bill){
        this.studentID = studentID;
        this.name = name;
        this.IC = IC;
        this.email = email;
        this.contact_number = contact_number;
//        this.program = program;
        this.status = status;
        this.bill = bill;
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
    
    

    
//    public ListInterface<Programme> getProgrammesList() {
//        return programmesList;
//    }
//
//    public void setProgrammesList(ListInterface<Programme> programmesList) {
//        this.programmesList = programmesList;
//    }

    public int getStudentID() {
        return studentID;
    }
    
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getContact_number() {
        return contact_number;
    }

//    public String getStuProgram() {
//        return program;
//    }
    
    public String getStatus() {
        return status;
    }

    public double getBill() {
        return bill;
    }
    

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

//    public void setprogram(String program) {
//        this.program = program;
//    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    public void setBill(double bill) {
        this.bill = bill;
    }
    
    @Override
    public String toString() {
        return String.format("  %-10s %-30s %-15s %-20s %-13s %-9s %-5s", studentID, name, IC, email, contact_number, status, bill);
    }
    
    
}
