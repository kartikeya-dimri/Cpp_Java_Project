#include<bits/stdc++.h>
using namespace std;
#include "Authenticate.h"
#include "CEO.h"


int main()
{
    // First ask how do you want to login
    // Exit
    while(1)
    {
        cout<<"Welcome to Google!\n";
        cout<<"Login as\n";
        cout<<"1)CEO\n2)HR\n3)Employee\n";

        string status;
        cin>>status;
        // Convert the string to uppercase for standard comparison
        transform(status.begin(),status.end(),status.begin(),::toupper);

        // Object of authenticate class to access the methods
        Authenticator* auth=new Authenticator();

        if(status=="CEO")
        {
            // Authenticate the CEO
            cout<<"Enter your username: ";
            string username;
            cin>>username;

            string password;
            cout<<"Enter your password: ";
            cin>>password;


            if(auth->verifyLogin(status,username,password))
            {
                cout<<"You are successfully logged in!\n";
                cout<<"Welcome CEO\n";
                // Now give access to add HR, add Projects etc.
                cout<<"What do you want to do?\n1)Add HR employees\n2)Remove HR employees\n3)Add Projects\n";
                string task;
                cin>>task;
                CEO* ceo=new CEO();

                transform(task.begin(),task.end(),task.begin(),::toupper);

                while(1)
                {
                    if(task=="ADD HR")
                    {
                        cout<<"Enter new employee ID: ";
                        string id;
                        cin>>id;
                        cout<<"Enter new employee's password: ";
                        string password;
                        cin>>password;
                        ceo->addHR(id,password);
                    }
                    else if(task=="REMOVE HR")
                    {
                        string id;
                        cout<<"Enter ID of employee to be fired: ";
                        cin>>id;
                        ceo->removeHR(id);
                    }
                    else if(task=="ADD PROJECT")
                    {
                        string projectname;
                        string projectid;
                        cout<<"Enter new project's name: ";
                        cin>>projectname;
                        cout<<"Enter new project's id: ";
                        cin>>projectid;

                        ceo->addProject(projectname,projectid);
                    }
                    else if(task=="EXIT")
                    {
                        ceo->loggedOut();
                        cout<<"Logged out successfully\n";
                        break;
                    }
                    else
                    {
                        cout<<"Invalid command\n";
                    }
                }
            }
            else
            {
                cout<<"\n";
                cout<<"Wrong username or wrong password\n";
            }
        }
        else if(status=="HR")
        {
            cout<<"Welcome HR\n";
        }
        else if(status=="EMPLOYEE")
        {
            cout<<"Welcome employee\n";
        }
        else if(status=="EXIT")
        {
            cout<<"Logged out successfully\n";
            break;
        }
        else
        {
            cout<<"Invalid command\n";
        }
        cout<<"\n";
    }



    return 0;
}