package Java_Main;

import db.AuthDb;

public class Auth{
    // check if anything is empty or not
    private static boolean isLoginCorrect(String username, String password, String status){
        return username==null || password==null || status==null || username.trim().isEmpty() || password.trim().isEmpty() || status.trim().isEmpty();
    }
    // will help to check username and password
    //also forgot password later
    public static boolean login(String username, String password, String status){
        //status- ceo, emp, etc.
        if(!isLoginCorrect(username, password, status)){
            System.out.println("Empty input");
            return false;
        }
        int userId;
        try {
            userId=Integer.parseInt(username);
        } catch (NumberFormatException e) {
            System.out.println("UserId entered is not a number");
            return false;
        }

        try{
            // use signin method of db
            return AuthDb.signin(userId, password, status);
        }
        catch(Exception e){
            System.out.println("Error in signin");
        }
        return false;
    }
    public static boolean forgotPassword(){
        //see later, don't know parameters right now


        return false;
    }
}