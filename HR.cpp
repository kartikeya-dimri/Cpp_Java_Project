#include "HR.h"//
// #include <bits/stdc++.h>
using namespace std;



HRDepartment::HRDepartment()
{
    // Bring the data from the files
    DataLoader* dl=new DataLoader();
    // Credentials
    credentials=dl->loadCredentials();
    // Employees
    employees=dl->loadEmployees();
    // Projects
    projects=dl->loadProjects();

    // Free allocated memory
    delete dl;
}

bool HRDepartment::validSkill(string skill)
{
    for (auto i : skills)
    {
        if (i == skill)
        {
            return true;
        }
    }
    return false;
}

vector<string> HRDepartment::assignSkills(string id)
{
    // Assign skills to the employee
    cout << "Enter the skills of the employee with id " << id << "\n";
    cout << "Enter \"over\" when you are done\n";

    string skillInput;
    vector<string> skillsAssigned;
    getline(cin, skillInput);

    while (1)
    {
        transform(skillInput.begin(), skillInput.end(), skillInput.begin(), ::tolower);
        // Check it
        if (!validSkill(skillInput))
        {
            cout << "Error! Invalid skill. \n";
            while (!validSkill(skillInput))
            {
                cout << "Please enter a valid skill: ";
                getline(cin, skillInput);
                if (skillInput == "over")
                {
                    return skillsAssigned;
                }
            }
        }
        // Valid skill entered
        skillsAssigned.push_back(skillInput);

        getline(cin, skillInput);
        if (skillInput == "over")
        {
            return skillsAssigned;
        }
    }
}

bool HRDepartment::validId(string id)
{
    // Duplicate
    for (auto i : credentials)
    {
        if (i.empId == id)
        {
            return false;
        }
    }
    return true;
}

void HRDepartment::addEmployee()
{   //INCOMPLETE-- NEED TO CHECK IF ALL THE INPUTS ARE OF VALID DATA TYPES
    cout << "Enter employee details\n";
    string name, age, id;
    // Nothing assigned to new hire
    // int noOfProjects=0;

    cout << "Enter employee name: ";
    getline(cin, name);
    cout << "Enter employee age: ";
    getline(cin, age);
    cout << "Enter employee id: ";
    getline(cin, id);

    // Keep asking till we get a valid id
    while (!validId(id) || id.empty())
    {
        cout << "Please enter a valid id: ";
        getline(cin, id);
    }
    string password;
    cout << "Enter employee's password: ";
    getline(cin, password);
    // Call assignSkills
    vector<string> skills = assignSkills(id);
    // assignedProjects vector will be empty

    // Empty vector assigned
    vector<string> projects;
    // Add the credentials
    credentials.push_back({id, password, "EMP", "HIRED"});
    employees.push_back({name, age, id, 0, projects, skills, "HIRED"});
}


// INCOMPLETE
void HRDepartment::fireEmployee(string ID)
{   //incomplete code by GreatNaveedBoy-->need to check if employee exists or not
    Employee emp;
    bool found=false;
    cout << "Are you sure you want to fire this employee?\n";
    for (auto i : employees)
    {
        if (i.id == ID)
        {
            cout << "Employee name: " << i.name << "\n";
            cout << "Employee id: " << i.id << "\n";
            emp=i;
            found=true;
            cout<<"Employee found\n";
            break;
        }
    }
    cout << "Enter yes to confirm, no to cancel\n";
    string confirm;
    getline(cin, confirm);
    transform(confirm.begin(), confirm.end(), confirm.end(), ::tolower);


    if (confirm == "no")
    {
        return;
    }
    else
    {
        // Check if the employee exists
        if(!found)
        {
            cout<<"Employee with ID "<<ID<<" not found!\n";
            return;
        }
        // Check if the employee is assigned to any projects
        if(emp.noOfProjects>0)
        {
            cerr<<"Error: Employee is assigned to projects, can't be fired right now\n";
            return;
        }
        // Pass references for altering the vector
        for(int j = 0; j < credentials.size(); j++)
        {
            if(credentials[j].empId == ID)
            {
                credentials[j].hired_status = "FIRED";
                break;  // Exit after finding and modifying
            }
        }
        for (int i = 0; i < employees.size(); i++)
        {
            if (employees[i].id == ID)
            {
                employees[i].hired_status = "FIRED";
                break;
            }
        }
    }
}

bool HRDepartment::isEmployeeAssignedProject(Employee e, string projectID)
{
    for(auto i:e.assignedProjects)
    {
        if(i==projectID)
        {
            return true;
        }
    }
    return false;
}

void HRDepartment::projectCompleted(string projectId)
{
    for (auto i : projects)
    {
        if (i.id == projectId)
        {
            // Project is completed
            i.completed = true;
        }
    }
    // NOW REMOVE THIS PROJECT FROM ALL THE EMPLOYEES WHO WERE WORKING ON THIS
    for (auto i : employees)
    {
        if (isEmployeeAssignedProject(i, projectId))
        {
            // Remove this project id from the employee's assignedProjects vector
            (i.assignedProjects).erase(remove(i.assignedProjects.begin(),i.assignedProjects.end(),projectId),i.assignedProjects.end());

            i.noOfProjects-=1;
        }
    }

}

// INCOMPLETE
vector<Employee> HRDepartment::searchEmployee()
{
    // Sort according to the availability
    // Assigned projects==availability
    cout<<"Enter the skills of the employees you are searching for\n";
    cout<<"Enter \"over\" when you are done";

    string skillInput;
    vector<string> skillsearch;

    getline(cin,skillInput);

    while (1)
    {
        while(!validSkill(skillInput))
        {
            cout<<"Please enter a valid skill!!: ";
            getline(cin,skillInput);
        }

        skillsearch.push_back(skillInput);

        getline(cin,skillInput);
        if(skillInput=="over")
        {
            break;
        }

    }


    vector<Employee> matchingEmployees;

    for(auto i:employees)
    {
        bool hasAllSkills=true;
        for(auto skill:skillsearch)
        {
            // One of the skills is not found
            if(find(i.skills.begin(),i.skills.end(),skill)==i.skills.end())
            {
                hasAllSkills=false;
                break;
            }
        }
        // Has all the required skills
        if(hasAllSkills)
        {
            matchingEmployees.push_back(i);
        }
    }

    return matchingEmployees;
}


void HRDepartment::hrRunner()
{
    cout << "Welcome to the HR Department\n";
    while (1)
    {
        cout<<"Select the number of the action that you want to do"<<endl;
        cout << "1)Add employee\n2)Fire employee\n3)Assign projects\n4)Project completed\n5)Logout\n";
        string choice;
        getline(cin, choice);
        // Standard comparison
        transform(choice.begin(), choice.end(), choice.begin(), ::tolower);
        if (choice == "1" or choice=="add employee")
        {
            addEmployee();
        }
        else if (choice == "2" or choice=="fire employee")
        {
            cout << "Enter the id of the employee you want to fire\n";
            string id;
            getline(cin, id);
            fireEmployee(id);
        }
        else if (choice == "3" or choice=="assign projects")
        {
            // assignProjects();
        }
        else if (choice == "4" or choice=="project completed")
        {
            cout << "Enter the id of the project which is completed\n";
            string projectId;
            getline(cin, projectId);
            projectCompleted(projectId);
        }
        else if (choice == "5" or choice=="logout")
        {
            loggedOut();
            break;
        }
    }
}

void HRDepartment::loggedOut(){
    DataWriter d;
    d.writeCredentials(credentials);
    d.writeEmployees(employees);
    d.writeProjects(projects);
}