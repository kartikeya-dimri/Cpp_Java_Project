#pragma once
#include "Authenticate.h"
#include "DataWriter.h"



class HRDepartment
{
private:
    std::vector<Authenticate> credentials;
    // For project assigning
    std::vector<Employee> employees;
    std::vector<Project> projects;
    int maxNoOfProjects = 3;
    // Standard skills
    std::vector<std::string> skills = {"cpp", "java", "javascript", "python"};

public:
    // Bring the data from the files
    HRDepartment();
    void hrRunner();
    // Assign the skills which are valid according to the company standards
    bool validSkill(std::string skill);
    std::vector<std::string> assignSkills(std::string id,bool addingEmployee=0);
    bool validId(std::string id);
    bool isEmployeeAssignedProject(Employee e,std::string projectId);
    void addEmployee();
    void fireEmployee(std::string id);
    void projectCompleted(std::string projectId);
    // Just return the ids of the employees satisfying the conditions
    std::vector<Employee> searchEmployee();
    void assignProjects(string projectId);
    void loggedOut();
    static bool sortEmployeesForAssign(Employee* & a, Employee* & b);//need to make this static because using with stl sort
};