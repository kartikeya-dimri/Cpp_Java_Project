#pragma once
#include <bits/stdc++.h>
using namespace std;
#include "Authenticate.h"

// struct Project
// {
//     string projectname;
//     string projectID;
// };

class CEO
{
    // Need the credentials 
private:
    vector<Authenticate> credentials;
    vector<Project> projects;

public:
    CEO();

    void ceoRunner();

    void addHR(string id, string password);
    void removeHR(string id);
    void addProject(string projectName, string projectID);
    bool uniqueHRID(string id);
    bool uniqueProjID(string id);
    void loggedOut();
};