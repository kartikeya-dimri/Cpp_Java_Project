#include "DataLoader.h"

vector<Authenticate> dataLoader::loadCredentials()
{
    vector<Authenticate> credentials;
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

    return credentials;
}


vector<Employee> dataLoader::loadEmployees()
{
    // Employees
    vector<Employee> employees;
    ifstream file;
    file.open("Employees.txt");
    string line;


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

    return employees;
}


vector<Project> dataLoader::loadProjects()
{
    ifstream file;
    string line;
    vector<Project> projects;

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

    return projects;
}