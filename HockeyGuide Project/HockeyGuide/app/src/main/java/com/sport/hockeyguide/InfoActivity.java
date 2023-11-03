package com.sport.hockeyguide;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class InfoActivity extends AppCompatActivity {
    ArrayList<DataState> states = new ArrayList<DataState>();
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        int orientation = getResources().getConfiguration().orientation;
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        LinearLayout linear = findViewById(R.id.linear_info);
        Glide.with(InfoActivity.this)
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
        try {
            run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setData(String title,String text, String image){

        states.add(new DataState (title,text,image));


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

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            states = new ArrayList<DataState>();
                            JSONArray arr = new JSONArray(myResponse);
                            JSONObject jsonObject;
                            for (int i = 0 ; i < arr.length() ; i++) {
                                jsonObject = new JSONObject(arr.get(i).toString());
                                System.out.println(jsonObject.optString("head"));
                                setData(jsonObject.optString("head"),
                                        jsonObject.optString("text"),
                                        jsonObject.optString("img"));


                            }
                            RecyclerView recyclerView = findViewById(R.id.list);
                            // создаем адаптер
                            DataStateAdapter adapter = new DataStateAdapter(InfoActivity.this, states);
                            // устанавливаем для списка адаптер
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }




                    }
                });

            }
        });
    }




}