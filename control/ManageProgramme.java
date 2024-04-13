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
/**
 *
 * @author Loo Suk Zhen
 */
public class ManageProgramme {

    private static ListInterface<Programme> programmeList = new ArrayList<>();
//    private static programmeDAO programmeDao = new programmeDAO("programme.dat");
//
    public static ListInterface<Programme> getProgrammeList() {
        return programmeList;
    }
    
    public static boolean addProgramme(String programmeCode, String programmeName, int duration, String faculty) {
        if (contains(programmeCode, programmeName)) {
            System.out.println("This programme already exists.\n");
            return false;
        }
        Programme programme = new Programme(programmeCode.toUpperCase(), programmeName, duration, faculty);
        programmeList.add(programme);
//        programmeDao.saveToFile(programmeList);
        return true;
    }

    public static boolean contains(String programmeCode, String name) {
        programmeCode = programmeCode.toUpperCase();

        for (int i = 0; i < programmeList.size(); i++) {
            if (programmeList.get(i).getProgrammeCode().equals(programmeCode)
                    || programmeList.get(i).getProgrammeName().equals(name)) {
                return true;
            }
        }
        // The programme with the given programme code is not found in the programme list, so return false.
        return false;
    }


    public static Programme findProgrammeByName(String progName) {
        for (int i = 0; i < programmeList.size(); i++) {
            if (programmeList.get(i).getProgrammeName().equals(progName)) {
                return programmeList.get(i);
            }
        }
        return null;
    }

    public static boolean removeProgramme(int proNum) {
        Programme programmeToRemove = programmeList.get(proNum - 1);

        for (int i = 0; i < programmeList.size(); i++) {
            Programme programme = programmeList.get(i);
            if (programme.equals(programmeToRemove)) {
                ListInterface<Course> courseList = programme.getCourseList();
                for (int j = 0; j < courseList.size(); j++) {
                    Course course = courseList.get(j);
                    int programmes = course.getProgrammesList().search(programmeToRemove);
                    // Remove the course from each programme's course list
                    course.getProgrammesList().remove(programmes);
                }
                programmeList.remove(i);
//                programmeDao.saveToFile(programmeList);
                return true;
            }
        }
        return false;
    }

}
