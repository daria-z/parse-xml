package com.example.parsexml;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import java.io.IOException;
import java.util.ArrayList;

public class FoodXmlParser {
    static final String KEY_FOOD = "food";
    static final String KEY_NAME = "name";
    static final String KEY_CALORIES = "calories";
    static final String KEY_COAST = "price";
    static XmlPullParser xpp;

    public FoodXmlParser(XmlPullParser xpp) {
        FoodXmlParser.xpp = xpp;
    }

    public static ArrayList<Food> getFoodList() {
        ArrayList<Food> foods;
        foods = new ArrayList<>();

        Food currentFood = null;
        String currentText = "";


        try {
            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagname = xpp.getName();

                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagname.equalsIgnoreCase(KEY_FOOD)) {
                            currentFood = new Food();
                            currentFood.setId(Integer.parseInt(xpp.getAttributeValue(0)));
                        }
                        break;

                    case XmlPullParser.TEXT:
                        currentText = xpp.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if (tagname.equalsIgnoreCase(KEY_FOOD)) {
                            foods.add(currentFood);
                        } else if (tagname.equalsIgnoreCase(KEY_NAME)) {
                            currentFood.setName(currentText);
                        } else if (tagname.equalsIgnoreCase(KEY_CALORIES)) {
                            currentFood.setCalories(Integer.parseInt(currentText));
                        } else if (tagname.equalsIgnoreCase(KEY_COAST)) {
                            currentFood.setPrice(currentText);
                        }
                        break;

                    default:
                        break;
                }

                eventType = xpp.next();
            }

        } catch (XmlPullParserException | IOException e) {
            throw new RuntimeException(e);
        }

        return foods;

    }

    public Food getFood(int id) {
        Food currentFood = new Food();
        String currentText = "";
        Boolean isFoodRight = false;

        try {
            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagname = xpp.getName();

                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagname.equalsIgnoreCase(KEY_FOOD)) {

                            if (String.valueOf(id).equals(xpp.getAttributeValue(0))) {
                                isFoodRight = true;
                            } else {
                                isFoodRight = false;
                            };
                        }
                    break;

                    case XmlPullParser.TEXT:
                        currentText = xpp.getText();
                    break;

                    case XmlPullParser.END_TAG:
                        if (tagname.equalsIgnoreCase(KEY_NAME) && isFoodRight) {
                            currentFood.setName(currentText);
                        } else if (tagname.equalsIgnoreCase(KEY_CALORIES) && isFoodRight) {
                            currentFood.setCalories(Integer.parseInt(currentText));
                        } else if (tagname.equalsIgnoreCase(KEY_COAST) && isFoodRight) {
                            currentFood.setPrice(currentText);
                        }
                    break;

                }
                eventType = xpp.next();
            }

        } catch (XmlPullParserException | IOException e) {
            throw new RuntimeException(e);
        }

        return currentFood;
    };
}
