package com.sport.fitnesshelper;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StepsActivity extends AppCompatActivity {

    private static final String SHARED_PREFS_FILE = "step_tracker_prefs";
    private static final String STEPS_KEY = "steps";
    private static final String CALORIES_KEY = "calories";

    private int steps = 0;
    private int calories = 0;

    private TextView tvStepsProgress;
    private TextView tvCalories;
    private EditText etSteps;
    private ProgressBar progressBar;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);

        tvStepsProgress = findViewById(R.id.tvStepsProgress);
        tvCalories = findViewById(R.id.tvCalories);
        etSteps = findViewById(R.id.etSteps);

        Button btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSteps();
            }
        });

        Button btnReset = findViewById(R.id.btnReset);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetProgress();
            }
        });
        progressBar = findViewById(R.id.progressBar);
        progressBar.setMax(10000);
        progressBar.setProgress(steps);
        sharedPreferences = getSharedPreferences(SHARED_PREFS_FILE, MODE_PRIVATE);

        steps = sharedPreferences.getInt(STEPS_KEY, 0);
        calories = sharedPreferences.getInt(CALORIES_KEY, 0);
    }

    private void saveSteps() {
        String stepsText = etSteps.getText().toString();
        if (!stepsText.isEmpty()) {
            int newSteps = Integer.parseInt(stepsText);
            steps += newSteps;
            calories += newSteps * 0.05;
            updateUI();
            etSteps.setText("");

            // Сохранение данных
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(STEPS_KEY, steps);
            editor.putInt(CALORIES_KEY, calories);
            editor.apply();
        }
    }

    private void resetProgress() {
        steps = 0;
        calories = 0;
        updateUI();
    }

    private void updateUI() {
        tvStepsProgress.setText("Steps walked: " + steps);
        tvCalories.setText("Calories: " + calories);
        progressBar.setProgress(steps);
    }
}
