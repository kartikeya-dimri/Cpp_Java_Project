package Java_Main;

import java.util.ArrayList;

public class EmployeeSkillData {
    private String id;
    private ArrayList<String> skills;
    private int noOfProjects;
    // Constructor
    public EmployeeSkillData(String id, ArrayList<String> skills, int noOfProjects) {
        this.id = id;
        this.skills = skills;
        this.noOfProjects = noOfProjects;
    }

    // Getter for id
    public String getId() {
        return id;
    }

    // Getter for skills
    public ArrayList<String> getSkills() {
        return skills;
    }

    // Getter for noOfProjects
    public int getNoOfProjects() {
        return noOfProjects;
    }

}
