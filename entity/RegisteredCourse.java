/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.io.Serializable;

/**
 *
 * @author Ow Yong Qing
 */
public class RegisteredCourse implements Serializable{
    private int studentID;
    private String code;
    private String status;
    
    public RegisteredCourse(){
    
    }
    
    public RegisteredCourse(int studentID, String code, String status){
        this.studentID = studentID;
        this.code = code;
        this.status = status;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    @Override
    public String toString() {
        return String.format(" %-5s %-8s %-8s", studentID, code, status);
    }
}
