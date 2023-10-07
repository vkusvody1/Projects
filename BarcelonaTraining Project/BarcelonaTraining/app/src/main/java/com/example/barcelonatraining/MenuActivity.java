package com.example.barcelonatraining;

import static com.example.barcelonatraining.MainActivity.APP_PREFERENCES_NAME;
import static com.example.barcelonatraining.MainActivity.APP_PREFERENCES_SCORE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;


public class MenuActivity extends AppCompatActivity {
    SharedPreferences mSettings;
    Intent intent;
    ProgressBar progressBar;
    TextView textView;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        progressBar = findViewById(R.id.progressBar);
        textView =findViewById(R.id.textViewProgress);
        mSettings = getSharedPreferences(APP_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        progressBar.setProgress(mSettings.getInt(APP_PREFERENCES_SCORE,0));
        textView.setText(Html.fromHtml("<font color=#EDBB00>ПРОГРЕСС: </font>" +
                "<font color=#A50044>" +
                mSettings.getInt(APP_PREFERENCES_SCORE,0) +
                "</font>" +"<font color=#EDBB00>P</font>"));
    }


     public void goToAnal(View view) {
        intent  = new Intent(MenuActivity.this, AnaliticsActivity.class);
        startActivity(intent);
    }
    public void goToSet(View view) {
        intent  = new Intent(MenuActivity.this, SettingsActivity.class);
        startActivity(intent);
    }
    public void goToTraining(View view) {
        intent  = new Intent(MenuActivity.this, TrainingActivity.class);
        startActivity(intent);
    }
    public void goToQuestion(View view) {
        intent  = new Intent(MenuActivity.this, QuestionActivity.class);
        startActivity(intent);
    }
}