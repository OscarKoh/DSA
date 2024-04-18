package DAO;

import adt.ArrayList;
import adt.ListInterface;
import entity.Tutor;
import java.io.*;

public class tutorDAO{
    private String filename = "tutors.dat";
    
    public void saveToFile(ListInterface<Tutor> tutorList){
       
       File tutorFile = new File(filename);
       
       if (!tutorFile.exists()) {
            try {
                tutorFile.createNewFile();
                System.out.println("File created: " + filename);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
       
       try{
           ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(tutorFile));
           oos.writeObject(tutorList);
           oos.close();
       }catch(IOException e){
           e.printStackTrace();
       }
    }
    
    public ListInterface<Tutor> retrieveFromFile(){
       File tutorFile = new File(filename);
       ListInterface<Tutor> tutorList = new ArrayList<>();
       
       try{
           ObjectInputStream ois = new ObjectInputStream(new FileInputStream(tutorFile));
           tutorList = (ListInterface<Tutor>)(ois.readObject());
           ois.close();
       }catch(IOException e){
           e.printStackTrace();
       }catch(ClassNotFoundException e){
           System.out.println("Class not found: " + e.getMessage());
           e.printStackTrace();
       }
       
       return tutorList;
    }
    
    
   
    
    
}
