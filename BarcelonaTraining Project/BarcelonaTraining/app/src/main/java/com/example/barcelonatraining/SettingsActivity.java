package com.example.barcelonatraining;

import androidx.appcompat.app.AppCompatActivity;
import static com.example.barcelonatraining.MainActivity.APP_PREFERENCES_NAME;
import static com.example.barcelonatraining.MainActivity.APP_PREFERENCES_HEIGHT;
import static com.example.barcelonatraining.MainActivity.APP_PREFERENCES_WEIGHT;
import static com.example.barcelonatraining.MainActivity.APP_PREFERENCES_SCORE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class SettingsActivity extends AppCompatActivity {
    SharedPreferences mSettings;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        mSettings = getSharedPreferences(APP_PREFERENCES_NAME, Context.MODE_PRIVATE);
        editor = mSettings.edit();
    }
    public void onClick (View v) {
        editor.putString(APP_PREFERENCES_NAME, "");
        editor.putString(APP_PREFERENCES_WEIGHT, "");
        editor.putString(APP_PREFERENCES_HEIGHT, "");
        editor.putInt(APP_PREFERENCES_SCORE,0);
        Intent intent = new Intent(SettingsActivity.this, DataActivity.class);
        startActivity(intent);
        editor.apply();
        System.out.println(mSettings.getString(APP_PREFERENCES_NAME,""));
    }
}