package Java_Main;
import java.util.ArrayList;

public class ProjectManager {

    public boolean addProj(String projName){
        //will call db code to check if a proj with exact same name already exists or not
        //if it's new proj name, then it'll be added to db as a new assigned project
        // if successfully added- will return true, else false

        return false;
    }

    public ArrayList<ProjectData> getProjects(String status,String caller, String empId){
        //this will also be useful for print option in myProjects section of Employee
        //this will be used to displaying project data on MyProjects section        
        //caller-can be ceo, hr or employee. if its employee then only projects assigned to him will be visible
        //status--"ONGOING", "COMPLETED", "UNASSIGNED", "ALL"
        //call db directly
        ArrayList<ProjectData> p=new ArrayList<>();
        return p;
    }

    public String assignProjects(String projectId){
        //this will return if the assignment was successful or not
        //"OK","NEED MORE EMPS"
        
        String res;
        return "res";
    }

    public void completeProject(String projId){
        //projId will always be valid
        //will pass on to db code
    }
    
}