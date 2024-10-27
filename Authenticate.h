#pragma once
#include<bits/stdc++.h>
using namespace std;

struct Project
{
    string name;
    string id;
    bool assigned=0;
    bool completed=0;
    // Just keep their ids
    vector<string> employeesAssigned;

    Project()=default;

    Project(string pName, string id)
        :name(pName),id(id) {}
};


struct Authenticate
{
    string empId;
    string password;
    string status;
    string hired_status;
};


class Authenticator
{
    private:
        vector<Authenticate> credentials;
    public:
        Authenticator();
        bool verifyLogin(string status,string username,string password);
        bool isUniqueId(string id);

        // friend class CEO;
};
