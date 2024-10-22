#include<bits/stdc++.h>
using namespace std;
#include "Authenticate.h"
#include "CEO.h"

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
                cout << "You are successfully logged in!\n";
                cout << "Welcome CEO\n";
                
                // Create CEO object once
                CEO* ceo = new CEO();

                while(1)
                {
                    cout << "What do you want to do?\n";
                    cout << "1) Add HR\n";
                    cout << "2) Remove HR\n";
                    cout << "3) Add Project\n";
                    cout << "4) Exit\n";
                    
                    string task;
                    getline(cin, task);
                    transform(task.begin(), task.end(), task.begin(), ::toupper);

                    if(task == "ADD HR" || task == "1")
                    {
                        cout << "Enter new employee ID: ";
                        string id;
                        getline(cin, id);
                        
                        cout << "Enter new employee's password: ";
                        string password;
                        getline(cin, password);
                        
                        ceo->addHR(id, password);
                    }
                    else if(task == "REMOVE HR" || task == "2")
                    {
                        cout << "Enter ID of employee to be fired: ";
                        string id;
                        getline(cin, id);
                        ceo->removeHR(id);
                    }
                    else if(task == "ADD PROJECT" || task == "3")
                    {
                        cout << "Enter new project's name: ";
                        string projectname;
                        getline(cin, projectname);
                        
                        cout << "Enter new project's id: ";
                        string projectid;
                        getline(cin, projectid);

                        ceo->addProject(projectname, projectid);
                    }
                    else if(task == "EXIT" || task == "4")
                    {
                        ceo->loggedOut();
                        cout << "Logged out successfully\n";
                        delete ceo; // Free memory
                        break;
                    }
                    else
                    {
                        cout << "Invalid command\n";
                    }
                }
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