package com.sport.allabouttennis;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        linearLay = (LinearLayout) findViewById(R.id.background_layout);

        Glide.with(MenuActivity.this)
                .load("http://116.203.114.103/AllAboutTennis/menu_background.jpg")
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
    public void onClickInfo(View view) {
        Intent intent = new Intent(MenuActivity.this, InformationActivity.class);
        startActivity(intent);
    }
    public void onClickTF(View view){
        Intent intent = new Intent(MenuActivity.this, TestActivity.class);
        startActivity(intent);
    }
}