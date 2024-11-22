package Java_Main;
import db.*;

import java.util.ArrayList;

public class ProjectManager {

    public static boolean addProj(String projName){
        //will call db code to check if a proj with exact same name already exists or not
        //if it's new proj name, then it'll be added to db as a new assigned project
        // if successfully added- will return true, else false
        return true;
        // return false;
    }

    public static ArrayList<ProjectData> getEmployeeProjects( String empId){
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
        ArrayList<ProjectData> p = new ArrayList<>();
    
        // Skills for employees
        ArrayList<String> skills1 = new ArrayList<>();
        skills1.add("Skill1");
        skills1.add("Skill2");
    
        ArrayList<String> skills2 = new ArrayList<>();
        skills2.add("Skill3");
        skills2.add("Skill4");
    
        ArrayList<String> skills3 = new ArrayList<>();
        skills3.add("Skill5");
        skills3.add("Skill6");
    
        ArrayList<String> skills4 = new ArrayList<>();
        skills4.add("Skill7");
        skills4.add("Skill8");
    
        // Employees
        EmployeeData emp1 = new EmployeeData("Rahul Sharma", "E001", "GP Sharma", "15-06-2000", "20000", "Bangalore", "Rahul.Sharma@gmail.com", "9999662255", "Btech", skills1);
        EmployeeData emp2 = new EmployeeData("Neha Singh", "E002", "Arun Singh", "20-09-1998", "25000", "Delhi", "Neha.Singh@gmail.com", "9999765432", "Mtech", skills2);
        EmployeeData emp3 = new EmployeeData("Karan Kapoor", "E003", "Ramesh Kapoor", "10-01-1995", "22000", "Mumbai", "Karan.Kapoor@gmail.com", "9999123456", "MBA", skills3);
        EmployeeData emp4 = new EmployeeData("Simran Kaur", "E004", "Harpreet Kaur", "25-12-1993", "27000", "Chandigarh", "Simran.Kaur@gmail.com", "8888456789", "MSc", skills4);
        EmployeeData emp5 = new EmployeeData("Amit Verma", "E005", "Manoj Verma", "05-03-1992", "30000", "Kolkata", "Amit.Verma@gmail.com", "7777865432", "PhD", skills2);
        EmployeeData emp6 = new EmployeeData("Priya Das", "E006", "Ravi Das", "18-08-1997", "21000", "Hyderabad", "Priya.Das@gmail.com", "6667543210", "BTech", skills1);
    
        // Employee lists
        ArrayList<EmployeeData> people1 = new ArrayList<>();
        people1.add(emp1);
        people1.add(emp3);
    
        ArrayList<EmployeeData> people2 = new ArrayList<>();
        people2.add(emp2);
        people2.add(emp4);
    
        ArrayList<EmployeeData> people3 = new ArrayList<>();
        people3.add(emp5);
        people3.add(emp6);
    
        // Ongoing projects
        ProjectData p1 = new ProjectData("Ongoing", "Proj-1", "Server Design", 5, people1);
        ProjectData p2 = new ProjectData("Ongoing", "Proj-2", "Database Optimization", 3, people2);
    
        // Unassigned projects
        ProjectData p3 = new ProjectData("Unassigned", "Proj-3", "Mobile App Development", 0, new ArrayList<>());
        ProjectData p4 = new ProjectData("Unassigned", "Proj-4", "AI Model Training", 0, new ArrayList<>());
    
        // Completed projects
        ProjectData p5 = new ProjectData("Completed", "Proj-5", "Website Development", 4, people1);
        ProjectData p6 = new ProjectData("Completed", "Proj-6", "Cloud Migration", 6, people3);
    
        // Additional Ongoing and Completed projects for variety
        ProjectData p7 = new ProjectData("Ongoing", "Proj-7", "Cybersecurity Setup", 4, people3);
        ProjectData p8 = new ProjectData("Completed", "Proj-8", "Machine Learning Research", 5, people2);
    
        // Add projects to the list based on the statusType
        if (statusType.equals("All")) {
            p.add(p1); // Ongoing
            p.add(p2); // Ongoing
            p.add(p3); // Unassigned
            p.add(p4); // Unassigned
            p.add(p5); // Completed
            p.add(p6); // Completed
            p.add(p7); // Ongoing
            p.add(p8); // Completed
        } else if (statusType.equals("Completed")) {
            p.add(p5); // Completed
            p.add(p6); // Completed
            p.add(p8); // Completed
        } else if (statusType.equals("Ongoing")) {
            p.add(p1); // Ongoing
            p.add(p2); // Ongoing
            p.add(p7); // Ongoing
        } else if (statusType.equals("Unassigned")) {
            p.add(p3); // Unassigned
            p.add(p4); // Unassigned
        }
    
        return p;
    }
    
//will implement this later
    public static String assignProjects(String projectId, int numOfEmps, ArrayList<String> skills){
        //no need to do error checking of the project id here, it'll always be valid and unassigned due to gui
        
        //this will return if the assignment was successful or not
        //"OK","NEED MORE EMPS"
        
        // inernally update status also unassigned -> ongoing
        String res = "Not enough employess with C++ Skill";
        return res;
    }


    public static void completeProject(String projId){
        //projId will always be valid
        //will pass on to db code
        //also free up the employees// free employee
    }
    
    // These methods were also required so I added
    public static boolean checkProject(String projId){
        // if true then it does not create a new project
        // check if given project exists or not
        return true;
    }

    public static ArrayList<String> getProjectDetails(String projId){
        // return only name and status of the project
        // // name stats
        ArrayList<String> info =  new ArrayList<>();
        // info.add("LMS Application");
        // info.add("Completed");
        return info;

        // return ProjectData object
    }
    
}