package com.example.parsexml;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;

public class FoodDetailsActivity extends AppCompatActivity {
    public static final String PREFS_NAME = "SharedPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);
        SharedPreferences storedData = getSharedPreferences(PREFS_NAME, 0);
        int foodId = storedData.getInt("id", 0);
        XmlPullParser xpp = getResources().getXml(R.xml.foods);

        FoodXmlParser foodParser = new FoodXmlParser(xpp);
        Food currentFood = foodParser.getFood(foodId);

        TextView name = (TextView) findViewById(R.id.foodName);
        name.setText(currentFood.getName());

        TextView calories = (TextView) findViewById(R.id.foodCalories);
        calories.setText(String.valueOf(currentFood.getCalories()));

        TextView price = (TextView) findViewById(R.id.foodPrice);
        price.setText(currentFood.getPrice());

    }

    public void goBack(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}