#include "Authenticate.h"
#include<bits/stdc++.h>
using namespace std;

Authenticator::Authenticator()
{
    // First initialize the vector with the credentials
    ifstream infile("Credentials.txt");
    string line;
    

    if(!infile.is_open())
    {
        cerr<<"Unable to open the file\n";
        return;
    }

    // Read each line in the file
    while(getline(infile,line))
    {
        stringstream iss(line);
        string part;
        vector<string> parts;
        while (getline(iss,part,':'))
        {
            // Get all the five parts
            parts.push_back(part);
        }
        // id:password:status:hired_status
        Authenticate auth={parts[0],parts[1],parts[2],parts[3]};
        // Credentials loaded
        credentials.push_back(auth);
    }
    // Close the file
    infile.close();
    
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