/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.happ;

import java.util.Date;

public class Notification {
    private String message;
    private Date notificationTime;
    private String status; // "Read" or "Unread"

    // Constructor
    public Notification(String message, Date notificationTime) {
        this.message = message;
        this.notificationTime = notificationTime;
        this.status = "Unread"; // Default status is "Unread"
    }

    // Gửi thông báo
    public void sendNotification() {
        System.out.println("Notification sent: " + message + " at " + notificationTime);
        this.status = "Read"; // Sau khi gửi, đánh dấu là đã đọc
    }

    // Đánh dấu thông báo là đã đọc
    public void markAsRead() {
        this.status = "Read";
    }

    // Lên lịch thông báo
    public void scheduleNotification(Date time) {
        this.notificationTime = time;
        System.out.println("Notification scheduled at: " + time);
    }

    // Getter và Setter cho message, notificationTime và status
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getNotificationTime() {
        return notificationTime;
    }

    public void setNotificationTime(Date notificationTime) {
        this.notificationTime = notificationTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (status.equals("Read") || status.equals("Unread")) {
            this.status = status;
        } else {
            throw new IllegalArgumentException("Status must be 'Read' or 'Unread'");
        }
    }

    // Phương thức toString để hiển thị thông tin thông báo
    @Override
    public String toString() {
        return "Notification{" +
                "message='" + message + '\'' +
                ", notificationTime=" + notificationTime +
                ", status='" + status + '\'' +
                '}';
    }
}
