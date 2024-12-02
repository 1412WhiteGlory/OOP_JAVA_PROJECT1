/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.happ;

import java.util.Objects;

public class Exercise {
    private String name;
    private int duration; // in minutes
    private String intensity;

    // Constructor
    public Exercise(String name, int duration, String intensity) {
        this.name = name;
        setDuration(duration); // Sử dụng setter để kiểm tra dữ liệu
        setIntensity(intensity); // Sử dụng setter để kiểm tra dữ liệu
    }

    // Cập nhật thời gian
    public void updateDuration(int duration) {
        setDuration(duration); // Sử dụng setter để kiểm tra dữ liệu
    }

    // Cập nhật cường độ
    public void updateIntensity(String intensity) {
        setIntensity(intensity); // Sử dụng setter để kiểm tra dữ liệu
    }

    // Getter cho duration
    public int getDuration() {
        return duration;
    }

    // Getter cho intensity
    public String getIntensity() {
        return intensity;
    }

    // Setter cho duration (bao gồm kiểm tra dữ liệu)
    public void setDuration(int duration) {
        if (duration >= 0) {
            this.duration = duration;
        } else {
            throw new IllegalArgumentException("Duration cannot be negative");
        }
    }

    // Setter cho intensity (bao gồm kiểm tra dữ liệu)
    public void setIntensity(String intensity) {
        if (intensity != null && (intensity.equals("Low") || intensity.equals("Medium") || intensity.equals("High"))) {
            this.intensity = intensity;
        } else {
            throw new IllegalArgumentException("Intensity must be Low, Medium, or High");
        }
    }

    // Phương thức toString để hiển thị thông tin bài tập
    @Override
    public String toString() {
        return "Exercise{" +
                "name='" + name + '\'' +
                ", duration=" + duration +
                " minutes, intensity='" + intensity + '\'' +
                '}';
    }

    // Override equals và hashCode để so sánh hai đối tượng Exercise
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Exercise exercise = (Exercise) obj;
        return duration == exercise.duration &&
                name.equals(exercise.name) &&
                intensity.equals(exercise.intensity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, duration, intensity);
    }
}
