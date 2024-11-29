package Gui;
import javax.swing.*;
import java.awt.*;

public class GradientPanel extends JPanel {

    private Color startColor = new Color(0x1CB5E0); // Cyan at the top
    private Color endColor = new Color(0x000046);   // Dark blue at the bottom
    
    public GradientPanel() {
        this.setLayout(null); // Set layout to null to enable absolute positioning of components
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Create a vertical gradient from top to bottom
        GradientPaint gradient = new GradientPaint(
            0, 0, startColor,       // Start at the top
            0, getHeight(), endColor // End at the bottom
        );

        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }

    // Main method to test GradientPanel
    public static void main(String[] args) {
        JFrame frame = new JFrame("Vertical Gradient Panel Example");
        GradientPanel gradientPanel = new GradientPanel();

        frame.add(gradientPanel);
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
