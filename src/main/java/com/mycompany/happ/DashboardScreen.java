package com.mycompany.happ;

import java.awt.*;
import javax.swing.*;

public class DashboardScreen extends JFrame {
    private User currentUser;
    private UserManager userManager;
    private Happ mainApp;

    private JLabel bmiLabel;      // Hiển thị BMI
    private JLabel statusLabel;   // Hiển thị trạng thái sức khỏe

    public DashboardScreen(UserManager userManager, User currentUser, Happ mainApp) {
        this.userManager = userManager;
        this.currentUser = currentUser;
        this.mainApp = mainApp;

        initUI();
    }

    private void initUI() {
        setTitle("Dashboard");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Tiêu đề chào mừng
        JLabel welcomeLabel = new JLabel("Welcome, " + currentUser.getName() + "!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Tab quản lý kế hoạch
        JTabbedPane tabbedPane = new JTabbedPane();

        // Tab Thông tin sức khỏe
        tabbedPane.add("Health Info", createHealthInfoPanel());

        // Tab Kế hoạch ăn uống
        tabbedPane.add("Diet Plan", createDietPlanPanel());

        // Tab Kế hoạch tập luyện
        tabbedPane.add("Exercise Plan", createExercisePlanPanel());

        // Nút đăng xuất
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> {
            mainApp.showLoginScreen(); // Quay lại màn hình đăng nhập
            dispose(); // Đóng cửa sổ Dashboard
        });

        // Bố cục chính
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(welcomeLabel, BorderLayout.NORTH);
        mainPanel.add(tabbedPane, BorderLayout.CENTER);
        mainPanel.add(logoutButton, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true); // Hiển thị giao diện

        // Kiểm tra xem người dùng đã có thông tin sức khỏe chưa
        if (currentUser.getBmi() != -1) {
            // Nếu có, hiển thị thông tin
            updateHealthInfo(currentUser.getBmi(), currentUser.getHealthStatus());
        }
    }

    // Tạo panel thông tin sức khỏe
   private JPanel createHealthInfoPanel() {
    JPanel healthPanel = new JPanel();
    healthPanel.setLayout(new BoxLayout(healthPanel, BoxLayout.Y_AXIS));  // Sắp xếp theo chiều dọc

    bmiLabel = new JLabel("BMI: Not Calculated");
    bmiLabel.setFont(new Font("Arial", Font.PLAIN, 14));

    statusLabel = new JLabel("Health Status: Unknown");
    statusLabel.setFont(new Font("Arial", Font.PLAIN, 14));

    // Nếu người dùng chưa nhập thông tin sức khỏe, thêm nút update
    if (currentUser.getBmi() == -1) {
        JButton updateButton = new JButton("Update Health Record");
        updateButton.addActionListener(e -> {
            mainApp.showHealthRecordScreen(currentUser);  // Chuyển sang màn hình HealthRecord
        });
        healthPanel.add(updateButton);  // Thêm nút vào panel
    } else {
        // Nếu đã có BMI, hiển thị thông tin sức khỏe
        updateHealthInfo(currentUser.getBmi(), currentUser.getHealthStatus());
    }

    healthPanel.add(bmiLabel);
    healthPanel.add(statusLabel);

    return healthPanel;
}


    // Tạo panel kế hoạch ăn uống
    private JPanel createDietPlanPanel() {
        JPanel dietPanel = new JPanel();
        dietPanel.add(new JLabel("This is Diet Plan Panel"));
        return dietPanel;
    }

    // Tạo panel kế hoạch tập luyện
    private JPanel createExercisePlanPanel() {
        JPanel exercisePanel = new JPanel();
        exercisePanel.add(new JLabel("This is Exercise Plan Panel"));
        return exercisePanel;
    }

    // Phương thức để cập nhật thông tin BMI và trạng thái sức khỏe
    public void updateHealthInfo(float bmi, String healthStatus) {
        bmiLabel.setText(String.format("BMI: %.2f", bmi));
        statusLabel.setText("Health Status: " + healthStatus);
        revalidate(); // Cập nhật lại giao diện
        repaint();
    }
}
