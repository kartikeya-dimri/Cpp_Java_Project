#include "Authenticate.h"

// Class which shows the data of the employee when he logs in

class Employees
{
    // Store the id for displaying of data
    private:
        // This will contain the data
        Employee emp;
        string id;
    public:
        // Load the employees as we need to print the details
        Employees(string id);
        Employee findEmployee(string id,vector<Employee> employees);
        void showData();
};