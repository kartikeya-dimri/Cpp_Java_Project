package Java_Main;
import java.util.ArrayList;

public class EmployeeSkillData {
    private String id;
    private ArrayList<String> skills;
    private int noOfProjects;

    // Constructor
    public EmployeeSkillData(String id, ArrayList<String> skills, int noOfProjects) {
        this.id = id;
        this.skills = skills != null ? new ArrayList<>(skills) : new ArrayList<>();
        this.noOfProjects = noOfProjects;
    }
    public EmployeeSkillData(String id, ArrayList<String> skills) {
        this.id = id;
        this.skills = skills != null ? new ArrayList<>(skills) : new ArrayList<>();
    }

    // Getter for id
    public String getId() {
        return id;
    }

    // Getter for skills
    public ArrayList<String> getSkills() {
        return new ArrayList<>(skills);
    }

    // Getter for noOfProjects
    public int getNoOfProjects() {
        return noOfProjects;
    }
}