package com.sport.fitnesshelper;


import android.annotation.SuppressLint;
import android.content.Intent;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class InfoActivity extends AppCompatActivity {

    LinearLayout linearLay;
    JSONArray arr;
    String url;
    TextView textTrain;
    Button btn;
    ScrollView scrollView;
    ImageView imageView;
    Intent intent;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
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
    }
    @Override
    protected void onStart() {
        super.onStart();
        try {
            url = "http://116.203.114.103/FitnessHelper/info.json";
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
                InfoActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            arr = new JSONArray(myResponse);
                            textTrain.setText(arr.getJSONObject(0).optString("text"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }
    public void Btn(View v) {
        btn.setLayoutParams(new LinearLayout.LayoutParams(0,0));
        scrollView.setOnTouchListener(null);
    }
    @Override
    public void onBackPressed () {
        super.onBackPressed();
        intent = new Intent(InfoActivity.this, MenuActivity.class);
        startActivity(intent);
    }
}