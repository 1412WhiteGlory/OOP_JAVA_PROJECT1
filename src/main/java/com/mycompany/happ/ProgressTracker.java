/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.happ;

import java.util.ArrayList;
import java.util.List;

public class ProgressTracker {
    private List<Goal> goals = new ArrayList<>();
    private float currentProgress;
    private String goalStatus;

    // Thêm mục tiêu vào danh sách
    public void setGoal(Goal goal) {
        goals.add(goal);
    }

    // Cập nhật tiến độ và kiểm tra trạng thái mục tiêu
    public void updateProgress(float progress) {
        if (progress >= 0 && progress <= 100) {
            this.currentProgress = progress;
            checkGoalStatus();
        } else {
            throw new IllegalArgumentException("Progress must be between 0 and 100");
        }
    }

    // Kiểm tra trạng thái của mục tiêu
    public void checkGoalStatus() {
        this.goalStatus = currentProgress >= 100 ? "Completed" : "In Progress";
    }

    // Hiển thị tiến độ và trạng thái của mục tiêu
    public void displayProgress() {
        System.out.println("Current Progress: " + currentProgress + "%, Status: " + goalStatus);
    }

    // Hiển thị thông tin về tất cả các mục tiêu
    public void displayGoals() {
        for (Goal goal : goals) {
            System.out.println(goal); // Gọi phương thức toString() của Goal
        }
    }

    // Getter và Setter cho các thuộc tính
    public float getCurrentProgress() {
        return currentProgress;
    }

    public void setCurrentProgress(float currentProgress) {
        updateProgress(currentProgress);
    }

    public String getGoalStatus() {
        return goalStatus;
    }

    public List<Goal> getGoals() {
        return goals;
    }

    // Phương thức toString để hiển thị thông tin tiến độ
    @Override
    public String toString() {
        return "ProgressTracker{" +
                "goals=" + goals +
                ", currentProgress=" + currentProgress +
                ", goalStatus='" + goalStatus + '\'' +
                '}';
    }
}
