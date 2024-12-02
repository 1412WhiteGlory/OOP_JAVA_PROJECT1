package com.mycompany.happ;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private List<User> users;  // Danh sách người dùng
    private User currentUser;  // Người dùng hiện tại

    public UserManager() {
        users = loadUsers();  // Tải người dùng từ tệp khi ứng dụng khởi động
    }

    // Phương thức đăng ký người dùng
    public boolean registerUser(String name, String email, String password, String phone, int age, String gender) {
        // Kiểm tra xem email đã tồn tại chưa
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return false; // Email đã tồn tại, không thể đăng ký
            }
        }

        // Tạo một đối tượng User mới và thêm vào danh sách
        User newUser = new User(name, email, password, phone, age, gender);
        users.add(newUser);
        saveUsers();  // Lưu lại danh sách người dùng vào tệp
        return true; // Đăng ký thành công
    }

    // Phương thức đăng nhập
    public boolean loginUser(String email, String password) {
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                currentUser = user;  // Lưu người dùng hiện tại khi đăng nhập thành công
                return true; // Đăng nhập thành công
            }
        }
        return false; // Đăng nhập thất bại
    }

    // Getter cho danh sách người dùng
    public List<User> getUsers() {
        return users;
    }

    // Getter cho người dùng hiện tại
    public User getCurrentUser() {
        return currentUser;  // Trả về người dùng hiện tại
    }

    // Lưu danh sách người dùng vào tệp
    private void saveUsers() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("users.ser"))) {
            oos.writeObject(users);  // Lưu đối tượng users vào tệp
        } catch (IOException e) {
        }
    }

    // Tải danh sách người dùng từ tệp
    private List<User> loadUsers() {
        List<User> loadedUsers = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("users.ser"))) {
            loadedUsers = (List<User>) ois.readObject();  // Đọc đối tượng người dùng từ tệp
        } catch (IOException | ClassNotFoundException e) {
            // Nếu không có tệp, tạo danh sách trống
            
        }
        return loadedUsers;
    }
    // Kiểm tra email hoặc số điện thoại đã tồn tại
public boolean isEmailOrPhoneTaken(String email, String phone) {
    for (User user : users) {
        if (user.getEmail().equals(email) || user.getPhone().equals(phone)) {
            return true;
        }
    }
    return false;
}

}
