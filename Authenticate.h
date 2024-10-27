#include "DataLoader.h"
using namespace std;

class Authenticator
{
private:
    vector<Authenticate> credentials;

public:
    Authenticator();
    // Ask username and password here to shorten main
    bool verifyLogin(string status);
    bool isUniqueId(string id);
};
