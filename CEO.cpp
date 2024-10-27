#include "CEO.h"
#include<bits/stdc++.h>
using namespace std;

CEO::CEO()
{
    ifstream infile("Credentials.txt");
    string line;

    // Read each line in the file
    while(getline(infile,line))
    {
        istringstream iss(line);
        string part;
        vector<string> parts;
        while (getline(iss,part,':'))
        {
            parts.push_back(part);
        }
        // Store them in the struct
        // id:password:status:hired_status
        Authenticate auth={parts[0],parts[1],parts[2],parts[3]};
        // Credentials loaded
        credentials.push_back(auth);
    }
    // Close the file
    infile.close();

    // Projects
    ifstream file;
    file.open("Projects.txt");
    while (getline(file, line))
    {
        Project project=Project();
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

void CEO::ceoRunner(){
    cout << "You are successfully logged in!\n";
    cout << "Welcome CEO\n";

    while(1)
    {
        cout << "What do you want to do?\n";
        cout << "1) Add HR\n";
        cout << "2) Remove HR\n";
        cout << "3) Add Project\n";
        cout << "4) Exit\n";
        
        string task;
        getline(cin, task);
        transform(task.begin(), task.end(), task.begin(), ::toupper);

        if(task == "ADD HR" || task == "1")
        {
            cout << "Enter new employee ID: ";
            string id;
            getline(cin, id);
            
            cout << "Enter new employee's password: ";
            string password;
            getline(cin, password);
            
            this->addHR(id, password);
        }
        else if(task == "REMOVE HR" || task == "2")
        {
            cout << "Enter ID of employee to be fired: ";
            string id;
            getline(cin, id);
            if(id=="1"){
                cout<<"CEO can't be fired."<<endl;
            }
            else{
                this->removeHR(id);
            }
        }
        else if(task == "ADD PROJECT" || task == "3")
        {
            cout << "Enter new project's name: ";
            string projectname;
            getline(cin, projectname);
            
            cout << "Enter new project's id: ";
            string projectid;
            getline(cin, projectid);

            this->addProject(projectname, projectid);
        }
        else if(task == "EXIT" || task == "4")
        {
            return;
        }
        else
        {
            cout << "Invalid command\n";
        }
    }
}


// Check if the entered ID is unique
bool CEO::uniqueHRID(string id)
{
    for(auto i:credentials)
    {
        if(i.empId==id)
        {
            return false;
        }
    }
    return true;
}

void CEO::addHR(string id, string password)
{
    // Write the credentials to the file
    string HRcredential=id+":"+password+":"+"HR"+":"+"HIRED";
    // First check if the id entered is unique

    // Wait till a unique id is entered
    while(!uniqueHRID(id))
    {
        cout<<"Please enter a unique ID!\n";
        getline(cin,id);
    }
    Authenticate hr={id,password,"HR","HIRED"};
    credentials.push_back(hr);
}

void CEO::removeHR(string id)
{
    for(int j = 0; j < credentials.size(); j++)
    {
        if(credentials[j].empId == id)
        {
            credentials[j].hired_status = "FIRED";
            return;  // Exit after finding and modifying
        }
    }
    cout << "HR with ID " << id << " not found!" << endl;
}

bool CEO::uniqueProjID(string id)
{
    for(auto i:projects)
    {
        if(i.id==id)
        {
            return false;
        }
    }
    return true;
}

void CEO::addProject(string projectName,string projectID)
{
    while(!uniqueProjID(projectID))
    {
        cout<<"Enter a valid project id\n";
        // cin>>projectID;
        getline(cin,projectID);
    }
    Project add=Project(projectName,projectID);
    projects.push_back(add);
}

void CEO::loggedOut()
{// {   BAD CODE BY GREATman
    // Now write back the contents of the vectors to the file
    ofstream outfile("Credentials.txt");

    for(int i=0;i<credentials.size();i++)
    {
        Authenticate j=credentials[i];
        string write=j.empId+":"+j.password+":"+j.status+":"+j.hired_status+":";
        outfile<<write<<"\n";
    }
    outfile.close();


    ofstream outfile1("Projects.txt");

    for(int i=0;i<projects.size();i++)
    {
        Project j=projects[i];
        string write=j.name+":"+j.id+":"+to_string(j.assigned)+":"+to_string(j.completed)+":";
        string emps="";
        for(int i=0; i<j.employeesAssigned.size()-1; i++){
            emps+=j.employeesAssigned[i]+",";
        }
        emps+=j.employeesAssigned[j.employeesAssigned.size()-1];
        // emps+=":";
        outfile1<<write<<emps<<endl;
    }
    outfile1.close();
}