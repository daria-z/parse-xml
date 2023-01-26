package com.example.parsexml;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.xmlpull.v1.XmlPullParser;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<Food> foodsList;
        XmlPullParser xpp = getResources().getXml(R.xml.foods);
        FoodXmlParser foodParser = new FoodXmlParser(xpp);
        foodsList = FoodXmlParser.getFood();

        FoodsAdapter adapter = new FoodsAdapter(this, foodsList);
        ListView listView = findViewById(R.id.food_list);
        listView.setAdapter(adapter);

    }
}
// превратить striing в integer (calories) - поменять тип данных
// добавить поле цена и id (вытащить из атрибута)