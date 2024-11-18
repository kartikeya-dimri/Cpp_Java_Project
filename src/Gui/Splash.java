package Gui;
// Making frame for Splash screen, appears first on opening the system
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Image;

public class Splash extends JFrame{
    // Constructor for Splash class
    Splash(){

        // create ImageIcon for frames
        ImageIcon i1 = new ImageIcon("icons-Splash/bg.gif");
        Image i2 = i1.getImage().getScaledInstance(800,600,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);

        // create label add icon to it
        JLabel label = new JLabel(i3);
        label.setBounds(0, 0, 800, 600);
        label.setText("Welcome to Employee Management System...");
    

        this.setSize(800,600); // set dimensions
        this.setVisible(true);             // set visibility to true
        this.setLayout(null);        // we dont need border layout(default)
        //this.setLocation(250,50); 
        this.setLocationRelativeTo(null);       // open frame in centre
        this.setTitle("Welcome");     // set title of frame
        this.setDefaultCloseOperation(EXIT_ON_CLOSE); // exit program on X
        this.add(label);                    // add image label to frame
        

        // hide frame after 5 sec
        try {
            Thread.sleep(5000);
            this.setVisible(false);
            this.dispose();
            new LoginPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Splash();
    }
}
