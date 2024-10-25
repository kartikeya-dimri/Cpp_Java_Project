#pragma once
#include<bits/stdc++.h>
using namespace std;


struct Authenticate
{
    string empId;
    string password;
    string status;
    string hired_status;
};

struct Employee
{
    string name;
    string age;
    string id;
    // Faster comparison of available employees
    int noOfProjects;
    vector<string> assignedProjects;
    vector<string> skills;
};


struct Project
{
    string name;
    string id;
    bool assigned;
    bool completed;
    // Just keep their ids
    vector<string> employeesAssigned;
};


class HRDepartment
{
    private:
        vector<Authenticate> credentials;
        // For project assigning
        vector<Employee> employees;
        vector<Project> projects;
        int maxNoOfProjects=3;
        // Standard skills
        vector<string> skills={"cpp","java","javascript","python"};

    public:
        // Bring the data from the files
        HRDepartment();
        // Assign the skills which are valid according to the company  standards
        bool validSkill(string skill);
        vector<string> assignSkills(string id);
        void addEmployee();
        void fireEmployee();
        void projectCompleted();
        // Just return the ids of the employees satisfying the conditions
        vector<string> searchEmployee();
        void assignProjects();
        void loggedOut();

};