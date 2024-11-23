package Java_Main;

public class ProjectAssignment {
    static {
        System.loadLibrary("projectassignment"); // Load the native library at runtime
    }

    // Employee class to match the structure in C++ code
    public static class Employee {
        public String name;
        public String id;
        public int noOfProjects;
        public String[] skills;

        public Employee(String name, String id, String[] skills, int noOfProjects) {
            this.name = name;
            this.id = id;
            this.skills = skills;
            this.noOfProjects = noOfProjects;
        }
    }

    // Native method declaration
    private native String[] assignProjects(int requiredNumber, String[] skills, Employee[] employees);

    // Public method to call the native function
    public String[] assignProjectsToEmployees(int requiredNumber, String[] skills, Employee[] employees) {
        return assignProjects(requiredNumber, skills, employees);
    }

    // Example main method to demonstrate usage
    public static void main(String[] args) {
        ProjectAssignment pa = new ProjectAssignment();

        // Example data
        String[] requiredSkills = {"Java", "Python", "C++"};
        Employee[] employees = new Employee[] {
            new Employee("John Doe", "E001", new String[]{"Java", "Python", "C++"}, 2),
            new Employee("Jane Smith", "E002", new String[]{"Java", "Python"}, 1),
            new Employee("Bob Johnson", "E003", new String[]{"Java", "C++", "Python"}, 3)
        };

        // Call the native method
        String[] result = pa.assignProjectsToEmployees(2, requiredSkills, employees);

        // Print results
        if (result[0].equals("1")) {
            System.out.println("Project assignment successful!");
            System.out.println("Assigned employees:");
            for (int i = 1; i < result.length; i++) {
                System.out.println("Employee ID: " + result[i]);
            }
        } else {
            System.out.println("Project assignment failed!");
            System.out.println(result[1]); // Print error message
        }
    }
}
