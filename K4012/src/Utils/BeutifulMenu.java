package Utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.BorderLayout;
import java.util.List;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Models.Doctor;
import Models.Nurse;

public class BeutifulMenu {

    public static int Width = 1000;
    public static int Height = 700;

    public static void showMenu(List<String> menu, MenuCallback callback) {
        // Create a new JFrame to display the menu
        JFrame frame = new JFrame("Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Width, Height);

        // Create a new JPanel to hold the menu items
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Use a vertical BoxLayout to make the menu scrollable
        panel.setBackground(new Color(250, 250, 250));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(Box.createRigidArea(new Dimension(0, 20))); // Add a rigid area at the top of the panel

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
            button.setPreferredSize(new Dimension(500, 50)); // Set a fixed height for the buttons
            button.setMaximumSize(new Dimension(500, 50)); // Set the maximum size to the same fixed height
            button.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 5, true)); // Set a border radius
            button.setAlignmentX(Component.CENTER_ALIGNMENT); // Align the button in the center horizontally
            panel.add(button);
            panel.add(Box.createRigidArea(new Dimension(0, 20))); // Add a rigid area below each button
            button.addActionListener(e -> {
                frame.dispose(); // Close the JFrame
                callback.onMenuSelected(index + 1);
            });
        }

        // Create a new JScrollPane to make the menu scrollable
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(600, 400));

        // Add the JScrollPane to the JFrame and show it
        frame.add(scrollPane);
        frame.setVisible(true);
    }

    // Define a callback interface for handling menu selections
    public interface MenuCallback {
        void onMenuSelected(int choice);
    }

    public interface ListCallback {
        void onItemSelected(int doctorId);

        void onReturn();
    }

    public static void showDoctorsList(List<Doctor> doctorsList, String title, ListCallback callback) {
        // Create a new JFrame to display the list of doctors
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Width, Height);

        // Create a new JPanel to hold the list of doctors
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Use a vertical BoxLayout to make the list scrollable
        panel.setBackground(new Color(250, 250, 250));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(Box.createRigidArea(new Dimension(0, 20))); // Add a rigid area at the top of the panel

        // Create a new JPanel to hold the selected doctor's information
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(new Color(250, 250, 250));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        infoPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Add a rigidarea at the top of the panel

        // Add a custom JButton for each doctor
        for (int i = 0; i < doctorsList.size(); i++) {
            final int index = i; // Create a final variable to hold the value of i
            Doctor doctor = doctorsList.get(i);
            JButton button = new JButton();
            button.setText(doctor.getName());
            button.setBackground(new Color(240, 240, 240));
            button.setForeground(new Color(60, 60, 60));
            button.setFocusPainted(false);
            button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            button.setFont(new Font("Arial", Font.PLAIN, 16));
            button.setOpaque(true);
            button.setPreferredSize(new Dimension(500, 80)); // Set a fixed size for the buttons
            button.setMaximumSize(new Dimension(600, 100)); // Set the maximum size to the same fixed size
            button.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 5, true)); // Set a border radius
            button.setAlignmentX(Component.CENTER_ALIGNMENT); // Align the button in the center horizontally
            panel.add(button);
            panel.add(Box.createRigidArea(new Dimension(0, 20))); // Add a rigid area below each button
            button.addActionListener(e -> {
                // Clear the infoPanel to remove any previously selected doctor's information
                infoPanel.removeAll();
                infoPanel.revalidate();
                infoPanel.repaint();

                // Add the selected doctor's information to the infoPanel
                JLabel nameLabel = new JLabel(
                        "Name: " + (doctor.getGender() == "male" ? "mr " : "mrs ") + doctor.getName());
                nameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                infoPanel.add(nameLabel);

                JLabel emailLabel = new JLabel("email: " + doctor.getEmail());
                emailLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                infoPanel.add(emailLabel);

                JLabel phoneLabel = new JLabel("phone: " + doctor.getPhoneNumber());
                phoneLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                infoPanel.add(phoneLabel);

                JLabel specialtyLabel = new JLabel("Specialty: " + doctor.getExpertise());
                specialtyLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                infoPanel.add(specialtyLabel);

                JLabel statusLabel = new JLabel("status: " + (doctor.isAvalable() ? "Available" : "Not available"));
                statusLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                infoPanel.add(statusLabel);

                JButton btnSelect = new JButton("Select");
                btnSelect.addActionListener(ee -> {
                    frame.dispose(); // Close the JFrame
                    callback.onItemSelected(doctor.getId());
                });
                infoPanel.add(btnSelect);

                // Repaint the infoPanel to display the selected doctor's information
                infoPanel.revalidate();
                infoPanel.repaint();
            });
        }

        // Create a new JPanel to hold the return button
        JPanel returnPanel = new JPanel();
        returnPanel.setLayout(new BoxLayout(returnPanel, BoxLayout.X_AXIS));
        returnPanel.setBackground(new Color(250, 250, 250));
        returnPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        returnPanel.add(Box.createHorizontalGlue()); // Add a horizontal glue to the left of the return button to keep
                                                     // it fixed to the right
        JButton returnButton = new JButton("Return");
        returnButton.setBackground(new Color(240, 240, 240));
        returnButton.setForeground(new Color(60, 60, 60));
        returnButton.setFocusPainted(false);
        returnButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        returnButton.setFont(new Font("Arial", Font.PLAIN, 16));
        returnButton.setOpaque(true);
        returnButton.setPreferredSize(new Dimension(100, 50)); // Set a fixed size for the button
        returnButton.setMaximumSize(new Dimension(100, 50)); // Set the maximum size to the same fixed size
        returnButton.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 5, true)); // Set a border
                                                                                                   // radius
        returnButton.setAlignmentX(Component.RIGHT_ALIGNMENT); // Align the button to the right horizontally
        returnPanel.add(returnButton);

        // Add the return button to the bottom of the frame
        frame.add(returnPanel, BorderLayout.SOUTH);

        // Create a new JScrollPane to make the list of doctors scrollable
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(600, 400));

        // Add the JScrollPane to the left side of the frame
        frame.add(scrollPane, BorderLayout.WEST);

        // Add the infoPanel to the right side of the frame
        frame.add(infoPanel, BorderLayout.CENTER);

        // Add an action listener to the return button to close the JFrame and call the
        // callback
        returnButton.addActionListener(e -> {
            frame.dispose(); // Close the JFrame
            callback.onReturn();
        });

        // Show the JFrame
        frame.setVisible(true);
    }
    
    
    public static void showNursesList(List<Nurse> nursesList, String title, ListCallback callback) {
        // Create a new JFrame to display the list of doctors
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Width, Height);

        // Create a new JPanel to hold the list of doctors
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Use a vertical BoxLayout to make the list scrollable
        panel.setBackground(new Color(250, 250, 250));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(Box.createRigidArea(new Dimension(0, 20))); // Add a rigid area at the top of the panel

        // Create a new JPanel to hold the selected doctor's information
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(new Color(250, 250, 250));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        infoPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Add a rigidarea at the top of the panel

        // Add a custom JButton for each doctor
        for (int i = 0; i < nursesList.size(); i++) {
            final int index = i; // Create a final variable to hold the value of i
            Nurse nurse = nursesList.get(i);
            JButton button = new JButton();
            button.setText(nurse.getName());
            button.setBackground(new Color(240, 240, 240));
            button.setForeground(new Color(60, 60, 60));
            button.setFocusPainted(false);
            button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            button.setFont(new Font("Arial", Font.PLAIN, 16));
            button.setOpaque(true);
            button.setPreferredSize(new Dimension(500, 80)); // Set a fixed size for the buttons
            button.setMaximumSize(new Dimension(600, 100)); // Set the maximum size to the same fixed size
            button.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 5, true)); // Set a border radius
            button.setAlignmentX(Component.CENTER_ALIGNMENT); // Align the button in the center horizontally
            panel.add(button);
            panel.add(Box.createRigidArea(new Dimension(0, 20))); // Add a rigid area below each button
            button.addActionListener(e -> {
                // Clear the infoPanel to remove any previously selected doctor's information
                infoPanel.removeAll();
                infoPanel.revalidate();
                infoPanel.repaint();

                // Add the selected doctor's information to the infoPanel
                JLabel nameLabel = new JLabel(
                        "Name: " + (nurse.getGender() == "male" ? "mr " : "mrs ") + nurse.getName());
                nameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                infoPanel.add(nameLabel);

                JLabel emailLabel = new JLabel("email: " + nurse.getEmail());
                emailLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                infoPanel.add(emailLabel);

                JLabel phoneLabel = new JLabel("phone: " + nurse.getPhoneNumber());
                phoneLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                infoPanel.add(phoneLabel);
                
                JLabel typeLabel = new JLabel("Type: " + nurse.getType());
                typeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                infoPanel.add(typeLabel);

                JLabel POWLabel = new JLabel("Place of work: " + nurse.getPlaceOfWork());
                POWLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                infoPanel.add(POWLabel);

                JLabel statusLabel = new JLabel("status: " + (nurse.isAvalable() ? "Available" : "Not available"));
                statusLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                infoPanel.add(statusLabel);

                JButton btnSelect = new JButton("Select");
                btnSelect.addActionListener(ee -> {
                    frame.dispose(); // Close the JFrame
                    callback.onItemSelected(nurse.getId());
                });
                infoPanel.add(btnSelect);

                // Repaint the infoPanel to display the selected doctor's information
                infoPanel.revalidate();
                infoPanel.repaint();
            });
        }

        // Create a new JPanel to hold the return button
        JPanel returnPanel = new JPanel();
        returnPanel.setLayout(new BoxLayout(returnPanel, BoxLayout.X_AXIS));
        returnPanel.setBackground(new Color(250, 250, 250));
        returnPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        returnPanel.add(Box.createHorizontalGlue()); // Add a horizontal glue to the left of the return button to keep
                                                     // it fixed to the right
        JButton returnButton = new JButton("Return");
        returnButton.setBackground(new Color(240, 240, 240));
        returnButton.setForeground(new Color(60, 60, 60));
        returnButton.setFocusPainted(false);
        returnButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        returnButton.setFont(new Font("Arial", Font.PLAIN, 16));
        returnButton.setOpaque(true);
        returnButton.setPreferredSize(new Dimension(100, 50)); // Set a fixed size for the button
        returnButton.setMaximumSize(new Dimension(100, 50)); // Set the maximum size to the same fixed size
        returnButton.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 5, true)); // Set a border
                                                                                                   // radius
        returnButton.setAlignmentX(Component.RIGHT_ALIGNMENT); // Align the button to the right horizontally
        returnPanel.add(returnButton);

        // Add the return button to the bottom of the frame
        frame.add(returnPanel, BorderLayout.SOUTH);

        // Create a new JScrollPane to make the list of doctors scrollable
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(600, 400));

        // Add the JScrollPane to the left side of the frame
        frame.add(scrollPane, BorderLayout.WEST);

        // Add the infoPanel to the right side of the frame
        frame.add(infoPanel, BorderLayout.CENTER);

        // Add an action listener to the return button to close the JFrame and call the
        // callback
        returnButton.addActionListener(e -> {
            frame.dispose(); // Close the JFrame
            callback.onReturn();
        });

        // Show the JFrame
        frame.setVisible(true);
    }
   

    // TODO
    // showPaymentsList
}