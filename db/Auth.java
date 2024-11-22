package db;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import org.json.JSONArray;
import org.mindrot.jbcrypt.BCrypt;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
    public static boolean signin(int empid,String pass, String pos) throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Company","root","123456@mysql");
        PreparedStatement initialisation=con.prepareStatement("create table if not exists credentials(id int auto_increment primary key ,password varchar(500) not null,position varchar(50));");
        initialisation.execute();
        System.out.println("Credentials success");
        PreparedStatement initialisation2=con.prepareStatement("create table if not exists details(id int primary key , position varchar(50), name varchar(100) not null,dob varchar(100),Fathername varchar(100),phoneNumber varchar(100),HighestQual varchar(100),Employed boolean default true,email varchar(100) not null,skills JSON,projectlist JSON,pcwo int default 0 ,salary int not null,address varchar(500),foreign key (id) references credentials(id));");
        initialisation2.execute();
        PreparedStatement ps0=con.prepareStatement("select *from credentials");
        ResultSet rs= ps0.executeQuery();
        boolean emplid=false;
        String hashpas = null;
        String p="";
        while (rs.next()){
            if(Objects.equals(empid, rs.getInt("id"))){
                emplid=true;
                p=rs.getString("position");
                hashpas=rs.getString("password");
                break;
            }
        }
        if(!emplid){
            System.out.println("Employee does not exist");
            con.close();
            return false;
        }
        if(!Passwordhashing.checkPassword(pass,hashpas) && Objects.equals(pos,p)){
            System.out.println("Incorrect Password");
            con.close();
            return false;
        }

        System.out.println("Signed in succesfully");
        con.close();
        return true;
    }
    public static String addemp(String position, String name,String dob, String Father, String email, ArrayList<String> skills,int salary,String phone,String qual,String address) throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Database loaded");
        Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Company","root","123456@mysql");
        System.out.println("Connection established");
        PreparedStatement initialisation=con.prepareStatement("create table if not exists credentials(id int auto_increment primary key ,password varchar(500) not null,position varchar(50));");
        initialisation.execute();
        System.out.println("Credentials success");
        PreparedStatement initialisation2=con.prepareStatement("create table if not exists details(id int primary key , position varchar(50), name varchar(100) not null, dob varchar(100),Fathername varchar(100),phoneNumber varchar(100),HighestQual varchar(100),Employed boolean default true,email varchar(100) not null,skills JSON,projectlist JSON,pcwo int default 0,salary int not null, address varchar(500),foreign key (id) references credentials(id));");
        initialisation2.execute();
        Random r=new Random();  //For password
        int rn=10^8 + r.nextInt(9*10^7);
        String password=String.valueOf(rn);
        String hashedPassword = Passwordhashing.hashPassword(password);
        //Adding employee in the credential database
        System.out.println("Error occur");
        //Do we want to check for any same emp???
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
            return "";
        }
        PreparedStatement ps2= con.prepareStatement("insert into details(id,position,name,dob,Fathername,phoneNumber,HighestQual,email,skills,salary,address) values(?,?, ?, ?, ?, ?, ?,?,?,?,?)");
        ps2.setInt(1,lastInsertedId);
        ps2.setString(2,position);
        ps2.setString(3, name);
        ps2.setString(4, dob);
        ps2.setString(5, Father);
        ps2.setString(6, phone);
        ps2.setString(7,qual);
        ps2.setString(8,email);
        JSONArray jsonArray = new JSONArray(skills);
        String jsonSkills = jsonArray.toString();

        ps2.setString(9,jsonSkills);//array list
        ps2.setInt(10,salary);
        ps2.setString(11,address);
        int k=ps2.executeUpdate();
        if(k>0){
            System.out.println("Success");
        }
        else{
            System.out.println("Fail");
        }

        con.close();
        return password;
    }
    public static boolean deleteuser(int empid,String pass) throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Company","root","123456@mysql");
        PreparedStatement ps0=con.prepareStatement("select *from details");
        ResultSet rs= ps0.executeQuery();
        boolean emplid=false;
        String hashpas = null;
        while (rs.next()){
            if(Objects.equals(empid, rs.getInt("id"))){
                emplid=true;
                hashpas=rs.getString("password");
                break;
            }
        }
        if(!emplid){
            System.out.println("Username does not exist");
            con.close();
            return false;
        }
        if(!Passwordhashing.checkPassword(pass,hashpas)){
            System.out.println("Incorrect Password");
            con.close();
            return false;
        }
        PreparedStatement ps=con.prepareStatement("delete from details where id=? ");
        //ps.setString(2,Passwordhashing.hashPassword(pass));
        ps.setInt(1,empid);
        int p=ps.executeUpdate();
        if(p>0){
            System.out.println("Employee deleted Succesfully");
        }
        else{
            System.out.println("Incorrect Employee id or Password");
            return false;
        }
        con.close();
        return true;
    }
    public static boolean forgotpassword(int empid,String pass) throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Company","root","123456@mysql");
        PreparedStatement ps0=con.prepareStatement("select *from credentials");
        ResultSet rs= ps0.executeQuery();
        boolean userexists=false;
        while (rs.next()){
            if(Objects.equals(empid, rs.getInt("id"))){
                userexists=true;
                break;
            }
        }
        if(!userexists){
            System.out.println("Employee does not exist");
            con.close();
            return false;
        }
        PreparedStatement ps=con.prepareStatement("update credentials set password= ? where id=?");
        ps.setString(1,Passwordhashing.hashPassword(pass));
        ps.setInt(2,empid);
        int p=ps.executeUpdate();
        if(p>0){
            System.out.println("Password Updated Succesfully");
        }
        else{
            //Mostly it won't go here
            System.out.println("Incorrect Employee id");
            return false;
        }
        con.close();
        return true;
    }
    //For ceo dashboard
    public static ArrayList<Integer> work_force() throws SQLException, ClassNotFoundException {
        ArrayList<Integer> wf=new ArrayList<Integer>();
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Database loaded");
        Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Company","root","123456@mysql");
        System.out.println("Connection established");
        PreparedStatement ps0=con.prepareStatement("select* from details");
        ResultSet rs= ps0.executeQuery();
        int nume=0;
        int numhr=0;
        while(rs.next()){
            if(rs.getBoolean("Employed")){
                if(rs.getString("position").equals("HR")){
                    numhr++;
                }
                else if(rs.getString("position").equals("EMP")){
                    nume++;
                }
            }
        }
        wf.add(nume);
        wf.add(numhr);
        return wf;
    }
    //for employee dashboard info
    public static ArrayList<String> empdash(String empId) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Database loaded");
        Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Company","root","123456@mysql");
        System.out.println("Connection established");
        PreparedStatement ps0=con.prepareStatement("select name,pcwo from details where id=?");
        ps0.setInt(1,Integer.parseInt(empId));
        ResultSet rs= ps0.executeQuery();
        ArrayList<String> empdashboard=new ArrayList<>();
        while(rs.next()){
            empdashboard.add(rs.getString("name"));
            empdashboard.add(empId);
            empdashboard.add(String.valueOf(rs.getInt("pcwo")));
        }
        return empdashboard;
    }
    public static ArrayList<String> getempdetail(String empId) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Database loaded");
        Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Company","root","123456@mysql");
        System.out.println("Connection established");
        PreparedStatement ps0=con.prepareStatement("select * from details where id=?");
        ps0.setInt(1,Integer.parseInt(empId));
        ResultSet rs= ps0.executeQuery();
        ArrayList<String> empdetail=new ArrayList<>();
        while(rs.next()){
            empdetail.add(rs.getString("name"));
            empdetail.add(rs.getString("Fathername"));
            empdetail.add(rs.getString("dob"));
            empdetail.add(String.valueOf(rs.getInt("salary")));
            empdetail.add(rs.getString("address"));
            empdetail.add(rs.getString("email"));
            empdetail.add(rs.getString("phoneNumber"));
            empdetail.add(rs.getString("highestQual"));
        }
        return empdetail;
    }
    public static ArrayList<String> getempprojects(String empId) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Database loaded");
        Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Company","root","123456@mysql");
        System.out.println("Connection established");
        PreparedStatement ps0=con.prepareStatement("select * from details where id=?");
        ps0.setInt(1,Integer.parseInt(empId));
        ResultSet rs= ps0.executeQuery();
        ArrayList<String> empproject=new ArrayList<>();
        Gson gson = new Gson();

        // Define the type for the ArrayList<String>
        Type listType = new TypeToken<ArrayList<String>>() {}.getType();

        // Convert JSON string back to ArrayList<String>
        empproject = gson.fromJson(rs.getString("projectlist"), listType);
        return empproject;
    }

//    public static void main(String[] args) throws Exception{
//        while(true) {
//            Scanner myObj = new Scanner(System.in);  // Create a Scanner object
//            System.out.println("Adding employee");
//            System.out.println("Enter employee position");
//            String position=myObj.nextLine();
//            System.out.println("Enter employee name");
//            String name=myObj.nextLine();
//            System.out.println("Enter employee email");
//            String email=myObj.nextLine();
//            System.out.println("Enter employee age");
//            String a=myObj.nextLine();
//            int age = Integer.parseInt(a);
//            System.out.println("Enter employee gender");
//            String gender=myObj.nextLine();
//            System.out.println("Enter employee skills");
//            String skills=myObj.nextLine();
//            System.out.println("Enter employee salary");
//            String s=myObj.nextLine();
//            int salary=Integer.parseInt(s);
//            addemp(position,name,email,skills,age,gender,salary);
//        }
    }

