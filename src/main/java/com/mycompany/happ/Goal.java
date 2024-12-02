/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.happ;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Goal {
    private int goalID;
    private String goalType;
    private float targetValue;
    private Date targetDate;  // Sử dụng Date để lưu ngày

    // Constructor
    public Goal(int goalID, String goalType, float targetValue, String targetDate) throws ParseException {
        this.goalID = goalID;
        this.goalType = goalType;
        this.targetValue = targetValue;
        setTargetDate(targetDate);  // Chuyển đổi từ String thành Date
    }

    // Cập nhật giá trị mục tiêu
    public void updateTargetValue(float value) {
        if (value >= 0) {
            this.targetValue = value;
        } else {
            throw new IllegalArgumentException("Target value cannot be negative");
        }
    }

    // Kiểm tra mục tiêu đã đạt chưa
    public boolean checkGoalAchieved(float currentValue) {
        return currentValue >= targetValue;
    }

    // Setter và Getter cho targetDate
    public Date getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(String targetDate) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  // Định dạng ngày
        this.targetDate = dateFormat.parse(targetDate);  // Chuyển từ String sang Date
    }

    // Getter và Setter cho các thuộc tính khác
    public int getGoalID() {
        return goalID;
    }

    public void setGoalID(int goalID) {
        this.goalID = goalID;
    }

    public String getGoalType() {
        return goalType;
    }

    public void setGoalType(String goalType) {
        this.goalType = goalType;
    }

    public float getTargetValue() {
        return targetValue;
    }

    public void setTargetValue(float targetValue) {
        this.targetValue = targetValue;
    }

    // Phương thức toString để hiển thị thông tin mục tiêu
    @Override
    public String toString() {
        return "Goal{" +
                "goalID=" + goalID +
                ", goalType='" + goalType + '\'' +
                ", targetValue=" + targetValue +
                ", targetDate=" + targetDate +
                '}';
    }
}
