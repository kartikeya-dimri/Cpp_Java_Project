#include<bits/stdc++.h>
using namespace std;

struct Employee{
    string name;
    string age;
    string id;
    // Faster comparison of available employees
    int noOfProjects;
    vector<string> assignedProjects;
    vector<string> skills;
    string hired_status;
};

bool sortEmployeesForAssign(Employee* & a, Employee* & b){
    return a->noOfProjects < b->noOfProjects;
}



// void assignProjects(string projectId) {
//     for (auto& project : projects) {
//         if (project.id!=projectId) {
//             continue; 
//         }
//         if(project.assigned|| project.completed){
//             // Skip projects that are already assigned or completed
//             cout<<"This project has already been assigned or completed"<<endl;
//             return;
//         }

//         // Prompt for skills needed for this project
//         cout << "Enter the skills needed for project: " << project.name << " (ID: " << project.id << ")\n";
//         cout << "Enter 'over' when you are done.\n";

//         vector<string> requiredSkills=assignSkills("");
//         project.skills=requiredSkills;
//         int requiredNumber;
//         cout<<"Enter the number of employees that are required for completing this project"<<endl;
//         cin>>requiredNumber;
//         cin.ignore(); // to clear the newline character from the input buffer

//         // Find employees with the required skills who are not fully assigned
//         vector<Employee*> availableEmployees;
//         for (auto& employee : employees) {
//             bool hasAllSkills = true;

//             // Check if employee has all required skills
//             for (const auto& skill : requiredSkills) {
//                 if (find(employee.skills.begin(), employee.skills.end(), skill) == employee.skills.end()) {
//                     hasAllSkills = false;
//                     break;
//                 }
//             }

//             // If employee has all skills and is not overloaded with projects, add them to available employees
//             if (hasAllSkills && employee.noOfProjects < maxNoOfProjects) {  // Assuming a threshold of 3 projects
//                 availableEmployees.push_back(&employee);
//             }
//         }

//         // Assign available employees to the project
//         if (!availableEmployees.empty() && availableEmployees.size()>=requiredNumber) {
//             //now I'll sort the employees on the basis of lesser number of assigned projects and take the ones with the least number of assigned projects
//             sort(availableEmployees.begin(),availableEmployees.end(),HRDepartment::sortEmployeesForAssign);
//              // Assign the required number of employees to the project
//             for (int i = 0; i < requiredNumber; i++) {
//                 Employee* emp = availableEmployees[i];
//                 emp->assignedProjects.push_back(project.id);//push this project ID into this employee's projects vector
//                 emp->noOfProjects++;
//                 project.employeesAssigned.push_back(emp->id);
//             }

//             // Mark the project as assigned
//             project.assigned = true;
//             cout << "Project " << project.name << " has been successfully assigned to employees." << endl;
//         } 
        
//         else {
//             int need=requiredNumber-availableEmployees.size();
//             cout << "Need to hire "<<need<<" more employees for completing this project-" << project.name << ".\n";
//             cout<<"Please add "<<need<<" more employees with these skills";
//             for(int i=1; i<=requiredSkills.size(); i++){
//                 cout<<i<<". "<<requiredSkills[i-1]<<endl;
//             }
//         }
//         return;
//     }
//     cout<<"No project with this project ID found. Invalid ID"<<endl;
// }


bool assignProjects(int peopleRequired, vector<string>skills, vector<Employees>emps) {
    //this emps vector has all the employees in the company
    //
    

       
        vector<string> requiredSkills=assignSkills("");
        project.skills=requiredSkills;
        int requiredNumber;
        cout<<"Enter the number of employees that are required for completing this project"<<endl;
        cin>>requiredNumber;
        cin.ignore(); // to clear the newline character from the input buffer

        // Find employees with the required skills who are not fully assigned
        vector<Employee*> availableEmployees;
        for (auto& employee : employees) {
            bool hasAllSkills = true;

            // Check if employee has all required skills
            for (const auto& skill : requiredSkills) {
                if (find(employee.skills.begin(), employee.skills.end(), skill) == employee.skills.end()) {
                    hasAllSkills = false;
                    break;
                }
            }

            // If employee has all skills and is not overloaded with projects, add them to available employees
            if (hasAllSkills && employee.noOfProjects < maxNoOfProjects) {  // Assuming a threshold of 3 projects
                availableEmployees.push_back(&employee);
            }
        }

        // Assign available employees to the project
        if (!availableEmployees.empty() && availableEmployees.size()>=requiredNumber) {
            //now I'll sort the employees on the basis of lesser number of assigned projects and take the ones with the least number of assigned projects
            sort(availableEmployees.begin(),availableEmployees.end(),HRDepartment::sortEmployeesForAssign);
             // Assign the required number of employees to the project
            for (int i = 0; i < requiredNumber; i++) {
                Employee* emp = availableEmployees[i];
                emp->assignedProjects.push_back(project.id);//push this project ID into this employee's projects vector
                emp->noOfProjects++;
                project.employeesAssigned.push_back(emp->id);
            }

            // Mark the project as assigned
            project.assigned = true;
            cout << "Project " << project.name << " has been successfully assigned to employees." << endl;
        } 
        
        else {
            int need=requiredNumber-availableEmployees.size();
            cout << "Need to hire "<<need<<" more employees for completing this project-" << project.name << ".\n";
            cout<<"Please add "<<need<<" more employees with these skills";
            for(int i=1; i<=requiredSkills.size(); i++){
                cout<<i<<". "<<requiredSkills[i-1]<<endl;
            }
        }
        return;
    }
    cout<<"No project with this project ID found. Invalid ID"<<endl;
}
