#include "HR.h"
// #include <bits/stdc++.h>
using namespace std;

// SHORTEN THIS
HRDepartment::HRDepartment()
{
    // Bring the data from the files

    // Credentials
    ifstream infile("Credentials.txt");
    string line;

    // Read each line in the file
    while (getline(infile, line))
    {
        istringstream iss(line);
        string part;
        vector<string> parts;
        while (getline(iss, part, ':'))
        {
            parts.push_back(part);
        }
        // Store them in the struct
        // id:password:status:hired_status
        Authenticate auth = {parts[0], parts[1], parts[2], parts[3]};
        // Credentials loaded
        credentials.push_back(auth);
    }
    // Close the file
    infile.close();

    // Employees
    vector<Employee> employees;
    ifstream file;
    file.open("Employees.txt");


    while (getline(file, line))
    {
        Employee employee;
        size_t pos = 0;

        // Extract name
        pos = line.find(":");
        employee.name = line.substr(0, pos);
        line.erase(0, pos + 1);

        // Extract age
        pos = line.find(":");
        employee.age = line.substr(0, pos);
        line.erase(0, pos + 1);

        // Extract id
        pos = line.find(":");
        employee.id = line.substr(0, pos);
        line.erase(0, pos + 1);

        // Extract noOfProjects
        pos = line.find(":");
        // Convert to string
        employee.noOfProjects = stoi(line.substr(0, pos));
        line.erase(0, pos + 1);

        // Separate projects and skills (delimited by ",,,")
        pos = line.find(",,,");
        string projectsPart = line.substr(0, pos);
        string skillsPart = pos != string::npos ? line.substr(pos + 3) : "";

        // Split projects by comma and add to assignedProjects
        istringstream projStream(projectsPart);
        string project;
        while (getline(projStream, project, ','))
        {
            employee.assignedProjects.push_back(project);
        }

        // Split skills by comma and add to skills
        istringstream skillStream(skillsPart);
        string skill;
        while (getline(skillStream, skill, ','))
        {
            employee.skills.push_back(skill);
        }

        employees.push_back(employee);
    }

    file.close();

    // Projects
    file.open("Projects.txt");
    while (getline(file, line))
    {
        Project project;
        size_t pos = 0;

        // Extract name
        pos = line.find(":");
        project.name = line.substr(0, pos);
        line.erase(0, pos + 1);

        // Extract id
        pos = line.find(":");
        project.id = line.substr(0, pos);
        line.erase(0, pos + 1);

        // Extract assigned (convert to bool)
        pos = line.find(":");
        project.assigned = (line.substr(0, pos) == "1");
        line.erase(0, pos + 1);

        // Extract completed (convert to bool)
        pos = line.find(":");
        project.completed = (line.substr(0, pos) == "1");
        line.erase(0, pos + 1);

        // Extract employeesAssigned (split by commas)
        istringstream employeeStream(line);
        string employeeId;
        while (getline(employeeStream, employeeId, ','))
        {
            project.employeesAssigned.push_back(employeeId);
        }

        projects.push_back(project);
    }

    file.close();
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
            cout << "Error! Invalid skill\n";
            while (!validSkill(skillInput))
            {
                cout << "Please enter a valid skill";
                getline(cin, skillInput);
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
{
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
    while (!validId(id))
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
void HRDepartment::fireEmployee(string id)
{   //incomplete code by GreatNaveedBoy-->need to check if employee exists or not
    cout << "Are you sure you want to fire this employee?\n";
    for (auto i : employees)
    {
        if (i.id == id)
        {
            cout << "Employee name: " << i.name << "\n";
            cout << "Employee id: " << i.id << "\n";
            break;
        }
    }
    cout << "Enter yes to confirm, no to cancel\n";
    string confirm;
    getline(cin, confirm);
    transform(confirm.begin(), confirm.end(), confirm.end(), ::tolower);

    // Check if the employee is assigned on any projects or not

    if (confirm == "no")
    {
        return;
    }
    else
    {
        for (auto i : credentials)
        {
            if (i.empId == id)
            {
                i.hired_status = "FIRED";
                break;
            }
        }
        for (auto i : employees)
        {
            if (i.id == id)
            {
                i.hired_status = "FIRED";
                return;
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