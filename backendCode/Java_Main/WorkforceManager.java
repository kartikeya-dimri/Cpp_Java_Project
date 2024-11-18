package Java_Main;
import java.util.ArrayList;

public class WorkforceManager {

    public ArrayList<String> add(String status, String name, String fatherName, String dob, String salary, String address, String email, String phoneNum, String highestQual, ArrayList<String>skills){
        //i'll need to check if the parameters are valid or not, like salary, address, email, phone number(10 digit)
        //return array-arr[0]-success/failure(1/0) and arr[1]-error message
        //if the parameters are valid then i'll pass it to database handling code. They'll need to automate the process of giving a new HrId 
        //as skills is a dropdown menu, it cant have errors
        ArrayList<String> result=new ArrayList<String>();
        return result; 
    }
    public ArrayList<String> remove(String status, String Id){
        //will return arr[4]-0 will be status-"OK","DNE","ONGOING_PROJ", rest three will be name, phone, email taken from DB if the hr exists
        //will call DB handling code with ("HR" , id) parameters. 
        ArrayList<String> result=new ArrayList<String>();
        return result; 
    }

    public void delete(String status, String Id){
        //call Db to remove it. it's confirmed that hr exists
    }

    

    //ceo.logout() - i think it can be implemented in the gui itself
}
