package Java_Main;
import java.util.ArrayList;
import db.*;


public class DisplayInfo {
    //this class has methods to get things like total no of added projects etc to display for ceo hr
    
    
    public static ArrayList<String> ceoDashboardInfo(){
        //will call db code and get total ongoing proj, emps and hrs
        // 0->projects, 1->employees, 2->hrs
        ArrayList<String> info=new ArrayList<>();//(no of ongoing proj, no of emps, no of hrs)
        info.add("10");
        info.add("50");
        info.add("500");
        return info;
    }

    public static ArrayList<String> empDashboardInfo(String empId){
        //will call db code and get name, ID and total ongoing projects
        //empId passed will be always valid
        ArrayList<String> info=new ArrayList<>();//(name, ID and total ongoing projects)
        info.add("Rahul Sharma");
        info.add("EMP1");
        info.add("5");
        return info;
    }
    
    public static ArrayList<String> hrDashboardInfo(String hrId){
        //will call db code and get hr name, total employees, ongoign projects
        //hrId won't be passed, all hrs will have the same dashboard
        ArrayList<String> info = new ArrayList<>();
        info.add("Deepak Yadav");
        info.add("36");
        info.add("5");
        return info;
    }
    
    // changing the signature and adding a status for differentiating between hr and employee.
    public static EmployeeData DashboardDetailsPrint(String status,String empId){
        //empId passed will be always valid
        //will pass it to db code
        //they'll return in data in form of EmployeeData structure
        
        // name id father_name dob salary address email phone highest_qualification skills
        ArrayList<String> skills = new ArrayList<>();
        skills.add("Skill1");
        skills.add("Skill2");
        skills.add("Skill3");
        EmployeeData e= new EmployeeData("Rahul Sharma", empId, "GP Sharma", "15-06-2000", "20000", "Bangalore", "Rahul.Sharma@gmail.com", "9999662255", "Btech", skills);
        //need to get all the fields
        // index of name is 0, id is 1, father_name is 2, dob is 3, salary is 4, address is 5, email is 6, phone is 7, highest_qualification is 8, skills is 9
        return e;
    }
}
