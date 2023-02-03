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

        Food curFood = null;
        String curText = "";


        try {
            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagname = xpp.getName();

                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagname.equalsIgnoreCase(KEY_FOOD)) {
                            curFood = new Food();
                            curFood.setId(Integer.parseInt(xpp.getAttributeValue(0)));
                        }
                        break;

                    case XmlPullParser.TEXT:
                        curText = xpp.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if (tagname.equalsIgnoreCase(KEY_FOOD)) {
                            foods.add(curFood);
                        } else if (tagname.equalsIgnoreCase(KEY_NAME)) {
                            curFood.setName(curText);
                        } else if (tagname.equalsIgnoreCase(KEY_CALORIES)) {
                            curFood.setCalories(Integer.parseInt(curText));
                        } else if (tagname.equalsIgnoreCase(KEY_COAST)) {
                            curFood.setPrice(curText);
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
        String curText = "";
        String currentId  = "";
        Boolean isFood = false;

        try {
            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagname = xpp.getName();

                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagname.equalsIgnoreCase(KEY_FOOD)) {

                            if (String.valueOf(id).equals(xpp.getAttributeValue(0))) {
                                isFood = true;
                            } else {
                                isFood = false;
                            };
                        }
                    break;

                    case XmlPullParser.TEXT:
                        curText = xpp.getText();
                    break;

                    case XmlPullParser.END_TAG:
                        if (tagname.equalsIgnoreCase(KEY_NAME) && isFood) {
                            currentFood.setName(curText);
                        } else if (tagname.equalsIgnoreCase(KEY_CALORIES) && isFood) {
                            currentFood.setCalories(Integer.parseInt(curText));
                        } else if (tagname.equalsIgnoreCase(KEY_COAST) && isFood) {
                            currentFood.setPrice(curText);
                        }
                    break;

                }
                eventType = xpp.next();
            }

        } catch (XmlPullParserException | IOException e) {
            throw new RuntimeException(e);
        }

        return currentFood;
        // парсим только когда у food находится нужный id
    };
}

//    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
//            XmlPullParser xpp = factory.newPullParser();
