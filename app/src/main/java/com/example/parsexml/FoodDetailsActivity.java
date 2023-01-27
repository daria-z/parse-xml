package com.example.parsexml;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

public class FoodDetailsActivity extends AppCompatActivity {
    public static final String PREFS_NAME = "SharedPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);
        SharedPreferences storedData = getSharedPreferences(PREFS_NAME, 0);
        String foodId = storedData.getString("id", null);

//        Food currentFood = foodParser.getFood(foodId);
        //выводим в тексовые поля вьюшки поля классов

    }
}