import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;
import java.util.Random;
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
    public static boolean signin(String userName,String pass) throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Company","root","123456@mysql");
        PreparedStatement ps0=con.prepareStatement("select *from credentials");
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
        System.out.println("Signed in succesfully");
        con.close();
        return true;
    }
    public static boolean addemp(String position,String name, String email,String skills,int age,String gender,int salary) throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Database loaded");
        Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Company","root","123456@mysql");
        System.out.println("Connection established");
        PreparedStatement initialisation=con.prepareStatement("create table if not exists credentials(id int auto_increment primary key ,password varchar(500) not null,position varchar(50));");
        initialisation.execute();
        System.out.println("Credentials success");
        PreparedStatement initialisation2=con.prepareStatement("create table if not exists details(id int primary key ,name varchar(100) not null,email varchar(100) not null,age int not null,gender varchar(50),skills varchar(500),projectlist JSON,pcwo int ,salary int not null,foreign key (id) references credentials(id));");
        initialisation2.execute();
        Random r=new Random();  //For password
        int rn=10^8 + r.nextInt(9*10^7);
        String password=String.valueOf(rn);
        String hashedPassword = Passwordhashing.hashPassword(password);
        //Adding employee in the credential database
        System.out.println("Error occur");

        PreparedStatement ps=con.prepareStatement("insert into credentials(password,position) values(?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setString(1, hashedPassword);
        ps.setString(2, position);
        int i=ps.executeUpdate();
        int lastInsertedId=0;
        if(i>0){
            System.out.println("Success");
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                lastInsertedId = generatedKeys.getInt(1);  // This is the last inserted ID
                System.out.println("Last inserted ID: " + lastInsertedId);
            }
        }
        else{
            System.out.println("Fail");
            return false;
        }
        PreparedStatement ps2= con.prepareStatement("insert into details(id , name,email,age,gender,skills,pcwo,salary) values(?,?, ?, ?, ?, ?, 0, ?)");
        ps2.setInt(1,lastInsertedId);
        ps2.setString(2, name);
        ps2.setString(3, email);
        ps2.setInt(4, age);
        ps2.setString(5, gender);
        ps2.setString(6, skills);
        ps2.setInt(7, salary);
        int k=ps2.executeUpdate();
        if(k>0){
            System.out.println("Success");
        }
        else{
            System.out.println("Fail");
        }
        con.close();
        return true;
    }
    public static boolean deleteuser(String userName,String pass) throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Company","root","123456@mysql");
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
    public static boolean forgotpassword(String userName,String pass) throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Company","root","123456@mysql");
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
            System.out.println("Adding employee");
            System.out.println("Enter employee position");
            String position=myObj.nextLine();
            System.out.println("Enter employee name");
            String name=myObj.nextLine();
            System.out.println("Enter employee email");
            String email=myObj.nextLine();
            System.out.println("Enter employee age");
            String a=myObj.nextLine();
            int age = Integer.parseInt(a);
            System.out.println("Enter employee gender");
            String gender=myObj.nextLine();
            System.out.println("Enter employee skills");
            String skills=myObj.nextLine();
            System.out.println("Enter employee salary");
            String s=myObj.nextLine();
            int salary=Integer.parseInt(s);
            addemp(position,name,email,skills,age,gender,salary);
        }
    }
}
