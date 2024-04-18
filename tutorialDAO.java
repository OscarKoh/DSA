/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import adt.ArrayList;
import adt.ListInterface;
import entity.TutorialGroup;
import java.io.*;

public class tutorialDAO {
    private String filename = "tutorialGroup.txt";
    
    public void saveToFile(ListInterface<TutorialGroup> tutorialGroup){
       
       File tutFile = new File(filename);
       
       if (!tutFile.exists()) {
            try {
                tutFile.createNewFile();
                System.out.println("File created: " + filename);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
       
       try{
           ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(tutFile));
           oos.writeObject(tutorialGroup);
           oos.close();
       }catch(IOException e){
           e.printStackTrace();
       }
    }
    
    public ListInterface<TutorialGroup> retrieveFromFile(){
       File tutFile = new File(filename);
       ListInterface<TutorialGroup> tutorialGroup = new ArrayList<>();
       
       try{
           ObjectInputStream ois = new ObjectInputStream(new FileInputStream(tutFile));
           tutorialGroup = (ListInterface<TutorialGroup>)(ois.readObject());
           ois.close();
       }catch(IOException e){
           e.printStackTrace();
       }catch(ClassNotFoundException e){
           System.out.println("Class not found: " + e.getMessage());
           e.printStackTrace();
       }
       
       return tutorialGroup;
    }
}