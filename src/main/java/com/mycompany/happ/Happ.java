package com.mycompany.happ;

import javax.swing.*;

public class Happ {
    private UserManager userManager;
    private JFrame mainFrame;

    public Happ() {
        userManager = new UserManager();
        mainFrame = new JFrame("Health Application");

        mainFrame.setSize(600, 400);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Happ app = new Happ();
            app.showRegisterScreen();  // Hiển thị màn hình đăng ký khi bắt đầu ứng dụng
        });
    }

    // Hiển thị màn hình đăng ký
    public void showRegisterScreen() {
        RegisterScreen registerScreen = new RegisterScreen(userManager, this);
        mainFrame.setContentPane(registerScreen);
        mainFrame.setVisible(true);
    }

    // Hiển thị màn hình đăng nhập
    public void showLoginScreen() {
        LoginScreen loginScreen = new LoginScreen(userManager, this);
        mainFrame.setContentPane(loginScreen);
        mainFrame.setVisible(true);
    }

    // Hiển thị màn hình HealthRecord sau khi đăng nhập thành công
    public void showHealthRecord(User currentUser) {
        if (currentUser == null) {
            JOptionPane.showMessageDialog(mainFrame, 
                "Current user is null. Cannot display health record.",
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Tạo một đối tượng HealthRecord với tham số là currentUser
        HealthRecord healthRecord = new HealthRecord(userManager, currentUser, this);

        // Cập nhật nội dung của JFrame với màn hình HealthRecord
        mainFrame.setContentPane(healthRecord);

        // Cập nhật giao diện
        mainFrame.revalidate();
        mainFrame.repaint();
        mainFrame.setVisible(true);
    }

    // Hiển thị màn hình Dashboard
    public void showDashboardScreen(User currentUser) {
        DashboardScreen dashboardScreen = new DashboardScreen(userManager, currentUser, this);
        mainFrame.setContentPane(dashboardScreen);
        mainFrame.setVisible(true);
    }

    // Cập nhật thông tin sức khỏe từ HealthRecord vào Dashboard
    public void updateDashboardHealthInfo(float bmi, String healthStatus) {
        // Truy cập màn hình Dashboard và cập nhật các thông tin BMI và trạng thái sức khỏe
        if (mainFrame.getContentPane() instanceof DashboardScreen) {
            DashboardScreen dashboardScreen = (DashboardScreen) mainFrame.getContentPane();
            dashboardScreen.updateHealthInfo(bmi, healthStatus);
        }
    }

    void showHealthRecordScreen(User currentUser) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
