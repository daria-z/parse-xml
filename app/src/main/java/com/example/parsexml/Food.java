package com.example.parsexml;

//всё что мы знаем о блюде

public class Food {
    private String name;
    private String price;

    private String calories;

    public Food(String name, String price) {
        this.name = name;
        this.price = price;
        this.calories = calories;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getCalories() {
        return calories;
    }

}
