package DAO;

import adt.ListInterface;
import entity.Course;
import java.io.*;
import adt.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class courseDAO {

    private static String fileName = "courses.txt";

    public static void saveToFile(ListInterface<Course> courseList) {
        File file = new File(fileName);
        try {
            if (!file.exists()) {
                file.createNewFile(); // Create a new file if it doesn't exist
            }
            ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(file));
            ooStream.writeObject(courseList);
            ooStream.close();
        } catch (IOException ex) {
            Logger.getLogger(courseDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("\nCannot save to file");
        }
    }

    public static ListInterface<Course> retrieveFromFile() {
        ListInterface<Course> courseList = new ArrayList<>();
        File file = new File(fileName);
        try (ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file))) {
            courseList = (ArrayList<Course>) oiStream.readObject();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(courseDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("\nNo such file.");
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(courseDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("\nError reading from file.");
        }
        return courseList;
    }
}
