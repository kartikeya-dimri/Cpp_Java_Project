import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;
import java.util.Scanner;

import org.mindrot.jbcrypt.BCrypt;
public class Auth {
    public static class Passwordhashing{
        public static String hashPassword(String plainPassword) {
            // Generate salt and hash the password
            return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
        }
        public static boolean checkPassword(String plainPassword, String hashedPassword) {
            return BCrypt.checkpw(plainPassword, hashedPassword);
        }
    }
    public static void signin() throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.print("Enter username: ");
        String userName = myObj.nextLine();
        System.out.println();
        System.out.print("Enter password: ");
        String pass=myObj.nextLine();
        Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/jdbc_db","root","123456@mysql");
        PreparedStatement ps0=con.prepareStatement("select *from details");
        ResultSet rs= ps0.executeQuery();
        boolean userexists=false;
        String hashpas = null;
        while (rs.next()){
            if(Objects.equals(userName, rs.getString("username"))){
                userexists=true;
                hashpas=rs.getString("pass");
                break;
            }
        }
        if(!userexists){
            System.out.println("Username does not exist");
            con.close();
            return ;
        }
        if(!Passwordhashing.checkPassword(pass,hashpas)){
            System.out.println("Incorrect Password");
            con.close();
            return;
        }
        System.out.println("Signed in succesfully");
        con.close();
        return;
    }
    public static boolean signup() throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Database loaded");
        //Taking input of username
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter username");
        String userName = myObj.nextLine();
        System.out.println("Enter Password");
        String pass=myObj.nextLine();


        Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/jdbc_db","root","123456@mysql");
        System.out.println("Connection established");
        PreparedStatement initialisation=con.prepareStatement("create table if not exists details(username varchar(50) primary key,pass varchar(500),age int not null);");
        initialisation.execute();

        String hashedPassword = Passwordhashing.hashPassword(pass);
        //checking if username already exists
        PreparedStatement ps0=con.prepareStatement("select *from details");
        ResultSet rs= ps0.executeQuery();
        boolean userexists=false;
        while (rs.next()){
            if(Objects.equals(userName, rs.getString("username"))){
                userexists=true;
                break;
            }
        }
        if(userexists){
            System.out.println("Username already exists write new one");
            con.close();
            return false;
        }


        PreparedStatement ps=con.prepareStatement("insert into details values('"+userName+"','"+hashedPassword+"','56')");
        int i=ps.executeUpdate();
        if(i>0){
            System.out.println("Success");
        }
        else{
            System.out.println("Fail");
            return false;
        }
        con.close();
        return true;
    }
    public static boolean deleteuser() throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.print("Enter username: ");
        String userName = myObj.nextLine();
        System.out.println();
        System.out.print("Enter password: ");
        String pass=myObj.nextLine();
        Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/jdbc_db","root","123456@mysql");
        PreparedStatement ps0=con.prepareStatement("select *from details");
        ResultSet rs= ps0.executeQuery();
        boolean userexists=false;
        String hashpas = null;
        while (rs.next()){
            if(Objects.equals(userName, rs.getString("username"))){
                userexists=true;
                hashpas=rs.getString("pass");
                break;
            }
        }
        if(!userexists){
            System.out.println("Username does not exist");
            con.close();
            return false;
        }
        if(!Passwordhashing.checkPassword(pass,hashpas)){
            System.out.println("Incorrect Password");
            con.close();
            return false;
        }
        PreparedStatement ps=con.prepareStatement("delete from details where username=? ");
        //ps.setString(2,Passwordhashing.hashPassword(pass));
        ps.setString(1,userName);
        int p=ps.executeUpdate();
        if(p>0){
            System.out.println("User deleted Succesfully");
        }
        else{
            System.out.println("Incorrect Username or Password");
            return false;
        }
        con.close();
        return true;
    }
    public static boolean forgotpassword() throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.print("Enter username: ");
        String userName = myObj.nextLine();
        System.out.println();
        System.out.print("Enter new password: ");
        String pass=myObj.nextLine();
        Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/jdbc_db","root","123456@mysql");
        PreparedStatement ps0=con.prepareStatement("select *from details");
        ResultSet rs= ps0.executeQuery();
        boolean userexists=false;
        while (rs.next()){
            if(Objects.equals(userName, rs.getString("username"))){
                userexists=true;
                break;
            }
        }
        if(!userexists){
            System.out.println("Username does not exist");
            con.close();
            return false;
        }
        PreparedStatement ps=con.prepareStatement("update details set pass= ? where username=?");
        ps.setString(1,Passwordhashing.hashPassword(pass));
        ps.setString(2,userName);
        int p=ps.executeUpdate();
        if(p>0){
            System.out.println("Password Updated Succesfully");
        }
        else{
            //Mostly it won't go here
            System.out.println("Incorrect Username");
            return false;
        }
        con.close();
        return true;
    }
    public static void main(String[] args) throws Exception{
        while(true) {
            Scanner myObj = new Scanner(System.in);  // Create a Scanner object
            System.out.print("Enter Function: ");
            int f = myObj.nextInt();
            if(f==0){
                signin();
            }
            else if(f==1){
                signup();
            }
            else if(f==2){
                forgotpassword();
            }
            else if(f==3){
                deleteuser();
            }
            else {
                break;
            }
        }
    }
}
