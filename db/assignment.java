package db;
import Backend.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.util.stream.Collectors;
import org.json.JSONArray;

public class assignment {
    public boolean addProj(String projName) {
        boolean isNewProject = false;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ayush", "root", "ayushsql");
            System.out.println("Connection established");

            // Check if project with this name already exists
            String checkQuery = "SELECT * FROM project WHERE project_name = ?";
            PreparedStatement checkStmt = con.prepareStatement(checkQuery);
            checkStmt.setString(1, projName);
            ResultSet rs = checkStmt.executeQuery();

            if (!rs.next()) {
                // No project with this name found, so it's a new project
                System.out.println("Project name is new, adding to database.");
                String query2 = "insert into project(project_name) values(?)";
                PreparedStatement ps1 = con.prepareStatement(query2);
                ps1.setString(1, projName);
                ps1.executeUpdate();
                // Add project with default project_id (or use auto increment)

                isNewProject = true;
            } else {
                System.out.println("Project name already exists in the database.");
            }

            rs.close();
            checkStmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isNewProject;
    }
    public static ArrayList<ProjectData> getProjects(String statusType) throws SQLException, ClassNotFoundException {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ayush", "root", "ayushsql");
            System.out.println("Connection established");
            String checkQuery = "SELECT * FROM project WHERE project_status = ?";
            PreparedStatement checkStmt = con.prepareStatement(checkQuery);
            checkStmt.setString(1, statusType);
            ResultSet rs = checkStmt.executeQuery();

            ArrayList<ProjectData> projects = new ArrayList<>();
            while (rs.next()) {
                String emplistJson = rs.getString("emplist");
                String projectstatus = rs.getString("status");
                String projectid = rs.getString("id");
                String projectname = rs.getString("project_name");
                int numOfEmp = Integer.parseInt(rs.getString("nume"));


                // Parse JSON into a list of integers
                JSONArray jsonArray = new JSONArray(emplistJson);
                ArrayList<Integer> employeeid = new ArrayList<>();
                ArrayList<EmployeeData> employeeids = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    String x = jsonArray.getString(i);
                    String query = "Select * from details where employee_id = ?";
                    PreparedStatement ps1 = con.prepareStatement(query);
                    int y = Integer.parseInt(x);
                    ps1.setInt(1, y);
                    ResultSet rs1 = ps1.executeQuery();
                    if (rs1.next()) {
                        String name1 = rs1.getString("name");
                        String fatherName = rs.getString("Fathername");
                        String dob = rs1.getString("dob");
                        String salary = rs1.getString("salary");
                        String address = rs1.getString("address");
                        String email = rs1.getString("email");
                        String phone = rs1.getString("phoneNumber");
                        String highestQualification = rs1.getString("HighestQual");
                        String skillsJson = rs1.getString("skills");
                        ArrayList<String> skills = new ArrayList<>();
                        JSONArray skillsArray = new JSONArray(skillsJson);
                        for (int j = 0; j < skillsArray.length(); j++) {
                            skills.add(skillsArray.getString(j));
                        }
                        EmployeeData e = new EmployeeData(name1, x, fatherName, dob, salary, address, email, phone, highestQualification, skills);
                        employeeids.add(e);
                    }
                }
                ProjectData p = new ProjectData(projectstatus, projectid, projectname, numOfEmp, employeeids);
                projects.add(p);


            }
            rs.close();
            checkStmt.close();
            con.close();
            return projects;

    }
    public static void completeProject(String projId) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ayush", "root", "ayushsql");
        System.out.println("Connection established");

        String checkQuery = "SELECT * FROM project WHERE id = ?";
        PreparedStatement checkStmt = con.prepareStatement(checkQuery);
        checkStmt.setString(1, projId);
        ResultSet rs = checkStmt.executeQuery();

        if (rs.next()) {
            // Update project status to 'completed'
            String updateQuery = "UPDATE project SET status = ? WHERE id = ?";
            PreparedStatement updateStmt = con.prepareStatement(updateQuery);
            updateStmt.setString(1, "completed");
            updateStmt.setString(2, projId);
            int rowsUpdated = updateStmt.executeUpdate();

            if (rowsUpdated > 0) {

                //  employee list
                String emplistJson = rs.getString("emplist");
                JSONArray jsonArray = new JSONArray(emplistJson);

                for (int i = 0; i < jsonArray.length(); i++) {
                    int employeeId = jsonArray.getInt(i);

                    // Fetch projectlist column of the employee
                    String fetchQuery = "SELECT projectlist FROM employee WHERE employee_id = ?";
                    PreparedStatement fetchStmt = con.prepareStatement(fetchQuery);
                    fetchStmt.setInt(1, employeeId);
                    ResultSet rs1 = fetchStmt.executeQuery();

                    if (rs1.next()) {
                        String projectlistJson = rs1.getString("projectlist");
                        JSONArray projectListArray = new JSONArray(projectlistJson);

                        // Remove the completed project ID
                        JSONArray updatedProjectList = new JSONArray();
                        for (int j = 0; j < projectListArray.length(); j++) {
                            if (!projectListArray.getString(j).equals(projId)) {
                                updatedProjectList.put(projectListArray.getString(j));
                            }
                        }

                        // Update the projectlist column with the modified JSON
                        String updateEmployeeQuery = "UPDATE employee SET projectlist = ? WHERE employee_id = ?";
                        PreparedStatement updateEmployeeStmt = con.prepareStatement(updateEmployeeQuery);
                        updateEmployeeStmt.setString(1, updatedProjectList.toString()); // Convert JSON array to string
                        updateEmployeeStmt.setInt(2, employeeId);
                        updateEmployeeStmt.executeUpdate();
                        System.out.println("Updated projectlist for employee ID: " + employeeId);
                    }

                    fetchStmt.close();
                }
            } else {
                System.out.println("Failed to update the project status.");
            }
        } else {
            System.out.println("No project found with ID: " + projId);
        }

        // Close resources
        rs.close();
        checkStmt.close();
        con.close();
    }


    public static boolean checkProject(String projId)  throws SQLException, ClassNotFoundException{
       boolean isProject = false;
       Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ayush", "root", "ayushsql");
        System.out.println("Connection established");
        String checkQuery = "SELECT * FROM project WHERE id = ?";
        PreparedStatement checkStmt = con.prepareStatement(checkQuery);
        checkStmt.setString(1, projId);
        ResultSet rs = checkStmt.executeQuery();
        if (rs.next()) {
            isProject = true;
        }


       return isProject;
    }
    public static ArrayList<String> getProjectDetails(String projId) throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ayush", "root", "ayushsql");
        System.out.println("Connection established");
        String checkQuery = "SELECT * FROM project WHERE id = ?";
        PreparedStatement checkStmt = con.prepareStatement(checkQuery);
        checkStmt.setString(1, projId);
        ResultSet rs = checkStmt.executeQuery();
        ArrayList<String> info =  new ArrayList<>();
        String name,status;
        if (rs.next()) {
            name = rs.getString("project_name");
            status = rs.getString("status");
            info.add(name);
            info.add(status);
        }
        return info;

    }



}

