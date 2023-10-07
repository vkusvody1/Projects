package com.example.barcelonatraining;

import androidx.appcompat.app.AppCompatActivity;
import static com.example.barcelonatraining.MainActivity.APP_PREFERENCES_NAME;
import static com.example.barcelonatraining.MainActivity.APP_PREFERENCES_HEIGHT;
import static com.example.barcelonatraining.MainActivity.APP_PREFERENCES_WEIGHT;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class DataActivity extends AppCompatActivity {
    SharedPreferences mSettings;
    SharedPreferences.Editor editor;
    EditText editName, editHeight, editWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        editName = findViewById(R.id.EtDataName);
        editHeight = findViewById(R.id.EtDataHeight);
        editWeight = findViewById(R.id.EtDataWeight);
        mSettings = getSharedPreferences(APP_PREFERENCES_NAME, Context.MODE_PRIVATE);
        editor = mSettings.edit();
    }
    public void onClick(View v) {
        editor.putString(APP_PREFERENCES_NAME, editName.getText().toString());
        editor.putString(APP_PREFERENCES_WEIGHT, editWeight.getText().toString());
        editor.putString(APP_PREFERENCES_HEIGHT, editHeight.getText().toString());
        editor.apply();
        Intent intent = new Intent(DataActivity.this, MenuActivity.class);
        startActivity(intent);
    }
}
