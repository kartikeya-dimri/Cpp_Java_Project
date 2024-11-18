package Java_Main;
import java.util.ArrayList;

public class ProjectData {
    // This is just a structure to organize all the data for a project
    private String status; // "COMPLETE", "ONGOING", "UNASSIGNED"
    // Other fields valid only if status isn't "UNASSIGNED"
    private String projId;
    private String name;
    private int numOfWorkingEmps;
    private ArrayList<EmployeeData> people;

    // Getter for status
    public String getStatus() {
        return status;
    }

    // Getter for projId
    public String getProjId() {
        return projId;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Getter for numOfWorkingEmps
    public int getNumOfWorkingEmps() {
        return numOfWorkingEmps;
    }

    // Getter for people
    public ArrayList<EmployeeData> getPeople() {
        return people;
    }
}
