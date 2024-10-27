// #pragma once
#include "UtilityStructs.h"
using namespace std;

// This class has all the methods for loading the data from the files

class dataLoader
{
    public:
        vector<Authenticate> loadCredentials();
        vector<Employee> loadEmployees();
        vector<Project> loadProjects();
};