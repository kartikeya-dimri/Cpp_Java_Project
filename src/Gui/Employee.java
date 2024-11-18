package Gui;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Employee extends JFrame implements ActionListener {
    
    // Declaration of buttons
    MenuButton dashButton, projectButton, logoutButton;
    JButton printButton;
    JButton printProjects;

    // Declaration of panels
    JPanel rightPanel;
    JPanel projectPanel;

    // Constructor
    Employee() {
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

        // CREATE RIGHT PANEL - DASHBOARD VIEW
        rightPanel = new JPanel();
        rightPanel.setBackground(new Color(0x000046));
        rightPanel.setBounds(220, 0, 780, 600);
        rightPanel.setLayout(null);

        JLabel empName = new JLabel("Kartikeya Dimri");
        empName.setForeground(Color.white);
        empName.setFont(new Font("sanserif", Font.BOLD, 20));
        empName.setBounds(20, 30, 200, 100);

        Cards card1 = new Cards(new Color(0x6A5ACD), new Color(0x1E90FF));
        card1.setBounds(20+20, 30, 200+10, 150);
        card1.add(empName);
        
        JLabel empDesignation = new JLabel("Sophomore");
        empDesignation.setForeground(Color.white);
        empDesignation.setFont(new Font("sanserif", Font.BOLD, 20));
        empDesignation.setBounds(20, 30, 200, 100);

        Cards card2 = new Cards(new Color(0xBA68C8), new Color(0x9575CD));
        card2.setBounds(250+10+20, 30, 200+10, 150);
        card2.add(empDesignation);

        JLabel empProjects = new JLabel("Active projects: 5");
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

        printButton = new JButton("Print Profile");
        printButton.setBounds(300,400,150,50);
        printButton.setFont(new Font("sanserif", Font.BOLD, 15));
        printButton.addActionListener(this);
        rightPanel.add(printButton);

        // CREATE PROJECT PANEL
        projectPanel = new JPanel();
        projectPanel.setBackground(new Color(0x000046));
        projectPanel.setBounds(220, 0, 780, 600);
        projectPanel.setLayout(null);

        JLabel myProjects =  new JLabel("My Projects");
        myProjects.setForeground(Color.white);
        myProjects.setFont(new Font("sanserif", Font.BOLD, 22));
        myProjects.setBounds(310, 20, 200, 50);
        projectPanel.add(myProjects);

        printProjects = new JButton("Print Projects");
        printProjects.setBounds(300, 400, 150, 50);
        printProjects.setFont(new Font("sanserif", Font.BOLD, 15));
        printProjects.addActionListener(this);
        projectPanel.add(printProjects);

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
            // print table
        }
        else if(e.getSource() == projectButton){
            // print myprojects
        }
    }

    // Main method to test frame
    public static void main(String[] args) {
        new Employee();
    }
}
