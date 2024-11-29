package Gui;
import Java_Main.*;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

import com.toedter.calendar.JDateChooser;

// import Backend files to call backend methods
// import Java_Main.*;

public class HR extends JFrame{

    // current login user
    private String currentUser;

    // Data for HR
    ArrayList<String> dashboardInfo;
    JTable projects;

    // now as we access different right panel in different function we will declare them as instance variables
    JPanel defaultPanel;
    JPanel addEmpPanel;
    JPanel removeEmpPanel;
    JPanel projectPanel;
    JPanel updatePanel;
    // constructor
    HR(String a){
        this.currentUser = a;
        dashboardInfo = DisplayInfo.hrDashboardInfo(this.currentUser);
        // now in lines of ceo employee we will hava a left panel in which there will be buttons like add employee, remove employee,
        // projects and then logout
        JLabel title = new JLabel("Online Portal");
        // exit on close
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("sanserif", Font.BOLD, 20));
        title.setBounds(20, 20, 200, 50);
        ImageIcon logo = new ImageIcon("icons-Employee/logo.png");
        Image scaledLogo = logo.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        title.setIcon(new ImageIcon(scaledLogo));
        
        // now buttons

        MenuButton AddempButton = new MenuButton("Hire emp", "profile.png");
        MenuButton RemoveempButton = new MenuButton("Search Emp", "projects.png");
        MenuButton ProjectButton = new MenuButton("Projects", "1.png");
        MenuButton changeStatusButton = new MenuButton("Status", "2.png");
        MenuButton logoutButton = new MenuButton("Logout", "logout.png");

        AddempButton.setBounds(0, 100, 200, 50);
        RemoveempButton.setBounds(0, 200, 200, 50);
        ProjectButton.setBounds(0,300,200,50);
        changeStatusButton.setBounds(0, 400, 200, 50);
        logoutButton.setBounds(0, 500, 200, 50);

        // left panel

        GradientPanel leftPanel = new GradientPanel();
        leftPanel.setBounds(0, 0, 215, 600);

        // add title label to panel
        leftPanel.add(title); 
        // add menuButtons
        leftPanel.add(AddempButton);
        leftPanel.add(RemoveempButton);
        leftPanel.add(ProjectButton);
        leftPanel.add(changeStatusButton);
        leftPanel.add(logoutButton);

        //----------------------------------------------------------------------------
        // create base frame on which all things will be added
        // add all components on the frame

        // now we will create 3 panels and will show one of them at a time based on the button clicked

        // first we will create the default panel which will be shown when the frame is loaded 
        defaultPanel = new JPanel();
        defaultPanel.setVisible(true);
        defaultPanel.setLayout(null);
        defaultPanel.setBounds(220, 0, 785, 600);
        // now we will call the default function
        default_panel_setting(defaultPanel);
        defaultPanel.setBackground(new Color(0x000046));
        
        // CREATE RIGHT PANEL - ADD EMPLOYEE VIEW
        addEmpPanel = new JPanel();
        addEmpPanel.setVisible(false);
        addEmpPanel.setLayout(null);
        addEmpPanel.setBounds(220, 0, 785, 600);
        // now we will call the function which will add the fields to add employee
        add_emp_fields(addEmpPanel);
        addEmpPanel.setBackground(new Color(0x000046));

        // now for remove employee
        removeEmpPanel = new JPanel();
        removeEmpPanel.setVisible(false);
        removeEmpPanel.setLayout(null);
        removeEmpPanel.setBounds(220, 0, 785, 600);
        // now we will call the function which will add the fields to add employee
        remove_emp_setting(removeEmpPanel);
        // now backgroung color
        removeEmpPanel.setBackground(new Color(0x000046));


        // now for project panel
        projectPanel = new JPanel();
        projectPanel.setVisible(false);
        projectPanel.setLayout(null);
        projectPanel.setBounds(220, 0, 785, 600);
        // now we will call the function which will add the fields to add employee
        project_panel_setting(projectPanel);
        // now backgroung color
        projectPanel.setBackground(new Color(0x000046));

        // now for status panel
        updatePanel = new JPanel();
        updatePanel.setVisible(false);
        updatePanel.setLayout(null);
        updatePanel.setBounds(220, 0, 785, 600);
        // now we will call the function which will add the fields to add employee
        changeStatusPanel(updatePanel);
        // now backgroung color
        updatePanel.setBackground(new Color(0x000046));


