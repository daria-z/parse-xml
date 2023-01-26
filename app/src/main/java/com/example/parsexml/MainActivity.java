package com.example.parsexml;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<Food> foodsList;
        try {
            XmlPullParser xpp = getResources().getXml(R.xml.foods);
            FoodParser foodParser = new FoodParser(xpp);
            foodsList = foodParser.getFoodsList();

            com.example.parsexml.FoodAdapter adapter = new com.example.parsexml.FoodAdapter(this, foodsList);
            ListView listView = findViewById(R.id.foods_list);
            listView.setAdapter(adapter);

        } catch (XmlPullParserException | IOException e) {
            Toast toast = Toast.makeText(getApplicationContext(), getResources().getText(R.string.file_read_error), Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
// превратить striing в integer (calories) - поменять тип данных
// добавить поле цена и id (вытащить из атрибута)