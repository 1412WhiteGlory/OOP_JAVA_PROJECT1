package com.mycompany.happ;
import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private String email;
    private String password;
    private String phone;
    private int age;
    private String gender;
    private float bmi; // Thêm BMI
    private String healthStatus; // Thêm trạng thái sức khỏe
    private boolean healthRecordCompleted; // Đánh dấu khi người dùng đã cập nhật thông tin sức khỏe

    // Constructor
    public User(String name, String email, String password, String phone, int age, String gender) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.age = age;
        this.gender = gender;
        this.bmi = -1; // -1 là giá trị mặc định khi chưa có thông tin sức khỏe
        this.healthStatus = "";
        this.healthRecordCompleted = false;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public float getBmi() {
        return bmi;
    }

    public void setBmi(float bmi) {
        this.bmi = bmi;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

    public boolean isHealthRecordCompleted() {
        return healthRecordCompleted;
    }

    public void setHealthRecordCompleted(boolean healthRecordCompleted) {
        this.healthRecordCompleted = healthRecordCompleted;
    }
}
