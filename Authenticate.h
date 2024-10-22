#pragma once
#include<bits/stdc++.h>
using namespace std;

// The four attributes in credentials.txt
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
