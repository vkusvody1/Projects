package com.sport.fitnesshelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

public class MenuActivity extends AppCompatActivity {

    LinearLayout linearLay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        linearLay = (LinearLayout) findViewById(R.id.background_layout);

        Glide.with(MenuActivity.this)
                .load("http://116.203.114.103/FitnessHelper/load_background.jpg")
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
    public void onClick1(View view) {
        Intent intent = new Intent(MenuActivity.this, InfoActivity.class);
        startActivity(intent);
    }
    public void onClick2(View view) {
        Intent intent = new Intent(MenuActivity.this, DietActivity.class);
        startActivity(intent);
    }
    public void onClick3(View view) {
        Intent intent = new Intent(MenuActivity.this, StepsActivity.class);
        startActivity(intent);
    }
}
