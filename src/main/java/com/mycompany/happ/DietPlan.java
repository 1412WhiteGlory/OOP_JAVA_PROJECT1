/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.happ;

import java.util.ArrayList;
import java.util.List;

public class DietPlan {
    private List<Meal> meals = new ArrayList<>();
    private float totalCalories;

    // Thêm bữa ăn vào kế hoạch và tính toán lại tổng calo
    public void addMeal(Meal meal) {
        if (meal != null) {
            meals.add(meal);
            totalCalories += meal.getCalories(); // Cập nhật trực tiếp tổng calo
        }
    }

    // Xóa bữa ăn khỏi kế hoạch và tính toán lại tổng calo
    public void removeMeal(Meal meal) {
        if (meal != null && meals.remove(meal)) {
            totalCalories -= meal.getCalories(); // Cập nhật trực tiếp tổng calo
        }
    }

    // Phương thức tính tổng calo (có thể không cần nếu dùng cách tính trực tiếp)
    public void calculateTotalCalories() {
        totalCalories = 0;
        for (Meal meal : meals) {
            totalCalories += meal.getCalories();
        }
    }

    // Getter và setter cho meals
    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
        calculateTotalCalories(); // Cập nhật tổng calo khi thay đổi danh sách bữa ăn
    }

    // Getter cho totalCalories
    public float getTotalCalories() {
        return totalCalories;
    }

    // Phương thức toString để hiển thị thông tin
    @Override
    public String toString() {
        return "DietPlan{" +
                "meals=" + meals +
                ", totalCalories=" + totalCalories +
                '}';
    }
}

