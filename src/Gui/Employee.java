package Gui;
import Java_Main.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import javax.swing.*;

// import backend files to call backend methods
// import Java_Main.*;


public class Employee extends JFrame implements ActionListener {
    private String currentUser;
    
    // Declaration of buttons
    MenuButton dashButton, projectButton, logoutButton;
    JButton printButton;
    JButton printProjects;

    // Declaration of panels
    JPanel rightPanel;
    JPanel projectPanel;

    // Declaration of Tables
    JTable table;
    JTable project;

    // Data for the current user
    ArrayList<String> employeeDashboard ;
    EmployeeData employeeProfile ;
    ArrayList<ProjectData> employeeProjects ;
    
    // Constructor
    Employee(String a) {
        System.out.println("emp constructor called");
        this.currentUser = a;
        employeeDashboard=DisplayInfo.empDashboardInfo(this.currentUser);
        employeeProfile= DisplayInfo.DashboardDetailsPrint("employee",this.currentUser);
        employeeProjects=ProjectManager.getEmployeeProjects(this.currentUser);
        // CREATE LEFT PANEL
        JLabel title = new JLabel("Online Portal");
        // exit on close
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("sanserif", Font.BOLD, 20));
        title.setBounds(20, 20, 200, 50);
        ImageIcon logo = new ImageIcon("icons-Employee/logo.png");
        Image scaledLogo = logo.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        title.setIcon(new ImageIcon(scaledLogo));

        dashButton = new MenuButton("Dashboard", "profile.png");
        dashButton.addActionListener(this);
        projectButton = new MenuButton("My Projects", "projects.png");
        projectButton.addActionListener(this);
        logoutButton = new MenuButton("Logout", "logout.png");
        logoutButton.addActionListener(this);

        dashButton.setBounds(0, 150, 200, 50);
        projectButton.setBounds(0, 300, 200, 50);
        logoutButton.setBounds(0, 450, 200, 50);

        GradientPanel leftPanel = new GradientPanel();
        leftPanel.setBounds(0, 0, 215, 600);
        leftPanel.add(title);
        leftPanel.add(dashButton);
        leftPanel.add(projectButton);
        leftPanel.add(logoutButton);

        //----------------------------------------------------------------------------------------------------------------------------------
        // CREATE RIGHT PANEL - DASHBOARD VIEW
        rightPanel = new JPanel();
        rightPanel.setBackground(new Color(0x000046));
        rightPanel.setBounds(220, 0, 780, 600);
        rightPanel.setLayout(null);

        // Store Card data using ArrayList employeeDashboard
        String cardName = employeeDashboard.get(0);
        JLabel empName = new JLabel(cardName);
        empName.setForeground(Color.white);
        empName.setFont(new Font("sanserif", Font.BOLD, 20));
        empName.setBounds(20, 30, 200, 100);

        Cards card1 = new Cards(new Color(0x6A5ACD), new Color(0x1E90FF));
        card1.setBounds(20+20, 30, 200+10, 150);
        card1.add(empName);
        
        String cardID = employeeDashboard.get(1);
        JLabel empId = new JLabel("ID : " + cardID);
        empId.setForeground(Color.white);
        empId.setFont(new Font("sanserif", Font.BOLD, 20));
        empId.setBounds(20, 30, 200, 100);

        Cards card2 = new Cards(new Color(0xBA68C8), new Color(0x9575CD));
        card2.setBounds(250+10+20, 30, 200+10, 150);
        card2.add(empId);

        String cardProjects = employeeDashboard.get(2);
        JLabel empProjects = new JLabel("Active projects: " + cardProjects);
        empProjects.setForeground(Color.white);
        empProjects.setFont(new Font("sanserif", Font.BOLD, 20));
        empProjects.setBounds(20, 30, 200, 100);

        // Cards card3 = new Cards(new Color(0xFFEB3B), new Color(0xFFC107));
        Cards card3 = new Cards(new Color(0xFBC02D), new Color(0xFFA000));
        card3.setBounds(480+20+20, 30, 200+10, 150);
        card3.add(empProjects);

        rightPanel.add(card1);
        rightPanel.add(card2);
        rightPanel.add(card3);

        // Create table for Employee profile
        // Data to display in the table using getters
        Object[][] data = {
            {"Name", employeeProfile.getName()},
            {"ID", employeeProfile.getId()},
            {"Father's Name", employeeProfile.getFatherName()},
            {"Date of Birth", employeeProfile.getDob()},
            {"Salary", employeeProfile.getSalary()},
            {"Address", employeeProfile.getAddress()},
            {"Email", employeeProfile.getEmail()},
            {"Phone Number", employeeProfile.getPhoneNum()},
            {"Highest Qualification", employeeProfile.getHighestQual()},
            {"Skills", String.join(", ", employeeProfile.getSkills())}
        };

        // Create the table model
        DefaultTableModel tableModel = new DefaultTableModel(data, new Object[]{"Field", "Data"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table cells non-editable
            }
        };

        // Create the JTable
        table = new JTable(tableModel);
        table.setFillsViewportHeight(true);

        // Set font size for table cells
        table.setFont(new Font("Serif", Font.PLAIN, 14));
        table.setRowHeight(25);
        

