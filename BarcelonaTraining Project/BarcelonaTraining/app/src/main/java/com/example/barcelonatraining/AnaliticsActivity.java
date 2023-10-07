package com.example.barcelonatraining;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import static com.example.barcelonatraining.MainActivity.APP_PREFERENCES_NAME;
import static com.example.barcelonatraining.MainActivity.APP_PREFERENCES_WEIGHT;
import static com.example.barcelonatraining.MainActivity.APP_PREFERENCES_SCORE;

public class AnaliticsActivity extends AppCompatActivity {
    SharedPreferences mSettings;
    SharedPreferences.Editor editor;
    EditText editTextR, editTextP;
    ProgressBar progressBar;
    TextView textView;
    int Rast = 0;
    int Pris = 0;
    int weight;
    int res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);
        editTextR = findViewById(R.id.EtRast);
        editTextP = findViewById(R.id.EtPris);
        progressBar= findViewById(R.id.progressBar3);
        textView = findViewById(R.id.textView7);
        mSettings = getSharedPreferences(APP_PREFERENCES_NAME, Context.MODE_PRIVATE);
        editor = mSettings.edit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        progressBar.setProgress(mSettings.getInt(APP_PREFERENCES_SCORE,0));
        textView.setText(Html.fromHtml("<font color=#ffffff>ПРОГРЕСС: </font>" +
                "<font color=#e97216>" +
                mSettings.getInt(APP_PREFERENCES_SCORE,0) +
                "</font>" +"<font color=#ffffff>P</font>"));
    }

    public void onClick (View v) {
            weight = Integer.parseInt(mSettings.getString(APP_PREFERENCES_WEIGHT,""));
            Rast = Integer.parseInt(editTextR.getText().toString());
            Pris = Integer.parseInt(editTextP.getText().toString());
            res = (((Rast + Pris*10)*weight)/1000)/2;
            int prog = progressBar.getProgress() + res;
            editor.putInt(APP_PREFERENCES_SCORE, prog);
            progressBar.setProgress(progressBar.getProgress() + res);
            String text = "<font color=" + "#e97216" + ">" + prog + "</font>";
            textView.setText(Html.fromHtml("<font color=#ffffff>ПРОГРЕСС: </font>" +
                    "<font color=#e97216>" +
                    prog +
                    "</font>" +"<font color=#ffffff> P</font>"));
                   editor.apply();
    }
}