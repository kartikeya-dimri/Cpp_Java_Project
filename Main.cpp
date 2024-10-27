// #include<bits/stdc++.h>-- this is already included in Authenticate.h and other files
// #include "Authenticate.h"//
#include "CEO.h"
#include "HR.h"//
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
            cout << "Enter your username: ";
            string username;
            getline(cin, username);

            string password;
            cout << "Enter your password: ";
            getline(cin, password);

            if(auth->verifyLogin(status, username, password))
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
            delete auth; // Free memory
        }
        else if(status == "HR")
        {
            cout << "Welcome HR\n";
            delete auth;
        }
        else if(status == "EMPLOYEE")
        {
            cout << "Welcome employee\n";
            delete auth;
        }
        else if(status == "EXIT")
        {
            cout << "Logged out successfully\n";
            delete auth;
            break;
        }
        else
        {
            cout << "Invalid command\n";
            delete auth;
        }
        cout << "\n";
    }

    return 0;
}