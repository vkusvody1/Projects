package com.sport.wintersports;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FlexActivity extends AppCompatActivity {

    LinearLayout linearLay;
    JSONArray arr;
    String url, bgurl, hach;
    TextView textTrain;
    Button btn;
    ScrollView scrollView;
    ImageView imageView;
    Intent intent;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hach = getIntent().getStringExtra("URL");
        setContentView(R.layout.activity_flex);
        linearLay = (LinearLayout) findViewById(R.id.background_layout);
        textTrain = findViewById(R.id.texttrain);
        scrollView = findViewById(R.id.scrollView);
        btn = findViewById(R.id.button);
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        if (hach.equals("rules")) {
            bgurl = "http://116.203.114.103/WinterSports/rules_background.png";
        } else {
            bgurl = "http://116.203.114.103/WinterSports/sports_background.jpg";
        }
        Glide.with(FlexActivity.this)
                .load(bgurl)
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
    @Override
    protected void onStart() {
        super.onStart();
        try {
            url = "http://116.203.114.103/WinterSports/" + hach + ".json";
            run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void run() throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String myResponse = response.body().string();
                FlexActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            arr = new JSONArray(myResponse);
                            textTrain.setText(arr.getJSONObject(0).optString("text"));
//                            Picasso.get().load(arr.getJSONObject(0).optString("img")).into(imageView);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }
    public void Btn2(View v) {
        if (hach.equals("rules")) {
            intent = new Intent(FlexActivity.this, MenuActivity.class);
            startActivity(intent);
        } else {
            intent = new Intent(FlexActivity.this, SportsActivity.class);
            startActivity(intent);
        }
    }
    public void Btn(View v) {
        btn.setLayoutParams(new LinearLayout.LayoutParams(0,0));
        scrollView.setOnTouchListener(null);
    }
    @Override
    public void onBackPressed () {
        if (hach.equals("rules")) {
            intent = new Intent(FlexActivity.this, MenuActivity.class);
            startActivity(intent);
        } else {
            intent = new Intent(FlexActivity.this, SportsActivity.class);
            startActivity(intent);
        }
    }
}