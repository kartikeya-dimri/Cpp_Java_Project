#include "DataLoader.h"//

vector<Authenticate> DataLoader::loadCredentials()
{
    vector<Authenticate> credentials;
    // Credentials
    ifstream infile("Credentials.txt");
    if(!infile.is_open()){
        cerr << "Error: Could not open file 'Credentials.txt'" << endl;
        // std::cout << "Current path: " << std::filesystem::current_path() << std::endl;
        return credentials;
    }

    string line;

    // Read each line in the file
    while (getline(infile, line))
    {
        if(line==""){//used when an empty string due to new line at the end of file is read, else it'll give seg fault
            break;
        }
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


vector<Employee> DataLoader::loadEmployees()
{
    // Employees
    vector<Employee> employees;
    ifstream file;
    file.open("Employees.txt");
    string line;

    if(!file) 
    {
        cerr<<"cant open file"<<endl;
    }


    while (getline(file, line))
    {
        if(line==""){//used when an empty string due to new line at the end of file is read, else it'll give seg fault
            break;
        }
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

vector<Project> DataLoader::loadProjects()
{
    //ProjectName:ProjectID:assigned(0/1):completed(0/1):emp1,emp2,emp3:skill1,skill2,skill3

    ifstream file("Projects.txt");
    string line;
    vector<Project> projects;

    if (!file)
    {
        cerr << "Cannot open file" << endl;
        return projects;
    }

    while (getline(file, line))
    {
        if (line.empty())
        {
            continue; // Skip empty lines to avoid segmentation faults
        }

        Project project;
        size_t pos = 0;

        // Extract project name
        pos = line.find(":");
        project.name = line.substr(0, pos);
        line.erase(0, pos + 1);

        // Extract project ID
        pos = line.find(":");
        project.id = line.substr(0, pos);
        line.erase(0, pos + 1);

        // Extract assigned status (convert to bool)
        pos = line.find(":");
        project.assigned = (line.substr(0, pos) == "1");
        line.erase(0, pos + 1);

        // Extract completed status (convert to bool)
        pos = line.find(":");
        project.completed = (line.substr(0, pos) == "1");
        line.erase(0, pos + 1);

        // Extract employeesAssigned (split by commas)
        pos = line.find(":");
        string employeesStr = line.substr(0, pos);
        line.erase(0, pos + 1);
        istringstream employeeStream(employeesStr);
        string employeeId;
        while (getline(employeeStream, employeeId, ','))
        {
            project.employeesAssigned.push_back(employeeId);
        }

        // Extract skills required (split by commas)
        istringstream skillStream(line);
        string skill;
        while (getline(skillStream, skill, ','))
        {
            project.skills.push_back(skill);
        }

        projects.push_back(project);
    }

    file.close();
    return projects;
}



{
    ifstream file;
    string line;
    vector<Project> projects;

    // Projects
    file.open("Projects.txt");

    if(!file) 
    {
        cerr<<"cant open file"<<endl;
    }
    while (getline(file, line))
    {
        if(line==""){//used when an empty string due to new line at the end of file is read, else it'll give seg fault
            break;
        }
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