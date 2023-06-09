package Utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.Dimension;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import DbContext.Interfaces.IDataBaseContext;
import Models.Admin;
import Models.ArticleForResume;
import Models.Doctor;
import Models.ExpeirenceForResume;
import Models.Nurse;
import Models.Patient;
import Models.PatientHospitalizationRecord;
import Models.PatientPayment;
import Models.Personel;
import Models.Resume;
import Models.Visit;

public class BeutifulMenu {

    public static int Width = 1000;
    public static int Height = 700;
    public static IDataBaseContext context;

    public static void showMenu(List<String> menu, String title, MenuCallback callback) {
        // Create a new JFrame to display the menu
        JFrame frame = new JFrame(title);
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

                JButton btnSelect = new JButton("Edit");
                btnSelect.addActionListener(ee -> {
                    frame.dispose(); // Close the JFrame
                    callback.onItemSelected(doctor.getId());
                });
                JButton btnRemove = new JButton("Remove");
                btnRemove.addActionListener(ee -> {
                    frame.dispose(); // Close the JFrame
                    callback.onItemSelectedForRemove(doctor.getId());
                });
                infoPanel.add(btnSelect);
                infoPanel.add(btnRemove);

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

                JButton btnSelect = new JButton("Edit");
                btnSelect.addActionListener(ee -> {
                    frame.dispose(); // Close the JFrame
                    callback.onItemSelected(nurse.getId());
                });
                JButton btnRemove = new JButton("Remove");
                btnRemove.addActionListener(ee -> {
                    frame.dispose(); // Close the JFrame
                    callback.onItemSelectedForRemove(nurse.getId());
                });
                infoPanel.add(btnSelect);
                infoPanel.add(btnRemove);

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

    public static void showPersonelsList(List<Personel> personelsList, String title, ListCallback callback) {
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
        for (int i = 0; i < personelsList.size(); i++) {
            final int index = i; // Create a final variable to hold the value of i
            Personel personel = personelsList.get(i);
            JButton button = new JButton();
            button.setText(personel.getName());
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
                        "Name: " + (personel.getGender() == "male" ? "mr " : "mrs ") + personel.getName());
                nameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                infoPanel.add(nameLabel);

                JLabel emailLabel = new JLabel("email: " + personel.getEmail());
                emailLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                infoPanel.add(emailLabel);

                JLabel phoneLabel = new JLabel("phone: " + personel.getPhoneNumber());
                phoneLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                infoPanel.add(phoneLabel);

                JLabel statusLabel = new JLabel("status: " + (personel.isAvalable() ? "Available" : "Not available"));
                statusLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                infoPanel.add(statusLabel);

                JButton btnSelect = new JButton("Edit");
                btnSelect.addActionListener(ee -> {
                    frame.dispose(); // Close the JFrame
                    callback.onItemSelected(personel.getId());
                });
                JButton btnRemove = new JButton("Remove");
                btnRemove.addActionListener(ee -> {
                    frame.dispose(); // Close the JFrame
                    callback.onItemSelectedForRemove(personel.getId());
                });
                infoPanel.add(btnSelect);
                infoPanel.add(btnRemove);

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

    public static void showPatientsList(List<Patient> patientsList, String title, ListCallback callback) {
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
        for (int i = 0; i < patientsList.size(); i++) {
            final int index = i; // Create a final variable to hold the value of i
            Patient patient = patientsList.get(i);
            JButton button = new JButton();
            button.setText(patient.getName());
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
                        "Name: " + (patient.getGender() == "male" ? "mr " : "mrs ") + patient.getName());
                nameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                infoPanel.add(nameLabel);

                JLabel emailLabel = new JLabel("email: " + patient.getEmail());
                emailLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                infoPanel.add(emailLabel);

                JLabel phoneLabel = new JLabel("phone: " + patient.getPhoneNumber());
                phoneLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                infoPanel.add(phoneLabel);

                JLabel statusLabel = new JLabel(
                        "insurance: " + (patient.isHaveInsured() ? "Available" : "Not available"));
                statusLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                infoPanel.add(statusLabel);

                JLabel descriptionLabel = new JLabel("description: " + patient.getDescription());
                descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                infoPanel.add(descriptionLabel);

                JButton btnSelect = new JButton("Edit");
                btnSelect.addActionListener(ee -> {
                    frame.dispose(); // Close the JFrame
                    callback.onItemSelected(patient.getId());
                });
                JButton btnRemove = new JButton("Remove");
                btnRemove.addActionListener(ee -> {
                    frame.dispose(); // Close the JFrame
                    callback.onItemSelectedForRemove(patient.getId());
                });
                infoPanel.add(btnSelect);
                infoPanel.add(btnRemove);

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

    public static void showVisitsList(List<Visit> visitsList, String title, ListCallback callback) {
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
        for (int i = 0; i < visitsList.size(); i++) {
            final int index = i; // Create a final variable to hold the value of i
            Visit visit = visitsList.get(i);
            JButton button = new JButton();

            Doctor doctor = context.Doctors().getById(visit.getDoctorId());
            Patient patient = context.Patients().getById(visit.getPatientId());

            button.setText(doctor.getName() + " - " + patient.getName());
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
                JLabel doctorNameLabel = new JLabel(
                        "Doctor Name: " + (doctor.getGender() == "male" ? "mr " : "mrs ") + doctor.getName());
                doctorNameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                infoPanel.add(doctorNameLabel);

                JLabel patientNameLabel = new JLabel(
                        "Patient Name: " + (patient.getGender() == "male" ? "mr " : "mrs ") + patient.getName());
                patientNameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                infoPanel.add(patientNameLabel);

                JLabel dateLabel = new JLabel("date of visit: " + visit.getDate());
                dateLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                infoPanel.add(dateLabel);

                JButton btnRemove = new JButton("Remove");
                btnRemove.addActionListener(ee -> {
                    frame.dispose(); // Close the JFrame
                    callback.onItemSelectedForRemove(visit.getId());
                });
                infoPanel.add(btnRemove);

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

    public static void showVisitsListForSelection(List<Visit> visitsList, String title, ListCallback callback) {
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
        for (int i = 0; i < visitsList.size(); i++) {
            final int index = i; // Create a final variable to hold the value of i
            Visit visit = visitsList.get(i);
            JButton button = new JButton();

            Doctor doctor = context.Doctors().getById(visit.getDoctorId());
            Patient patient = context.Patients().getById(visit.getPatientId());

            button.setText(doctor.getName() + " - " + patient.getName());
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
                JLabel doctorNameLabel = new JLabel(
                        "Doctor Name: " + (doctor.getGender() == "male" ? "mr " : "mrs ") + doctor.getName());
                doctorNameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                infoPanel.add(doctorNameLabel);

                JLabel patientNameLabel = new JLabel(
                        "Patient Name: " + (patient.getGender() == "male" ? "mr " : "mrs ") + patient.getName());
                patientNameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                infoPanel.add(patientNameLabel);

                JLabel dateLabel = new JLabel("date of visit: " + visit.getDate());
                dateLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                infoPanel.add(dateLabel);

                JButton btnSelect = new JButton("Select");
                btnSelect.addActionListener(ee -> {
                    frame.dispose(); // Close the JFrame
                    callback.onItemSelected(visit.getId());
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

    public static void showDoctorsListForSelection(List<Doctor> doctorsList, String title, ListCallback callback) {
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

    public static void showNursesListForSelection(List<Nurse> nursesList, String title, ListCallback callback) {
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

    public static void showPatientsListForSelection(List<Patient> patientsList, String title, ListCallback callback) {
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
        for (int i = 0; i < patientsList.size(); i++) {
            final int index = i; // Create a final variable to hold the value of i
            Patient patient = patientsList.get(i);
            JButton button = new JButton();
            button.setText(patient.getName());
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
                        "Name: " + (patient.getGender() == "male" ? "mr " : "mrs ") + patient.getName());
                nameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                infoPanel.add(nameLabel);

                JLabel emailLabel = new JLabel("email: " + patient.getEmail());
                emailLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                infoPanel.add(emailLabel);

                JLabel phoneLabel = new JLabel("phone: " + patient.getPhoneNumber());
                phoneLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                infoPanel.add(phoneLabel);

                JLabel statusLabel = new JLabel(
                        "insurance: " + (patient.isHaveInsured() ? "Available" : "Not available"));
                statusLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                infoPanel.add(statusLabel);

                JLabel descriptionLabel = new JLabel("description: " + patient.getDescription());
                descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                infoPanel.add(descriptionLabel);

                JButton btnSelect = new JButton("Select");
                btnSelect.addActionListener(ee -> {
                    frame.dispose(); // Close the JFrame
                    callback.onItemSelected(patient.getId());
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

    public static void showHospitalizationList(List<PatientHospitalizationRecord> hospitalizationList, String title,
            ListCallback callback) {
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
        for (int i = 0; i < hospitalizationList.size(); i++) {
            final int index = i; // Create a final variable to hold the value of i
            PatientHospitalizationRecord hospitalization = hospitalizationList.get(i);
            JButton button = new JButton();

            Doctor doctor = context.Doctors().getById(hospitalization.getDoctorId());
            Patient patient = context.Patients().getById(hospitalization.getPatientId());

            button.setText(doctor.getName() + " - " + patient.getName());
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
                JLabel doctorNameLabel = new JLabel(
                        "Doctor Name: " + (doctor.getGender() == "male" ? "mr " : "mrs ") + doctor.getName());
                doctorNameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                infoPanel.add(doctorNameLabel);

                JLabel patientNameLabel = new JLabel(
                        "Patient Name: " + (patient.getGender() == "male" ? "mr " : "mrs ") + patient.getName());
                patientNameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                infoPanel.add(patientNameLabel);

                JLabel startDateLabel = new JLabel("date of hospitalization: " + hospitalization.getStartDate());
                startDateLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                infoPanel.add(startDateLabel);

                if (!hospitalization.getEndDate().isEmpty()) {
                    JLabel endDateLabel = new JLabel("date of hospitalization: " + hospitalization.getEndDate());
                    endDateLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                    infoPanel.add(endDateLabel);
                }

                JLabel statusLabel = new JLabel(
                        "status: " + (hospitalization.getEndDate().isEmpty() ? "active" : "ended"));
                statusLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                infoPanel.add(statusLabel);

                JButton btnRemove = new JButton("Remove");
                btnRemove.addActionListener(ee -> {
                    frame.dispose(); // Close the JFrame
                    callback.onItemSelectedForRemove(hospitalization.getId());
                });
                infoPanel.add(btnRemove);

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

    public static void showPatientPaymentsList(List<PatientPayment> payments, String title, ListCallback callback) {
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
        for (int i = 0; i < payments.size(); i++) {
            final int index = i; // Create a final variable to hold the value of i
            PatientPayment patientPayment = payments.get(i);
            Visit visit = context.Visits().getById(patientPayment.getVisitId());
            JButton button = new JButton();
            button.setText(Long.toString(visit.getVisitPrice()));
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
                JLabel dateLabel = new JLabel(
                        "Visit Time: " + (visit.getDate()));
                dateLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                infoPanel.add(dateLabel);

                Doctor doctor = context.Doctors().getById(visit.getDoctorId());

                JLabel doctorLabel = new JLabel(
                        "Doctor name: " + (doctor.getGender() == "male" ? "Mr. " : "Mrs. ") + doctor.getName());
                doctorLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                infoPanel.add(doctorLabel);

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

    private static void showDoctorResume(Resume resume) {
        List<ArticleForResume> articles = context.ArticleForResumeDbServices()
                .getAllArticleForResumeList(resume.getId());
        List<ExpeirenceForResume> experiences = context.ExperienceForResumeDbServices()
                .getExperiencesForResume(resume.getId());

        // Create a new JFrame to display the doctor's resume
        JFrame frame = new JFrame("Doctor's Resume");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);

        // Create a new JPanel to hold the components
        JPanel panel = new JPanel(new BorderLayout());

        // Create a new JPanel to display the experiences on the right side
        JPanel experiencesPanel = new JPanel();
        experiencesPanel.setLayout(new BoxLayout(experiencesPanel, BoxLayout.Y_AXIS));
        experiencesPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create a new JPanel to display the articles on the left side
        JPanel articlesPanel = new JPanel();
        articlesPanel.setLayout(new BoxLayout(articlesPanel, BoxLayout.Y_AXIS));
        articlesPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Display experiences
        JLabel experiencesLabel = new JLabel("Experiences");
        experiencesLabel.setFont(new Font("Arial", Font.BOLD, 16));
        experiencesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        experiencesPanel.add(experiencesLabel);

        for (ExpeirenceForResume experience : experiences) {
            JLabel experienceLabel = new JLabel("Workplace: " + experience.getNameOfWorkplace());
            experienceLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            experiencesPanel.add(experienceLabel);

            JLabel startDateLabel = new JLabel("Start Date: " + experience.getStartDate());
            startDateLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            experiencesPanel.add(startDateLabel);

            JLabel endDateLabel = new JLabel("End Date: " + experience.getEndDate());
            endDateLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            experiencesPanel.add(endDateLabel);

            experiencesPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add some spacing between experiences
        }

        // Display articles
        JLabel articlesLabel = new JLabel("Articles");
        articlesLabel.setFont(new Font("Arial", Font.BOLD, 16));
        articlesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        articlesPanel.add(articlesLabel);

        for (ArticleForResume article : articles) {
            JLabel articleLabel = new JLabel("Article Name: " + article.getName());
            articleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            articlesPanel.add(articleLabel);

            JLabel impactFactorLabel = new JLabel("Impact Factor: " + article.getImpactFactor());
            impactFactorLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            articlesPanel.add(impactFactorLabel);

            articlesPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add some spacing between articles
        }

        // Add the experiences panel to the right side and the articles panel to the
        // left side
        panel.add(experiencesPanel, BorderLayout.EAST);
        panel.add(articlesPanel, BorderLayout.WEST);

        // Add the panel to the frame
        frame.add(panel);

        // Show the frame
        frame.setVisible(true);
    }

    public static void showDoctorsResumeListForAcception(List<Doctor> doctorsList, String title,
            ListCallback callback) {

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

                JButton btnResume = new JButton("Resume");
                btnResume.addActionListener(ee -> {
                    List<Resume> resumes = context.Resumes().getResumesForDoctor(doctor.getId());
                    if (resumes.size() > 0) {
                        showDoctorResume(resumes.get(0));
                    }
                });

                JButton btnSelect = new JButton("Accept");
                btnSelect.addActionListener(ee -> {
                    frame.dispose(); // Close the JFrame
                    callback.onItemSelected(doctor.getId());
                });
                JButton btnRemove = new JButton("Decline");
                btnRemove.addActionListener(ee -> {
                    frame.dispose(); // Close the JFrame
                    callback.onItemSelectedForRemove(doctor.getId());
                });
                infoPanel.add(btnSelect);
                infoPanel.add(btnRemove);
                infoPanel.add(btnResume);

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

    public static void showVisitsListForAcception(List<Visit> visitsList, String title, ListCallback callback) {
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
        for (int i = 0; i < visitsList.size(); i++) {
            final int index = i; // Create a final variable to hold the value of i
            Visit visit = visitsList.get(i);
            JButton button = new JButton();

            Doctor doctor = context.Doctors().getById(visit.getDoctorId());
            Patient patient = context.Patients().getById(visit.getPatientId());

            button.setText(doctor.getName() + " - " + patient.getName());
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
                JLabel doctorNameLabel = new JLabel(
                        "Doctor Name: " + (doctor.getGender() == "male" ? "mr " : "mrs ") + doctor.getName());
                doctorNameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                infoPanel.add(doctorNameLabel);

                JLabel patientNameLabel = new JLabel(
                        "Patient Name: " + (patient.getGender() == "male" ? "mr " : "mrs ") + patient.getName());
                patientNameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                infoPanel.add(patientNameLabel);

                JLabel dateLabel = new JLabel("date of visit: " + visit.getDate());
                dateLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                infoPanel.add(dateLabel);

                JButton btnRemove = new JButton("Decline");
                btnRemove.addActionListener(ee -> {
                    frame.dispose(); // Close the JFrame
                    callback.onItemSelectedForRemove(visit.getId());
                });
                infoPanel.add(btnRemove);

                JButton btnSelect = new JButton("Accept");
                btnSelect.addActionListener(ee -> {
                    frame.dispose(); // Close the JFrame
                    callback.onItemSelected(visit.getId());
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

    public static void showAdminProfile(Admin admin, List<String> menuItems, MenuCallback callback) {
        // TODO
    }

    public static void showDoctorProfile(Doctor doctor, List<String> menuItems, MenuCallback callback) {
        // TODO
    }

    public static void showNurseProfile(Nurse nurse, List<String> menuItems, MenuCallback callback) {
        // TODO
    }

    public static void showPersonelProfile(Personel personel, List<String> menuItems, MenuCallback callback) {
        // TODO
    }

    public static void getUsernamePassFromUser(getUsernamePassCallBack callBack) {
        // Create a new JFrame to display the window
        JFrame frame = new JFrame("Enter username password");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 150);

        // Create a new JPanel to hold the components
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create a new JLabel and two JTextFields for username and password
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();

        // Create a new JButton for submitting the credentials
        JButton submitButton = new JButton("Submit");

        // Create a new JPanel to hold the username components
        JPanel usernamePanel = new JPanel(new BorderLayout());
        usernamePanel.add(usernameLabel, BorderLayout.NORTH);
        usernamePanel.add(usernameField, BorderLayout.CENTER);

        // Create a new JPanel to hold the password components
        JPanel passwordPanel = new JPanel(new BorderLayout());
        passwordPanel.add(passwordLabel, BorderLayout.NORTH);
        passwordPanel.add(passwordField, BorderLayout.CENTER);

        // Add the username panel, password panel, and submit button to the main panel
        panel.add(usernamePanel, BorderLayout.NORTH);
        panel.add(passwordPanel, BorderLayout.CENTER);
        panel.add(submitButton, BorderLayout.SOUTH);

        // Add the main panel to the frame
        frame.add(panel);

        // Add an ActionListener to the submit button
        submitButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            frame.dispose();
            callBack.onSubmit(username, password);
        });

        // Show the frame
        frame.setVisible(true);

    }

    public interface getUsernamePassCallBack {
        void onSubmit(String username, String password);
    }

    public static void showAlert(String title, String message) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void getEmailFromUser(getEmailCllBack callBack) {
        // Create a new JFrame to display the window
        JFrame frame = new JFrame("Enter email");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 150);

        // Create a new JPanel to hold the components
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create a new JLabel and two JTextFields for username and password
        JLabel emailLable = new JLabel("email:");
        JTextField emailField = new JTextField();

        // Create a new JButton for submitting the credentials
        JButton submitButton = new JButton("Submit");

        // Create a new JPanel to hold the username components
        JPanel emailPanel = new JPanel(new BorderLayout());
        emailPanel.add(emailLable, BorderLayout.NORTH);
        emailPanel.add(emailField, BorderLayout.CENTER);

        // Add the username panel, password panel, and submit button to the main panel
        panel.add(emailPanel, BorderLayout.NORTH);
        panel.add(submitButton, BorderLayout.SOUTH);

        // Add the main panel to the frame
        frame.add(panel);

        // Add an ActionListener to the submit button
        submitButton.addActionListener(e -> {
            String email = emailField.getText();
            frame.dispose();
            callBack.onSubmit(email);
        });

        // Show the frame
        frame.setVisible(true);
    }

    public interface getEmailCllBack {
        void onSubmit(String email);
    }

    public static void getDoctorFromUser(CreateDoctorCallBack createDoctorCallBack) {
        // Create a new JFrame to hold the input fields
        JFrame frame = new JFrame("Doctor Information");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Width, Height);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        // Create the input fields and labels
        JTextField expertiseField = new JTextField();
        expertiseField.setPreferredSize(new Dimension(300, expertiseField.getPreferredSize().height));
        JLabel expertiseLabel = new JLabel("Expertise:");
        JPanel expertisePanel = new JPanel();
        expertisePanel.add(expertiseLabel);
        expertisePanel.add(expertiseField);

        JTextField salaryField = new JTextField();
        salaryField.setPreferredSize(new Dimension(300, salaryField.getPreferredSize().height));
        JLabel salaryLabel = new JLabel("Salary:");
        JPanel salaryPanel = new JPanel();
        salaryPanel.add(salaryLabel);
        salaryPanel.add(salaryField);

        JTextField shiftField = new JTextField();
        shiftField.setPreferredSize(new Dimension(300, shiftField.getPreferredSize().height));
        JLabel shiftLabel = new JLabel("Shift:");
        JPanel shiftPanel = new JPanel();
        shiftPanel.add(shiftLabel);
        shiftPanel.add(shiftField);

        JTextField usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(300, usernameField.getPreferredSize().height));
        JLabel usernameLabel = new JLabel("Username:");
        JPanel usernamePanel = new JPanel();
        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameField);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(300, passwordField.getPreferredSize().height));
        JLabel passwordLabel = new JLabel("Password:");
        JPanel passwordPanel = new JPanel();
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);

        JTextField nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(300, nameField.getPreferredSize().height));
        JLabel nameLabel = new JLabel("Name:");
        JPanel namePanel = new JPanel();
        namePanel.add(nameLabel);
        namePanel.add(nameField);

        JTextField genderField = new JTextField();
        genderField.setPreferredSize(new Dimension(300, genderField.getPreferredSize().height));
        JLabel genderLabel = new JLabel("Gender:");
        JPanel genderPanel = new JPanel();
        genderPanel.add(genderLabel);
        genderPanel.add(genderField);

        JTextField phoneNumberField = new JTextField();
        phoneNumberField.setPreferredSize(new Dimension(300, phoneNumberField.getPreferredSize().height));
        JLabel phoneNumberLabel = new JLabel("Phone Number:");
        JPanel phoneNumberPanel = new JPanel();
        phoneNumberPanel.add(phoneNumberLabel);
        phoneNumberPanel.add(phoneNumberField);

        JTextField emailField = new JTextField();
        emailField.setPreferredSize(new Dimension(300, emailField.getPreferredSize().height));
        JLabel emailLabel = new JLabel("Email:");
        JPanel emailPanel = new JPanel();
        emailPanel.add(emailLabel);
        emailPanel.add(emailField);

        JTextField ageField = new JTextField();
        ageField.setPreferredSize(new Dimension(300, ageField.getPreferredSize().height));
        JLabel ageLabel = new JLabel("Age:");
        JPanel agePanel = new JPanel();
        agePanel.add(ageLabel);
        agePanel.add(ageField);

        // Add the input fields and labels to the JFrame
        frame.add(Box.createVerticalStrut(10));
        frame.add(expertisePanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(salaryPanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(shiftPanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(usernamePanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(passwordPanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(namePanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(genderPanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(phoneNumberPanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(emailPanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(agePanel);

        // Create a button for the user to submit their input
        JButton submitButton = new JButton("Submit");
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the input values from the fields
                String expertise = expertiseField.getText();
                long salary = Long.parseLong(salaryField.getText());
                String shift = shiftField.getText();
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String name = nameField.getText();
                String gender = genderField.getText();
                String phoneNumber = phoneNumberField.getText();
                String email = emailField.getText();
                int age = Integer.parseInt(ageField.getText());

                // Create a new Doctor object with the input values
                Doctor doctor = new Doctor();
                doctor.setExpertise(expertise);
                doctor.setSalary(salary);
                doctor.setShift(shift);
                doctor.setUsername(username);
                doctor.setPassword(password);
                doctor.setName(name);
                doctor.setGender(gender);
                doctor.setPhoneNumber(phoneNumber);
                doctor.setEmail(email);
                doctor.setAge(age);

                // Close the JFrame
                frame.dispose();

                // Call the callback interface with the created Doctor object
                createDoctorCallBack.onDoctorCreated(doctor);
            }
        });

        // Add the submit button to the JFrame
        frame.add(Box.createVerticalStrut(20));
        frame.add(submitButton);
        frame.add(Box.createVerticalStrut(10));

        // Set the JFrame to visible
        frame.setVisible(true);
    }

    public static void getDoctorFromUserForEdit(CreateDoctorCallBack createDoctorCallBack, Doctor oldDoctor) {
        // Create a new JFrame to hold the input fields
        JFrame frame = new JFrame("Edit Doctor " + oldDoctor.getUsername());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Width, Height);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        // Create the input fields and labels
        JTextField expertiseField = new JTextField();
        expertiseField.setPreferredSize(new Dimension(300, expertiseField.getPreferredSize().height));
        JLabel expertiseLabel = new JLabel("Expertise:");
        JPanel expertisePanel = new JPanel();
        expertiseField.setText(oldDoctor.getExpertise());
        expertisePanel.add(expertiseLabel);
        expertisePanel.add(expertiseField);

        JTextField salaryField = new JTextField();
        salaryField.setPreferredSize(new Dimension(300, salaryField.getPreferredSize().height));
        JLabel salaryLabel = new JLabel("Salary:");
        JPanel salaryPanel = new JPanel();
        salaryField.setText(Long.toString(oldDoctor.getSalary()));
        salaryPanel.add(salaryLabel);
        salaryPanel.add(salaryField);

        JTextField shiftField = new JTextField();
        shiftField.setPreferredSize(new Dimension(300, shiftField.getPreferredSize().height));
        JLabel shiftLabel = new JLabel("Shift:");
        JPanel shiftPanel = new JPanel();
        shiftField.setText(oldDoctor.getShift());
        shiftPanel.add(shiftLabel);
        shiftPanel.add(shiftField);

        JTextField usernameField = new JTextField();
        usernameField.setText(oldDoctor.getUsername());

        JTextField nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(300, nameField.getPreferredSize().height));
        JLabel nameLabel = new JLabel("Name:");
        JPanel namePanel = new JPanel();
        nameField.setText(oldDoctor.getName());
        namePanel.add(nameLabel);
        namePanel.add(nameField);

        JTextField genderField = new JTextField();
        genderField.setPreferredSize(new Dimension(300, genderField.getPreferredSize().height));
        JLabel genderLabel = new JLabel("Gender:");
        JPanel genderPanel = new JPanel();
        genderField.setText(oldDoctor.getGender());
        genderPanel.add(genderLabel);
        genderPanel.add(genderField);

        JTextField phoneNumberField = new JTextField();
        phoneNumberField.setPreferredSize(new Dimension(300, phoneNumberField.getPreferredSize().height));
        JLabel phoneNumberLabel = new JLabel("Phone Number:");
        JPanel phoneNumberPanel = new JPanel();
        phoneNumberField.setText(oldDoctor.getPhoneNumber());
        phoneNumberPanel.add(phoneNumberLabel);
        phoneNumberPanel.add(phoneNumberField);

        JTextField emailField = new JTextField();
        emailField.setPreferredSize(new Dimension(300, emailField.getPreferredSize().height));
        JLabel emailLabel = new JLabel("Email:");
        JPanel emailPanel = new JPanel();
        emailField.setText(oldDoctor.getEmail());
        emailPanel.add(emailLabel);
        emailPanel.add(emailField);

        JTextField ageField = new JTextField();
        ageField.setPreferredSize(new Dimension(300, ageField.getPreferredSize().height));
        JLabel ageLabel = new JLabel("Age:");
        JPanel agePanel = new JPanel();
        ageField.setText(Integer.toString(oldDoctor.getAge()));
        agePanel.add(ageLabel);
        agePanel.add(ageField);

        // Add the input fields and labels to the JFrame
        frame.add(Box.createVerticalStrut(10));
        frame.add(expertisePanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(salaryPanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(shiftPanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(namePanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(genderPanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(phoneNumberPanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(emailPanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(agePanel);

        // Create a button for the user to submit their input
        JButton submitButton = new JButton("Submit");
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the input values from the fields
                String expertise = expertiseField.getText();
                long salary = Long.parseLong(salaryField.getText());
                String shift = shiftField.getText();
                String username = usernameField.getText();
                String name = nameField.getText();
                String gender = genderField.getText();
                String phoneNumber = phoneNumberField.getText();
                String email = emailField.getText();
                int age = Integer.parseInt(ageField.getText());

                // Create a new Doctor object with the input values
                Doctor doctor = new Doctor();
                doctor.setExpertise(expertise);
                doctor.setSalary(salary);
                doctor.setShift(shift);
                doctor.setUsername(username);
                doctor.setPassword(oldDoctor.getPassword());
                doctor.setName(name);
                doctor.setGender(gender);
                doctor.setPhoneNumber(phoneNumber);
                doctor.setEmail(email);
                doctor.setAge(age);
                doctor.setId(oldDoctor.getId());

                // Close the JFrame
                frame.dispose();

                // Call the callback interface with the created Doctor object
                createDoctorCallBack.onDoctorCreated(doctor);
            }
        });

        // Add the submit button to the JFrame
        frame.add(Box.createVerticalStrut(20));
        frame.add(submitButton);
        frame.add(Box.createVerticalStrut(10));

        // Set the JFrame to visible
        frame.setVisible(true);
    }

    public static void getNurseFromUser(CreateNurseCallBack createNurseCallBack) {
        // Create a new JFrame to hold the input fields
        JFrame frame = new JFrame("Nurse Information");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Width, Height);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        // Create the input fields and labels
        JTextField TypeField = new JTextField();
        TypeField.setPreferredSize(new Dimension(300, TypeField.getPreferredSize().height));
        JLabel typeLabel = new JLabel("Type:");
        JPanel typePanel = new JPanel();
        typePanel.add(typeLabel);
        typePanel.add(TypeField);

        JTextField placeOfWorkField = new JTextField();
        placeOfWorkField.setPreferredSize(new Dimension(300, placeOfWorkField.getPreferredSize().height));
        JLabel placeOfWordkLabel = new JLabel("place of work:");
        JPanel placeOfWorkPanel = new JPanel();
        placeOfWorkPanel.add(placeOfWordkLabel);
        placeOfWorkPanel.add(placeOfWorkField);

        JTextField salaryField = new JTextField();
        salaryField.setPreferredSize(new Dimension(300, salaryField.getPreferredSize().height));
        JLabel salaryLabel = new JLabel("Salary:");
        JPanel salaryPanel = new JPanel();
        salaryPanel.add(salaryLabel);
        salaryPanel.add(salaryField);

        JTextField shiftField = new JTextField();
        shiftField.setPreferredSize(new Dimension(300, shiftField.getPreferredSize().height));
        JLabel shiftLabel = new JLabel("Shift:");
        JPanel shiftPanel = new JPanel();
        shiftPanel.add(shiftLabel);
        shiftPanel.add(shiftField);

        JTextField usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(300, usernameField.getPreferredSize().height));
        JLabel usernameLabel = new JLabel("Username:");
        JPanel usernamePanel = new JPanel();
        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameField);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(300, passwordField.getPreferredSize().height));
        JLabel passwordLabel = new JLabel("Password:");
        JPanel passwordPanel = new JPanel();
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);

        JTextField nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(300, nameField.getPreferredSize().height));
        JLabel nameLabel = new JLabel("Name:");
        JPanel namePanel = new JPanel();
        namePanel.add(nameLabel);
        namePanel.add(nameField);

        JTextField genderField = new JTextField();
        genderField.setPreferredSize(new Dimension(300, genderField.getPreferredSize().height));
        JLabel genderLabel = new JLabel("Gender:");
        JPanel genderPanel = new JPanel();
        genderPanel.add(genderLabel);
        genderPanel.add(genderField);

        JTextField phoneNumberField = new JTextField();
        phoneNumberField.setPreferredSize(new Dimension(300, phoneNumberField.getPreferredSize().height));
        JLabel phoneNumberLabel = new JLabel("Phone Number:");
        JPanel phoneNumberPanel = new JPanel();
        phoneNumberPanel.add(phoneNumberLabel);
        phoneNumberPanel.add(phoneNumberField);

        JTextField emailField = new JTextField();
        emailField.setPreferredSize(new Dimension(300, emailField.getPreferredSize().height));
        JLabel emailLabel = new JLabel("Email:");
        JPanel emailPanel = new JPanel();
        emailPanel.add(emailLabel);
        emailPanel.add(emailField);

        JTextField ageField = new JTextField();
        ageField.setPreferredSize(new Dimension(300, ageField.getPreferredSize().height));
        JLabel ageLabel = new JLabel("Age:");
        JPanel agePanel = new JPanel();
        agePanel.add(ageLabel);
        agePanel.add(ageField);

        // Add the input fields and labels to the JFrame
        frame.add(Box.createVerticalStrut(10));
        frame.add(typePanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(placeOfWorkPanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(salaryPanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(shiftPanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(usernamePanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(passwordPanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(namePanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(genderPanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(phoneNumberPanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(emailPanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(agePanel);

        // Create a button for the user to submit their input
        JButton submitButton = new JButton("Submit");
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the input values from the fields
                String type = TypeField.getText();
                String placeOfWork = placeOfWorkField.getText();
                long salary = Long.parseLong(salaryField.getText());
                String shift = shiftField.getText();
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String name = nameField.getText();
                String gender = genderField.getText();
                String phoneNumber = phoneNumberField.getText();
                String email = emailField.getText();
                int age = Integer.parseInt(ageField.getText());

                // Create a new Doctor object with the input values
                Nurse nurse = new Nurse();
                nurse.setType(type);
                nurse.setPlaceOfWork(placeOfWork);
                nurse.setSalary(salary);
                nurse.setShift(shift);
                nurse.setUsername(username);
                nurse.setPassword(password);
                nurse.setName(name);
                nurse.setGender(gender);
                nurse.setPhoneNumber(phoneNumber);
                nurse.setEmail(email);
                nurse.setAge(age);

                // Close the JFrame
                frame.dispose();

                // Call the callback interface with the created Doctor object
                createNurseCallBack.onNurseCreated(nurse);
            }
        });

        // Add the submit button to the JFrame
        frame.add(Box.createVerticalStrut(20));
        frame.add(submitButton);
        frame.add(Box.createVerticalStrut(10));

        // Set the JFrame to visible
        frame.setVisible(true);
    }

    public static void getNurseFromUserForEdit(CreateNurseCallBack createNurseCallBack, Nurse oldNurse) {
        // Create a new JFrame to hold the input fields
        JFrame frame = new JFrame("Edit Nurse: " + oldNurse.getUsername());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Width, Height);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        // Create the input fields and labels
        JTextField TypeField = new JTextField();
        TypeField.setPreferredSize(new Dimension(300, TypeField.getPreferredSize().height));
        JLabel typeLabel = new JLabel("Type:");
        JPanel typePanel = new JPanel();
        TypeField.setText(oldNurse.getType());
        typePanel.add(typeLabel);
        typePanel.add(TypeField);

        JTextField placeOfWorkField = new JTextField();
        placeOfWorkField.setPreferredSize(new Dimension(300, placeOfWorkField.getPreferredSize().height));
        JLabel placeOfWordkLabel = new JLabel("place of work:");
        JPanel placeOfWorkPanel = new JPanel();
        placeOfWorkField.setText(oldNurse.getPlaceOfWork());
        placeOfWorkPanel.add(placeOfWordkLabel);
        placeOfWorkPanel.add(placeOfWorkField);

        JTextField salaryField = new JTextField();
        salaryField.setPreferredSize(new Dimension(300, salaryField.getPreferredSize().height));
        JLabel salaryLabel = new JLabel("Salary:");
        JPanel salaryPanel = new JPanel();
        salaryField.setText(Long.toString(oldNurse.getSalary()));
        salaryPanel.add(salaryLabel);
        salaryPanel.add(salaryField);

        JTextField shiftField = new JTextField();
        shiftField.setPreferredSize(new Dimension(300, shiftField.getPreferredSize().height));
        JLabel shiftLabel = new JLabel("Shift:");
        JPanel shiftPanel = new JPanel();
        shiftField.setText(oldNurse.getShift());
        shiftPanel.add(shiftLabel);
        shiftPanel.add(shiftField);

        JTextField usernameField = new JTextField();
        usernameField.setText(oldNurse.getUsername());

        JTextField nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(300, nameField.getPreferredSize().height));
        JLabel nameLabel = new JLabel("Name:");
        JPanel namePanel = new JPanel();
        nameField.setText(oldNurse.getName());
        namePanel.add(nameLabel);
        namePanel.add(nameField);

        JTextField genderField = new JTextField();
        genderField.setPreferredSize(new Dimension(300, genderField.getPreferredSize().height));
        JLabel genderLabel = new JLabel("Gender:");
        JPanel genderPanel = new JPanel();
        genderField.setText(oldNurse.getGender());
        genderPanel.add(genderLabel);
        genderPanel.add(genderField);

        JTextField phoneNumberField = new JTextField();
        phoneNumberField.setPreferredSize(new Dimension(300, phoneNumberField.getPreferredSize().height));
        JLabel phoneNumberLabel = new JLabel("Phone Number:");
        JPanel phoneNumberPanel = new JPanel();
        phoneNumberField.setText(oldNurse.getPhoneNumber());
        phoneNumberPanel.add(phoneNumberLabel);
        phoneNumberPanel.add(phoneNumberField);

        JTextField emailField = new JTextField();
        emailField.setPreferredSize(new Dimension(300, emailField.getPreferredSize().height));
        JLabel emailLabel = new JLabel("Email:");
        JPanel emailPanel = new JPanel();
        emailField.setText(oldNurse.getEmail());
        emailPanel.add(emailLabel);
        emailPanel.add(emailField);

        JTextField ageField = new JTextField();
        ageField.setPreferredSize(new Dimension(300, ageField.getPreferredSize().height));
        JLabel ageLabel = new JLabel("Age:");
        JPanel agePanel = new JPanel();
        ageField.setText(Integer.toString(oldNurse.getAge()));
        agePanel.add(ageLabel);
        agePanel.add(ageField);

        // Add the input fields and labels to the JFrame
        frame.add(Box.createVerticalStrut(10));
        frame.add(typePanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(placeOfWorkPanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(salaryPanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(shiftPanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(namePanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(genderPanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(phoneNumberPanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(emailPanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(agePanel);

        // Create a button for the user to submit their input
        JButton submitButton = new JButton("Submit");
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the input values from the fields
                String type = TypeField.getText();
                String placeOfWork = placeOfWorkField.getText();
                long salary = Long.parseLong(salaryField.getText());
                String shift = shiftField.getText();
                String username = usernameField.getText();
                String name = nameField.getText();
                String gender = genderField.getText();
                String phoneNumber = phoneNumberField.getText();
                String email = emailField.getText();
                int age = Integer.parseInt(ageField.getText());

                // Create a new Doctor object with the input values
                Nurse nurse = new Nurse();
                nurse.setType(type);
                nurse.setPlaceOfWork(placeOfWork);
                nurse.setSalary(salary);
                nurse.setShift(shift);
                nurse.setUsername(username);
                nurse.setName(name);
                nurse.setGender(gender);
                nurse.setPhoneNumber(phoneNumber);
                nurse.setEmail(email);
                nurse.setAge(age);
                nurse.setPassword(oldNurse.getPassword());
                nurse.setId(oldNurse.getId());

                // Close the JFrame
                frame.dispose();

                // Call the callback interface with the created Doctor object
                createNurseCallBack.onNurseCreated(nurse);
            }
        });

        // Add the submit button to the JFrame
        frame.add(Box.createVerticalStrut(20));
        frame.add(submitButton);
        frame.add(Box.createVerticalStrut(10));

        // Set the JFrame to visible
        frame.setVisible(true);
    }

    public static void getPersonelFromUser(CreatePersonelCallBack createPersonelCallBack) {
        // Create a new JFrame to hold the input fields
        JFrame frame = new JFrame("Personel Information");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Width, Height);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        // Create the input fields and labels
        JTextField salaryField = new JTextField();
        salaryField.setPreferredSize(new Dimension(300, salaryField.getPreferredSize().height));
        JLabel salaryLabel = new JLabel("Salary:");
        JPanel salaryPanel = new JPanel();
        salaryPanel.add(salaryLabel);
        salaryPanel.add(salaryField);

        JTextField shiftField = new JTextField();
        shiftField.setPreferredSize(new Dimension(300, shiftField.getPreferredSize().height));
        JLabel shiftLabel = new JLabel("Shift:");
        JPanel shiftPanel = new JPanel();
        shiftPanel.add(shiftLabel);
        shiftPanel.add(shiftField);

        JTextField usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(300, usernameField.getPreferredSize().height));
        JLabel usernameLabel = new JLabel("Username:");
        JPanel usernamePanel = new JPanel();
        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameField);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(300, passwordField.getPreferredSize().height));
        JLabel passwordLabel = new JLabel("Password:");
        JPanel passwordPanel = new JPanel();
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);

        JTextField nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(300, nameField.getPreferredSize().height));
        JLabel nameLabel = new JLabel("Name:");
        JPanel namePanel = new JPanel();
        namePanel.add(nameLabel);
        namePanel.add(nameField);

        JTextField genderField = new JTextField();
        genderField.setPreferredSize(new Dimension(300, genderField.getPreferredSize().height));
        JLabel genderLabel = new JLabel("Gender:");
        JPanel genderPanel = new JPanel();
        genderPanel.add(genderLabel);
        genderPanel.add(genderField);

        JTextField phoneNumberField = new JTextField();
        phoneNumberField.setPreferredSize(new Dimension(300, phoneNumberField.getPreferredSize().height));
        JLabel phoneNumberLabel = new JLabel("Phone Number:");
        JPanel phoneNumberPanel = new JPanel();
        phoneNumberPanel.add(phoneNumberLabel);
        phoneNumberPanel.add(phoneNumberField);

        JTextField emailField = new JTextField();
        emailField.setPreferredSize(new Dimension(300, emailField.getPreferredSize().height));
        JLabel emailLabel = new JLabel("Email:");
        JPanel emailPanel = new JPanel();
        emailPanel.add(emailLabel);
        emailPanel.add(emailField);

        JTextField ageField = new JTextField();
        ageField.setPreferredSize(new Dimension(300, ageField.getPreferredSize().height));
        JLabel ageLabel = new JLabel("Age:");
        JPanel agePanel = new JPanel();
        agePanel.add(ageLabel);
        agePanel.add(ageField);

        // Add the input fields and labels to the JFrame
        frame.add(Box.createVerticalStrut(10));
        frame.add(salaryPanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(shiftPanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(usernamePanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(passwordPanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(namePanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(genderPanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(phoneNumberPanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(emailPanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(agePanel);

        // Create a button for the user to submit their input
        JButton submitButton = new JButton("Submit");
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the input values from the fields
                long salary = Long.parseLong(salaryField.getText());
                String shift = shiftField.getText();
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String name = nameField.getText();
                String gender = genderField.getText();
                String phoneNumber = phoneNumberField.getText();
                String email = emailField.getText();
                int age = Integer.parseInt(ageField.getText());

                // Create a new Doctor object with the input values
                Personel nurse = new Personel();
                nurse.setSalary(salary);
                nurse.setShift(shift);
                nurse.setUsername(username);
                nurse.setPassword(password);
                nurse.setName(name);
                nurse.setGender(gender);
                nurse.setPhoneNumber(phoneNumber);
                nurse.setEmail(email);
                nurse.setAge(age);

                // Close the JFrame
                frame.dispose();

                // Call the callback interface with the created Doctor object
                createPersonelCallBack.onPersonelCreated(nurse);
            }
        });

        // Add the submit button to the JFrame
        frame.add(Box.createVerticalStrut(20));
        frame.add(submitButton);
        frame.add(Box.createVerticalStrut(10));

        // Set the JFrame to visible
        frame.setVisible(true);
    }

    public static void getPersonelFromUserForEdit(CreatePersonelCallBack createPersonelCallBack, Personel oldPersonel) {
        // Create a new JFrame to hold the input fields
        JFrame frame = new JFrame("Edit Personel: " + oldPersonel.getUsername());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Width, Height);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        // Create the input fields and labels
        JTextField salaryField = new JTextField();
        salaryField.setPreferredSize(new Dimension(300, salaryField.getPreferredSize().height));
        JLabel salaryLabel = new JLabel("Salary:");
        JPanel salaryPanel = new JPanel();
        salaryField.setText(Long.toString(oldPersonel.getSalary()));
        salaryPanel.add(salaryLabel);
        salaryPanel.add(salaryField);

        JTextField shiftField = new JTextField();
        shiftField.setPreferredSize(new Dimension(300, shiftField.getPreferredSize().height));
        JLabel shiftLabel = new JLabel("Shift:");
        JPanel shiftPanel = new JPanel();
        shiftField.setText(oldPersonel.getShift());
        shiftPanel.add(shiftLabel);
        shiftPanel.add(shiftField);

        JTextField usernameField = new JTextField();
        usernameField.setText(oldPersonel.getUsername());

        JTextField nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(300, nameField.getPreferredSize().height));
        JLabel nameLabel = new JLabel("Name:");
        JPanel namePanel = new JPanel();
        nameField.setText(oldPersonel.getName());
        namePanel.add(nameLabel);
        namePanel.add(nameField);

        JTextField genderField = new JTextField();
        genderField.setPreferredSize(new Dimension(300, genderField.getPreferredSize().height));
        JLabel genderLabel = new JLabel("Gender:");
        JPanel genderPanel = new JPanel();
        genderField.setText(oldPersonel.getGender());
        genderPanel.add(genderLabel);
        genderPanel.add(genderField);

        JTextField phoneNumberField = new JTextField();
        phoneNumberField.setPreferredSize(new Dimension(300, phoneNumberField.getPreferredSize().height));
        JLabel phoneNumberLabel = new JLabel("Phone Number:");
        JPanel phoneNumberPanel = new JPanel();
        phoneNumberField.setText(oldPersonel.getPhoneNumber());
        phoneNumberPanel.add(phoneNumberLabel);
        phoneNumberPanel.add(phoneNumberField);

        JTextField emailField = new JTextField();
        emailField.setPreferredSize(new Dimension(300, emailField.getPreferredSize().height));
        JLabel emailLabel = new JLabel("Email:");
        JPanel emailPanel = new JPanel();
        emailField.setText(oldPersonel.getEmail());
        emailPanel.add(emailLabel);
        emailPanel.add(emailField);

        JTextField ageField = new JTextField();
        ageField.setPreferredSize(new Dimension(300, ageField.getPreferredSize().height));
        JLabel ageLabel = new JLabel("Age:");
        JPanel agePanel = new JPanel();
        ageField.setText(Integer.toString(oldPersonel.getAge()));
        agePanel.add(ageLabel);
        agePanel.add(ageField);

        // Add the input fields and labels to the JFrame
        frame.add(Box.createVerticalStrut(10));
        frame.add(salaryPanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(shiftPanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(namePanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(genderPanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(phoneNumberPanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(emailPanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(agePanel);

        // Create a button for the user to submit their input
        JButton submitButton = new JButton("Submit");
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the input values from the fields
                long salary = Long.parseLong(salaryField.getText());
                String shift = shiftField.getText();
                String username = usernameField.getText();
                String name = nameField.getText();
                String gender = genderField.getText();
                String phoneNumber = phoneNumberField.getText();
                String email = emailField.getText();
                int age = Integer.parseInt(ageField.getText());

                // Create a new Doctor object with the input values
                Personel personel = new Personel();
                personel.setSalary(salary);
                personel.setShift(shift);
                personel.setUsername(username);
                personel.setPassword(oldPersonel.getPassword());
                personel.setName(name);
                personel.setGender(gender);
                personel.setPhoneNumber(phoneNumber);
                personel.setEmail(email);
                personel.setAge(age);
                personel.setId(oldPersonel.getId());

                // Close the JFrame
                frame.dispose();

                // Call the callback interface with the created Doctor object
                createPersonelCallBack.onPersonelCreated(personel);
            }
        });

        // Add the submit button to the JFrame
        frame.add(Box.createVerticalStrut(20));
        frame.add(submitButton);
        frame.add(Box.createVerticalStrut(10));

        // Set the JFrame to visible
        frame.setVisible(true);
    }

    public static void getPatientFromUser(CreatePatientCallBack createPatientCallBack) {
        // Create a new JFrame to hold the input fields
        JFrame frame = new JFrame("Patient Information");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Width, Height);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        // Create the input fields and labels
        JTextField nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(300, nameField.getPreferredSize().height));
        JLabel nameLabel = new JLabel("Name:");
        JPanel namePanel = new JPanel();
        namePanel.add(nameLabel);
        namePanel.add(nameField);

        JTextField genderField = new JTextField();
        genderField.setPreferredSize(new Dimension(300, genderField.getPreferredSize().height));
        JLabel genderLabel = new JLabel("Gender:");
        JPanel genderPanel = new JPanel();
        genderPanel.add(genderLabel);
        genderPanel.add(genderField);

        JTextField phoneNumberField = new JTextField();
        phoneNumberField.setPreferredSize(new Dimension(300, phoneNumberField.getPreferredSize().height));
        JLabel phoneNumberLabel = new JLabel("Phone Number:");
        JPanel phoneNumberPanel = new JPanel();
        phoneNumberPanel.add(phoneNumberLabel);
        phoneNumberPanel.add(phoneNumberField);

        JTextField emailField = new JTextField();
        emailField.setPreferredSize(new Dimension(300, emailField.getPreferredSize().height));
        JLabel emailLabel = new JLabel("Email:");
        JPanel emailPanel = new JPanel();
        emailPanel.add(emailLabel);
        emailPanel.add(emailField);

        JTextField ageField = new JTextField();
        ageField.setPreferredSize(new Dimension(300, ageField.getPreferredSize().height));
        JLabel ageLabel = new JLabel("Age:");
        JPanel agePanel = new JPanel();
        agePanel.add(ageLabel);
        agePanel.add(ageField);

        JTextField descriptionField = new JTextField();
        descriptionField.setPreferredSize(new Dimension(300, descriptionField.getPreferredSize().height));
        JLabel descriptionLabel = new JLabel("Description:");
        JPanel descriptionPanel = new JPanel();
        descriptionPanel.add(descriptionLabel);
        descriptionPanel.add(descriptionField);

        JCheckBox insuredBox = new JCheckBox();
        insuredBox.setPreferredSize(new Dimension(300, descriptionField.getPreferredSize().height));
        JLabel insuredLabel = new JLabel("Have insured:");
        JPanel insuredPanel = new JPanel();
        insuredPanel.add(insuredLabel);
        insuredPanel.add(insuredBox);

        // Add the input fields and labels to the JFrame
        frame.add(Box.createVerticalStrut(10));
        frame.add(namePanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(genderPanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(phoneNumberPanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(emailPanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(agePanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(descriptionPanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(insuredPanel);

        // Create a button for the user to submit their input
        JButton submitButton = new JButton("Submit");
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the input values from the fields
                String name = nameField.getText();
                String gender = genderField.getText();
                String phoneNumber = phoneNumberField.getText();
                String email = emailField.getText();
                int age = Integer.parseInt(ageField.getText());
                String description = descriptionField.getText();
                Boolean insurance = insuredBox.isSelected();

                // Create a new Doctor object with the input values
                Patient patient = new Patient();
                patient.setName(name);
                patient.setGender(gender);
                patient.setPhoneNumber(phoneNumber);
                patient.setEmail(email);
                patient.setAge(age);
                patient.setDescription(description);
                patient.setHaveInsured(insurance);

                // Close the JFrame
                frame.dispose();

                // Call the callback interface with the created Doctor object
                createPatientCallBack.onPatientCreated(patient);
            }
        });

        // Add the submit button to the JFrame
        frame.add(Box.createVerticalStrut(20));
        frame.add(submitButton);
        frame.add(Box.createVerticalStrut(10));

        // Set the JFrame to visible
        frame.setVisible(true);
    }

    public static void getPatientFromUserForEdit(CreatePatientCallBack createPatientCallBack, Patient oldPatient) {
        // Create a new JFrame to hold the input fields
        JFrame frame = new JFrame("Edit Patient: " + oldPatient.getName());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Width, Height);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        // Create the input fields and labels
        JTextField nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(300, nameField.getPreferredSize().height));
        JLabel nameLabel = new JLabel("Name:");
        JPanel namePanel = new JPanel();
        nameField.setText(oldPatient.getName());
        namePanel.add(nameLabel);
        namePanel.add(nameField);

        JTextField genderField = new JTextField();
        genderField.setPreferredSize(new Dimension(300, genderField.getPreferredSize().height));
        JLabel genderLabel = new JLabel("Gender:");
        JPanel genderPanel = new JPanel();
        genderField.setText(oldPatient.getGender());
        genderPanel.add(genderLabel);
        genderPanel.add(genderField);

        JTextField phoneNumberField = new JTextField();
        phoneNumberField.setPreferredSize(new Dimension(300, phoneNumberField.getPreferredSize().height));
        JLabel phoneNumberLabel = new JLabel("Phone Number:");
        JPanel phoneNumberPanel = new JPanel();
        phoneNumberField.setText(oldPatient.getPhoneNumber());
        phoneNumberPanel.add(phoneNumberLabel);
        phoneNumberPanel.add(phoneNumberField);

        JTextField emailField = new JTextField();
        emailField.setPreferredSize(new Dimension(300, emailField.getPreferredSize().height));
        JLabel emailLabel = new JLabel("Email:");
        JPanel emailPanel = new JPanel();
        emailField.setText(oldPatient.getEmail());
        emailPanel.add(emailLabel);
        emailPanel.add(emailField);

        JTextField ageField = new JTextField();
        ageField.setPreferredSize(new Dimension(300, ageField.getPreferredSize().height));
        JLabel ageLabel = new JLabel("Age:");
        JPanel agePanel = new JPanel();
        ageField.setText(Integer.toString(oldPatient.getAge()));
        agePanel.add(ageLabel);
        agePanel.add(ageField);

        JTextField descriptionField = new JTextField();
        descriptionField.setPreferredSize(new Dimension(300, descriptionField.getPreferredSize().height));
        JLabel descriptionLabel = new JLabel("Description:");
        JPanel descriptionPanel = new JPanel();
        descriptionField.setText(oldPatient.getDescription());
        descriptionPanel.add(descriptionLabel);
        descriptionPanel.add(descriptionField);

        JCheckBox insuredBox = new JCheckBox();
        insuredBox.setPreferredSize(new Dimension(300, descriptionField.getPreferredSize().height));
        JLabel insuredLabel = new JLabel("Have insured:");
        JPanel insuredPanel = new JPanel();
        insuredBox.setSelected(oldPatient.isHaveInsured());
        insuredPanel.add(insuredLabel);
        insuredPanel.add(insuredBox);

        // Add the input fields and labels to the JFrame
        frame.add(Box.createVerticalStrut(10));
        frame.add(namePanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(genderPanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(phoneNumberPanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(emailPanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(agePanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(descriptionPanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(insuredPanel);

        // Create a button for the user to submit their input
        JButton submitButton = new JButton("Submit");
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the input values from the fields
                String name = nameField.getText();
                String gender = genderField.getText();
                String phoneNumber = phoneNumberField.getText();
                String email = emailField.getText();
                int age = Integer.parseInt(ageField.getText());
                String description = descriptionField.getText();
                Boolean insurance = insuredBox.isSelected();

                // Create a new Doctor object with the input values
                Patient patient = new Patient();
                patient.setName(name);
                patient.setGender(gender);
                patient.setPhoneNumber(phoneNumber);
                patient.setEmail(email);
                patient.setAge(age);
                patient.setDescription(description);
                patient.setId(oldPatient.getId());
                patient.setHaveInsured(insurance);

                // Close the JFrame
                frame.dispose();

                // Call the callback interface with the created Doctor object
                createPatientCallBack.onPatientCreated(patient);
            }
        });

        // Add the submit button to the JFrame
        frame.add(Box.createVerticalStrut(20));
        frame.add(submitButton);
        frame.add(Box.createVerticalStrut(10));

        // Set the JFrame to visible
        frame.setVisible(true);
    }

    private static void getDoctorResume(GetDoctorResumeCallBack callBack) {
        List<ArticleForResume> articles = new ArrayList<>();
        List<ExpeirenceForResume> expeirences = new ArrayList<>();

        // Create a new JFrame to display the window
        JFrame frame = new JFrame("Doctor Resume");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize((int) (0.7 * Width), (int) (0.7 * Height));

        // Create a new JPanel to hold the components
        JPanel panel = new JPanel(new GridBagLayout());

        // Create GridBagConstraints for component placement
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Create a new JPanel for the left side
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        // Create a new JLabel and JTextField for the left side
        JLabel articleNameLable = new JLabel("Article name:");
        leftPanel.add(articleNameLable);
        JTextField articleNameInput = new JTextField();
        leftPanel.add(articleNameInput);

        JLabel articleImpactFactorLable = new JLabel("Impact factor:");
        leftPanel.add(articleImpactFactorLable);
        JTextField articleImpactFactorInput = new JTextField();
        leftPanel.add(articleImpactFactorInput);

        JButton btnAddArticle = new JButton("Add article");

        // Add the components to the left side panel
        leftPanel.add(btnAddArticle);

        JLabel txtArticles = new JLabel("Articles:");

        leftPanel.add(txtArticles);

        // Add the left side panel to the main panel using GridBagConstraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        panel.add(leftPanel, gbc);

        // Create a new JPanel for the right side
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        // Create a new JLabel and JTextField for the right side
        JLabel workplaceName = new JLabel("Name of the workplace:");
        rightPanel.add(workplaceName);
        JTextField workplaceNameInput = new JTextField();
        rightPanel.add(workplaceNameInput);

        JLabel startDate = new JLabel("start date:");
        rightPanel.add(startDate);
        JTextField startDateInput = new JTextField();
        rightPanel.add(startDateInput);

        JLabel endDate = new JLabel("end date:");
        rightPanel.add(endDate);
        JTextField endDateInput = new JTextField();
        rightPanel.add(endDateInput);

        JButton btnAddExp = new JButton("Add Experience");
        rightPanel.add(btnAddExp);
        JLabel txtExps = new JLabel("Experiences :");
        rightPanel.add(txtExps);

        // Add the right side panel to the main panel using GridBagConstraints
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        panel.add(rightPanel, gbc);

        // Add the main panel to the frame
        frame.add(panel);

        // Add action listeners to the submit buttons
        btnAddArticle.addActionListener(e -> {
            ArticleForResume articleForResume = new ArticleForResume();
            articleForResume.setImpactFactor(Long.parseLong(articleImpactFactorInput.getText()));
            articleForResume.setName(articleNameInput.getText());
            articleImpactFactorInput.setText("");
            articleNameInput.setText("");

            if (articleForResume.getName().isEmpty()) {
                return;
            }

            articles.add(articleForResume);
            txtArticles.setText(txtArticles.getText() + "," + articleForResume.getName());
        });

        btnAddExp.addActionListener(e -> {
            ExpeirenceForResume exp = new ExpeirenceForResume();
            exp.setStartDate(startDateInput.getText());
            exp.setEndDate(endDateInput.getText());
            exp.setNameOfWorkplace(workplaceNameInput.getText());

            if (exp.getNameOfWorkplace().isEmpty()) {
                return;
            }

            startDateInput.setText("");
            endDateInput.setText("");
            workplaceNameInput.setText("");

            expeirences.add(exp);
            txtExps.setText(txtExps.getText() + "," + exp.getNameOfWorkplace());
        });

        JButton btnDone = new JButton("Done");
        panel.add(btnDone);

        btnDone.addActionListener(e -> {
            frame.dispose();
            callBack.onResumeCreated(articles, expeirences);
        });

        // Show the frame
        frame.setVisible(true);
    }

    public static void getAndSendDoctorResume(SendDoctorResumeCallBack callBack) {
        // Create a new JFrame to hold the input fields
        JFrame frame = new JFrame("Doctor Information");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Width, Height);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        // Create the input fields and labels
        JTextField expertiseField = new JTextField();
        expertiseField.setPreferredSize(new Dimension(300, expertiseField.getPreferredSize().height));
        JLabel expertiseLabel = new JLabel("Expertise:");
        JPanel expertisePanel = new JPanel();
        expertisePanel.add(expertiseLabel);
        expertisePanel.add(expertiseField);

        JTextField salaryField = new JTextField();
        salaryField.setPreferredSize(new Dimension(300, salaryField.getPreferredSize().height));
        JLabel salaryLabel = new JLabel("Salary:");
        JPanel salaryPanel = new JPanel();
        salaryPanel.add(salaryLabel);
        salaryPanel.add(salaryField);

        JTextField shiftField = new JTextField();
        shiftField.setPreferredSize(new Dimension(300, shiftField.getPreferredSize().height));
        JLabel shiftLabel = new JLabel("Shift:");
        JPanel shiftPanel = new JPanel();
        shiftPanel.add(shiftLabel);
        shiftPanel.add(shiftField);

        JTextField usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(300, usernameField.getPreferredSize().height));
        JLabel usernameLabel = new JLabel("Username:");
        JPanel usernamePanel = new JPanel();
        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameField);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(300, passwordField.getPreferredSize().height));
        JLabel passwordLabel = new JLabel("Password:");
        JPanel passwordPanel = new JPanel();
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);

        JTextField nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(300, nameField.getPreferredSize().height));
        JLabel nameLabel = new JLabel("Name:");
        JPanel namePanel = new JPanel();
        namePanel.add(nameLabel);
        namePanel.add(nameField);

        JTextField genderField = new JTextField();
        genderField.setPreferredSize(new Dimension(300, genderField.getPreferredSize().height));
        JLabel genderLabel = new JLabel("Gender:");
        JPanel genderPanel = new JPanel();
        genderPanel.add(genderLabel);
        genderPanel.add(genderField);

        JTextField phoneNumberField = new JTextField();
        phoneNumberField.setPreferredSize(new Dimension(300, phoneNumberField.getPreferredSize().height));
        JLabel phoneNumberLabel = new JLabel("Phone Number:");
        JPanel phoneNumberPanel = new JPanel();
        phoneNumberPanel.add(phoneNumberLabel);
        phoneNumberPanel.add(phoneNumberField);

        JTextField emailField = new JTextField();
        emailField.setPreferredSize(new Dimension(300, emailField.getPreferredSize().height));
        JLabel emailLabel = new JLabel("Email:");
        JPanel emailPanel = new JPanel();
        emailPanel.add(emailLabel);
        emailPanel.add(emailField);

        JTextField ageField = new JTextField();
        ageField.setPreferredSize(new Dimension(300, ageField.getPreferredSize().height));
        JLabel ageLabel = new JLabel("Age:");
        JPanel agePanel = new JPanel();
        agePanel.add(ageLabel);
        agePanel.add(ageField);

        JTextField universityField = new JTextField();
        universityField.setPreferredSize(new Dimension(300, universityField.getPreferredSize().height));
        JLabel universityLabel = new JLabel("university:");
        JPanel universityPanel = new JPanel();
        universityPanel.add(universityLabel);
        universityPanel.add(universityField);

        JTextField GPAField = new JTextField();
        GPAField.setPreferredSize(new Dimension(300, GPAField.getPreferredSize().height));
        JLabel GPALabel = new JLabel("GPA:");
        JPanel GPAPanel = new JPanel();
        GPAPanel.add(GPALabel);
        GPAPanel.add(GPAField);

        JTextField LevelField = new JTextField();
        LevelField.setPreferredSize(new Dimension(300, LevelField.getPreferredSize().height));
        JLabel LevelLabel = new JLabel("Level of education:");
        JPanel LevelPanel = new JPanel();
        LevelPanel.add(LevelLabel);
        LevelPanel.add(LevelField);

        // Add the input fields and labels to the JFrame
        frame.add(Box.createVerticalStrut(10));
        frame.add(expertisePanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(salaryPanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(shiftPanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(usernamePanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(passwordPanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(namePanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(genderPanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(phoneNumberPanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(emailPanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(agePanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(universityPanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(GPAPanel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(LevelPanel);

        // Create a button for the user to submit their input
        JButton submitButton = new JButton("Submit");
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the input values from the fields
                String expertise = expertiseField.getText();
                long salary = Long.parseLong(salaryField.getText());
                String shift = shiftField.getText();
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String name = nameField.getText();
                String gender = genderField.getText();
                String phoneNumber = phoneNumberField.getText();
                String email = emailField.getText();
                int age = Integer.parseInt(ageField.getText());

                // Create a new Doctor object with the input values
                Doctor doctor = new Doctor();
                doctor.setExpertise(expertise);
                doctor.setSalary(salary);
                doctor.setShift(shift);
                doctor.setUsername(username);
                doctor.setPassword(password);
                doctor.setName(name);
                doctor.setGender(gender);
                doctor.setPhoneNumber(phoneNumber);
                doctor.setEmail(email);
                doctor.setAge(age);

                // Close the JFrame
                frame.dispose();

                getDoctorResume(new GetDoctorResumeCallBack() {

                    @Override
                    public void onResumeCreated(List<ArticleForResume> articles,
                            List<ExpeirenceForResume> expeirences) {
                        Resume resume = new Resume();

                        resume.setUniversity(universityField.getText());
                        resume.setGPA(GPAField.getText());
                        resume.setLevelOfEducation(LevelField.getText());
                        callBack.onRequestCreated(doctor, resume, articles, expeirences);
                    }
                });

            }
        });

        // Add the submit button to the JFrame
        frame.add(Box.createVerticalStrut(20));
        frame.add(submitButton);
        frame.add(Box.createVerticalStrut(10));

        // Set the JFrame to visible
        frame.setVisible(true);
    }

    public static void getVisitRequest(CreateVisitRequestCallBack callBack) {
        showDoctorsListForSelection(context.Doctors().getAllAcceptedDoctorsList(), "Select doctor for visit",
                new ListCallback() {

                    @Override
                    public void onItemSelected(int doctorId) {
                        Visit visit = new Visit(doctorId, LoginInfo.LoginId, 20000, null);

                        callBack.onRequestCreated(visit);
                    }

                    @Override
                    public void onItemSelectedForRemove(int Id) {
                    }

                    @Override
                    public void onReturn() {
                        callBack.onRequestCreated(null);
                    }

                });
    }

    public interface SendDoctorResumeCallBack {
        void onRequestCreated(Doctor doctor, Resume resume, List<ArticleForResume> articles,
                List<ExpeirenceForResume> expeirences);

        void onReturn();
    }

    private interface GetDoctorResumeCallBack {
        void onResumeCreated(List<ArticleForResume> articles,
                List<ExpeirenceForResume> expeirences);
    }

    public interface CreateVisitRequestCallBack {
        void onRequestCreated(Visit visit);
    }

    public interface ListCallback {
        void onItemSelected(int doctorId);

        void onItemSelectedForRemove(int Id);

        void onReturn();
    }

    public interface CreateDoctorCallBack {
        public void onDoctorCreated(Doctor doctor);
    }

    public interface CreatePatientCallBack {
        public void onPatientCreated(Patient patient);
    }

    public interface CreateNurseCallBack {
        public void onNurseCreated(Nurse nurse);
    }

    public interface CreatePersonelCallBack {
        public void onPersonelCreated(Personel personel);
    }

}