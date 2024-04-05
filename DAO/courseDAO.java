package DAO;

import adt.ListInterface;
import entity.Course;
import java.io.*;
import adt.ArrayList;
/**
 *
 * @author Loo Suk Zhen
 */
public class courseDAO {

    private final String fileName;

    public courseDAO(String fileName) {
        this.fileName = fileName;
    }

    public void saveToFile(ListInterface<Course> courseList) {
        try (ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            ooStream.writeObject(courseList);
            System.out.println("Data saved successfully.");
        } catch (IOException ex) {
            System.err.println("Error saving data to file: " + ex.getMessage());
        }
    }

    public ListInterface<Course> retrieveFromFile() {
        ListInterface<Course> courseList = new ArrayList<>();
        try (ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(fileName))) {
            courseList = (ArrayList<Course>) oiStream.readObject();
            System.out.println("Data retrieved successfully.");
        } catch (FileNotFoundException ex) {
            System.err.println("File not found: " + ex.getMessage());
        } catch (IOException | ClassNotFoundException ex) {
            System.err.println("Error reading data from file: " + ex.getMessage());
        }
        return courseList;
    }
}
