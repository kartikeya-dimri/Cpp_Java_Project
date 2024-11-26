#include<bits/stdc++.h>
using namespace std;

struct Employee{
    string id;
    // Faster comparison of available employees
    int noOfProjects;
    vector<string> skills;
};

bool sortEmployeesForAssign(Employee* & a, Employee* & b){
    return a->noOfProjects < b->noOfProjects;
}

vector<string> assignProjects(int requiredNumber, vector<string>skills, vector<Employee>employees) {
    //this emps vector has all the employees in the company
    //
    int maxNoOfProjects=5;
    vector<string>result;//result[0] is 1/0 bool successful or not-if 1, then rest will be filled with empIds , if 0- then result[1] will hold a string which will have info about adding new employees with skills
    // Find employees with the required skills who are not fully assigned
    vector<Employee*> availableEmployees;
    for (auto& employee : employees) {
        bool hasAllSkills = true;

        // Check if employee has all required skills
        for (const auto& skill : skills) {
            if (find(employee.skills.begin(), employee.skills.end(), skill) == employee.skills.end()) {
                hasAllSkills = false;
                break;
            }
        }

        // If employee has all skills and is not overloaded with projects, add them to available employees
        if (hasAllSkills && employee.noOfProjects < maxNoOfProjects) {  // Assuming a threshold of 5 projects
            availableEmployees.push_back(&employee);
        }
    }

    // Assign available employees to the project
    if (availableEmployees.size()>=requiredNumber) {
        //now I'll sort the employees on the basis of lesser number of assigned projects and take the ones with the least number of assigned projects
        result.push_back("1");
        sort(availableEmployees.begin(),availableEmployees.end(),sortEmployeesForAssign);
        
        // Assign the required number of employees to the project
        for (int i = 0; i < requiredNumber; i++) {
            Employee* emp = availableEmployees[i];
            result.push_back(emp->id);
        }

    } 
    else {
        result.push_back("0");
        int need=requiredNumber-availableEmployees.size();
        string message="Need to hire "+to_string(need)+" more employees for completing this project.\n";
        message+="Please add "+to_string(need)+" more employees with these skills\n";
        for(int i=1; i<=skills.size(); i++){
            message+=to_string(i)+". "+skills[i-1]+"\n";
        }
        result.push_back(message);
    }
    return result;
}
