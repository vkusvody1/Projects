package com.sport.workouts;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

public class MenuActivity extends AppCompatActivity {

    LinearLayout linearLay;
    String url;
    RadioGroup radioGroup;
    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        linearLay = (LinearLayout) findViewById(R.id.background_layout);
        radioGroup = findViewById(R.id.radioGroup);

        Glide.with(MenuActivity.this)
                .load("http://116.203.114.103/Exercises/menu_background.jpg")
                .into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        linearLay.setBackground(resource);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                    }
                });
    }
    public void onClick1(View v) {
        Intent intent = new Intent(MenuActivity.this, ArticleActivity.class);
        int checkedbutton = radioGroup.getCheckedRadioButtonId();

        if (checkedbutton == R.id.radioButton1) {
            url = "legs";
        } if (checkedbutton == R.id.radioButton2) {
            url = "back";
        } if (checkedbutton == R.id.radioButton3) {
            url = "core";
        } if (checkedbutton == R.id.radioButton4) {
            url = "arms";
        }
        intent.putExtra("GROUP_NAME", url);
        startActivity(intent);
    }
}