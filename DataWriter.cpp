#include "DataWriter.h"
using namespace std;

void DataWriter::writeProjects(const std::vector<Project>& projects){
    ofstream outfile1("Projects.txt");

    for(int i=0;i<projects.size();i++)
    {
        Project j=projects[i];
        string write=j.name+":"+j.id+":"+to_string(j.assigned)+":"+to_string(j.completed)+":";
        string emps="";
        for(int i=0; i<j.employeesAssigned.size(); i++){
            emps+=j.employeesAssigned[i];
            if(i<j.employeesAssigned.size()-1){
                emps+=",";
            }
        }
        // emps+=j.employeesAssigned[j.employeesAssigned.size()-1];
        // // emps+=":";
        outfile1<<write<<emps<<endl;
    }
    outfile1.close();
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
    ofstream outfile("Employees.txt");

    if (!outfile) {
        cerr << "Error: Could not open Employees.txt for writing." << endl;
        return;
    }

    for (const auto& employee : employees) {
        // Write the employee details in the same format expected by loadEmployees()
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
        ss << ",,,";

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