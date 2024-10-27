#pragma once
#include<bits/stdc++.h>
using namespace std;
#include "Authenticate.h"

// This class has all the methods for loading the data from the files

class dataLoader
{
    public:
        vector<Authenticate> loadCredentials();
        vector<Employee> loadEmployees();
        vector<Project> loadProjects();
};