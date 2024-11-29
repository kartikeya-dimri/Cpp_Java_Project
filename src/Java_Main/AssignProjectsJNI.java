package Java_Main;
import db.*;
import java.util.ArrayList;

public class AssignProjectsJNI {

    // Declare native methods
    public static native ArrayList<String> assignProjects(int requiredNumber, ArrayList<String> skills, ArrayList<EmployeeSkillData> employees);
    public static native String generatePassword(int length);

    static {
        // Load the native library containing the implementations
        System.loadLibrary("library_jni");
    }

}

