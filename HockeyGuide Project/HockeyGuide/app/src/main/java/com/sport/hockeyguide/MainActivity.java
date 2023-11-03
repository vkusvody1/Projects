package com.sport.hockeyguide;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView logo_menu = findViewById(R.id.iv_menu);
        Glide.with(MainActivity.this)
                .load("http://116.203.114.103/HockeyGuide/logo.png")
                .into(logo_menu);
        int orientation = getResources().getConfiguration().orientation;
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        LinearLayout linear = findViewById(R.id.linear_main);
        Glide.with(MainActivity.this)
                .load("http://116.203.114.103/HockeyGuide/background.png")
                .into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        linear.setBackground(resource);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }
                });
    }


    public void onCLick1(View v) {
        Intent intent = new Intent(MainActivity.this, InfoActivity.class);
        intent.putExtra("url", "http://116.203.114.103/HockeyGuide/taktiki.json");
        startActivity(intent);
    }
    public void onCLick2(View v) {
        Intent intent = new Intent(MainActivity.this, InfoActivity.class);
        intent.putExtra("url", "http://116.203.114.103/HockeyGuide/class.json");
        startActivity(intent);
    }
    public void onCLick3(View v) {
        Intent intent = new Intent(MainActivity.this, InfoActivity.class);
        intent.putExtra("url", "http://116.203.114.103/HockeyGuide/tren.json");
        startActivity(intent);
    }



}