/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import DAO.programmeDAO;
import adt.ArrayList;
import adt.ListInterface;
import entity.Course;
import entity.Programme;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Loo Suk Zhen
 */
public class ManageProgramme {

    private static ListInterface<Programme> programmeList = new ArrayList<>();
    private static programmeDAO programmeDao = new programmeDAO("programme.dat");

    public static ListInterface<Programme> getProgrammeList() {
        return programmeList = programmeDao.retrieveFromFile();
    }

    public static boolean addProgramme(String programmeCode, String programmeName, int duration, String faculty) {
        if (contains(programmeCode, programmeName)) {
            System.out.println("This programme already exists.\n");
            return false;
        }

        Programme programme = new Programme(programmeCode.toUpperCase(), programmeName, duration, faculty);
        programmeList.add(programme);
        programmeDao.saveToFile(programmeList);
        return true;
    }

    public static boolean contains(String programmeCode, String name) {
        programmeCode = programmeCode.toUpperCase();

        for (int i = 0; i < programmeList.size(); i++) {
            if (programmeList.get(i).getProgrammeCode().equals(programmeCode)
                    || programmeList.get(i).getProgrammeName().equals(name)) {
                // The programme with the given programme code is found, so return true.
                return true;
            }
        }

        // The programme with the given programme code is not found in the programme list, so return false.
        return false;
    }

}