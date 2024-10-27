#pragma once//
#include<bits/stdc++.h>
using namespace std;

// The structs for all the different methods
struct Project
{
    string name;
    string id;
    bool assigned=0;
    bool completed=0;
    // Just keep their ids
    vector<string> employeesAssigned;

    Project()=default;

    Project(string pName, string id)
        :name(pName),id(id) {}
};


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
    string hired_status;
};