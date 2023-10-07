package com.sport.wintersports;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

public class MenuActivity extends AppCompatActivity {

    LinearLayout linearLay;
    Intent intent;
    String url;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        linearLay = (LinearLayout) findViewById(R.id.background_layout);

        Glide.with(MenuActivity.this)
                .load("http://116.203.114.103/WinterSports/menu_background.jpg")
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
    public void onClickButton1(View view) {
        intent  = new Intent(MenuActivity.this, FlexActivity.class);
        url = "rules";
        intent.putExtra("URL", url);
        startActivity(intent);
    }

    public void onClickButton2(View view) {
        intent  = new Intent(MenuActivity.this, SportsActivity.class);
        startActivity(intent);
    }

    public void onClickButton3(View view) {
        intent  = new Intent(MenuActivity.this, GuessActivity.class);
        startActivity(intent);
    }
}