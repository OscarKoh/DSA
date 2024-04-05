package DAO;

import adt.ArrayList;
import adt.ListInterface;
import entity.Programme;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Loo Suk Zhen
 */
public class programmeDAO {

    private final String fileName;

    public programmeDAO(String fileName) {
        this.fileName = fileName;
    }

    public void saveToFile(ListInterface<Programme> programmeList) {
        File file = new File(fileName);
        try {
            if (!file.exists()) {
                file.createNewFile(); // Create a new file if it doesn't exist
            }
            ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(file));
            ooStream.writeObject(programmeList);
            ooStream.close();
        } catch (IOException ex) {
            Logger.getLogger(programmeDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("\nCannot save to file");
        }
    }

    public ListInterface<Programme> retrieveFromFile() {
        ListInterface<Programme> programmeList = new ArrayList<>();
        File file = new File(fileName);
        try (ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file))) {
            programmeList = (ArrayList<Programme>) oiStream.readObject();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(programmeDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("\nNo such file.");
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(programmeDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("\nError reading from file.");
        }
        return programmeList;
    }
}
