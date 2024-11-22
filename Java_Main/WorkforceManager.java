package Java_Main;

import java.util.ArrayList;

public class WorkforceManager {

    public static ArrayList<String> add(String status, String name, String fatherName, String dob, String salary, String address, String email, String phoneNum, String highestQual, ArrayList<String>skills){
        //i'll need to check if the parameters are valid or not, like salary, address, email, phone number(10 digit)
        //return array-arr[0]-success/failure(1/0) and arr[1]-error message
        //if the parameters are valid then i'll pass it to database handling code. They'll need to automate the process of giving a new HrId 
        //as skills is a dropdown menu, it cant have errors
        ArrayList<String> result=new ArrayList<String>();
        result.add("0");
        result.add("Invalid parameters");

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
        result.add("1");
        result.add("Rahul Sharma");
        result.add("9999662255");
        result.add("harsh@gmain.com");

        
        return result; 
    }
    
    public static void delete(String status, String Id){
        //call Db to remove it. it's confirmed that hr exists
        System.out.println("HR removed successfully");
    }
    

    

    //ceo.logout() - i think it can be implemented in the gui itself - Done in GUI
}
