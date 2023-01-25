package com.example.parsexml;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.xmlpull.v1.XmlPullParser;



public class MainActivity extends AppCompatActivity {
    private String KEY_NAME = "name";
    private String KEY_COAST = "price";
    private String curText;

    private static final String TAG = "MainActivity";

    @Override    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            XmlPullParser xpp = getResources().getXml(R.xml.foods);
            int eventType = xpp.getEventType();


            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagname = xpp.getName();

                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagname.equalsIgnoreCase(KEY_NAME)) {
                            Log.v(TAG, "Start tag " + xpp.getName());
                        }
                        break;
                    case XmlPullParser.TEXT:
                        curText = xpp.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if (tagname.equalsIgnoreCase(KEY_NAME)) {
                            Log.v(TAG, curText);
                        } else if (tagname.equalsIgnoreCase(KEY_COAST)) {
                            Log.v(TAG, curText);
                        }
                        break;
                }

                eventType = xpp.next();
            }

            Log.v(TAG, "End document");
        }
        catch (Throwable t) {
            Toast.makeText(this,
                            "Ошибка при загрузке XML-документа: " + t, Toast.LENGTH_SHORT)
                    .show();
        }
    }
}
// превратить striing в integer (calories) - поменять тип данных
// добавить поле цена и id (вытащить из атрибута)