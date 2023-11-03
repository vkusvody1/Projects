package com.sport.fitnesshelper;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class DietActivity extends AppCompatActivity {
    private TextView caloriesTextView;
    private Button addButton;
    private Button clearButton;
    private RecyclerView recyclerView;

    private int totalCalories = 0;
    private ArrayList<FoodItem> foodItems = new ArrayList<>();
    private FoodAdapter foodAdapter;

    private SharedPreferences sharedPreferences;
    private Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);

        caloriesTextView = findViewById(R.id.caloriesTextView);
        addButton = findViewById(R.id.addButton);
        clearButton = findViewById(R.id.clearButton);
        recyclerView = findViewById(R.id.recyclerView);

        // Настройка RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        foodAdapter = new FoodAdapter(foodItems);
        recyclerView.setAdapter(foodAdapter);

        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        loadSavedData();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DietActivity.this, AddActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totalCalories = 0;
                foodItems.clear();
                updateCaloriesDisplay();
                foodAdapter.notifyDataSetChanged();
                saveData();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String name = data.getStringExtra("name");
                int calories = data.getIntExtra("calories", 0);

                FoodItem foodItem = new FoodItem(name, calories);
                foodItems.add(foodItem);
                totalCalories += calories;

                updateCaloriesDisplay();
                foodAdapter.notifyDataSetChanged();
                saveData();
            }
        }
    }

    private void updateCaloriesDisplay() {
        caloriesTextView.setText("Calories: " + totalCalories);
    }

    private void saveData() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String json = gson.toJson(foodItems);
        editor.putString("foodItems", json);
        editor.putInt("totalCalories", totalCalories);
        editor.apply();
    }

    private void loadSavedData() {
        String json = sharedPreferences.getString("foodItems", null);
        Type type = new TypeToken<ArrayList<FoodItem>>() {}.getType();
        ArrayList<FoodItem> savedFoodItems = gson.fromJson(json, type);

        if (savedFoodItems != null) {
            foodItems.addAll(savedFoodItems);
        }

        totalCalories = sharedPreferences.getInt("totalCalories", 0);

        updateCaloriesDisplay();
    }
}
