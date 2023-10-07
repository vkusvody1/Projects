package com.sport.allabouttennis;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Random;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TestActivity extends AppCompatActivity {
    String response, question;
    Boolean answer;
    TextView textView;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        textView = findViewById(R.id.textViewTest);
        try {
            run();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int orientation = getResources().getConfiguration().orientation;
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        ConstraintLayout layout1 = findViewById(R.id.linear_test);

        Glide.with(TestActivity.this)
                .load("http://116.203.114.103/AllAboutTennis/test_background.jpg")
                .into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        layout1.setBackground(resource);
                    }
                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                    }
                });
    }

    public void btnTrue(View vv) {
        proverka(true);
    }
    public void btnFalse(View vv) {
        proverka(true);
    }
    public void proverka(Boolean bool) {
        if (bool == answer) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "You are right!", Toast.LENGTH_SHORT);
            toast.show();
        }else {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Wrong!Try again!", Toast.LENGTH_SHORT);
            toast.show();
        }
        vopros(response);
    }

    public void vopros(String response) {
        this.response = response;
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray arr = new JSONArray(jsonObject.getJSONArray("questions").toString());
            Random rand = new Random();

            int n = rand.nextInt(arr.length());
            JSONObject json = new JSONObject(arr.get(n).toString());
            textView.setText(json.optString("question"));
            System.out.println(json.opt("answer"));
            answer = (Boolean) json.opt("answer");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    void run() throws IOException {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://116.203.114.103/AllAboutTennis/questions.json")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String myResponse = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        vopros(myResponse);
                    }

                });

            }
        });
    }
    public void BackBtn(View v) {
        intent = new Intent(TestActivity.this, MenuActivity.class);
        startActivity(intent);

    }
    @Override
    public void onBackPressed () {
        intent = new Intent(TestActivity.this, MenuActivity.class);
        startActivity(intent);

    }
}