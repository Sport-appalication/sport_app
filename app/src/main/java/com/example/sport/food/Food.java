package com.example.sport.food;

public class Food {
    private String name;
    private double calory;
    private double fat;

    public Food(String name, double calory, double fat) {
        this.name = name;
        this.calory = calory;
        this.fat = fat;
    }

    public String getName() {
        return name;
    }

    public double getCalory() {
        return calory;
    }

    public double getFat() {
        return fat;
    }
}
