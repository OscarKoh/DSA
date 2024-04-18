package DAO;

import adt.ListInterface;
import entity.Course;
import java.io.*;
import adt.ArrayList;
//import java.util.logging.Level;
//import java.util.logging.Logger;

public class courseDAO {
    private String filename = "courses.dat";
    
    public void saveToFile(ListInterface<Course>courseList){
       
       File courseFile = new File(filename);
       
       if (!courseFile.exists()) {
            try {
                courseFile.createNewFile();
                System.out.println("File created: " + filename);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
       
       try{
           ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(courseFile));
           oos.writeObject(courseList);
           oos.close();
       }catch(IOException e){
           e.printStackTrace();
       }
    }
    
    public ListInterface<Course> retrieveFromFile(){
       File courseFile = new File(filename);
       ListInterface<Course> courseList = new ArrayList<>();
       
       try{
           ObjectInputStream ois = new ObjectInputStream(new FileInputStream(courseFile));
           courseList = (ListInterface<Course>)(ois.readObject());
           ois.close();
       }catch(IOException e){
           e.printStackTrace();
       }catch(ClassNotFoundException e){
           System.out.println("Class not found: " + e.getMessage());
           e.printStackTrace();
       }
       
       return courseList;
    }
    
    
   
    
    
}