package db;
import Java_Main.*;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.util.stream.Collectors;
import org.json.JSONArray;
import org.json.JSONException;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class Assignment {
    public static Connection establish() throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Driver loaded");
        Connection con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/company","root","dimriKartik126");
        System.out.println("Connection established");
        return con;
    }
    public static int numberOfProject() throws SQLException, ClassNotFoundException {
        int count=0;
        Connection con=establish();
        String query="SELECT *FROM project;";
        PreparedStatement ps=con.prepareStatement(query);
       ResultSet rs= ps.executeQuery();
       while(rs.next()){
        count++;
       }
       return count;
        

    }
    public static boolean addProj(String projName)throws SQLException, ClassNotFoundException  {
        boolean isNewProject = false;
        Connection con=establish();


            // Check if project with this name already exists
            String checkQuery = "SELECT * FROM project WHERE project_name = ?;";
            PreparedStatement checkStmt = con.prepareStatement(checkQuery);
            checkStmt.setString(1, projName);
            ResultSet rs = checkStmt.executeQuery();

            if (!rs.next()) {
                // No project with this name found, so it's a new project
                System.out.println("Project name is new, adding to database.");
                String query2 = "insert into project(project_name) values(?);";
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

        return isNewProject;
    }
    public static ArrayList<ProjectData> getProjects(String statusType) throws SQLException, ClassNotFoundException {

        Connection con=establish();
        System.out.println(statusType+"   Am ayush\n\n\n");
        String checkQuery="";
        PreparedStatement checkStmt;
        if(statusType.equals("All")){
            checkQuery = "SELECT * FROM project;";    
             checkStmt = con.prepareStatement(checkQuery);
        }
        else{
            checkQuery = "SELECT * FROM project WHERE status = ?;";
             checkStmt = con.prepareStatement(checkQuery);
            checkStmt.setString(1, statusType);
        }
            
            
            ResultSet rs = checkStmt.executeQuery();
            
            

            ArrayList<ProjectData> projects = new ArrayList<>();
            while (rs.next()) {
                System.out.println("inside database");
                String emplistJson = rs.getString("emplist");
                String projectstatus = rs.getString("status");
                int projectid1 = rs.getInt("id");
                System.out.println(projectid1);
                String projectname = rs.getString("project_name");
                int numOfEmp = rs.getInt("nume");
                
                System.out.println(projectname);
                //JSONArray jsonArray=null;
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<String>>() {}.getType();
                ArrayList<String> jsonArray = gson.fromJson(rs.getString("emplist"), type);
                ArrayList<EmployeeData> employeeids=null;
                // Parse JSON into a list of integers
                try{
                employeeids = new ArrayList<>();
                if(jsonArray!=null){
                //     System.out.println("next step");
                
                for (int i = 0; i < jsonArray.size(); i++) {
                    String x = jsonArray.get(i);
                    String query = "Select * from details where id = ?;";
                    PreparedStatement ps1 = con.prepareStatement(query);
                    System.out.println("inside inner loop");
                    int y = Integer.parseInt(x);
                    ps1.setInt(1, y);
                    ResultSet rs1 = ps1.executeQuery();
                    System.out.println("why");
                    if (rs1.next()) {
                        ArrayList<String> empdetails=AuthDb.getempdetail(x);
                        // String name1 = rs1.getString("name");
                        // String fatherName = rs1.getString("Fathername");
                        // String dob = rs1.getString("dob");
                        // int salary1 = rs1.getInt("salary");
                        // String salary=Integer.toString(salary1);
                        // String address = rs1.getString("address");
                        // String email = rs1.getString("email");
                        // String phone = rs1.getString("phoneNumber");
                        // String highestQualification = rs1.getString("HighestQual");
                        ArrayList<String> skills = AuthDb.getempskills(x);
                        EmployeeData e = new EmployeeData(empdetails.get(0), x, empdetails.get(1), empdetails.get(2), empdetails.get(3), empdetails.get(4), empdetails.get(5), empdetails.get(6),empdetails.get(7), skills);
                        employeeids.add(e);
                    }
                }
            }
            }catch(Exception e){
                e.printStackTrace();
            }
                String projectid=Integer.toString(projectid1);
                System.out.println(projectid);
                ProjectData p = new ProjectData(projectstatus, projectid, projectname, numOfEmp, employeeids);
                projects.add(p);


            }
            rs.close();
            checkStmt.close();
            con.close();
            return projects;

    }
    public static void completeProject(String projId) throws SQLException, ClassNotFoundException {
        Connection con=establish();
        int y=Integer.parseInt(projId);
        String checkQuery = "SELECT * FROM project WHERE id = ?;";
        PreparedStatement checkStmt = con.prepareStatement(checkQuery);
        checkStmt.setInt(1, Integer.parseInt(projId));
        ResultSet rs = checkStmt.executeQuery();
        System.out.println("Step1");

        if (rs.next()) {
            // Update project status to 'completed'
            String updateQuery = "UPDATE project SET status = ? WHERE id = ?;";
            PreparedStatement updateStmt = con.prepareStatement(updateQuery);
            updateStmt.setString(1, "Completed");
            updateStmt.setInt(2, y);
            int rowsUpdated = updateStmt.executeUpdate();
            System.out.println("Step2");

            if (rowsUpdated > 0) {

                //  employee list
                String emplistJson = rs.getString("emplist");
                JSONArray jsonArray = new JSONArray(emplistJson);
                System.out.println("Step3");
                for (int i = 0; i < jsonArray.length(); i++) {
                    int employeeId = Integer.parseInt(jsonArray.getString(i));
                    System.out.println("step4");
                    // Fetch projectlist column of the employee
                    String fetchQuery = "SELECT * FROM details WHERE id = ?;";
                    PreparedStatement fetchStmt = con.prepareStatement(fetchQuery);
                    fetchStmt.setInt(1, employeeId);
                    ResultSet rs1 = fetchStmt.executeQuery();

                    if (rs1.next()) {
                        String projectlistJson = rs1.getString("projectlist");
                        JSONArray projectListArray = new JSONArray(projectlistJson);
                        System.out.println("Step5");
                        // Remove the completed project ID
                        JSONArray updatedProjectList = new JSONArray();
                        try{
                        for (int j = 0; j < projectListArray.length(); j++) {
                            System.out.println("Step6");
                            if (!projectListArray.getString(j).equals(projId)) {
                                System.out.println("step6.5");
                                updatedProjectList.put(projectListArray.getString(j));
                            }
                            System.out.println("90");
                        }}
                        catch(Exception e){
                            e.printStackTrace();
                        }
                        System.out.println("Gee");
                        int pcwo=rs1.getInt("pcwo");
                        int upcwo=pcwo-1;
                        System.out.println("Step7");
                        String update="UPDATE details set pcwo = ? WHERE id = ?;";
                        PreparedStatement updateStmt2 = con.prepareStatement(update);
                        updateStmt2.setInt(1, upcwo);
                        updateStmt2.setInt(2, employeeId);
                        int rowsUpdated2 = updateStmt2.executeUpdate();


                        System.out.println("Step8");
                        // Update the projectlist column with the modified JSON
                        String updateEmployeeQuery = "UPDATE details SET projectlist = ? WHERE id = ?;";
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
       Connection con=establish();
        int y=Integer.parseInt(projId);
        String checkQuery = "SELECT * FROM project WHERE id = ?;";
        PreparedStatement checkStmt = con.prepareStatement(checkQuery);
        checkStmt.setInt(1, y);
        ResultSet rs = checkStmt.executeQuery();
        if (rs.next()) {
            isProject = true;
        }


       return isProject;
    }
    public static ArrayList<String> getProjectDetails(String projId) throws SQLException, ClassNotFoundException{
        Connection con=establish();
        int id=Integer.parseInt(projId);
        String checkQuery = "SELECT * FROM project WHERE id = ?;";
        PreparedStatement checkStmt = con.prepareStatement(checkQuery);
        checkStmt.setInt(1, id);
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
    public static ArrayList<ProjectData> getEmployeeProjects( String empId) throws SQLException, ClassNotFoundException {
        ArrayList<ProjectData> projects = new ArrayList<>();
        Connection con=establish();
        System.out.println(empId);
        int y=Integer.parseInt(empId);
        System.out.println("Projects of Ayush");
        String checkQuery = "SELECT * FROM details WHERE id = ?;";
        PreparedStatement checkStmt = con.prepareStatement(checkQuery);
        checkStmt.setInt(1, y);
        ResultSet rs = checkStmt.executeQuery();
        System.out.println(y);
        System.out.println("Ayush");
        if(rs.next()) {
            String emplistJson = rs.getString("projectlist");
            JSONArray jsonArray = new JSONArray(emplistJson);
            System.out.println("In loop");
            for (int i = 0; i < jsonArray.length(); i++) {
                System.out.println("In inner loop");
                int projectId = Integer.parseInt(jsonArray.getString(i));
                String query="SELECT * FROM project WHERE id = ?;";
                PreparedStatement queryStmt = con.prepareStatement(query);
                queryStmt.setInt(1, projectId);
                System.out.println(projectId);
                ResultSet rs1 = queryStmt.executeQuery();
                if (rs1.next()) {
                    System.out.println("Hello");
                    String status = rs1.getString("status");
                    String name = rs1.getString("project_name");
                    int nume=rs1.getInt("nume");
                    String id=Integer.toString(projectId);
                    System.out.println(id);
                    String emplistJson1 = rs1.getString("emplist");
                    System.out.println("lo");
                    JSONArray jsonArray1 = new JSONArray(emplistJson);
                    System.out.println("show");
                    ArrayList<EmployeeData> empList1 = new ArrayList<>();
                    System.out.println("hey");
                    for (int j = 0; j < jsonArray1.length(); j++) {
                        int employeeId = Integer.parseInt(jsonArray1.getString(j));
                        String fetchQuery = "SELECT * FROM details WHERE id = ?;";
                        System.out.println(employeeId);
                        PreparedStatement fetchStmt = con.prepareStatement(fetchQuery);
                        fetchStmt.setInt(1, employeeId);
                        ResultSet rs2 = fetchStmt.executeQuery();
                        if (rs2.next()) {
                            System.out.println("qwert");
                            String name1 = rs2.getString("name");
                            String fatherName = rs2.getString("Fathername");
                            String dob = rs2.getString("dob");
                            int salary1 = rs2.getInt("salary");
                            String salary=Integer.toString(salary1);
                            String address = rs2.getString("address");
                            String email = rs2.getString("email");
                            String phone = rs2.getString("phoneNumber");
                            String highestQualification = rs2.getString("HighestQual");

                            //String skillsJson = rs2.getString("skills");
                            System.out.println("skills");
                            ArrayList<String> skills = AuthDb.getempskills(jsonArray1.getString(j));
                            //System.out.println(skillsJson);
//                            JSONArray skillsArray = new JSONArray(skillsJson);
//                            System.out.println("Dill");
//                            for (int k = 0; k < skillsArray.length(); k++) {
//                                System.out.println("Next step");
//                                skills.add(skillsArray.getString(k));
//                            }
                            System.out.println("Kll");
                            EmployeeData e = new EmployeeData(name1, id, fatherName, dob, salary, address, email, phone, highestQualification, skills);
                            empList1.add(e);

                        }

                    }
                    ProjectData p=new ProjectData(status,id,name,nume,empList1);
                    projects.add(p);

                }
            }

        }
        return projects;


    }
    public static void dbCallAssignProjects(String projectid,ArrayList<String> employees,int nume) throws SQLException, ClassNotFoundException {
        Connection con=establish();
        int y = Integer.parseInt(projectid);
        String checkQuery = "SELECT * FROM project WHERE id = ?;";
        PreparedStatement checkStmt = con.prepareStatement(checkQuery);
        checkStmt.setInt(1, y);
        ResultSet rs = checkStmt.executeQuery();

        if (rs.next()) {
            JSONArray employeeJsonArray = new JSONArray(employees);


            String updateQuery = "UPDATE project SET nume = ?, emplist = ?,status =? WHERE id = ?;";
            PreparedStatement updateStmt = con.prepareStatement(updateQuery);
            updateStmt.setInt(1, nume); // Update nume
            updateStmt.setString(2, employeeJsonArray.toString());
            updateStmt.setString(3, "Ongoing");
            updateStmt.setInt(4, y);
            updateStmt.executeUpdate();
        }

        for(int i=0;i<employees.size();i++){
            int eid=Integer.parseInt(employees.get(i));
            String query="SELECT * FROM details WHERE id = ?;";
            PreparedStatement queryStmt = con.prepareStatement(query);
            queryStmt.setInt(1, eid);
            ResultSet rs1= queryStmt.executeQuery();
            if(rs1.next()) {
                int pcwo=rs1.getInt("pcwo");
                int upcwo=pcwo+1;
                String query1="UPDATE details SET pcwo = ? WHERE id = ?;";
                PreparedStatement query1Stmt = con.prepareStatement(query1);
                query1Stmt.setInt(1, upcwo);
                query1Stmt.setInt(2, eid);
                query1Stmt.executeUpdate();
                String projectListJson = rs1.getString("projectlist");
                JSONArray projectListArray;

                if (projectListJson == null || projectListJson.isEmpty()) {
                    projectListArray = new JSONArray();
                } else {
                    projectListArray = new JSONArray(projectListJson);
                }

                projectListArray.put(projectid);

                // Step 5: Update the project_list column
                String updateProjectListQuery = "UPDATE details SET projectlist = ? WHERE id = ?;";
                PreparedStatement updateProjectListStmt = con.prepareStatement(updateProjectListQuery);
                updateProjectListStmt.setString(1, projectListArray.toString());
                updateProjectListStmt.setInt(2, eid);
                updateProjectListStmt.executeUpdate();


       }
    }


    



}

     }

        