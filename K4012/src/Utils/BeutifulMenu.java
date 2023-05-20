package Utils;

import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BeutifulMenu {
    public static void showMenu(List<String> menu, MenuCallback callback) {
        // Create a new JFrame to display the menu
        JFrame frame = new JFrame("Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
    
        // Create a new JPanel to hold the menu items
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(menu.size(), 1, 0, 10)); // Set the horizontal and vertical gaps
        panel.setBackground(new Color(250, 250, 250));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    
        // Add a custom JButton for each menu item
        for (int i = 0; i < menu.size(); i++) {
            final int index = i; // Create a final variable to hold the value of i
            String menuItem = menu.get(i);
            JButton button = new JButton();
            button.setText(menuItem);
            button.setBackground(new Color(240, 240, 240));
            button.setForeground(new Color(60, 60, 60));
            button.setFocusPainted(false);
            button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            button.setFont(new Font("Arial", Font.PLAIN, 16));
            button.setOpaque(true);
            button.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 5, true)); // Set a border radius
            button.setAlignmentX(Component.CENTER_ALIGNMENT); // Align the button in the center horizontally
            panel.add(button);
            button.addActionListener(e -> {
                frame.dispose(); // Close the JFrame
                callback.onMenuSelected(index + 1);
            });
        }
    
        // Add the JPanel to the JFrame and show it
        frame.add(panel);
        frame.setVisible(true);
    }
    
    // Define a callback interface for handling menu selections
    public interface MenuCallback {
        void onMenuSelected(int choice);
    }
}