        // now action listeners for the buttons
        AddempButton.addActionListener(_ -> {
            defaultPanel.setVisible(false);
            addEmpPanel.setVisible(true);
            removeEmpPanel.setVisible(false);
            updatePanel.setVisible(false);
            projectPanel.setVisible(false);
        });
        // now for remove employee
        RemoveempButton.addActionListener(_ -> {
            defaultPanel.setVisible(false);
            addEmpPanel.setVisible(false);
            updatePanel.setVisible(false);
            removeEmpPanel.setVisible(true);
            projectPanel.setVisible(false);
        });
        // now for projects
        ProjectButton.addActionListener(_ -> {
            defaultPanel.setVisible(false);
            addEmpPanel.setVisible(false);
            updatePanel.setVisible(false);
            removeEmpPanel.setVisible(false);
            projectPanel.setVisible(true);
        });
        // now for update status
        changeStatusButton.addActionListener(_ -> {
            defaultPanel.setVisible(false);
            addEmpPanel.setVisible(false);
            updatePanel.setVisible(true);
            removeEmpPanel.setVisible(false);
            projectPanel.setVisible(false);
        });

        // now for logout
        logoutButton.addActionListener(_ -> {
             int result = JOptionPane.showConfirmDialog(
                null,
                "Are you sure you want to continue?",
                "Warning",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.WARNING_MESSAGE
            );
    
            // Check the user's response
            if (result == JOptionPane.OK_OPTION) {
                // Execute code if the user clicks OK
                // we also will change the right panel
                this.dispose();
                new LoginPage();
                
                // Place the code you want to execute here
            } 
        });



        this.setSize(1000, 600);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(null);


