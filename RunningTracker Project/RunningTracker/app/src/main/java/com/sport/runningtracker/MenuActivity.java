package com.sport.runningtracker;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    private ArrayList<Run> runList;
    private RunAdapter adapter;
    private double totalDistance = 0;
    private double dailyGoal = 5; // Установите цель на день в километрах

    private EditText distanceInput;
    private EditText timeInput;
    private TextView progressText;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        runList = new ArrayList<>();
        adapter = new RunAdapter(this, runList);

        ListView listView = findViewById(R.id.run_list);
        listView.setAdapter(adapter);

        distanceInput = findViewById(R.id.distance_input);
        timeInput = findViewById(R.id.time_input);
        progressText = findViewById(R.id.progress_text);
        progressBar = findViewById(R.id.progress_bar);

        updateProgressBar();

        Button addRunButton = findViewById(R.id.add_run_button);
        addRunButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String distance = distanceInput.getText().toString();
                String time = timeInput.getText().toString();

                if (!distance.isEmpty() && !time.isEmpty()) {
                    double distanceValue = Double.parseDouble(distance);
                    totalDistance += distanceValue;

                    Run run = new Run(distance, time);
                    runList.add(run);
                    adapter.notifyDataSetChanged();

                    distanceInput.setText("");
                    timeInput.setText("");

                    updateProgressBar();
                }
            }
        });
    }

    @SuppressLint("ResourceType")
    private void updateProgressBar() {
        int progress = (int) ((totalDistance / dailyGoal) * 100);
        progressBar.setProgress(progress);
        progressText.setText(getString(R.id.progress_text,totalDistance, dailyGoal));
    }
}
