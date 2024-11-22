package Java_Main;

public class Auth{
    // will help to check username and password
    //also forgot password later
    public static boolean login(String username, String password, String status){
        //status- ceo, emp, etc.
        //don't have to do anything here, will directly pass this to database code


        if(username.equals("admin") && password.equals("admin") && status.equals("ceo")){
            return true;
        }
        // now for hr
        if(username.equals("hr") && password.equals("hr") && status.equals("hr")){
            return true;
        }
        // now for employee
        if(username.equals("emp") && password.equals("emp") && status.equals("emp")){
            return true;
        }
        return false;
    }
    public static boolean forgotPassword(){
        //see later, don't know parameters right now


        return false;
    }
}