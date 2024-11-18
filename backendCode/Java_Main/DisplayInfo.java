package Java_Main;
import java.util.ArrayList;

public class DisplayInfo {
    //this class has methods to get things like total no of added projects etc to display for ceo hr
    
    
    public ArrayList<String> ceoDashboardInfo(){
        //will call db code and get total ongoing proj, emps and hrs

        ArrayList<String> info=new ArrayList<>();//(no of ongoing proj, no of emps, no of hrs)
        return info;
    }

    public ArrayList<String> empDashboardInfo(String empId){
        //will call db code and get name, ID and total ongoing projects
        //empId passed will be always valid
        ArrayList<String> info=new ArrayList<>();//(name, ID and total ongoing projects)
        return info;
    }
    public ArrayList<String> hrDashboardInfo(){
        //will call db code and get name, ID and total ongoing projects
        //hrId won't be passed, all hrs will have the same dashboard

        ArrayList<String> info=new ArrayList<>();//(total ongoing projects, total employees, total projects)
        return info;
    }
    

    public EmployeeData empDashboardDetailsPrint(String empId){
        //empId passed will be always valid
        //will pass it to db code
        //they'll return in data in form of EmployeeData structure
        EmployeeData e=new EmployeeData(empId, empId, empId, empId, empId, empId, empId, empId, empId, null);
        //need to get all the fields
        return e;
    }
}
