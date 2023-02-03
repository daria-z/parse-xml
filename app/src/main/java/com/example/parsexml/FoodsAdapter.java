package com.example.parsexml;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class FoodsAdapter extends ArrayAdapter<Food> {
    public FoodsAdapter(Context context, ArrayList<Food> foods) {
        super(context, 0, foods);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Food food = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_dish, parent, false);
        }
        TextView foodIdView = convertView.findViewById(R.id.foodlist_foodId);
        TextView foodNameView = convertView.findViewById(R.id.foodName);
        TextView caloriesView = convertView.findViewById(R.id.foodCalories);
        TextView priceView = convertView.findViewById(R.id.foodPrice);
        foodIdView.setText(String.valueOf(food.getId()));
        foodNameView.setText(food.getName());
        caloriesView.setText(String.valueOf(food.getCalories()));
        priceView.setText(String.valueOf(food.getPrice()));
        return convertView;
    }
}
