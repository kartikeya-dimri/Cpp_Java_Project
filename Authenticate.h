<<<<<<< HEAD
// #pragma once
=======
#pragma once//
>>>>>>> ecac801ada864101560b42c4fa683d65151b1887
#include "DataLoader.h"
using namespace std;

class Authenticator
{
private:
    vector<Authenticate> credentials;

public:
    Authenticator();
    bool verifyLogin(string status, string username, string password);
    bool isUniqueId(string id);

    // friend class CEO;
};
