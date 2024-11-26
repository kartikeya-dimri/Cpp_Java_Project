package Gui;
import Java_Main.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPage extends JFrame implements ActionListener{
    
    JButton ceoButton, hrButton, empButton;
    JPanel leftPanel;
    JPanel rightPanel;
    LoginPage(){

        //-------------------------------------------------------------------------------------------------
        // create left panel with 3 login options - ceo, hr, employee and exit button

        // create label for top-left, having Portal title and Company logo
        JLabel title = new JLabel("Online Portal");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("sanserif", Font.BOLD, 20));
        title.setBounds(20, 20, 200, 50);
        ImageIcon logo = new ImageIcon("icons-Employee/logo.png");
        Image scaledLogo = logo.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT );
        ImageIcon finalLogo = new ImageIcon(scaledLogo);
        title.setIcon(finalLogo);

        // create buttons for ceo, hr and employee login
        ceoButton = new MenuButton("CEO", "/Login/ceo.png");
        ceoButton.setBounds(0, 150, 200, 50);
        ceoButton.addActionListener(this);

        hrButton = new MenuButton("HR", "/Login/hr.png");
        hrButton.addActionListener(this);
        hrButton.setBounds(0, 300, 200, 50);

        empButton = new MenuButton("Employee", "/Login/employee.png");
        empButton.setBounds(0, 450, 200, 50);
        empButton.addActionListener(this);

        // create gradient panel 
        GradientPanel leftPanel = new GradientPanel();
        leftPanel.setBounds(0, 0, 215, 600);

        // add title label to panel
        leftPanel.add(title); 
        // add menuButtons
        leftPanel.add(ceoButton);
        leftPanel.add(hrButton);
        leftPanel.add(empButton);

        //-------------------------------------------------------------------------------------------------
        // create login panel - default on opening

        // create landing page label
        JLabel landingLabel =  new JLabel("Welcome");
        landingLabel.setForeground(Color.white);
        landingLabel.setFont(new Font("sanserif", Font.BOLD, 22));
        
        ImageIcon icon = new ImageIcon("icons-Employee/Login/employee-clipart.png");
        Image scaledIcon = icon.getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT);
        ImageIcon finalIcon = new ImageIcon(scaledIcon);
        landingLabel.setIcon(finalIcon);
        
        landingLabel.setHorizontalTextPosition(JLabel.CENTER);
        landingLabel.setVerticalTextPosition(JLabel.TOP);
        landingLabel.setIconTextGap(50);
        landingLabel.setBounds(250, 20, 500, 500);


        rightPanel = new JPanel();
        rightPanel.setBackground(new Color(0x000046));
        rightPanel.setBounds(220, 0, 780, 600); // 220 - 1000
        rightPanel.setLayout(null);
        rightPanel.add(landingLabel);

        //-------------------------------------------------------------------------------------------------
        // Create a frame with same dimension as of employee, hr, ceo
        // create base frame on which all things will be added
        this.setSize(1000, 600);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.add(leftPanel);
        this.add(rightPanel);
        this.setVisible(true);

    }

    public static void main(String[] args) {
        new LoginPage();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == ceoButton){
            rightPanel.removeAll();
            rightPanel.repaint();
            rightPanel.revalidate();
            int ycod=90;
           
            // show option to login as ceo

            JLabel ceoLabel = new JLabel("CEO Login");
            ceoLabel.setFont(new Font("sanserif", Font.BOLD, 20));
            ceoLabel.setForeground(Color.WHITE);
            ceoLabel.setBounds(300, 200-ycod, 200, 50);
            rightPanel.add(ceoLabel);
            

            JLabel usernameLabel = new JLabel("Username");
            usernameLabel.setFont(new Font("sanserif", Font.BOLD, 15));
            usernameLabel.setForeground(Color.WHITE);
            usernameLabel.setBounds(250, 250-ycod, 200, 50);
            rightPanel.add(usernameLabel);
            // now the textbox
            JTextField usernameField = new JTextField();
            usernameField.setBounds(250, 300-ycod, 200, 30);
            rightPanel.add(usernameField);
            // now the password label
            JLabel passwordLabel = new JLabel("Password");
            passwordLabel.setFont(new Font("sanserif", Font.BOLD, 15));
            passwordLabel.setForeground(Color.WHITE);
            passwordLabel.setBounds(250, 350-ycod, 200, 50);
            rightPanel.add(passwordLabel);
            // now the password field
            JPasswordField passwordField = new JPasswordField();
            passwordField.setBounds(250, 400-ycod, 200, 30);
            rightPanel.add(passwordField);
            // now the login button
            JButton loginButton = new JButton("Login");
            loginButton.setBounds(200, 450-ycod, 75, 40);
            rightPanel.add(loginButton);
            loginButton.addActionListener(_ -> {
                // same thing that we did in hr login
                System.out.println("Login button clicked");
                // now here i should check if the username and password are correct which are there in aunthentication file in backend package so lets import that 
                String password = new String(passwordField.getPassword());
                if(Auth.login(usernameField.getText(), password, "ceo")){
                    System.out.println("if");
                    this.dispose();
                    new Ceo(usernameField.getText());
                }
                else{
                    System.out.println("else");

                    // show error message
                    JLabel errorLabel = new JLabel("Invalid Username or Password");
                    // also the font should be a bit bigger
                    errorLabel.setFont(new Font("sanserif", Font.BOLD, 15));
                    // here i should disable the login button and input and password 
                    loginButton.setEnabled(false);
                    usernameField.setEnabled(false);
                    passwordField.setEnabled(false);

                    errorLabel.setForeground(Color.RED);
                    errorLabel.setBounds(250, 500-ycod, 300, 50);
                    rightPanel.add(errorLabel);
                    // but still it is not visible so i should repaint it
                    rightPanel.repaint();
                    rightPanel.revalidate();
                    // now i should remove this error message after 2 seconds and also clear the password field
                    // also for this long login button should be disabled and after 2 seconds it should be enabled
                    new java.util.Timer().schedule( 
                        new java.util.TimerTask() {
                            @Override
                            public void run() {
                                rightPanel.remove(errorLabel);
                                loginButton.setEnabled(true);
                                usernameField.setEnabled(true);
                                passwordField.setEnabled(true);

                                passwordField.setText("");

                                rightPanel.repaint();
                                rightPanel.revalidate();
                            }
                        }, 
                        2000 
                    );

                }
            });
            // now the forgot password button
            JButton forgotPasswordButton = new JButton("Forgot Password");
            forgotPasswordButton.setBounds(420, 450-ycod, 140, 40);
            rightPanel.add(forgotPasswordButton);


        }

        else if(e.getSource() == hrButton){
            rightPanel.removeAll();
            rightPanel.repaint();
            rightPanel.revalidate();
            int ycod=90;

            // show option to login as hr
            JLabel hrLabel = new JLabel("HR Login");
            hrLabel.setFont(new Font("sanserif", Font.BOLD, 20));
            hrLabel.setForeground(Color.WHITE);
            hrLabel.setBounds(300, 200-ycod, 200, 50);
            rightPanel.add(hrLabel);
  
            JLabel usernameLabel = new JLabel("Username");
            usernameLabel.setFont(new Font("sanserif", Font.BOLD, 15));
            usernameLabel.setForeground(Color.WHITE);
            usernameLabel.setBounds(250, 250-ycod, 200, 50);
            rightPanel.add(usernameLabel);
            // now the textbox
            JTextField usernameField = new JTextField();
            usernameField.setBounds(250, 300-ycod, 200, 30);
            rightPanel.add(usernameField);
            // now the password label
            JLabel passwordLabel = new JLabel("Password");
            passwordLabel.setFont(new Font("sanserif", Font.BOLD, 15));
            passwordLabel.setForeground(Color.WHITE);
            passwordLabel.setBounds(250, 350-ycod, 200, 50);
            rightPanel.add(passwordLabel);
            // now the password field
            JPasswordField passwordField = new JPasswordField();
            passwordField.setBounds(250, 400-ycod, 200, 30);
            rightPanel.add(passwordField);
            // now the login button
            JButton loginButton = new JButton("Login");
            loginButton.setBounds(200, 450-ycod, 75, 40);
            rightPanel.add(loginButton);
            loginButton.addActionListener(t -> {
                System.out.println("Login button clicked");
                // now here i should check if the username and password are correct which are there in aunthentication file in backend package so lets import that 
                String password = new String(passwordField.getPassword());
                if(Auth.login(usernameField.getText(), password, "hr")){
                    System.out.println("if");
                    this.dispose();
                    new HR(usernameField.getText());
                }
                // now the password was wrong
                else{
                    System.out.println("else");

                    // show error message
                    JLabel errorLabel = new JLabel("Invalid Username or Password");
                    // also the font should be a bit bigger
                    errorLabel.setFont(new Font("sanserif", Font.BOLD, 15));
                    // here i should disable the login button and input and password 
                    loginButton.setEnabled(false);
                    usernameField.setEnabled(false);
                    passwordField.setEnabled(false);

                    errorLabel.setForeground(Color.RED);
                    errorLabel.setBounds(250, 500-ycod, 300, 50);
                    rightPanel.add(errorLabel);
                    // but still it is not visible so i should repaint it
                    rightPanel.repaint();
                    rightPanel.revalidate();
                    // now i should remove this error message after 2 seconds and also clear the password field
                    // also for this long login button should be disabled and after 2 seconds it should be enabled
                    new java.util.Timer().schedule( 
                        new java.util.TimerTask() {
                            @Override
                            public void run() {
                                rightPanel.remove(errorLabel);
                                loginButton.setEnabled(true);
                                usernameField.setEnabled(true);
                                passwordField.setEnabled(true);

                                passwordField.setText("");

                                rightPanel.repaint();
                                rightPanel.revalidate();
                            }
                        }, 
                        2000 
                    );
                }
            });
            // now the forgot password button
            JButton forgotPasswordButton = new JButton("Forgot Password");
            forgotPasswordButton.setBounds(420, 450-ycod, 140, 40);
            rightPanel.add(forgotPasswordButton);
        }

        else if(e.getSource() == empButton){
            rightPanel.removeAll();
            rightPanel.repaint();
            rightPanel.revalidate();
            int ycod=90;

            // show option to login as employee
            JLabel employeeLabel = new JLabel("Employee Login");
            employeeLabel.setFont(new Font("sanserif", Font.BOLD, 20));
            employeeLabel.setForeground(Color.WHITE);
            employeeLabel.setBounds(265, 200-ycod, 200, 50);
            rightPanel.add(employeeLabel);
            
            JLabel usernameLabel = new JLabel("Username");
            usernameLabel.setFont(new Font("sanserif", Font.BOLD, 15));
            usernameLabel.setForeground(Color.WHITE);
            usernameLabel.setBounds(250, 250-ycod, 200, 50);
            rightPanel.add(usernameLabel);
            // now the textbox
            JTextField usernameField = new JTextField();
            usernameField.setBounds(250, 300-ycod, 200, 30);
            rightPanel.add(usernameField);
            // now the password label
            JLabel passwordLabel = new JLabel("Password");
            passwordLabel.setFont(new Font("sanserif", Font.BOLD, 15));
            passwordLabel.setForeground(Color.WHITE);
            passwordLabel.setBounds(250, 350-ycod, 200, 50);
            rightPanel.add(passwordLabel);
            // now the password field
            JPasswordField passwordField = new JPasswordField();
            passwordField.setBounds(250, 400-ycod, 200, 30);
            rightPanel.add(passwordField);
            // now the login button
            JButton loginButton = new JButton("Login");
            loginButton.setBounds(200, 450-ycod, 75, 40);
            rightPanel.add(loginButton);
            loginButton.addActionListener(_ -> {
                // same thing that we did in hr login
                String password = new String(passwordField.getPassword());
                if(Auth.login(usernameField.getText(), password, "emp")){
                    System.out.println("if");
                    this.dispose();
                    new Employee(usernameField.getText());
                }
                else{
                    System.out.println("else");

                    // show error message
                    JLabel errorLabel = new JLabel("Invalid Username or Password");
                    // also the font should be a bit bigger
                    errorLabel.setFont(new Font("sanserif", Font.BOLD, 15));
                    // here i should disable the login button and input and password 
                    loginButton.setEnabled(false);
                    usernameField.setEnabled(false);
                    passwordField.setEnabled(false);

                    errorLabel.setForeground(Color.RED);
                    errorLabel.setBounds(250, 500-ycod, 300, 50);
                    rightPanel.add(errorLabel);
                    // but still it is not visible so i should repaint it
                    rightPanel.repaint();
                    rightPanel.revalidate();
                    // now i should remove this error message after 2 seconds and also clear the password field
                    // also for this long login button should be disabled and after 2 seconds it should be enabled
                    new java.util.Timer().schedule( 
                        new java.util.TimerTask() {
                            @Override
                            public void run() {
                                rightPanel.remove(errorLabel);
                                loginButton.setEnabled(true);
                                usernameField.setEnabled(true);
                                passwordField.setEnabled(true);

                                passwordField.setText("");

                                rightPanel.repaint();
                                rightPanel.revalidate();
                            }
                        }, 
                        2000 
                    );

                }

            });
            // now the forgot password button
            JButton forgotPasswordButton = new JButton("Forgot Password");
            forgotPasswordButton.setBounds(420, 450-ycod, 140, 40);
            rightPanel.add(forgotPasswordButton);

        }
    }

}
