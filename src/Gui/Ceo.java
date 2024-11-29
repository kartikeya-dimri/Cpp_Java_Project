package Gui;
import Java_Main.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;


public class Ceo extends JFrame{
    private String currentUser;
    // constructor 
    Ceo(String currentUser){
        this.currentUser = currentUser;
        // System.out.println("ceo called");
        
        //----------------------------------------------------------------------------
        // create label for top-left, having Portal title and Company logo
        JLabel title = new JLabel("Online Portal");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("sanserif", Font.BOLD, 20));
        title.setBounds(20, 20, 200, 50);
        ImageIcon logo = new ImageIcon("icons-employee/logo.png");
        Image scaledLogo = logo.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT );
        ImageIcon finalLogo = new ImageIcon(scaledLogo);
        title.setIcon(finalLogo);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //----------------------------------------------------------------------------
        // create buttons for Dashboard and Projects
        MenuButton AddHrButton = new MenuButton("Add Hr", "profile.png");
        MenuButton RemoveHrButton = new MenuButton("Search Hr", "projects.png");
        MenuButton logoutButton = new MenuButton("Logout", "logout.png");
        MenuButton addProjectButton = new MenuButton("Add Project", "logout.png");

        AddHrButton.setBounds(0, 100, 200, 50);
        RemoveHrButton.setBounds(0, 200, 200, 50);
        addProjectButton.setBounds(0,300,200,50);
        logoutButton.setBounds(0, 400, 200, 50);

        //----------------------------------------------------------------------------

        // Create right panel that will change based on button click
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(null);
        rightPanel.setBounds(220, 0, 785, 600);
        // default setting of right panel
        default_setting(rightPanel);
        rightPanel.setBackground(new Color(0x000046));
        this.add(rightPanel);

        //----------------------------------------------------------------------------

        // now i want to add action listeners to the buttons
        // when clicked, the right panel will change based on the button clicked
        // for now, i will just add a label to show that the button click is working
        AddHrButton.addActionListener(_ -> {
            rightPanel.removeAll();
            
            
            rightPanel.revalidate();
            rightPanel.repaint();
            addAddEmployeeForm(rightPanel);
        });
        // now for the project button
        RemoveHrButton.addActionListener(_ -> {
            rightPanel.removeAll();
            removeEmployeeForm(rightPanel);
            rightPanel.revalidate();
            rightPanel.repaint();
        });
        // now for the project button
        addProjectButton.addActionListener(_ -> {
            // name of the project
            rightPanel.removeAll();
            addProjectForm(rightPanel);
            rightPanel.revalidate();
            rightPanel.repaint();
        });
        // now for the logout button
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
                rightPanel.removeAll();
                rightPanel.revalidate();
                rightPanel.repaint();
                this.dispose();
                new LoginPage();
                
                // Place the code you want to execute here
            } 
        });

        
        //----------------------------------------------------------------------------


        //----------------------------------------------------------------------------
        // create gradient panel 
        GradientPanel leftPanel = new GradientPanel();
        leftPanel.setBounds(0, 0, 215, 600);

        // add title label to panel
        leftPanel.add(title); 
        // add menuButtons
        leftPanel.add(AddHrButton);
        leftPanel.add(RemoveHrButton);
        leftPanel.add(logoutButton);
        leftPanel.add(addProjectButton);

        //----------------------------------------------------------------------------
        // create base frame on which all things will be added
        
        this.setSize(1000, 600);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(null);

        // add all components on the frame
        this.add(leftPanel);
        this.add(rightPanel);
        this.setVisible(true);
        //----------------------------------------------------------------------------

    }

    
    

    private void addAddEmployeeForm(JPanel rightPanel) {
       // create label for heading
        JLabel heading = new JLabel("Add HR Details");
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
        // calChooser.setDateFormatString("dd/MM/yyyy");  // Set format

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
        Back.addActionListener(_ -> {
            System.out.println(skillsDropdown.getSelectedSkills());
            rightPanel.removeAll();
            // we should add the default setting of the right panel
            default_setting(rightPanel);
            rightPanel.revalidate();
            rightPanel.repaint();
        });
        // now for the add button we will call the add method of the backend and that will return an arraylist of string with success or failure and error message 
        Add.addActionListener(_ -> {
            // we will call the add method of the backend and pass the values of the text fields
            // we will get the result in an arraylist of string
            // but dropdown are list and we have to change that to arraylist
            @SuppressWarnings({ "unchecked", "rawtypes" })
            ArrayList<String> skillsList = new ArrayList(skillsDropdown.getSelectedSkills());
            String dob1 = ((JTextField)calChooser.getDateEditor().getUiComponent()).getText();
            // System.out.println(dob1);

            ArrayList<String> result = WorkforceManager.add("HR", name_text.getText(), father_text.getText(), dob1, salary_text.getText(), add_text.getText(), email_text.getText(), phone_text.getText(), educationBox.getSelectedItem().toString(), skillsList);
            // now we will check if the result is success or failure
            if(result.get(0).equals("1")){
                // success
                JOptionPane.showMessageDialog(null, "HR added successfully");
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
                JOptionPane.showMessageDialog(null, "HR not added. Error: " + result.get(1));
            }
        });
    }

    private void removeEmployeeForm(JPanel rightPanel){
        JLabel label = new JLabel("HR ID");
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
        back.addActionListener(_ -> {
            rightPanel.removeAll();
            default_setting(rightPanel);
            rightPanel.revalidate();
            rightPanel.repaint();
        });

        // search button will call the dashboard method of the backend and will get the details of the hr
        search.addActionListener(_ -> {
            // we will call the dashboard method of the backend and pass the empID
            // we will get the result in an arraylist of string
            ArrayList<String> result = WorkforceManager.searchForDelete("CEO", empID.getText());
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
                JOptionPane.showMessageDialog(null, "HR not found. Error: " + result.get(1));
                textEmail.setText("");
                textName.setText("");
                textPhone.setText("");
                
            }

        });
        // now for delete button but before calling the method we will so a confirmation dialog box and only if the user clicks ok then we will call the delete method
        delete.addActionListener(_ -> {
            int result = JOptionPane.showConfirmDialog(
                null,
                "Are you sure you want to delete this HR?",
                "Warning",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.WARNING_MESSAGE
            );
    
            // Check the user's response
            if (result == JOptionPane.OK_OPTION) {
                // Execute code if the user clicks OK
                // we will call the delete method of the backend and pass the empID
                WorkforceManager.delete("HR", empID.getText());
                JOptionPane.showMessageDialog(null, "HR deleted successfully");
                // we will also remove the text from the labels
                textName.setText("");
                textPhone.setText("");
                textEmail.setText("");
                empID.setText("");
                delete.setVisible(false);
            } 
        });




    }
    private void addProjectForm(JPanel rightPanel){
        // here we only need one text field and a button which is for adding the project and its name
        // we have to create a text field for the project name
        JLabel label = new JLabel("Project Name");
        label.setBounds(50, 50, 150, 30);
        label.setFont(new Font("Tahoma", Font.BOLD, 15));
        label.setForeground(new Color(0xCCCCCC));
        JTextField text = new JTextField();
        text.setBounds(200, 50, 150, 30);
        // now we have to create a button for adding the project and a button for back

        JButton add = new JButton("Add");
        add.setBounds(80, 100, 100, 30);
        add.setBackground(Color.black);
        add.setForeground(Color.white);
        // button for back
        JButton back = new JButton("Back");
        back.setBounds(220, 100, 100, 30);
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        // now we have to add these components to the right panel
        rightPanel.add(label);
        rightPanel.add(text);
        rightPanel.add(add);
        rightPanel.add(back);
        // now i should add action listeners to the buttons and first for back
        back.addActionListener(_ -> {
            rightPanel.removeAll();
            default_setting(rightPanel);
            rightPanel.revalidate();
            rightPanel.repaint();
        }); 
        // now for add button and it will just return true or false 
        // if it is false then we will say that project already exists
        // if it is true then we will say project added successfully
        add.addActionListener(_ -> {
            // we will call the add project method of the backend and pass the project name
            // we will get the result in an arraylist of string
            
            // now we will check if the result is success or failure
            if(ProjectManager.addProj(text.getText())){
                // success
                JOptionPane.showMessageDialog(null, "Project added successfully");
            }
            else{
                // failure
                JOptionPane.showMessageDialog(null, "Project already exists");
            }
            text.setText("");
                
        });
    }

    // main method to test frame
    private void default_setting(JPanel rightPanel){
        // here i want to add a msg welcoming the ceo and then three cards for total number of projects 
        // total number of employees and total number of hr
        // i will add a label for the welcome msg
        // i want to display the name that we got that is current user
        // JLabel welcome = new JLabel("Welcome CEO");
        JLabel welcome = new JLabel("Welcome " + "Rahul Sharma");
        // also i have increase the width of this label

        welcome.setFont(new Font("Tahoma", Font.BOLD, 20));
        welcome.setForeground(Color.white);
        // now i will add the three cards
        // first card for total number of projects
        // that will be of the class that i created which is Cards.java
        // here i will call display info class to get the total number of projects, employees and hr
        JLabel totalProjects = new JLabel("Total Projects " +DisplayInfo.ceoDashboardInfo().get(0));
        totalProjects.setForeground(Color.white);
        totalProjects.setFont(new Font("sanserif", Font.BOLD, 15));
        totalProjects.setBounds(20, 30, 200, 100);

        JLabel totalEmployees = new JLabel("Total Employees: " + DisplayInfo.ceoDashboardInfo().get(1));
        totalEmployees.setForeground(Color.white);
        totalEmployees.setFont(new Font("sanserif", Font.BOLD, 15));
        totalEmployees.setBounds(20, 30, 200, 100);

        JLabel totalHr = new JLabel("Total HRs: " + DisplayInfo.ceoDashboardInfo().get(2));
        totalHr.setForeground(Color.white);
        totalHr.setFont(new Font("sanserif", Font.BOLD, 15));
        totalHr.setBounds(20, 30, 200, 100);

        // create cards
        Cards card1 = new Cards(new Color(0x6A5ACD), new Color(0x1E90FF)); // Blue-purple gradient
        card1.add(totalProjects);
        
        Cards card2 = new Cards(new Color(0xBA68C8), new Color(0x9575CD)); // Purple gradient
        card2.add(totalEmployees);

        Cards card3 = new Cards(new Color(0xFBC02D), new Color(0xFFA000));
        //Cards card3 = new Cards(new Color(0xFFEB3B), new Color(0xFFC107)); // Yellow gradient
        card3.add(totalHr);
        // now we will add the components to the right panel
        // first we have to set bounds for the welcome label and for the cards
        // welcome.setBounds(20, 20, 200, 50);
        // card1.setBounds(20, 100, 200, 150);
        // card2.setBounds(20, 300, 200, 150);
        // card3.setBounds(20, 500, 200, 150);
        // this card setting is putting them vertically whereas i want them horizontally so i will do it again
        welcome.setBounds(20, 20, 270, 50);
        card1.setBounds(20, 100, 200, 150);
        card2.setBounds(250, 100, 200, 150);
        card3.setBounds(480, 100, 200, 150);

        // now we will add the components to the right panel
        rightPanel.add(welcome);
        rightPanel.add(card1);
        rightPanel.add(card2);
        rightPanel.add(card3);




    }
    
    public static void main(String[] args) {
        // call constructor
        new Ceo("Kartikeya Dimri");
    }



}