        this.add(leftPanel);
        this.add(defaultPanel);
        this.add(addEmpPanel);
        this.add(removeEmpPanel);
        this.add(projectPanel);
        this.add(updatePanel);
        this.setVisible(true);

    }

    private void project_panel_setting(JPanel rightPanel){    
        // label 
        JLabel viewProject = new JLabel("Filter by project status : ");
        viewProject.setForeground(Color.white);
        viewProject.setFont(new Font("sanserif", Font.BOLD, 20));
        viewProject.setBounds(20, 20, 250, 30);

        // drop down
        Choice status = new Choice();
        status.add("All");
        status.add("Unassigned");
        status.add("Ongoing");
        status.add("Completed");
        status.setBounds(300, 25, 200, 30);

        // back button
        JButton back = new JButton("Back");
        back.setBounds(150, 80, 100, 30);
        back.setBackground(Color.black);
        back.setForeground(Color.white);

        // search button
        JButton search = new JButton("Search");
        search.setBounds(20, 80, 100, 30);
        search.setBackground(Color.black);
        search.setForeground(Color.white);
        
        
        // create table where status = statusType
        // search.addActionListener(_ -> {
        //     // Get the selected status type from the dropdown
        //     String statusType = status.getSelectedItem();
            
        //     // Fetch project details based on the selected type
        //     ArrayList<ArrayList<String>> projectDetails = ProjectManager.getProjects(statusType);
            
        //     // Define column names
        //     String[] columnNames = {"Status", "Project ID", "Project Name", "Number of Employees"};
            
        //     // Convert the ArrayList data into a 2D array for the JTable
        //     String[][] tableData = new String[projectDetails.size()][columnNames.length];
        //     for (int i = 0; i < projectDetails.size(); i++) {
        //         ArrayList<String> project = projectDetails.get(i);
        //         for (int j = 0; j < project.size(); j++) {
        //             tableData[i][j] = project.get(j);
        //         }
        //     }
            
        //     // Create the table with the data and column names
        //     projects = new JTable(tableData, columnNames);
        //     projects.setFillsViewportHeight(true);
        //     projects.setFont(new Font("Serif", Font.PLAIN, 14));
        //     projects.setRowHeight(25);
            
        //     // Add the table to a scroll pane
        //     JScrollPane scrollPane = new JScrollPane(projects);
        //     scrollPane.setBounds(20, 130, 600, 200);  // Adjust as needed for your layout
        //     scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            
        //     // Clear existing components (if re-searching)
        //     rightPanel.removeAll();
        //     rightPanel.revalidate();
        //     rightPanel.repaint();
            
        //     // Add components back to the panel
        //     rightPanel.add(viewProject);
        //     rightPanel.add(status);
        //     rightPanel.add(search);
        //     rightPanel.add(back);
        //     rightPanel.add(scrollPane);
            
        // });

        search.addActionListener(_ -> {
    // Get the selected status type from the dropdown
            String statusType = status.getSelectedItem();
            
            // Fetch project details based on the selected type
            ArrayList<ProjectData> projectsList = ProjectManager.getProjects(statusType);
            
            // Define column names
            String[] columnNames = {"Status", "Project ID", "Project Name", "Number of Employees", "Employee IDs"};
            
            // Convert the project details into a 2D array for the JTable
            String[][] tableData = new String[projectsList.size()][columnNames.length];
            for (int i = 0; i < projectsList.size(); i++) {
                ProjectData project = projectsList.get(i);
                tableData[i][0] = project.getStatus();
                tableData[i][1] = project.getProjId();
                tableData[i][2] = project.getName();
                tableData[i][3] = String.valueOf(project.getNumOfWorkingEmps());
                
                // Create a comma-separated string of employee IDs for the last column
                ArrayList<EmployeeData> employees = project.getPeople();
                StringBuilder employeeIds = new StringBuilder();
                for (EmployeeData emp : employees) {
                    if (employeeIds.length() > 0) {
                        employeeIds.append(", ");
                    }
                    employeeIds.append(emp.getId());
                }
                tableData[i][4] = employeeIds.toString();
            }
    
            // Create the table with the data and column names
            JTable projectsTable = new JTable(tableData, columnNames);
            projectsTable.setFillsViewportHeight(true);
            projectsTable.setFont(new Font("Serif", Font.PLAIN, 14));
            projectsTable.setRowHeight(25);

            projectsTable.getColumnModel().getColumn(0).setPreferredWidth(100); // Status
            projectsTable.getColumnModel().getColumn(1).setPreferredWidth(100); // Project ID
            projectsTable.getColumnModel().getColumn(2).setPreferredWidth(200); // Project Name
            projectsTable.getColumnModel().getColumn(3).setPreferredWidth(150); // Number of Employees
            projectsTable.getColumnModel().getColumn(4).setPreferredWidth(250); // Employee IDs
            
            // Add the table to a scroll pane
            JScrollPane scrollPane = new JScrollPane(projectsTable);
            scrollPane.setBounds(20, 130, 600, 200);  // Adjust as needed for your layout
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            
            // Clear existing components (if re-searching)
            rightPanel.removeAll();
            rightPanel.revalidate();
            rightPanel.repaint();
            
            // Add components back to the panel
            rightPanel.add(viewProject);
            rightPanel.add(status);
            rightPanel.add(search);
            rightPanel.add(back);
            rightPanel.add(scrollPane);
        });

        

        back.addActionListener(_ -> {
            // status.select("All");
            // rightPanel.remove(projects);
            // rightPanel.revalidate();
            // rightPanel.repaint();
            rightPanel.setVisible(false);
            defaultPanel.setVisible(true);
        });


            // add to panel
            rightPanel.add(viewProject);
            rightPanel.add(status);
            rightPanel.add(search);
            rightPanel.add(back);
    
    }

    // private void remove_emp_setting(JPanel rightPanel){
    //     JLabel label = new JLabel("Employee ID");
    //     label.setBounds(50, 50, 100, 30);
    //     label.setFont(new Font("Tahoma", Font.BOLD, 15));
    //     label.setForeground(new Color(0xCCCCCC));

    //     // Drop-down for empID
    //     Choice empID = new Choice();
    //     empID.setBounds(200, 50, 150, 30);

    //     // Labels for employee details
    //     JLabel labelName = new JLabel("Name");
    //     labelName.setBounds(50, 100, 100, 30);
    //     labelName.setFont(new Font("Tahoma", Font.BOLD, 15));
    //     labelName.setForeground(new Color(0xCCCCCC));

    //     JLabel textName = new JLabel();
    //     textName.setBounds(200, 100, 200, 30);

    //     JLabel labelPhone = new JLabel("Phone");
    //     labelPhone.setBounds(50, 150, 100, 30);
    //     labelPhone.setFont(new Font("Tahoma", Font.BOLD, 15));
    //     labelPhone.setForeground(new Color(0xCCCCCC));

    //     JLabel textPhone = new JLabel();
    //     textPhone.setBounds(200, 150, 200, 30);

    //     JLabel labelEmail = new JLabel("Email");
    //     labelEmail.setBounds(50, 200, 100, 30);
    //     labelEmail.setFont(new Font("Tahoma", Font.BOLD, 15));
    //     labelEmail.setForeground(new Color(0xCCCCCC));

    //     JLabel textEmail = new JLabel();
    //     textEmail.setBounds(200, 200, 200, 30);

    //     // Buttons
    //     JButton delete = new JButton("Delete");
    //     delete.setBounds(80, 300, 100, 30);
    //     delete.setBackground(Color.black);
    //     delete.setForeground(Color.white);


    //     JButton back = new JButton("Back");
    //     back.setBounds(220, 300, 100, 30);
    //     back.setBackground(Color.black);
    //     back.setForeground(Color.white);

    //     rightPanel.add(label);
    //     rightPanel.add(empID);
    //     rightPanel.add(labelName);
    //     rightPanel.add(textName);
    //     rightPanel.add(labelPhone);
    //     rightPanel.add(textPhone);
    //     rightPanel.add(labelEmail);
    //     rightPanel.add(textEmail);
    //     rightPanel.add(delete);
    //     rightPanel.add(back);

    //     // back button
    //     back.addActionListener(_ -> {
    //         rightPanel.setVisible(false);
    //         defaultPanel.setVisible(true);
    //     });


    // }

    private void remove_emp_setting(JPanel rightPanel){
        JLabel label = new JLabel("EMP ID");
        label.setBounds(50, 50, 100, 30);
        label.setFont(new Font("Tahoma", Font.BOLD, 15));
        label.setForeground(new Color(0xCCCCCC));

        // this will be a text field now where they will type the id of the hr
        JTextField empID = new JTextField();
        empID.setBounds(200, 50, 150, 30);

        // Labels for employee details
        JLabel labelName = new JLabel("Name");
        labelName.setBounds(50, 100, 100, 30);
        labelName.setFont(new Font("Tahoma", Font.BOLD, 15));
        labelName.setForeground(new Color(0xCCCCCC));

        JLabel textName = new JLabel();
        textName.setBounds(200, 100, 300, 30);

        JLabel labelPhone = new JLabel("Phone");
        labelPhone.setBounds(50, 150, 100, 30);
        labelPhone.setFont(new Font("Tahoma", Font.BOLD, 15));
        labelPhone.setForeground(new Color(0xCCCCCC));

        JLabel textPhone = new JLabel();
        textPhone.setBounds(200, 150, 300, 30);

        JLabel labelEmail = new JLabel("Email");
        labelEmail.setBounds(50, 200, 100, 30);
        labelEmail.setFont(new Font("Tahoma", Font.BOLD, 15));
        labelEmail.setForeground(new Color(0xCCCCCC));

        JLabel textEmail = new JLabel();
        textEmail.setBounds(200, 200, 350, 30);

        // Buttons
        JButton delete = new JButton("Delete");
        delete.setBounds(80, 300, 100, 30);
        delete.setBackground(Color.black);
        delete.setForeground(Color.white);
        delete.setVisible(false);


        JButton back = new JButton("Back");
        back.setBounds(220, 300, 100, 30);
        back.setBackground(Color.black);
        back.setForeground(Color.white);

        // we have to make one more button for search and will put it next to  the empID text field
        JButton search = new JButton("Search");
        search.setBounds(400, 50, 100, 30);
        search.setBackground(Color.black);
        search.setForeground(Color.white);


        // now we will add all the components to the right panel
        rightPanel.add(label);
        rightPanel.add(empID);
        rightPanel.add(labelName);
        rightPanel.add(textName);
        rightPanel.add(labelPhone);
        rightPanel.add(textPhone);
        rightPanel.add(labelEmail);
        rightPanel.add(textEmail);
        rightPanel.add(delete);
        rightPanel.add(back);
        rightPanel.add(search);
        // rightPanel.add(i1Label);
        // rightPanel.add(i2Label);
        // back button
        // back button
        back.addActionListener(_ -> {
            rightPanel.setVisible(false);
            defaultPanel.setVisible(true);
        });

        // search button will call the dashboard method of the backend and will get the details of the hr
        search.addActionListener(_ -> {
            // we will call the dashboard method of the backend and pass the empID
            // we will get the result in an arraylist of string
            ArrayList<String> result = WorkforceManager.searchForDelete("HR", empID.getText());
            // now we will check if the result is success or failure
            if(result.get(0).equals("1")){
                // success
                // we will set the text of the labels to the values that we got
                textName.setText(result.get(1));
                textPhone.setText(result.get(2));
                textEmail.setText(result.get(3));
                // also we need to make them a bit large and change the color to make it visible on the background
                textName.setFont(new Font("Tahoma", Font.BOLD, 15));
                textName.setForeground(new Color(0xCCCCCC));
                textPhone.setFont(new Font("Tahoma", Font.BOLD, 15));
                textPhone.setForeground(new Color(0xCCCCCC));
                textEmail.setFont(new Font("Tahoma", Font.BOLD, 15));
                textEmail.setForeground(new Color(0xCCCCCC));

                delete.setVisible(true);
            }else{
                // failure
                JOptionPane.showMessageDialog(null, "Employee not found. Error: " + result.get(1));
                textName.setText("");
                textEmail.setText("");
                textPhone.setText("");
                
            }

        });
        // now for delete button but before calling the method we will so a confirmation dialog box and only if the user clicks ok then we will call the delete method
        delete.addActionListener(_ -> {
            int result = JOptionPane.showConfirmDialog(
                null,
                "Are you sure you want to delete this Employee?",
                "Warning",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.WARNING_MESSAGE
            );
    
            // Check the user's response
            if (result == JOptionPane.OK_OPTION) {
                // Execute code if the user clicks OK
                // we will call the delete method of the backend and pass the empID
                WorkforceManager.delete("EMP", empID.getText());
                JOptionPane.showMessageDialog(null, "Employee deleted successfully");
                // we will also remove the text from the labels
                textName.setText("");
                textPhone.setText("");
                textEmail.setText("");
                empID.setText("");
                delete.setVisible(false);
            } 
        });


    }
    
    private void default_panel_setting(JPanel rightPanel){
        // here i want to add a msg welcoming the ceo and then three cards for total number of projects 
        // total number of employees and total number of hr
        // i will add a label for the welcome msg
        JLabel welcome = new JLabel("Welcome!");
        welcome.setFont(new Font("Tahoma", Font.BOLD, 20));
        welcome.setForeground(Color.white);
        // now i will add the three cards
        // first card for total number of projects
        // that will be of the class that i created which is Cards.java
        JLabel empName = new JLabel("Name: " + dashboardInfo.get(0));
        empName.setForeground(Color.white);
        empName.setFont(new Font("sanserif", Font.BOLD, 16));
        empName.setBounds(20, 30, 200, 100);

        JLabel totalEmployees = new JLabel("Total Employees: " + dashboardInfo.get(1));
        totalEmployees.setForeground(Color.white);
        totalEmployees.setFont(new Font("sanserif", Font.BOLD, 16));
        totalEmployees.setBounds(20, 30, 200, 100);

        JLabel ongoingProjects = new JLabel("Ongoing Projects: " + dashboardInfo.get(2));
        ongoingProjects.setForeground(Color.white);
        ongoingProjects.setFont(new Font("sanserif", Font.BOLD, 16));
        ongoingProjects.setBounds(20, 30, 200, 100);

        // create cards
        Cards card1 = new Cards(new Color(0x6A5ACD), new Color(0x1E90FF)); // Blue-purple gradient
        card1.add(empName);
        
        Cards card2 = new Cards(new Color(0xBA68C8), new Color(0x9575CD)); // Purple gradient
        card2.add(totalEmployees);

        Cards card3 = new Cards(new Color(0xFBC02D), new Color(0xFFA000));
        //Cards card3 = new Cards(new Color(0xFFEB3B), new Color(0xFFC107)); // Yellow gradient
        card3.add(ongoingProjects);
        // now we will add the components to the right panel
        // first we have to set bounds for the welcome label and for the cards
        
        // this card setting is putting them vertically whereas i want them horizontally so i will do it again
        welcome.setBounds(20, 20, 200, 50);
        card1.setBounds(20, 100, 200, 150);
        card2.setBounds(250, 100, 200, 150);
        card3.setBounds(480, 100, 200, 150);

        // now we will add the components to the right panel
        rightPanel.add(welcome);
        rightPanel.add(card1);
        rightPanel.add(card2);
        rightPanel.add(card3);

    }
    private void add_emp_fields(JPanel rightPanel){
        // create label for heading
        JLabel heading = new JLabel("Add Employee Details");
        heading.setFont(new Font("serif",Font.BOLD,25));
        heading.setBounds(275,25,300,50);
        heading.setForeground(Color.white);

        // create labels for input text fields
        // 1.name
        JLabel name = new JLabel("Name :");
        name.setBounds(10,80,150,25);
        name.setFont(new Font("serif",Font.BOLD,20));
        name.setForeground(new Color(0xCCCCCC));
        JTextField name_text = new JTextField();
        name_text.setBounds(160,80,100,20);
        name_text.setBackground(Color.white);
        // 2.fathers name
        JLabel father = new JLabel("Fathers name :");
        father.setBounds(10,120,150,25);
        father.setFont(new Font("serif",Font.BOLD,20));
        father.setForeground(new Color(0xCCCCCC));
        JTextField father_text = new JTextField();
        father_text.setBounds(160,120,100,20);
        father_text.setBackground(Color.white);
        // 3.DOB
        JLabel dob = new JLabel("DOB :");
        dob.setBounds(10,160,150,25);
        dob.setFont(new Font("serif",Font.BOLD,20));
        dob.setForeground(new Color(0xCCCCCC));
        JDateChooser calChooser = new JDateChooser();
        calChooser.setBounds(160, 160, 100, 20);
        calChooser.setBackground(Color.white);

        // 4.salary
        JLabel salary = new JLabel("Salary :");
        salary.setBounds(10,200,150,25);
        salary.setFont(new Font("serif",Font.BOLD,20));
        salary.setForeground(new Color(0xCCCCCC));
        JTextField salary_text = new JTextField();
        salary_text.setBounds(160,200,100,20);
        salary_text.setBackground(Color.white);
        // 5.address
        JLabel add = new JLabel("Address :");
        add.setBounds(10,240,150,25);
        add.setFont(new Font("serif",Font.BOLD,20));
        add.setForeground(new Color(0xCCCCCC));
        JTextField add_text = new JTextField();
        add_text.setBounds(160,240,100,20);
        add_text.setBackground(Color.white);

        // 6.email
        JLabel email = new JLabel("Email :");
        email.setBounds(350,80,200,25);
        email.setFont(new Font("serif",Font.BOLD,20));
        email.setForeground(new Color(0xCCCCCC));
        JTextField email_text = new JTextField();
        email_text.setBounds(600,80,100,20);
        email_text.setBackground(Color.white);
        // 7.phone no.
        JLabel phone = new JLabel("Phone no. :");
        phone.setBounds(350,120,200,25);
        phone.setFont(new Font("serif",Font.BOLD,20));
        phone.setForeground(new Color(0xCCCCCC));
        JTextField phone_text = new JTextField();
        phone_text.setBounds(600,120,100,20);
        phone_text.setBackground(Color.white);
        // 8.comboBox- Highest Education
        JLabel education = new JLabel("Highest Qualification :");
        education.setBounds(350,160,200,25);
        education.setFont(new Font("serif",Font.BOLD,20));
        education.setForeground(new Color(0xCCCCCC));
        String[] qualification = {"Btech","Mtech","BCom","MBA","BA","MA","BSc","Msc","Phd","High School", "Other"};
        JComboBox<String> educationBox = new JComboBox<String>(qualification);
        educationBox.setBackground(Color.white);
        educationBox.setBounds(600, 160, 100, 20);
        // 9. Drop down for skills
        JLabel skills = new JLabel("Skills :");
        skills.setBounds(350,200,200,25);
        skills.setFont(new Font("serif",Font.BOLD,20));
        skills.setForeground(new Color(0xCCCCCC));

        String[] skillOptions = {"C++", "Java", "Python", "UI/UX", "Management"};
        SkillsDropdown skillsDropdown = new SkillsDropdown(skillOptions);
        skillsDropdown.setBounds(600, 200, 100, 20);
        

        // create buttons
        JButton Add = new JButton("Add");
        Add.setBounds(150, 350, 150, 25);;
        Add.setForeground(Color.white);
        Add.setBackground(Color.blue);
        Add.setFocusPainted(false);
        
        JButton Back = new JButton("Back");
        Back.setBounds(500, 350, 150, 25);;
        Back.setForeground(Color.white);
        Back.setBackground(Color.blue);
        Back.setFocusPainted(false);
        
        // add all components to the right panel
        rightPanel.add(heading);
        rightPanel.add(name);
        rightPanel.add(name_text);
        rightPanel.add(father);
        rightPanel.add(father_text);
        rightPanel.add(dob);
        rightPanel.add(calChooser);
        rightPanel.add(salary);
        rightPanel.add(salary_text);
        rightPanel.add(add);
        rightPanel.add(add_text);
        rightPanel.add(email);
        rightPanel.add(email_text);
        rightPanel.add(phone);
        rightPanel.add(phone_text);
        rightPanel.add(education);
        rightPanel.add(educationBox);
        rightPanel.add(skills);
        rightPanel.add(skills);
        rightPanel.add(skillsDropdown);
        rightPanel.add(Add);
        rightPanel.add(Back);
        // add action listeners to the buttons
        // Back.addActionListener(_ -> {
        //     // System.out.println(skillsDropdown.getSelectedSkills());
        //     rightPanel.removeAll();
        //     // we should add the default setting of the right panel
        //     default_panel_setting(rightPanel);
        //     rightPanel.revalidate();
        //     rightPanel.repaint();
        // });

        // back button
        Back.addActionListener(_ -> {
            rightPanel.setVisible(false);
            defaultPanel.setVisible(true);
        });

        // now for the add button we will call the add method of the backend and that will return an arraylist of string with success or failure and error message 
        Add.addActionListener(_ -> {
            // we will call the add method of the backend and pass the values of the text fields
            // we will get the result in an arraylist of string
            // but dropdown are list and we have to change that to arraylist
            @SuppressWarnings({ "unchecked", "rawtypes" })
            ArrayList<String> skillsList = new ArrayList(skillsDropdown.getSelectedSkills());
            String dob1 = ((JTextField)calChooser.getDateEditor().getUiComponent()).getText();

            ArrayList<String> result = WorkforceManager.add("EMP", name_text.getText(), father_text.getText(), dob1, salary_text.getText(), add_text.getText(), email_text.getText(), phone_text.getText(), educationBox.getSelectedItem().toString(), skillsList);
            // now we will check if the result is success or failure
            if(result.get(0).equals("1")){
                // success
                JOptionPane.showMessageDialog(null, "Employee added successfully");
                JOptionPane.showMessageDialog(null, result.get(1));


                // now clear all the fields
                name_text.setText("");
                father_text.setText("");
                calChooser.setDate(null);;
                salary_text.setText("");
                add_text.setText("");
                email_text.setText("");
                phone_text.setText("");
                // educationBox.setSelectedIndex(-1);
                skillsDropdown.clearSelection();

            }else{
                // failure
                JOptionPane.showMessageDialog(null, "Employee not added. Error: " + result.get(1));
            }
        });
    }

    private void changeStatusPanel(JPanel rightPanel) {

        JLabel projId = new JLabel("Project ID");
        projId.setBounds(50, 50, 100, 30);
        projId.setFont(new Font("Tahoma", Font.BOLD, 15));
        projId.setForeground(new Color(0xCCCCCC));

        // Text Box for Proj ID
        JTextField projIdText = new JTextField();
        projIdText.setBounds(160,50,100,30);
        projIdText.setBackground(Color.white);
        projIdText.setBounds(200, 50, 150, 30);

        // Search Button
        JButton search = new JButton("Search");
        search.setBounds(350, 50, 100, 30);
        search.setBackground(Color.black);
        search.setForeground(Color.white);
        search.setFocusPainted(false);

        // Labels for project details
        JLabel labelName = new JLabel("Name");
        labelName.setBounds(50, 100, 100, 30);
        labelName.setFont(new Font("Tahoma", Font.BOLD, 15));
        labelName.setForeground(new Color(0xCCCCCC));

        JLabel textName = new JLabel();
        textName.setFont(new Font("Tahoma", Font.BOLD, 15));
        textName.setForeground(new Color(0xCCCCCC));
        textName.setBounds(200, 100, 200, 30);

        JLabel labelStatus = new JLabel("Status");
        labelStatus.setBounds(50, 150, 100, 30);
        labelStatus.setFont(new Font("Tahoma", Font.BOLD, 15));
        labelStatus.setForeground(new Color(0xCCCCCC));

        JLabel textStatus = new JLabel();
        textStatus.setFont(new Font("Tahoma", Font.BOLD, 15));
        textStatus.setForeground(new Color(0xCCCCCC));
        textStatus.setBounds(200, 150, 200, 30);

        // Buttons
        JButton assign = new JButton("Assign");
        assign.setBounds(80, 300, 120, 30);
        assign.setBackground(Color.black);
        assign.setForeground(Color.white);
        assign.setFocusPainted(false);

        JButton complete = new JButton("Complete");
        complete.setBounds(80, 300, 120, 30);
        complete.setBackground(Color.black);
        complete.setForeground(Color.white);
        complete.setFocusPainted(false);

        JButton back = new JButton("Back");
        back.setBounds(220, 300, 120, 30);
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        back.setFocusPainted(false);

        rightPanel.add(projId);
        rightPanel.add(projIdText);
        rightPanel.add(labelName);
        rightPanel.add(textName);
        rightPanel.add(labelStatus);
        rightPanel.add(textStatus);
        rightPanel.add(back);
        rightPanel.add(search);

        // back button
        back.addActionListener(_ -> {
            projIdText.setText("");
            textName.setText("");
            textStatus.setText("");
            rightPanel.remove(assign);
            rightPanel.remove(complete);
            rightPanel.setVisible(false);
            defaultPanel.setVisible(true);
        });

        // complete button
        complete.addActionListener(_ -> {
            
            int confirm = JOptionPane.showConfirmDialog(
                null,
                "Are you sure you want to change status to complete?",
                "Confirm",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
            );

            // If user confirms, clear fields and switch panels
            if (confirm == JOptionPane.YES_OPTION) {

                // update in backend
                String id = projIdText.getText();
                ProjectManager.completeProject(id);
                projIdText.setText("");
                textName.setText("");
                textStatus.setText("");

            }

        });

        // assign button
        assign.addActionListener(_ -> {
            // Create a new form in a dialog
        JDialog dialog = new JDialog(SwingUtilities.getWindowAncestor(rightPanel), "Assign Employees");
        dialog.setLayout(null);
        dialog.setSize(400, 300);

        // Label and input for number of employees
        JLabel numLabel = new JLabel("Number of Employees:");
        numLabel.setBounds(20, 20, 150, 30);
        dialog.add(numLabel);

        JTextField numField = new JTextField();
        numField.setBounds(180, 20, 150, 30);
        dialog.add(numField);

        // Label and dropdown for skills
        JLabel skillsLabel = new JLabel("Select Skills:");
        skillsLabel.setBounds(20, 70, 150, 30);
        dialog.add(skillsLabel);

        String[] skillOptions = {"C++", "Java", "Python", "UI/UX", "Management"};
        JList<String> skillList = new JList<>(skillOptions);
        skillList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollPane = new JScrollPane(skillList);
        scrollPane.setBounds(180, 70, 150, 80);
        dialog.add(scrollPane);

        // Submit button
        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(100, 200, 80, 30);
        dialog.add(submitButton);

        // Cancel button
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(200, 200, 80, 30);
        dialog.add(cancelButton);

        // Array to hold results
        final int[] num = {0};
        final ArrayList<String> selectedSkills = new ArrayList<>();

        // Submit button action
        submitButton.addActionListener(_ -> {
                num[0] = Integer.parseInt(numField.getText());
                selectedSkills.clear();
                selectedSkills.addAll(skillList.getSelectedValuesList());

                if (num[0] <= 0 || selectedSkills.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Please enter a valid number of employees and select at least one skill.");
                } 
                else {
                    dialog.dispose();
                    // Now you have num[0] and selectedSkills populated
                    System.out.println("Number of Employees: " + num[0]);
                    System.out.println("Skills: " + selectedSkills);
                    String assigned = ProjectManager.assignProjects(projIdText.getText(), num[0], selectedSkills);

                    if(assigned.equals("Ok")){
                        JOptionPane.showMessageDialog(dialog, "Assigned Project " + projIdText.getText());
                        projIdText.setText("");
                        textName.setText("");
                        textStatus.setText("");
                        rightPanel.remove(assign);

                    }

                    else{
                        JOptionPane.showMessageDialog(dialog, assigned);
                        projIdText.setText("");
                        textName.setText("");
                        textStatus.setText("");
                        rightPanel.remove(assign);
                    }
                }
        });

            // Cancel button action
            cancelButton.addActionListener(_-> dialog.dispose());

            // Show the dialog
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);

            // Use num[0] and selectedSkills after the dialog closes
});

        
        // Search button
        search.addActionListener(_ -> {
            String projectId = projIdText.getText();

            if (ProjectManager.checkProject(projectId)) {
                ArrayList<String> projectDetails = ProjectManager.getProjectDetails(projectId);
                String name = projectDetails.get(0);
                String status = projectDetails.get(1);

                if (status.equals("Completed")) {
                    JOptionPane.showMessageDialog(null, "Project " + projectId + " is completed");
                    projIdText.setText("");
                } else if (status.equals("Ongoing")) {
                    textName.setText(name);
                    textStatus.setText(status);
                    if (rightPanel.isAncestorOf(assign)) {
                        rightPanel.remove(assign);
                    }
                    if (!rightPanel.isAncestorOf(complete)) {
                        rightPanel.add(complete);
                    }
                } else if (status.equals("Unassigned")) {
                    textName.setText(name);
                    textStatus.setText(status);
                    if (rightPanel.isAncestorOf(complete)) {
                        rightPanel.remove(complete);
                    }
                    if (!rightPanel.isAncestorOf(assign)) {
                        rightPanel.add(assign);
                    }
                }

                // Refresh the panel
                rightPanel.revalidate();
                rightPanel.repaint();
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Project ID!");
                textName.setText("");
                textStatus.setText("");
                projIdText.setText("");
            }
        });
                

    }
 
    // main method to test frame
    public static void main(String[] args) {
        new HR("HR1");
    }
}
