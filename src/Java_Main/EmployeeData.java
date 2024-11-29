package Java_Main;

import java.util.ArrayList;

public class EmployeeData extends EmployeeSkillData {
    private String name;
    private String fatherName;
    private String dob;
    private String salary;
    private String address;
    private String email;
    private String phoneNum;
    private String highestQual;

    // Constructor with all parameters
    public EmployeeData(String name, String id, String fatherName, String dob, String salary, String address, 
                        String email, String phoneNum, String highestQual, ArrayList<String> skills) {
        super(id, skills);
        this.name = name;
        this.fatherName = fatherName;
        this.dob = dob;
        this.salary = salary;
        this.address = address;
        this.email = email;
        this.phoneNum = phoneNum;
        this.highestQual = highestQual;
    }

    // Constructor with mandatory fields
    public EmployeeData(String name, String id) {
        super(id, new ArrayList<>(), 0);
        this.name = name;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getFatherName() {
        return fatherName;
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
}