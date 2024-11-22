package Java_Main;

import java.util.ArrayList;

public class EmployeeData {
    private String name;
    private String id;
    private String fatherName;
    private String dob;
    private String salary;
    private String address;
    private String email;
    private String phoneNum;
    private String highestQual;
    private ArrayList<String> skills;


    // Constructor with all parameters. This will be useful for print option in the employee dashboard
    public EmployeeData(String name, String id, String fatherName, String dob, String salary, String address, 
                    String email, String phoneNum, String highestQual, ArrayList<String> skills) {
        this.name = name;
        this.id=id;
        this.fatherName = fatherName;
        this.dob = dob;
        this.salary = salary;
        this.address = address;
        this.email = email;
        this.phoneNum = phoneNum;
        this.highestQual = highestQual;
        this.skills = skills != null ? new ArrayList<>(skills) : new ArrayList<>();
    }

    // Constructor with mandatory fields- name and id--may be useful somewhere
    public EmployeeData(String name, String id) {
        this.name = name;
        this.id=id;
        this.skills = new ArrayList<>();
    }


    // Getters
    public String getFatherName() {
        return fatherName;
    }
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDob() {
        return dob;
    }

    public String getSalary() {
        return salary;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getHighestQual() {
        return highestQual;
    }

    public ArrayList<String> getSkills() {
        return new ArrayList<>(skills);
    }

}
