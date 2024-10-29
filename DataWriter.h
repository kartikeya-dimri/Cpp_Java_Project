#pragma once//
#include "UtilityStructs.h"

// This class has all the methods for writing the data to the files

class DataWriter
{
    public:
        void writeCredentials(const std::vector<Authenticate>& credentials);
        void writeProjects(const std::vector<Project>& projects);
        void writeEmployees(const std::vector<Employee>& emps);
};