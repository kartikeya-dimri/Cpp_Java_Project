package Java_Main;

import db.*;
import java.util.ArrayList;

public class ProjectManager {

    public static boolean addProj(String projName) {
        //will call db code to check if a proj with exact same name already exists or not
        //if it's new proj name, then it'll be added to db as a new assigned project
        // if successfully added- will return true, else false\
        if (!Assignment.addProj(projName)) {
            return false;
        }
        return true;
        // return false;
    }

    public static ArrayList<ProjectData> getEmployeeProjects(String empId) {
        //this will also be useful for print option in myProjects section of Employee
        //this will be used to displaying project data on MyProjects section        
        //caller-can be ceo, hr or employee. if its employee then only projects assigned to him will be visible
        //status--"ONGOING", "COMPLETED", "UNASSIGNED", "ALL"
        //call db directly

        try {
            return Assignment.getEmployeeProjects(empId);
        } catch (Exception e) {
            return new ArrayList<ProjectData>();
        }
    }

    // need this method in HR for table of projects by status
    public static ArrayList<ProjectData> getProjects(String statusType) {
        // this will return all projects with the given status(COMPLETED, ONGOING, UNASSIGNED)
        try {
            return Assignment.getProjects(statusType);
        } catch (Exception e) {

        }
        return new ArrayList<ProjectData>();
    }

//will implement this later
    public static String assignProjects(String projectId, int numOfEmps, ArrayList<String> skills) {
        //no need to do error checking of the project id here, it'll always be valid and unassigned due to gui

        //this will return if the assignment was successful or not
        //"OK","NEED MORE EMPS"
        // inernally update status also unassigned -> ongoing
        String res = "Not enough employess with C++ Skill";
        return res;
    }

    public static void completeProject(String projId) {
        //projId will always be valid
        //will pass on to db code
        //also free up the employees// free employee

        // check if the project exists or not
        try {
            if (!Assignment.checkProject(projId)) {
                return;
            }
        } catch (Exception e) {
            System.out.println("Error in checking project");
            return;
        }
        // the project exists
        try {
            Assignment.completeProject(projId);
        } catch (Exception e) {
            System.out.println("Error in completing project");
            return;
        }

    }

    // These methods were also required so I added
    public static boolean checkProject(String projId) {
        // if true then it does not create a new project
        // check if given project exists or not
        try {
            return Assignment.checkProject(projId);
        } catch (Exception e) {
            return false;
        }
    }

    public static ArrayList<String> getProjectDetails(String projId) {
        // return only name and status of the project
        // // name stats
        try {
            return Assignment.getProjectDetails(projId);
        } catch (Exception e) {
            // empty arraylist
            return new ArrayList<String>();
        }
    }

}
