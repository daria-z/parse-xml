package com.example.parsexml;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.xmlpull.v1.XmlPullParser;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    public static final String PREFS_NAME = "SharedPrefsFile";
    ArrayList<Food> foodsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        XmlPullParser xpp = getResources().getXml(R.xml.foods);
        FoodXmlParser foodParser = new FoodXmlParser(xpp);
        foodsList = FoodXmlParser.getFoodList();

        FoodsAdapter adapter = new FoodsAdapter(this, foodsList);
        ListView listView = findViewById(R.id.food_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(foodsListClickedHandler);

    }

    private AdapterView.OnItemClickListener foodsListClickedHandler = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView parent, View v, int position, long id) {
            goForward(position);
        }
    };

    private  void goForward( int position ) {
        Food selectedFood = foodsList.get(position);
        SharedPreferences storedData = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor storedDataEditor = storedData.edit();
        storedDataEditor.putInt("id", selectedFood.getId());
        storedDataEditor.commit();
        Intent intent = new Intent(this, FoodDetailsActivity.class);
        startActivity(intent);
    }
}