package com.example.barcelonatraining;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPreferences {
    private static final String SHARED_PREFS_NAME = "MyPrefs";
    private SharedPreferences sharedPreferences;
    private static MySharedPreferences instance;

    private MySharedPreferences(Context context) {
        sharedPreferences = context.getApplicationContext().getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);
    }

    public static synchronized MySharedPreferences getInstance(Context context) {
        if (instance == null) {
            instance = new MySharedPreferences(context);
        }
        return instance;
    }

    public void saveTextValue(String key, String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }

    public String getTextValue(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }

    public void saveIntValue(String key, int value) {
        sharedPreferences.edit().putInt(key, value).apply();
    }

    public int getIntValue(String key, int defaultValue) {
        return sharedPreferences.getInt(key, defaultValue);
    }
}