        // Custom renderer to make the first column bold
        DefaultTableCellRenderer boldRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                           boolean hasFocus, int row, int column) {
                Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (column == 0) {
                    cellComponent.setFont(new Font("Serif", Font.BOLD, 14));
                } else {
                    cellComponent.setFont(new Font("Serif", Font.PLAIN, 14));
                }
                return cellComponent;
            }
        };

        // Apply the renderer to the first column
        table.getColumnModel().getColumn(0).setCellRenderer(boldRenderer);
        // Set column widths
        table.getColumnModel().getColumn(0).setPreferredWidth(200);  // Field column width
        table.getColumnModel().getColumn(1).setPreferredWidth(300);  // Data column width


        // Add the table to a scroll pane and add it to the frame
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(180, 200, 500, 300);
        rightPanel.add(scrollPane);

        printButton = new JButton("Print Profile");
        printButton.setBounds(315,510,150,50);
        printButton.setFont(new Font("sanserif", Font.BOLD, 15));
        printButton.addActionListener(this);
        rightPanel.add(printButton);

        //----------------------------------------------------------------------------------------------------------------------------------
        // CREATE PROJECT PANEL
        projectPanel = new JPanel();
        projectPanel.setBackground(new Color(0x000046));
        projectPanel.setBounds(220, 0, 780, 600);
        projectPanel.setLayout(null);

        JLabel myProjects =  new JLabel("My Projects");
        myProjects.setForeground(Color.white);
        myProjects.setFont(new Font("sanserif", Font.BOLD, 22));
        myProjects.setBounds(320, 20, 200, 50);
        projectPanel.add(myProjects);

        // create Table to display project details
        // status project_id name numofemps {p1,p2,p3}
        // Column names for the table
        String[] columnNames = {"Status", "Project ID", "Project Name", "Number of Employees"};

        // Populate table data
        Object[][] data2 = new Object[employeeProjects.size()][4];
        for (int i = 0; i < employeeProjects.size(); i++) {
            ProjectData projectData = employeeProjects.get(i);
            data2[i][0] = projectData.getStatus();
            data2[i][1] = projectData.getProjId();
            data2[i][2] = projectData.getName();
            data2[i][3] = projectData.getNumOfWorkingEmps();
        }
 
        // Create the table model
        DefaultTableModel tableModel2 = new DefaultTableModel(data2, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make cells non-editable
            }
        };
 
         // Create the JTable
        project = new JTable(tableModel2);
        project.setFillsViewportHeight(true);
        project.setFont(new Font("Serif", Font.PLAIN, 14));
        project.setRowHeight(25);
 
         // Add the project to a scroll pane and set it in the frame
        JScrollPane scrollPane2 = new JScrollPane(project);
        scrollPane2.setBounds(140, 120, 500, 300);
        projectPanel.add(scrollPane2);

        printProjects = new JButton("Print Projects");
        printProjects.setBounds(315, 450, 150, 50);
        printProjects.setFont(new Font("sanserif", Font.BOLD, 15));
        printProjects.addActionListener(this);
        projectPanel.add(printProjects);
        
        //----------------------------------------------------------------------------------------------------------------------------------
        // CREATE BASE FRAME
        this.setSize(1000, 600);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        // Add components to the frame
        this.add(leftPanel);
        this.add(rightPanel);    // Start with rightPanel visible
        this.add(projectPanel);   // projectPanel will initially be hidden
        projectPanel.setVisible(false); // Initially hide projectPanel

        this.setVisible(true);
    }

    //----------------------------------------------------------------------------------------------------------------------------------
    // buttons action listener
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == projectButton) {
            rightPanel.setVisible(false);     // Hide rightPanel (Dashboard)
            projectPanel.setVisible(true);    // Show projectPanel
        }
        else if (e.getSource() == dashButton) {
            projectPanel.setVisible(false);   // Hide projectPanel
            rightPanel.setVisible(true);      // Show rightPanel (Dashboard)
        }
        else if (e.getSource() == logoutButton) {
            
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
                this.dispose();
                new LoginPage();
                
            } 

        }

        else if(e.getSource() == printButton){
            // print table of employee profile
            try {
                boolean complete = table.print(JTable.PrintMode.FIT_WIDTH);
                if (complete) {
                    JOptionPane.showMessageDialog(this, "Print job completed", "Print", JOptionPane.INFORMATION_MESSAGE);
                } 
                else {
                    JOptionPane.showMessageDialog(this, "Print job cancelled", "Print", JOptionPane.WARNING_MESSAGE);
                }
                } 
            catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error while printing: " + ex.getMessage(), "Print Error", JOptionPane.ERROR_MESSAGE);
            }


        }


        else if(e.getSource() == printProjects){
            // print myprojects
            try {
                boolean complete = project.print(JTable.PrintMode.FIT_WIDTH);
                if (complete) {
                    JOptionPane.showMessageDialog(this, "Print job completed", "Print", JOptionPane.INFORMATION_MESSAGE);
                } 
                else {
                    JOptionPane.showMessageDialog(this, "Print job cancelled", "Print", JOptionPane.WARNING_MESSAGE);
                }
                } 
            catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error while printing: " + ex.getMessage(), "Print Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //----------------------------------------------------------------------------------------------------------------------------------
    // Main method to test frame
    public static void main(String[] args) {
        new Employee("EMP1");
    }
}
