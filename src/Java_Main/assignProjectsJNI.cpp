#include <jni.h>
#include <bits/stdc++.h>
using namespace std;

// Struct to represent Employee
struct Employee {
    string id;
    int noOfProjects;
    vector<string> skills;
};

// Comparator for sorting employees
bool sortEmployeesForAssign(Employee* &a, Employee* &b) {
    return a->noOfProjects < b->noOfProjects;
}

vector<string> assignProjects(int requiredNumber, vector<string> skills, vector<Employee> employees) {
    cout<<"Inside assignProjects"<<endl;
    int maxNoOfProjects = 5;
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
        sort(availableEmployees.begin(), availableEmployees.end(), sortEmployeesForAssign);
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

extern "C" JNIEXPORT jobject JNICALL Java_Java_1Main_AssignProjectsJNI_assignProjects
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
    vector<Employee> employees;
    jclass employeeClass = env->FindClass("Java_Main/EmployeeSkillData");
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

        employees.push_back(Employee{empId, (int)noOfProjects, empSkills});
    }

    vector<string> result = assignProjects((int)requiredNumber, skills, employees);

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