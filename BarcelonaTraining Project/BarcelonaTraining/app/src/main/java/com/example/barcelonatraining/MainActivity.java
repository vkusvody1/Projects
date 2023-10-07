package com.example.barcelonatraining;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    public static final String APP_PREFERENCES_NAME = "myname";
    public static final String APP_PREFERENCES_HEIGHT = "myheight";
    public static final String APP_PREFERENCES_WEIGHT = "myweight";
    public static final String APP_PREFERENCES_SCORE = "myscore";
    public static final String APP_PREFERENCES_DAYOFWEEK = "myday";
    Intent intent;
    String dayOfWeek;
    Calendar calendar;
    Handler handler;
    int day;
    SharedPreferences mSettings;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSettings = getSharedPreferences(APP_PREFERENCES_NAME, Context.MODE_PRIVATE);
        editor = mSettings.edit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!mSettings.getString(APP_PREFERENCES_NAME, "").isEmpty()) {
            intent = new Intent(MainActivity.this, MenuActivity.class);
        } else {
            intent = new Intent(MainActivity.this, DataActivity.class);
        }
        calendar = Calendar.getInstance();
        day = calendar.get(Calendar.DAY_OF_WEEK);

        switch (day) {
            case Calendar.SUNDAY:
                dayOfWeek = "sunday";
                break;
            case Calendar.MONDAY:
                dayOfWeek = "monday";
                break;
            case Calendar.TUESDAY:
                dayOfWeek = "tuesday";
                break;
            case Calendar.WEDNESDAY:
                dayOfWeek = "wednesday";
                break;
            case Calendar.THURSDAY:
                dayOfWeek = "thursday";
                break;
            case Calendar.FRIDAY:
                dayOfWeek = "friday";
                break;
            case Calendar.SATURDAY:
                dayOfWeek = "saturday";
                break;
        }

        if (!mSettings.getString(APP_PREFERENCES_DAYOFWEEK, "").equals(dayOfWeek)) {
            editor.putInt(APP_PREFERENCES_SCORE, 0);
            editor.putString(APP_PREFERENCES_DAYOFWEEK, dayOfWeek);
            editor.apply();
        }

        handler = new Handler();
        handler.postDelayed(() -> startActivity(intent), 3000);

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
