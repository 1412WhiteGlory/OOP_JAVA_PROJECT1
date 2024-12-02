package com.mycompany.happ;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterScreen extends JPanel {
    private UserManager userManager;
    private Happ mainApp;

    public RegisterScreen(UserManager userManager, Happ mainApp) {
        this.userManager = userManager;
        this.mainApp = mainApp;
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());

        // Label đăng ký
        JLabel registerLabel = new JLabel("Create a New Account");
        registerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Các trường nhập liệu cho thông tin đăng ký
        JTextField nameField = new JTextField(20);
        JTextField emailField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);
        JPasswordField confirmPasswordField = new JPasswordField(20); // Nhập lại mật khẩu
        JTextField phoneField = new JTextField(20);
        JTextField ageField = new JTextField(20); // Nhập tuổi
        String[] genderOptions = {"Male", "Female", "Other"};
        JComboBox<String> genderComboBox = new JComboBox<>(genderOptions); // Giới tính
        JButton registerButton = new JButton("Register");

        // Xử lý nút Register
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String email = emailField.getText();
                char[] password = passwordField.getPassword();
                char[] confirmPassword = confirmPasswordField.getPassword(); // Nhập lại mật khẩu
                String phone = phoneField.getText();
                String ageText = ageField.getText(); // Nhập tuổi
                String gender = (String) genderComboBox.getSelectedItem(); // Giới tính

                // Kiểm tra các trường không được để trống
                if (name.isEmpty() || email.isEmpty() || password.length == 0 || confirmPassword.length == 0 || phone.isEmpty() || ageText.isEmpty()) {
                    JOptionPane.showMessageDialog(RegisterScreen.this, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Kiểm tra email
                if (!email.contains("@")) {
                    JOptionPane.showMessageDialog(RegisterScreen.this, "Email must contain '@'!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Kiểm tra số điện thoại
                if (!phone.matches("^0\\d{9}$")) {
                    JOptionPane.showMessageDialog(RegisterScreen.this, "Phone number must be 10 digits starting with 0", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Kiểm tra mật khẩu
                if (!new String(password).equals(new String(confirmPassword))) {
                    JOptionPane.showMessageDialog(RegisterScreen.this, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Kiểm tra tuổi
                int age;
                try {
                    age = Integer.parseInt(ageText);
                    if (age <= 0) {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(RegisterScreen.this, "Invalid age input!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Đăng ký tài khoản
                if (userManager.isEmailOrPhoneTaken(email, phone)) {
                    JOptionPane.showMessageDialog(RegisterScreen.this, "Email or phone number already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (userManager.registerUser(name, email, new String(password), phone, age, gender)) {
                    JOptionPane.showMessageDialog(RegisterScreen.this, "Account created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    mainApp.showLoginScreen();
                } else {
                    JOptionPane.showMessageDialog(RegisterScreen.this, "Registration failed!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Bố cục giao diện
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2, 5, 5)); // Cập nhật thêm tuổi và giới tính
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(new JLabel("Confirm Password:"));
        panel.add(confirmPasswordField);
        panel.add(new JLabel("Phone:"));
        panel.add(phoneField);
        panel.add(new JLabel("Age:"));
        panel.add(ageField);
        panel.add(new JLabel("Gender:"));
        panel.add(genderComboBox);
        panel.add(new JLabel()); // Dòng trống
        panel.add(registerButton);

        // Nút chuyển đến màn hình đăng nhập
        JButton loginButton = new JButton("Already have an account? Login here");
        loginButton.addActionListener(e -> mainApp.showLoginScreen());

        // Thêm thành phần vào giao diện
        add(registerLabel, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        add(loginButton, BorderLayout.SOUTH);
    }
}
