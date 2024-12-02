/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.happ;

import java.util.ArrayList;
import java.util.List;

public class ExercisePlan {
    private List<Exercise> exercises = new ArrayList<>();
    private int totalDuration;

    // Thêm bài tập vào kế hoạch và cập nhật tổng thời gian
    public void addExercise(Exercise exercise) {
        if (exercise != null) {
            exercises.add(exercise);
            totalDuration += exercise.getDuration(); // Cập nhật trực tiếp tổng thời gian
        }
    }

    // Xóa bài tập khỏi kế hoạch và cập nhật tổng thời gian
    public void removeExercise(Exercise exercise) {
        if (exercise != null && exercises.remove(exercise)) {
            totalDuration -= exercise.getDuration(); // Cập nhật trực tiếp tổng thời gian
        }
    }

    // Phương thức tính tổng thời gian (có thể không cần nếu dùng cách tính trực tiếp)
    public void calculateTotalDuration() {
        totalDuration = 0;
        for (Exercise exercise : exercises) {
            totalDuration += exercise.getDuration();
        }
    }

    // Getter cho totalDuration
    public int getTotalDuration() {
        return totalDuration;
    }

    // Getter và setter cho exercises
    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
        calculateTotalDuration(); // Cập nhật tổng thời gian khi thay đổi danh sách bài tập
    }

    // Phương thức toString để hiển thị thông tin kế hoạch tập luyện
    @Override
    public String toString() {
        return "ExercisePlan{" +
                "exercises=" + exercises +
                ", totalDuration=" + totalDuration +
                '}';
    }
}
