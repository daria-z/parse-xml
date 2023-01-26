package com.example.parsexml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

public class FoodParser {
    private static String KEY_NAME = "name";
    private static String KEY_PRICE = "price";

    private static XmlPullParser xpp;

    public FoodParser(XmlPullParser xpp) throws XmlPullParserException, IOException {
        this.xpp = xpp;
    }

    public static ArrayList<Food> getFoodsList() throws XmlPullParserException {
        ArrayList<Food> foods = new ArrayList<>();
        int eventType = xpp.getEventType();

        try {
            String nodeName = "";

            String foodName = "";
            String price = "";

            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagname = xpp.getName();

                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagname.equals(KEY_NAME)) {
                            nodeName = KEY_NAME;
                        } else if (tagname.equals(KEY_PRICE)) {
                            nodeName = KEY_PRICE;
                        } else {
                            nodeName = "";
                        }
                    break;

                    case XmlPullParser.TEXT:
                        if (nodeName.equals(KEY_NAME)) {
                            foodName = xpp.getText();
                        } else if (nodeName.equals(KEY_PRICE)) {
                            price = xpp.getText();
                        }
                    break;

                    case XmlPullParser.END_TAG:
                        if (tagname.equals("food")) {
                            foods.add(new Food(foodName, price));
                        }
                        break;

                    default:
                        break;
                }
                eventType = xpp.next();
            }



            return foods;
        } catch (Throwable t) {
            return foods;
        }
    }
}
