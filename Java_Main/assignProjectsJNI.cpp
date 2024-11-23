#include <jni.h>
#include<bits/stdc++.h>

using namespace std;

// Employee struct to match the Java-side structure
struct Employee {
    string name;
    string id;
    int noOfProjects;
    vector<string> skills;
};

bool sortEmployeesForAssign(Employee* &a, Employee* &b) {
    return a->noOfProjects < b->noOfProjects;
}

// JNI method definition without generated header
extern "C" JNIEXPORT jobjectArray JNICALL Java_com_example_ProjectAssignment_assignProjects(
    JNIEnv* env, jobject obj, jint requiredNumber, jobjectArray skillsArray, jobjectArray employeesArray) {
    
    // Convert Java arrays to C++ vectors
    vector<string> skills;
    vector<Employee> employees;
    int maxNoOfProjects = 5;

    // Convert skills array
    jsize skillsLength = env->GetArrayLength(skillsArray);
    for (int i = 0; i < skillsLength; i++) {
        jstring str = (jstring)env->GetObjectArrayElement(skillsArray, i);
        const char* skillStr = env->GetStringUTFChars(str, nullptr);
        skills.push_back(string(skillStr));
        env->ReleaseStringUTFChars(str, skillStr);
        env->DeleteLocalRef(str);
    }

    // Convert employees array
    jsize empLength = env->GetArrayLength(employeesArray);
    for (int i = 0; i < empLength; i++) {
        jobject empObj = env->GetObjectArrayElement(employeesArray, i);
        
        // Get Employee class and field IDs
        jclass empClass = env->GetObjectClass(empObj);
        jfieldID idField = env->GetFieldID(empClass, "id", "Ljava/lang/String;");
        jfieldID noOfProjectsField = env->GetFieldID(empClass, "noOfProjects", "I");
        jfieldID skillsField = env->GetFieldID(empClass, "skills", "[Ljava/lang/String;");

        // Get employee data
        jstring idStr = (jstring)env->GetObjectField(empObj, idField);
        jint noOfProjects = env->GetIntField(empObj, noOfProjectsField);
        jobjectArray empSkills = (jobjectArray)env->GetObjectField(empObj, skillsField);

        Employee emp;
        const char* idChars = env->GetStringUTFChars(idStr, nullptr);
        emp.id = string(idChars);
        emp.noOfProjects = noOfProjects;

        // Get employee skills
        jsize skillsLen = env->GetArrayLength(empSkills);
        for (int j = 0; j < skillsLen; j++) {
            jstring skillStr = (jstring)env->GetObjectArrayElement(empSkills, j);
            const char* skill = env->GetStringUTFChars(skillStr, nullptr);
            emp.skills.push_back(string(skill));
            env->ReleaseStringUTFChars(skillStr, skill);
            env->DeleteLocalRef(skillStr);
        }

        employees.push_back(emp);
        env->ReleaseStringUTFChars(idStr, idChars);
        env->DeleteLocalRef(idStr);
        env->DeleteLocalRef(empSkills);
        env->DeleteLocalRef(empObj);
    }

    // Find available employees
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

    // Create result array
    vector<string> result;
    if (availableEmployees.size() >= requiredNumber) {
        result.push_back("1");
        sort(availableEmployees.begin(), availableEmployees.end(), sortEmployeesForAssign);
        for (int i = 0; i < requiredNumber; i++) {
            result.push_back(availableEmployees[i]->id);
        }
    } else {
        result.push_back("0");
        int need = requiredNumber - availableEmployees.size();
        string message = "Need to hire " + to_string(need) + " more employees for completing this project.\n";
        message += "Please add " + to_string(need) + " more employees with these skills\n";
        for (size_t i = 1; i <= skills.size(); i++) {
            message += to_string(i) + ". " + skills[i-1] + "\n";
        }
        result.push_back(message);
    }

    // Convert result to Java String array
    jobjectArray resultArray = env->NewObjectArray(result.size(), 
        env->FindClass("java/lang/String"), 
        env->NewStringUTF(""));

    for (size_t i = 0; i < result.size(); i++) {
        env->SetObjectArrayElement(resultArray, i, 
            env->NewStringUTF(result[i].c_str()));
    }

    return resultArray;
}
