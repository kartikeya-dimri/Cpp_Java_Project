package Java_Main;
import db.*;
import java.util.ArrayList;


public class DisplayInfo {
    //this class has methods to get things like total no of added projects etc to display for ceo hr
    
    
    public static ArrayList<String> ceoDashboardInfo(){
        //will call db code and get total ongoing proj, emps and hrs
        // 0->projects, 1->employees, 2->hrs
        // first get the integer arraylist
        ArrayList<Integer> array=new ArrayList<>();
        try {
            array=AuthDb.work_force();
        } catch (Exception e) {
            System.out.println("Error in db");
        }//(no of ongoing proj, no of emps, no of hrs)
        ArrayList<String> ceoInfo=new ArrayList<>();

        for(int i:array){
            ceoInfo.add(Integer.toString(i));
        }
        return ceoInfo;
    }

    public static ArrayList<String> empDashboardInfo(String empId){
        //will call db code and get name, ID and total ongoing projects
        //empId passed will be always valid
        ArrayList<String> info=new ArrayList<>();//(name, ID and total ongoing projects)
        try {
            info=AuthDb.empdash(empId);
        } catch (Exception e) {
            System.out.println("Error in DB");
        }
        return info;
    }
    
    public static ArrayList<String> hrDashboardInfo(String hrId){
        //will call db code and get hr name, total employees, ongoign projects
        //hrId won't be passed, all hrs will have the same dashboard
        ArrayList<String> info = new ArrayList<>();

        try {
            info=AuthDb.hrdashboard(hrId);
        } catch (Exception e) {
            System.out.println("Error in DB");
        }
        return info;
    }
    
    // changing the signature and adding a status for differentiating between hr and employee.
    // call getempdetail method from db
    public static EmployeeData DashboardDetailsPrint(String status,String empId){
        //empId passed will be always valid
        //will pass it to db code
        //they'll return in data in form of EmployeeData structure
        
        // name id father_name dob salary address email phone highest_qualification skills
        ArrayList<String> skills = new ArrayList<>();
        //need to get all the fields
        // THESE ARE THE INDICES
        // index of name is 0, id is 1, father_name is 2, dob is 3, salary is 4, address is 5, email is 6, phone is 7, highest_qualification is 8, skills is 9

        ArrayList<String> empdetails = new ArrayList<>();
        // calling the db methods
        try {
            empdetails=AuthDb.getempdetail(empId);
        } catch (Exception e) {
            System.out.println("Error in DB");
        }

        // now we have the skills arraylist
        try {
            skills=AuthDb.getempskills(empId);
        } catch (Exception e) {
            System.out.println("Error in DB");
        }

        // now we have all the data
        EmployeeData empData = new EmployeeData(empdetails.get(0), empdetails.get(1), empdetails.get(2), empdetails.get(3), empdetails.get(4), empdetails.get(5), empdetails.get(6), empdetails.get(7), empdetails.get(8), skills);

        return empData;
        
    }
}
