#include<bits/stdc++.h>
using namespace std;
#include "Authenticate.h"


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
            int username;
            cin>>username;

            string password;
            cout<<"Enter your password: ";
            cin>>password;


            if(auth->verifyLogin(status,username,password))
            {
                cout<<"You are successfully logged in!\n";
                cout<<"Welcome CEO\n";
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