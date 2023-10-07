package com.sport.wintersports;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

public class SportsActivity extends AppCompatActivity {

    LinearLayout linearLay;
    Intent intent;
    String url;
    Button b11,b12,b13,b21,b22,b23,b31,b32,b33,b41,b42,b43,b51,b52,b53;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports);
        linearLay = (LinearLayout) findViewById(R.id.background_layout);

        Glide.with(SportsActivity.this)
                .load("http://116.203.114.103/WinterSports/sports_background.jpg")
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
    public void onClickButton11(View view) {
        intent  = new Intent(SportsActivity.this, FlexActivity.class);
        url = "11";
        intent.putExtra("URL", url);
        startActivity(intent);
    }

    public void onClickButton12(View view) {
        intent  = new Intent(SportsActivity.this, FlexActivity.class);
        url = "12";
        intent.putExtra("URL", url);
        startActivity(intent);
    }

    public void onClickButton13(View view) {
        intent  = new Intent(SportsActivity.this, FlexActivity.class);
        url = "13";
        intent.putExtra("URL", url);
        startActivity(intent);
    }

    public void onClickButton21(View view) {
        intent  = new Intent(SportsActivity.this, FlexActivity.class);
        url = "21";
        intent.putExtra("URL", url);
        startActivity(intent);
    }

    public void onClickButton22(View view) {
        intent  = new Intent(SportsActivity.this, FlexActivity.class);
        url = "22";
        intent.putExtra("URL", url);
        startActivity(intent);
    }

    public void onClickButton23(View view) {
        intent  = new Intent(SportsActivity.this, FlexActivity.class);
        url = "23";
        intent.putExtra("URL", url);
        startActivity(intent);
    }

    public void onClickButton31(View view) {
        intent  = new Intent(SportsActivity.this, FlexActivity.class);
        url = "31";
        intent.putExtra("URL", url);
        startActivity(intent);
    }

    public void onClickButton32(View view) {
        intent  = new Intent(SportsActivity.this, FlexActivity.class);
        url = "32";
        intent.putExtra("URL", url);
        startActivity(intent);
    }

    public void onClickButton33(View view) {
        intent  = new Intent(SportsActivity.this, FlexActivity.class);
        url = "33";
        intent.putExtra("URL", url);
        startActivity(intent);
    }

    public void onClickButton41(View view) {
        intent  = new Intent(SportsActivity.this, FlexActivity.class);
        url = "41";
        intent.putExtra("URL", url);
        startActivity(intent);
    }

    public void onClickButton42(View view) {
        intent  = new Intent(SportsActivity.this, FlexActivity.class);
        url = "42";
        intent.putExtra("URL", url);
        startActivity(intent);
    }

    public void onClickButton43(View view) {
        intent  = new Intent(SportsActivity.this, FlexActivity.class);
        url = "43";
        intent.putExtra("URL", url);
        startActivity(intent);
    }

    public void onClickButton51(View view) {
        intent  = new Intent(SportsActivity.this, FlexActivity.class);
        url = "51";
        intent.putExtra("URL", url);
        startActivity(intent);
    }

    public void onClickButton52(View view) {
        intent  = new Intent(SportsActivity.this, FlexActivity.class);
        url = "52";
        intent.putExtra("URL", url);
        startActivity(intent);
    }

    public void onClickButton53(View view) {
        intent  = new Intent(SportsActivity.this, FlexActivity.class);
        url = "53";
        intent.putExtra("URL", url);
        startActivity(intent);
    }
    @Override
    public void onBackPressed () {
            intent = new Intent(SportsActivity.this, MenuActivity.class);
            startActivity(intent);
    }
}