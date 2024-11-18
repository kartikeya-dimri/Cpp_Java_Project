package Gui;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

// import com.toedter.calendar.JDateChooser;

public class HR extends JFrame{
    // now as we access different right panel in different function we will declare them as instance variables

    JPanel defaultPanel;
    JPanel addEmpPanel;
    JPanel removeEmpPanel;
    JPanel projectPanel;
    // constructor
    HR(){
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

        MenuButton AddempButton = new MenuButton("Add Emp", "profile.png");
        MenuButton RemoveempButton = new MenuButton("Remove Emp", "projects.png");
        MenuButton logoutButton = new MenuButton("Logout", "logout.png");
        MenuButton ProjectButton = new MenuButton("Projects", "logout.png");

        AddempButton.setBounds(0, 100, 200, 50);
        RemoveempButton.setBounds(0, 200, 200, 50);
        ProjectButton.setBounds(0,300,200,50);
        logoutButton.setBounds(0, 400, 200, 50);

        // left panel

        GradientPanel leftPanel = new GradientPanel();
        leftPanel.setBounds(0, 0, 215, 600);

        // add title label to panel
        leftPanel.add(title); 
        // add menuButtons
        leftPanel.add(AddempButton);
        leftPanel.add(RemoveempButton);
        leftPanel.add(logoutButton);
        leftPanel.add(ProjectButton);

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


        // now action listeners for the buttons
        AddempButton.addActionListener(_ -> {
            defaultPanel.setVisible(false);
            addEmpPanel.setVisible(true);
            removeEmpPanel.setVisible(false);
            projectPanel.setVisible(false);
        });
        // now for remove employee
        RemoveempButton.addActionListener(_ -> {
            defaultPanel.setVisible(false);
            addEmpPanel.setVisible(false);
            removeEmpPanel.setVisible(true);
            projectPanel.setVisible(false);
        });
        // now for projects
        ProjectButton.addActionListener(_ -> {
            defaultPanel.setVisible(false);
            addEmpPanel.setVisible(false);
            removeEmpPanel.setVisible(false);
            projectPanel.setVisible(true);
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
        this.setVisible(true);

    }

    private void project_panel_setting(JPanel rightPanel){
        // kartikeya will implement this using tables and sql.
        
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

        // search button
        JButton search = new JButton("Search");
        search.setBounds(20, 80, 100, 30);
        search.setBackground(Color.black);
        search.setForeground(Color.white);
        search.addActionListener(_ -> {
           String statusType = status.getSelectedItem();
           System.out.println(statusType);

           // create table where status = statusType
        });

        // back button
        JButton back = new JButton("Back");
        back.setBounds(150, 80, 100, 30);
        back.setBackground(Color.black);
        back.setForeground(Color.white);

        back.addActionListener(_ -> {
            rightPanel.setVisible(false);
            defaultPanel.setVisible(true);
        });

        // add to panel
        rightPanel.add(viewProject);
        rightPanel.add(status);
        rightPanel.add(search);
        rightPanel.add(back);

    }

    private void remove_emp_setting(JPanel rightPanel){
        JLabel label = new JLabel("Employee ID");
        label.setBounds(50, 50, 100, 30);
        label.setFont(new Font("Tahoma", Font.BOLD, 15));
        label.setForeground(new Color(0xCCCCCC));

        // Drop-down for empID
        Choice empID = new Choice();
        empID.setBounds(200, 50, 150, 30);

        // Labels for employee details
        JLabel labelName = new JLabel("Name");
        labelName.setBounds(50, 100, 100, 30);
        labelName.setFont(new Font("Tahoma", Font.BOLD, 15));
        labelName.setForeground(new Color(0xCCCCCC));

        JLabel textName = new JLabel();
        textName.setBounds(200, 100, 200, 30);

        JLabel labelPhone = new JLabel("Phone");
        labelPhone.setBounds(50, 150, 100, 30);
        labelPhone.setFont(new Font("Tahoma", Font.BOLD, 15));
        labelPhone.setForeground(new Color(0xCCCCCC));

        JLabel textPhone = new JLabel();
        textPhone.setBounds(200, 150, 200, 30);

        JLabel labelEmail = new JLabel("Email");
        labelEmail.setBounds(50, 200, 100, 30);
        labelEmail.setFont(new Font("Tahoma", Font.BOLD, 15));
        labelEmail.setForeground(new Color(0xCCCCCC));

        JLabel textEmail = new JLabel();
        textEmail.setBounds(200, 200, 200, 30);

        // Buttons
        JButton delete = new JButton("Delete");
        delete.setBounds(80, 300, 100, 30);
        delete.setBackground(Color.black);
        delete.setForeground(Color.white);


        JButton back = new JButton("Back");
        back.setBounds(220, 300, 100, 30);
        back.setBackground(Color.black);
        back.setForeground(Color.white);

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

        // back button
        back.addActionListener(_ -> {
            rightPanel.setVisible(false);
            defaultPanel.setVisible(true);
        });


    }
    private void default_panel_setting(JPanel rightPanel){
        // here i want to add a msg welcoming the ceo and then three cards for total number of projects 
        // total number of employees and total number of hr
        // i will add a label for the welcome msg
        JLabel welcome = new JLabel("Welcome HR");
        welcome.setFont(new Font("Tahoma", Font.BOLD, 20));
        welcome.setForeground(Color.white);
        // now i will add the three cards
        // first card for total number of projects
        // that will be of the class that i created which is Cards.java
        JLabel empName = new JLabel("Total Ongoing Projects: 10");
        empName.setForeground(Color.white);
        empName.setFont(new Font("sanserif", Font.BOLD, 13));
        empName.setBounds(20, 30, 200, 100);

        JLabel empDesignation = new JLabel("Total Employees: 100");
        empDesignation.setForeground(Color.white);
        empDesignation.setFont(new Font("sanserif", Font.BOLD, 15));
        empDesignation.setBounds(20, 30, 200, 100);

        JLabel empProjects = new JLabel("Total Projects: 5");
        empProjects.setForeground(Color.white);
        empProjects.setFont(new Font("sanserif", Font.BOLD, 15));
        empProjects.setBounds(20, 30, 200, 100);

        // create cards
        Cards card1 = new Cards(new Color(0x6A5ACD), new Color(0x1E90FF)); // Blue-purple gradient
        card1.add(empName);
        
        Cards card2 = new Cards(new Color(0xBA68C8), new Color(0x9575CD)); // Purple gradient
        card2.add(empDesignation);

        Cards card3 = new Cards(new Color(0xFBC02D), new Color(0xFFA000));
        //Cards card3 = new Cards(new Color(0xFFEB3B), new Color(0xFFC107)); // Yellow gradient
        card3.add(empProjects);
        // now we will add the components to the right panel
        // first we have to set bounds for the welcome label and for the cards
        // welcome.setBounds(20, 20, 200, 50);
        // card1.setBounds(20, 100, 200, 150);
        // card2.setBounds(20, 300, 200, 150);
        // card3.setBounds(20, 500, 200, 150);
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
        // JDateChooser calChooser = new JDateChooser();
        // calChooser.setBounds(160, 160, 100, 20);
        // calChooser.setBackground(Color.white);

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
        // rightPanel.add(calChooser);
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
        Back.addActionListener(_ -> {
            // System.out.println(skillsDropdown.getSelectedSkills());
            rightPanel.removeAll();
            // we should add the default setting of the right panel
            default_panel_setting(rightPanel);
            rightPanel.revalidate();
            rightPanel.repaint();
        });
    }
    
    // main method to test frame
    public static void main(String[] args) {
        new HR();
    }
}
