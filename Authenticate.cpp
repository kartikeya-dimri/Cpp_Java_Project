#include "Authenticate.h"
#include<bits/stdc++.h>
using namespace std;
#include "DataLoader.h"

Authenticator::Authenticator()
{
    dataLoader* dl=new dataLoader();
    // Credentials
    credentials=dl->loadCredentials();  
}

// Verify the login
bool Authenticator::verifyLogin(string status,string username,string password)
{
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