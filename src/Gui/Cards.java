package Gui;
import javax.swing.*;
import java.awt.*;

public class Cards extends JPanel {
    
    private Color startColor;
    private Color endColor;
    
    // Constructor to set start and end colors for gradient
    public Cards(Color startColor, Color endColor) {
        this.startColor = startColor;
        this.endColor = endColor;
        this.setLayout(null); // Set layout to null for absolute positioning if needed
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        // Draw the gradient background
        GradientPaint gradient = new GradientPaint(
            0, 0, startColor,        // Start color at the top
            0, getHeight(), endColor // End color at the bottom
        );
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Draw the translucent circles
        g2d.setColor(new Color(255, 255, 255, 50)); // White with transparency
        int radius = 100;

        // Circle 1 (top left)
        g2d.fillOval(-50, 30, radius * 2, radius * 2);

        // Circle 2 (center right)
        g2d.fillOval(getWidth() - radius - 30, getHeight() / 2 - radius, radius * 2, radius * 2);

        // Circle 3 (bottom left)
        g2d.fillOval(50, getHeight() - radius - 50, radius * 2, radius * 2);
    }


    // main method to test Card
    public static void main(String[] args) {
        JFrame frame = new JFrame("Gradient Cards with Circles");
        frame.setSize(800, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        // Create gradient cards with circle patterns
        Cards card1 = new Cards(new Color(0x6A5ACD), new Color(0x1E90FF)); // Blue-purple gradient
        card1.setPreferredSize(new Dimension(200, 150));
        card1.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        Cards card2 = new Cards(new Color(0xBA68C8), new Color(0x9575CD)); // Purple gradient
        card2.setPreferredSize(new Dimension(200, 150));
        card2.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        Cards card3 = new Cards(new Color(0xFFEB3B), new Color(0xFFC107)); // Yellow gradient
        card3.setPreferredSize(new Dimension(200, 150));
        card3.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Add cards to the frame
        frame.add(card1);
        frame.add(card2);
        frame.add(card3);
        
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
