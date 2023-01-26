package com.example.parsexml;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.parsexml.Food;
import com.example.parsexml.R;

import java.util.ArrayList;

public class FoodAdapter extends ArrayAdapter<Food> {
    public FoodAdapter(Context context, ArrayList<Food> foods) {
        super(context, 0, foods);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Food food = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_dish, parent, false);
        }
        TextView foodNameView = convertView.findViewById(R.id.foodName);
        TextView priceView = convertView.findViewById(R.id.foodPrice);
        foodNameView.setText(food.getName());
        priceView.setText(food.getPrice());
        return convertView;
    }
}
