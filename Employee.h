#include "Authenticate.h"

// Class which shows the data of the employee when he logs in

class Employee
{
    // Store the id for displaying of data
    private:
        string id;
    public:
        bool verifyEmployee();
        void showData();
};