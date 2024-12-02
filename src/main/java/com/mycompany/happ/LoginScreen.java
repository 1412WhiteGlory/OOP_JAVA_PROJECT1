package com.mycompany.happ;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScreen extends JPanel {
    private UserManager userManager;
    private Happ mainApp;

    public LoginScreen(UserManager userManager, Happ mainApp) {
        this.userManager = userManager;
        this.mainApp = mainApp;
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());

        // Label chào mừng
        JLabel welcomeLabel = new JLabel("Login to Your Account");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Các trường nhập liệu cho Email và Password
        JTextField emailField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);
        JButton loginButton = new JButton("Login");

        // Đăng nhập khi nhấn nút
        loginButton.addActionListener((ActionEvent e) -> {
            String email = emailField.getText().trim();  // Lấy email và loại bỏ khoảng trắng
            char[] password = passwordField.getPassword();  // Lấy mật khẩu
            
            // Kiểm tra nếu các trường không được để trống
            if (email.isEmpty() || password.length == 0) {
                JOptionPane.showMessageDialog(LoginScreen.this, "Email and Password cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (userManager.loginUser(email, new String(password))) {
                // Đăng nhập thành công, kiểm tra thông tin sức khỏe của người dùng
                User currentUser = userManager.getCurrentUser();
                if (currentUser.getBmi() == -1 || currentUser.getHealthStatus().isEmpty()) {
                    // Nếu chưa có thông tin sức khỏe, hiển thị màn hình HealthRecord để người dùng nhập thông tin
                    mainApp.showHealthRecord(currentUser);
                } else {
                    // Nếu có thông tin sức khỏe, hiển thị Dashboard
                    mainApp.showDashboardScreen(currentUser);
                }
            } else {
                // Thông báo lỗi khi đăng nhập thất bại
                JOptionPane.showMessageDialog(LoginScreen.this, "Invalid email or password!", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Nút quay lại màn hình đăng ký
        JButton backButton = new JButton("Back to Register");
        backButton.addActionListener(e -> mainApp.showRegisterScreen());

        // Cấu trúc bố cục
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 5, 5));
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(new JLabel());  // Dòng trống
        panel.add(loginButton);

        // Nút quay lại màn hình đăng ký
        JPanel backPanel = new JPanel();
        backPanel.add(backButton);

        add(welcomeLabel, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        add(backPanel, BorderLayout.SOUTH);  // Đặt nút quay lại ở cuối cùng
    }
}
