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

extern "C" {
    JNIEXPORT jobject JNICALL Java_Java_1Main_AssignProjects_assignProjects(
        JNIEnv* env, jclass clazz, jint requiredNumber, jobject skillsList, jobject employeesList) {
        
        // Convert Java ArrayList<String> (skills) to C++ vector<string>
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

        // Convert Java ArrayList<EmployeeSkillData> to C++ vector<Employee>
        jint employeesSize = env->CallIntMethod(employeesList, sizeMethod);
        vector<Employee> employees;
        jclass employeeClass = env->FindClass("Java_Main/EmployeeSkillData");
        jmethodID getIdMethod = env->GetMethodID(employeeClass, "getId", "()Ljava/lang/String;");
        jmethodID getSkillsMethod = env->GetMethodID(employeeClass, "getSkills", "()Ljava/util/ArrayList;");
        jmethodID getNoOfProjectsMethod = env->GetMethodID(employeeClass, "getNoOfProjects", "()I");

        for (int i = 0; i < employeesSize; i++) {
            jobject employeeObj = env->CallObjectMethod(employeesList, getMethod, i);
            
            // Extract Employee fields
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

        // Call the assignProjects logic
        vector<string> result = assignProjects((int)requiredNumber, skills, employees);

        // Convert the result vector<string> to Java ArrayList<String>
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
}
