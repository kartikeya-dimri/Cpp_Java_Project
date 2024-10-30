#include "DataWriter.h"
using namespace std;

void DataWriter::writeProjects(const std::vector<Project>& projects) {
    // Format: ProjectName:ProjectID:assigned(0/1):completed(0/1):emp1,emp2,emp3:skill1,skill2,skill3

    ofstream outfile("Projects.txt");

    for (const auto& project : projects) {
        // Basic project details
        string write = project.name + ":" + project.id + ":" + 
                       to_string(project.assigned) + ":" + 
                       to_string(project.completed) + ":";

        // Concatenate employee IDs into a comma-separated string
        string emps;
        for (int i = 0; i < project.employeesAssigned.size(); i++) {
            emps += project.employeesAssigned[i];
            if (i < project.employeesAssigned.size() - 1) {
                emps += ",";
            }
        }

        // Concatenate skills into a comma-separated string
        string skills;
        for (int i = 0; i < project.skills.size(); i++) {
            skills += project.skills[i];
            if (i < project.skills.size() - 1) {
                skills += ",";
            }
        }

        // Write the line to the file
        outfile << write << emps << ":" << skills << endl;
    }

    outfile.close();
}


void DataWriter::writeCredentials(const std::vector<Authenticate>& credentials){
    ofstream outfile("Credentials.txt");

    for(int i=0;i<credentials.size();i++)
    {
        Authenticate j=credentials[i];
        string write=j.empId+":"+j.password+":"+j.status+":"+j.hired_status+":";
        outfile<<write<<"\n";
    }
    outfile.close();
}


void DataWriter::writeEmployees(const vector<Employee>& employees) {
    //name:age:id:numOfProjects:projectid1,2,3..:skills1,2,3
    
    ofstream outfile("Employees.txt");

    if (!outfile) {
        cerr << "Error: Could not open Employees.txt for writing." << endl;
        return;
    }

    for (const auto& employee : employees) {
        // Write the employee details in the same format expected by loadEmployees()
        if(employee.hired_status=="FIRED"){
            continue;
        }
        stringstream ss;
        ss << employee.name << ":" 
           << employee.age << ":" 
           << employee.id << ":"
           << employee.noOfProjects << ":";

        // Write assigned projects, separated by commas
        for (size_t i = 0; i < employee.assignedProjects.size(); ++i) {
            ss << employee.assignedProjects[i];
            if (i < employee.assignedProjects.size() - 1) {
                ss << ",";
            }
        }

        // Add delimiter to separate projects and skills
        ss << ":";

        // Write skills, separated by commas
        for (size_t i = 0; i < employee.skills.size(); ++i) {
            ss << employee.skills[i];
            if (i < employee.skills.size() - 1) {
                ss << ",";
            }
        }

        // Write the constructed line to the file
        outfile << ss.str() << endl;
    }

    outfile.close();
}