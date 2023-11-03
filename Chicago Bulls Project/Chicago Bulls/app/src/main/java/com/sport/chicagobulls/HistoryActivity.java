package com.sport.chicagobulls;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

public class HistoryActivity extends AppCompatActivity {
    String header, img, text;
    TextView tv_header, tv_text;
    ImageView imageView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        tv_header = findViewById(R.id.textV_header);
        tv_text = findViewById(R.id.tv_text);
        Intent intent = getIntent();
        header = intent.getStringExtra("header");
        img = intent.getStringExtra("img");
        text = intent.getStringExtra("text");
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) LinearLayout linear = findViewById(R.id.linear_history);
        Glide.with(HistoryActivity.this)
                .load("http://116.203.114.103/ChicagoBulls/menu_background.jpg")
                .into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        linear.setBackground(resource);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }
                });


        tv_header.setText(header);
        tv_text.setText(text);
        imageView = findViewById(R.id.imageView2);
        Glide.with(imageView.getContext())
                .load(img)
                .into(imageView);
    }
}