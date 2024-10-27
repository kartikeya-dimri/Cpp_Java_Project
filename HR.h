#pragma once//
#include "Authenticate.h"
using namespace std;



class HRDepartment
{
private:
    vector<Authenticate> credentials;
    // For project assigning
    vector<Employee> employees;
    vector<Project> projects;
    int maxNoOfProjects = 3;
    // Standard skills
    vector<string> skills = {"cpp", "java", "javascript", "python"};

public:
    // Bring the data from the files
    HRDepartment();
    // Assign the skills which are valid according to the company  standards
    bool validSkill(string skill);
    vector<string> assignSkills(string id);
    bool validId(string id);
    bool isEmployeeAssignedProject(Employee e,string projectId);
    void addEmployee();
    void fireEmployee(string id);
    void projectCompleted(string projectId);
    // Just return the ids of the employees satisfying the conditions
    vector<Employee> searchEmployee();
    void assignProjects();
    void loggedOut();
};