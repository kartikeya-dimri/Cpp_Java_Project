package Java_Main;
import db.*;

import java.util.ArrayList;

public class WorkforceManager {

    private static boolean isAddEmployeeInputCorrect(String status, String name, String fatherName, String dob, String salary, String address, String email, String phoneNum, String highestQual, ArrayList<String>skills){
        if(status==null || name==null || fatherName==null || dob==null || salary==null || address==null || email==null || phoneNum==null || highestQual==null || skills==null){
            return false;
        }
        if(status.trim().isEmpty() || name.trim().isEmpty() || fatherName.trim().isEmpty() || dob.trim().isEmpty() || salary.trim().isEmpty() || address.trim().isEmpty() || email.trim().isEmpty() || phoneNum.trim().isEmpty() || highestQual.trim().isEmpty()){
            return false;
        }   
        if(phoneNum.length()!=10){
            return false;
        }

        return true;
    }

    // THIS IS ADD EMPLOYEE
    public static ArrayList<String> add(String status, String name, String fatherName, String dob, String salary, String address, String email, String phoneNum, String highestQual, ArrayList<String>skills){
        //i'll need to check if the parameters are valid or not, like salary, address, email, phone number(10 digit)
        //return array-arr[0]-success/failure(1/0) and arr[1]-error message
        //if the parameters are valid then i'll pass it to database handling code. They'll need to automate the process of giving a new HrId 
        //as skills is a dropdown menu, it cant have errors
        ArrayList<String> result=new ArrayList<String>();
        if(!isAddEmployeeInputCorrect(status, name, fatherName, dob, salary, address, email, phoneNum, highestQual, skills)){
            // first is for failure second is the error message
            result.add("0");
            result.add("Invalid parameters");
            return result;
        }
        result.add("1");
        int Salary;
        try {
            Salary=Integer.parseInt(salary);
        } catch (NumberFormatException e) {
            result.add("Salary is not a number");
            return result;
        }

        try {
            AuthDb.addemp(status, name, dob, fatherName, email, skills, Salary, phoneNum, highestQual, address);
        } catch (Exception e) {
            result.add("Error in adding employee");
            return result;
        }

        return result; 
    }
    
    public static ArrayList<String> searchForDelete(String status, String Id){
        // ceo will delete only hr and hr can only delete employees (new comm)

        // status can be of two types hr or employee
        // depending on that i'll call the respective function
        // arr[0]->will be 1 or 0
        // if arr[0]=0 then arr[1] will be the error message
        // else arr[1,2,3] will be name, phone, email of the hr/employee
        //will return arr[4]-0 will be status-"OK","DNE","ONGOING_PROJ", rest three will be name, phone, email taken from DB if the hr exists
        //will call DB handling code with ("HR" , id) parameters. 
        ArrayList<String> result=new ArrayList<String>();
        // result.add("0");
        // result.add("Invalid parameters");
        // first for success
        // if correct id then return name phone email
        try {
            result=AuthDb.searchfordelete(status, Id);
        } catch (Exception e) {
            result.add("0");
            result.add("Error in searching for employee (Exception thrown)");
            return result;
        }

        
        return result; 
    }
    
    // THIS TAKES CARE OF FIRING THE HR AND THE EMPLOYEE
    public static void delete(String status, String Id){
        //call Db to remove it. it's confirmed that hr exists
        try {
            AuthDb.deleteEmp(status, Id);
        } catch (Exception e) {
            System.out.println("Error in deleting employee");
        }
        
    }

    //ceo.logout() - i think it can be implemented in the gui itself - Done in GUI
}
