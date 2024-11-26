package Java_Main;

import java.util.ArrayList;

public class AssignProjectsJNI {

    // Declare the native method
    public static native ArrayList<String> assignProjects(int requiredNumber, ArrayList<String> skills, ArrayList<EmployeeSkillData> employees);

    static {
        System.loadLibrary("nativeLib"); // Load the native library
    }

    // public static void main(String[] args) {
    //     // // Example skills
    //     // ArrayList<String> skills = new ArrayList<>();
    //     // skills.add("Java");
    //     // skills.add("C++");

    //     // // Example employees
    //     // ArrayList<EmployeeSkillData> employees = new ArrayList<>();
    //     // employees.add(new EmployeeSkillData("E1", new ArrayList<>(List.of("Java", "Python")), 2));
    //     // employees.add(new EmployeeSkillData("E2", new ArrayList<>(List.of("C++", "Java")), 3));
    //     // employees.add(new EmployeeSkillData("E3", new ArrayList<>(List.of("Python")), 5));

    //     // Call native method
    //     ArrayList<String> result = AssignProjects.assignProjects(2, skills, employees);

    //     // Print the result
    //     for (String res : result) {
    //         System.out.println(res);
    //     }
    // }
}
