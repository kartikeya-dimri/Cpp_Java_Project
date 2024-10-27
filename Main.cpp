// #include<bits/stdc++.h>-- this is already included in Authenticate.h and other files
// #include "Authenticate.h"//
#include "CEO.h"
#include "HR.h"
#include "Employees.h"
using namespace std;

int main()
{
    while(1)
    {
        cout << "Welcome to Google!\n";
        cout << "Login as\n";
        cout << "1)CEO\n2)HR\n3)Employee\n";

        // WARNING!!!!!
        // DO NOT USE CIN
        // USE ONLY GETLINE FOR INPUT BUFFER ISSUES
        string status;
        getline(cin,status);
        
        // Convert the string to uppercase for standard comparison
        transform(status.begin(), status.end(), status.begin(), ::toupper);

        // Object of authenticate class to access the methods
        Authenticator* auth = new Authenticator();

        if(status == "CEO")
        {

            if(auth->verifyLogin(status))
            {
                    
                // Create CEO object once
                CEO* ceo = new CEO();
                ceo->ceoRunner();
                ceo->loggedOut();
                cout << "Logged out successfully\n";
                delete ceo; // Free memory
            }
            else
            {
                cout << "\nWrong username or wrong password\n";
            }
        }
        else if(status == "HR")
        {
            if(auth->verifyLogin(status))
            {
                // Create HR object once
                HRDepartment* hr = new HRDepartment();
                hr->hrRunner();
                hr->loggedOut();
                cout << "Logged out successfully\n";
                delete hr; // Free memory
            }
            else
            {
                cout << "\nWrong username or wrong password\n";
            }
        }
        else if(status == "EMPLOYEE")
        {
            if(auth->verifyLogin(status))
            {
                cout << "Enter your ID: ";
                string id;
                getline(cin, id);
                Employees* emp = new Employees(id);
                emp->showData();
                delete emp; // Free memory
            }
            else
            {
                cout << "\nWrong username or wrong password\n";
            }
        }
        else if(status == "EXIT")
        {
            cout << "Logged out successfully\n";
            break;
        }
        else
        {
            cout << "Invalid command\n";
        }
        delete auth; // Free memory
        cout << "\n";
    }

    return 0;
}