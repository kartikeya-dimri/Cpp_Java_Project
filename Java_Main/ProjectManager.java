package Java_Main;
import db.*;
import java.util.ArrayList;

public class ProjectManager {

    public static boolean addProj(String projName){
        //will call db code to check if a proj with exact same name already exists or not
        //if it's new proj name, then it'll be added to db as a new assigned project
        // if successfully added- will return true, else false\
        if(!Assignment.addProj(projName)){
            return false;
        }
        return true;
        // return false;
    }

    public static ArrayList<ProjectData> getEmployeeProjects(String empId){
        //this will also be useful for print option in myProjects section of Employee
        //this will be used to displaying project data on MyProjects section        
        //caller-can be ceo, hr or employee. if its employee then only projects assigned to him will be visible
        //status--"ONGOING", "COMPLETED", "UNASSIGNED", "ALL"
        //call db directly
        ArrayList<ProjectData> p=new ArrayList<>();
        ArrayList<String> skills = new ArrayList<>();
        skills.add("Skill1");
        skills.add("Skill2");
        skills.add("Skill3");
        EmployeeData e= new EmployeeData("Rahul Sharma", empId, "GP Sharma", "15-06-2000", "20000", "Bangalore", "Rahul.Sharma@gmail.com", "9999662255", "Btech", skills);
        ArrayList<EmployeeData> people = new ArrayList<>();
        people.add(e);
        ProjectData p1 = new ProjectData("Ongoing", "Proj-1", "Server Design", 5, people);
        p.add(p1);
        return p;
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
    
    public static String assignProjects(String projectId, int numOfEmpsRequired, ArrayList<String> skills){
        //no need to do error checking of the project id here, it'll always be valid and unassigned due to gui
        
        // vector<string> assignProjects(int requiredNumber, vector<string>skills, vector<Employee>employees)

        ArrayList<EmployeeSkillData> totalEmps;//dbTeam: make a methods which will return the details of all teh employees in this form
        //then jni call will happen
        ArrayList<String>result=//jni call to cpp
        
        if(result.get(0).equals("1")){
            //assignment was successful
            //now need to call db again to modify employee and project details
            //i'll pass db the project id and an array of emp ids to which the assignment has happened
            //the project needs to converted to assigned-along with the details of employees updated
            ArrayList<String>assignedEmps;
            dbCallAssignProjects(projectId,assignedEmps );

            return "1";
        }
        else{
            //return the error message
            return result.get(1);//has info about how many more people are required with the required skills
        }
    
    }


    public static void completeProject(String projId){
        //projId will always be valid
        //will pass on to db code
        //also free up the employees// free employee

        // check if the project exists or not
        try {
            if(!Assignment.checkProject(projId)){
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
    public static boolean checkProject(String projId){
        // if true then it does not create a new project
        // check if given project exists or not
        try {
            return Assignment.checkProject(projId);
        } catch (Exception e) {
            return false;
        }
    }

    public static ArrayList<String> getProjectDetails(String projId){
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