package Gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class MenuButton extends JButton {

    // Constructor
    public MenuButton(String text, String imageName) {
        this.setBorderPainted(false);    // Remove border
        this.setContentAreaFilled(false); // Remove background
        this.setFocusPainted(false);     // Remove focus border
        this.setText(text);

        // Load and scale icon
        ImageIcon icon = new ImageIcon("icons-Employee/" + imageName);
        Image scaledIcon = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon finalIcon = new ImageIcon(scaledIcon);
        this.setIcon(finalIcon);
        
        // Set font and initial properties
        this.setFont(new Font("SanSerif", Font.BOLD, 20));
        this.setForeground(Color.WHITE);
        
        // Add MouseListener for hover effect
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Change color and add shadow border
                MenuButton.this.setForeground(Color.RED);
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Revert to original color and remove border
                MenuButton.this.setForeground(Color.WHITE);
                
            }
        });
    }

    // Main method for testing MenuButton
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create and add the MenuButton
        MenuButton myButton = new MenuButton("Click me", "logo.png");
        myButton.setBounds(100, 100, 200, 100);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        frame.add(myButton);
        
        frame.setVisible(true);
    }
}
