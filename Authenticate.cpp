#include "Authenticate.h"//
using namespace std;

Authenticator::Authenticator()
{

    DataLoader* dl=new DataLoader();
    // Credentials
    credentials=dl->loadCredentials();  
}

// Verify the login
bool Authenticator::verifyLogin(string status)
{
    cout << "Enter your user ID: ";
    string username;
    getline(cin, username);

    string password;
    cout << "Enter your password: ";
    getline(cin, password);
    for(auto i:credentials)
    {
        if(i.status==status and i.empId==username and i.password==password && i.hired_status=="HIRED")
        {
            return true;
        }
    }
    return false;
}

// Check if the entered ID is unique
bool Authenticator::isUniqueId(string id)
{
    for(auto i:credentials)
    {
        if(i.empId==id)
        {
            return false;
        }
    }
    return true;
}