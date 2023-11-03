package com.sport.sportquiz;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

public class MainActivity extends AppCompatActivity {
    Button btn_start;
    ImageView logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_start = findViewById(R.id.button);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) LinearLayout linear = findViewById(R.id.linear_main);
        Glide.with(MainActivity.this)
                .load("http://116.203.114.103/HockeyQuiz/menu_background.png")
                .into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        linear.setBackground(resource);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }
                });

        logo = findViewById(R.id.imageView);
        Glide.with(logo.getContext())
                .load("http://116.203.114.103/HockeyQuiz/hockey1.png")
                .into(logo);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences preferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
        int correctAnswers = preferences.getInt("correct_answers", 0);
        TextView textView = findViewById(R.id.textView);
        textView.setText("Count of correct answers:  " + correctAnswers);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
        int correctAnswers = preferences.getInt("correct_answers", 0);
        TextView textView = findViewById(R.id.textView);
        textView.setText("Count of correct answers:  " + correctAnswers);
    }



}