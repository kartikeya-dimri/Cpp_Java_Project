#include <jni.h>
using namespace std;
#include <cstdlib>
#include <ctime>
#include <bits/stdc++.h>

class ProjectAssigner {
public:
    struct Employee {
        string id;
        int noOfProjects;
        vector<string> skills;
    };

    static vector<string> assignProjects(int requiredNumber, vector<string> skills, vector<Employee> employees) {
        const int maxNoOfProjects = 5;
        vector<string> result;

        vector<Employee*> availableEmployees;
        for (auto& employee : employees) {
            bool hasAllSkills = true;

            for (const auto& skill : skills) {
                if (find(employee.skills.begin(), employee.skills.end(), skill) == employee.skills.end()) {
                    hasAllSkills = false;
                    break;
                }
            }

            if (hasAllSkills && employee.noOfProjects < maxNoOfProjects) {
                availableEmployees.push_back(&employee);
            }
        }

        if (availableEmployees.size() >= requiredNumber) {
            result.push_back("1");
            sort(availableEmployees.begin(), availableEmployees.end(), [](Employee*& a, Employee*& b) {
                return a->noOfProjects < b->noOfProjects;
            });

            for (int i = 0; i < requiredNumber; i++) {
                Employee* emp = availableEmployees[i];
                result.push_back(emp->id);
            }
        } else {
            result.push_back("0");
            int need = requiredNumber - availableEmployees.size();
            string message = "Need to hire " + to_string(need) + " more employees for completing this project.\n";
            message += "Please add " + to_string(need) + " more employees with these skills\n";
            for (int i = 1; i <= skills.size(); i++) {
                message += to_string(i) + ". " + skills[i - 1] + "\n";
            }
            result.push_back(message);
        }
        return result;
    }
};

extern "C" JNIEXPORT jobject JNICALL Java_AssignProjectsJNI_assignProjects
(JNIEnv* env, jclass clazz, jint requiredNumber, jobject skillsList, jobject employeesList) {
    jclass arrayListClass = env->GetObjectClass(skillsList);
    jmethodID sizeMethod = env->GetMethodID(arrayListClass, "size", "()I");
    jmethodID getMethod = env->GetMethodID(arrayListClass, "get", "(I)Ljava/lang/Object;");

    jint skillsSize = env->CallIntMethod(skillsList, sizeMethod);
    vector<string> skills;
    for (int i = 0; i < skillsSize; i++) {
        jstring skill = (jstring)env->CallObjectMethod(skillsList, getMethod, i);
        const char* skillChars = env->GetStringUTFChars(skill, NULL);
        skills.push_back(string(skillChars));
        env->ReleaseStringUTFChars(skill, skillChars);
    }

    jint employeesSize = env->CallIntMethod(employeesList, sizeMethod);
    vector<ProjectAssigner::Employee> employees;
    jclass employeeClass = env->FindClass("EmployeeSkillData");
    jmethodID getIdMethod = env->GetMethodID(employeeClass, "getId", "()Ljava/lang/String;");
    jmethodID getSkillsMethod = env->GetMethodID(employeeClass, "getSkills", "()Ljava/util/ArrayList;");
    jmethodID getNoOfProjectsMethod = env->GetMethodID(employeeClass, "getNoOfProjects", "()I");

    for (int i = 0; i < employeesSize; i++) {
        jobject employeeObj = env->CallObjectMethod(employeesList, getMethod, i);
        jstring id = (jstring)env->CallObjectMethod(employeeObj, getIdMethod);
        const char* idChars = env->GetStringUTFChars(id, NULL);
        string empId(idChars);
        env->ReleaseStringUTFChars(id, idChars);

        jint noOfProjects = env->CallIntMethod(employeeObj, getNoOfProjectsMethod);

        jobject employeeSkillsList = env->CallObjectMethod(employeeObj, getSkillsMethod);
        jint empSkillsSize = env->CallIntMethod(employeeSkillsList, sizeMethod);
        vector<string> empSkills;
        for (int j = 0; j < empSkillsSize; j++) {
            jstring skill = (jstring)env->CallObjectMethod(employeeSkillsList, getMethod, j);
            const char* skillChars = env->GetStringUTFChars(skill, NULL);
            empSkills.push_back(string(skillChars));
            env->ReleaseStringUTFChars(skill, skillChars);
        }

        employees.push_back(ProjectAssigner::Employee{empId, (int)noOfProjects, empSkills});
    }

    vector<string> result = ProjectAssigner::assignProjects((int)requiredNumber, skills, employees);

    jclass arrayListClassResult = env->FindClass("java/util/ArrayList");
    jmethodID arrayListInit = env->GetMethodID(arrayListClassResult, "<init>", "()V");
    jmethodID arrayListAdd = env->GetMethodID(arrayListClassResult, "add", "(Ljava/lang/Object;)Z");

    jobject resultList = env->NewObject(arrayListClassResult, arrayListInit);
    for (const string& res : result) {
        jstring resStr = env->NewStringUTF(res.c_str());
        env->CallBooleanMethod(resultList, arrayListAdd, resStr);
        env->DeleteLocalRef(resStr);
    }

    return resultList;
}




class PasswordGenerator {
public:
    // Generate a random password of the specified length
    static string generateRandomPassword(int length) {
        const string upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        const string lowerCase = "abcdefghijklmnopqrstuvwxyz";
        const string digits = "0123456789";
        const string specialChars = "!@#$%^&*()-_=+[]{}|;:',.<>?/";

        const string allChars = upperCase + lowerCase + digits + specialChars;

        if (length < 1) {
            throw invalid_argument("Password length must be at least 4 to include all character types.");
        }

        srand(time(nullptr)); // Seed for random number generation

        string password;

        // Ensure at least one character of each type is included
        password += upperCase[rand() % upperCase.size()];
        password += lowerCase[rand() % lowerCase.size()];
        password += digits[rand() % digits.size()];
        password += specialChars[rand() % specialChars.size()];

        // Fill the rest of the password length with random characters
        for (int i = 4; i < length; ++i) {
            password += allChars[rand() % allChars.size()];
        }

        // Shuffle the password to make it unpredictable
        random_shuffle(password.begin(), password.end());
        return password;
    }
};

extern "C" JNIEXPORT jstring JNICALL Java_AssignProjectsJNI_generatePassword
(JNIEnv* env, jclass clazz, jint length) {
    try {
        string password = PasswordGenerator::generateRandomPassword((int)length);
        return env->NewStringUTF(password.c_str());
    } catch (const exception& e) {
        // Handle invalid argument exception
        string error = "Error: " + string(e.what());
        return env->NewStringUTF(error.c_str());
    }
}
