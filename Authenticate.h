#pragma once//
#include "DataLoader.h"
using namespace std;




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
