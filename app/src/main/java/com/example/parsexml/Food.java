package com.example.parsexml;

// класс для всей информации о блюде

public class Food {
    private int id;

    private String name;
    private String price;
    private int calories;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        String clear_price = price.substring(1);
        Float result_price = Float.parseFloat(clear_price) + 20;
        this.price = String.valueOf(result_price);
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }
}
