package com.example.parsexml;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import java.io.IOException;
import java.util.ArrayList;

public class FoodXmlParser {
    static final String KEY_FOOD = "food";
    static XmlPullParser xpp;

    public FoodXmlParser(XmlPullParser xpp) {
        FoodXmlParser.xpp = xpp;
    }

    public static ArrayList<Food> getFood() {

        // List of Food, который мы вернём
        ArrayList<Food> foods;
        foods = new ArrayList<>();

        // временное хранилице для Food, пока мы его парсим
        Food curFood = null;
        // временное хронилище  для текста пока мы его парсим
        String curText = "";

        try {
            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagname = xpp.getName();

                String KEY_NAME = "name";
                String KEY_COAST = "price";
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagname.equalsIgnoreCase(KEY_FOOD)) {
                            curFood = new Food();
                        }
                        break;

                    case XmlPullParser.TEXT:
                        curText = xpp.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if (tagname.equalsIgnoreCase(KEY_FOOD)) {
                            foods.add(curFood);
                        } else if (tagname.equalsIgnoreCase(KEY_NAME)) {
                            assert curFood != null;
                            curFood.setName(curText);
                        } else if (tagname.equalsIgnoreCase(KEY_COAST)) {
                            assert curFood != null;
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
}

//    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
//            XmlPullParser xpp = factory.newPullParser();