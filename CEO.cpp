#pragma once
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

    // Now read the projects
    ifstream infile1("Projects.txt");
    // Read each line in the file
    while(getline(infile1,line))
    {
        istringstream iss(line);
        string part;
        vector<string> parts;
        while (getline(iss,part,':'))
        {
            parts.push_back(part);
        }
        // name:id
        Project proj={parts[0],parts[1]};
        projects.push_back(proj);
    }
    // Close the file
    infile1.close();
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
        if(i.projectID==id)
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
    Project add={projectName,projectID};
    projects.push_back(add);
}

void CEO::loggedOut()
{
    // Now write back the contents of the vectors to the file
    ofstream outfile("Credentials.txt");

    for(int i=0;i<credentials.size();i++)
    {
        Authenticate j=credentials[i];
        string write=j.empId+":"+j.password+":"+j.status+":"+j.hired_status;
        outfile<<write<<"\n";
    }
    outfile.close();


    ofstream outfile1("Projects.txt");

    for(int i=0;i<projects.size();i++)
    {
        Project j=projects[i];
        string write=j.projectID+":"+j.projectname;
        outfile1<<write<<"\n";
    }
    outfile1.close();
}