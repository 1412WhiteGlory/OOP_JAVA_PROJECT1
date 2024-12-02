/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.happ;

import java.util.ArrayList;
import java.util.List;

public class Meal {
    private String name;
    private float calories;
    private List<String> ingredients;

    public Meal(String name, float calories, List<String> ingredients) {
        this.name = name;
        this.calories = calories;
        this.ingredients = ingredients != null ? ingredients : new ArrayList<>();
    }

    public void updateCalories(float calories) {
        if (calories >= 0) {
            this.calories = calories;
        } else {
            throw new IllegalArgumentException("Calories cannot be negative");
        }
    }

    public String viewIngredients() {
        return "Ingredients: " + String.join(", ", ingredients);
    }

    public void addIngredient(String ingredient) {
        if (ingredient != null && !ingredient.isEmpty()) {
            ingredients.add(ingredient);
        } else {
            throw new IllegalArgumentException("Ingredient cannot be null or empty");
        }
    }

    public void removeIngredient(String ingredient) {
        if (ingredient != null && !ingredient.isEmpty()) {
            ingredients.remove(ingredient);
        } else {
            throw new IllegalArgumentException("Ingredient cannot be null or empty");
        }
    }

    public float getCalories() {
        return calories;
    }

    public String getName() {
        return name;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    @Override
    public String toString() {
        return "Meal{" + "name='" + name + '\'' + ", calories=" + calories + ", ingredients=" + ingredients + '}';
    }
}
