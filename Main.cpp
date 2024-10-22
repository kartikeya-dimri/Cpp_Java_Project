#include<bits/stdc++.h>
using namespace std;




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

        if(status=="CEO")
        {
            // Authenticate the CEO
            cout<<"Welcome CEO\n";
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