package com.mycompany.happ;

import javax.swing.*;
import java.awt.*;

public class HealthRecord extends JPanel {
    private final UserManager userManager;
    private final User currentUser;
    private final Happ mainApp;

    private JTextField weightField;
    private JTextField heightField;

    private JLabel bmiLabel;
    private JLabel statusLabel;

    public HealthRecord(UserManager userManager, User currentUser, Happ mainApp) {
        this.userManager = userManager;
        this.currentUser = currentUser;
        this.mainApp = mainApp;

        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        // Tiêu đề
        JLabel titleLabel = new JLabel("Health Record", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        // Panel nhập thông tin sức khỏe
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputPanel.add(new JLabel("Weight (kg):"));
        weightField = new JTextField();
        inputPanel.add(weightField);

        inputPanel.add(new JLabel("Height (m):"));
        heightField = new JTextField();
        inputPanel.add(heightField);

        add(inputPanel, BorderLayout.CENTER);

        // Panel hiển thị kết quả
        JPanel resultPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        resultPanel.setBorder(BorderFactory.createTitledBorder("Results"));

        bmiLabel = new JLabel("BMI: ");
        statusLabel = new JLabel("Status: ");
        resultPanel.add(bmiLabel);
        resultPanel.add(statusLabel);

        add(resultPanel, BorderLayout.EAST);

        // Panel nút bấm
        JPanel buttonPanel = new JPanel();
        JButton calculateButton = new JButton("Calculate");
        JButton backButton = new JButton("Back to Dashboard");

        calculateButton.addActionListener(e -> calculateAndDisplayResults());

        backButton.addActionListener(e -> {
    if (!bmiLabel.getText().equals("BMI: ")) {
        // Lấy thông tin BMI và trạng thái sức khỏe
        float bmi = Float.parseFloat(bmiLabel.getText().replace("BMI: ", ""));
        String healthStatus = statusLabel.getText().replace("Health Status: ", "");

        // Cập nhật thông tin vào đối tượng User
        currentUser.setBmi(bmi);
        currentUser.setHealthStatus(healthStatus);
        currentUser.setHealthRecordCompleted(true); // Đánh dấu là đã cập nhật thông tin sức khỏe

        // Cập nhật thông tin vào Dashboard
        mainApp.updateDashboardHealthInfo(bmi, healthStatus);
    }

    // Quay lại Dashboard và đóng cửa sổ HealthRecord
    mainApp.showDashboardScreen(currentUser); // Quay lại Dashboard
    JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(HealthRecord.this);
    currentFrame.dispose(); // Đóng cửa sổ HealthRecord
});






        buttonPanel.add(calculateButton);
        buttonPanel.add(backButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void calculateAndDisplayResults() {
    try {
        // Lấy thông tin từ các trường nhập liệu
        float weight = Float.parseFloat(weightField.getText());
        float height = Float.parseFloat(heightField.getText());

        // Kiểm tra dữ liệu hợp lệ
        if (weight <= 0 || height <= 0) {
            JOptionPane.showMessageDialog(this, "Weight and height must be greater than 0.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Tính toán BMI
        float bmi = weight / (height * height);

        // Hiển thị kết quả trên HealthRecord
        bmiLabel.setText(String.format("BMI: %.2f", bmi));
        String healthStatus = getHealthStatus(bmi);
        statusLabel.setText("Health Status: " + healthStatus);

        // Cập nhật thông tin vào đối tượng User
        currentUser.setBmi(bmi);
        currentUser.setHealthStatus(healthStatus);

        // Thông báo thành công
        JOptionPane.showMessageDialog(this, "BMI updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Please enter valid numeric values.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}


    private String getHealthStatus(float bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi >= 18.5 && bmi < 24.9) {
            return "Normal weight";
        } else if (bmi >= 25 && bmi < 29.9) {
            return "Overweight";
        } else {
            return "Obesity";
        }
    }
}
