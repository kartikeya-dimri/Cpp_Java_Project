package Gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class SkillsDropdown extends JPanel {
    private JTextField displayField;               // Field to display selected skills
    private JPopupMenu popupMenu;                  // Popup menu to hold the JList
    private JList<SkillItem> skillsList;           // JList with checkboxes for each skill
    private DefaultListModel<SkillItem> listModel; // Model for skills list

    public SkillsDropdown(String[] skills) {
        setLayout(new BorderLayout());

        // Display field for selected skills
        displayField = new JTextField();
        displayField.setEditable(false);
        add(displayField, BorderLayout.CENTER);

        // Popup menu
        popupMenu = new JPopupMenu();

        // List model with skills wrapped in SkillItem (with selection state)
        listModel = new DefaultListModel<>();
        for (String skill : skills) {
            listModel.addElement(new SkillItem(skill, false));
        }

        // JList with custom cell renderer for checkboxes
        skillsList = new JList<>(listModel);
        skillsList.setCellRenderer(new CheckboxListRenderer());
        skillsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Toggle checkbox on click
        skillsList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = skillsList.locationToIndex(e.getPoint());
                SkillItem item = listModel.getElementAt(index);
                item.setSelected(!item.isSelected());  // Toggle selection
                skillsList.repaint();
                updateDisplayField();
            }
        });

        // Add skills list to popup
        popupMenu.add(new JScrollPane(skillsList));
        popupMenu.setPreferredSize(new Dimension(150, 100));

        // Show popup on clicking the display field
        displayField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                popupMenu.show(displayField, 0, displayField.getHeight());
            }
        });

        updateDisplayField();  // Initialize display field
    }

    private void updateDisplayField() {
        List<String> selectedSkills = new ArrayList<>();
        for (int i = 0; i < listModel.getSize(); i++) {
            SkillItem item = listModel.getElementAt(i);
            if (item.isSelected()) {
                selectedSkills.add(item.getName());
            }
        }
        displayField.setText(String.join(", ", selectedSkills));
    }

    public List<String> getSelectedSkills() {
        List<String> selectedSkills = new ArrayList<>();
        for (int i = 0; i < listModel.getSize(); i++) {
            SkillItem item = listModel.getElementAt(i);
            if (item.isSelected()) {
                selectedSkills.add(item.getName());
            }
        }
        return selectedSkills;
    }

    // Inner class to represent each skill with selection state
    private static class SkillItem {
        private String name;
        private boolean selected;

        public SkillItem(String name, boolean selected) {
            this.name = name;
            this.selected = selected;
        }

        public String getName() { return name; }
        public boolean isSelected() { return selected; }
        public void setSelected(boolean selected) { this.selected = selected; }
    }

    // Custom renderer to display each item as a checkbox
    private static class CheckboxListRenderer extends JCheckBox implements ListCellRenderer<SkillItem> {
        @Override
        public Component getListCellRendererComponent(
                JList<? extends SkillItem> list, SkillItem value, int index,
                boolean isSelected, boolean cellHasFocus) {
            setText(value.getName());
            setSelected(value.isSelected());
            setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());
            setForeground(isSelected ? list.getSelectionForeground() : list.getForeground());
            return this;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Skills Multi-Select Checkbox Dropdown");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(300, 200);

            // Define the list of skills
            String[] skills = {"C++", "Java", "Python", "UI/UX", "Management"};
            SkillsDropdown skillsDropdown = new SkillsDropdown(skills);
            frame.add(skillsDropdown, BorderLayout.NORTH);

            frame.setVisible(true);
        });
    }
}
