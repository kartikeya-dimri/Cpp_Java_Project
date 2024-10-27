#include "Employees.h"

Employee Employees::findEmployee(string id,vector<Employee> employees)
{
    for(auto i:employees)
    {
        if(i.id==id)
        {
            return i;
        }
    }
}

Employees::Employees(string id)
{
    this->id = id;
    DataLoader *dl = new DataLoader();
    vector<Employee> employees = dl->loadEmployees();
    emp=findEmployee(id,employees);
}


void Employees::showData()
{
    cout << "You are successfully logged in\n";
    cout<<"Name: "<<emp.name<<"\n";
    cout<<"Age: "<<emp.age<<"\n";
    cout<<"ID: "<<emp.id<<"\n";
    cout<<"No of projects: "<<emp.noOfProjects<<"\n";
    cout<<"Assigned projects: ";
    for(int i=0; i<emp.assignedProjects.size()-1; i++)
    {
        cout<<emp.assignedProjects[i]<<",";
    }
    cout<<emp.assignedProjects[emp.assignedProjects.size()-1]<<"\n";
    cout<<"Skills: ";
    for(int i=0; i<emp.skills.size()-1; i++)
    {
        cout<<emp.skills[i]<<",";
    }
    cout<<emp.skills[emp.skills.size()-1]<<"\n";
    cout<<"Hired status: "<<emp.hired_status<<"\n";
}