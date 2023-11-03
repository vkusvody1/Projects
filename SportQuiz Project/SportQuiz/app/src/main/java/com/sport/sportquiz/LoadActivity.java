package com.sport.sportquiz;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.Executors;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class LoadActivity extends AppCompatActivity {


    private LinearLayout linearLayout;
    private String phoneName;
    private String locale;
    private String uniqueId;
    Intent intent;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        linearLayout = findViewById(R.id.background_layout);

        phoneName = Build.MODEL;
        locale = Locale.getDefault().getLanguage();
        uniqueId = generateUniqueId();

        Glide.with(LoadActivity.this)
                .load("http://116.203.114.103/HockeyQuiz/load_background.jpg")
                .into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        linearLayout.setBackground(resource);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                    }
                });
        intent = new Intent(LoadActivity.this, MainActivity.class);
        Executors.newSingleThreadExecutor().execute(() -> sendPostRequest(phoneName, locale, uniqueId));

    }

    @Override
    protected void onStart() {
        super.onStart();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
            }
        }, 3000);
    }

    private String generateUniqueId() {
        return UUID.randomUUID().toString();
    }


    // отправка пост-запроса
    private void sendPostRequest(String phoneName, String locale, String uniqueId) {
        String urlString = "http://116.203.114.103/splash.php";
        String result = "";

        try {
            OkHttpClient client = new OkHttpClient();

            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
            String postData = "phone_name=" + URLEncoder.encode(phoneName, "UTF-8") +
                    "&locale=" + URLEncoder.encode(locale, "UTF-8") +
                    "&unique=" + URLEncoder.encode(uniqueId, "UTF-8");

            RequestBody body = RequestBody.create(mediaType, postData);

            Request request = new Request.Builder()
                    .url(urlString)
                    .post(body)
                    .build();

            Response response = client.newCall(request).execute();
            result = response.body().string();
            handleResponse(result);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("ne");
        }
        System.out.println(result);
    }

    // обработка ответа
    private void handleResponse(String result) {
        if (result.equals("https://example.com")) {
            intent = new Intent(LoadActivity.this, WebViewActivity.class);
            String url = result;
            intent.putExtra("URL", url);
            System.out.println("da");
        }  else if (result.equals("nopush")) {
            intent = new Intent(LoadActivity.this, MainActivity.class);
            // subscription ne prisilat
            System.out.println("da");
        } else if (result.equals("no")){
            intent = new Intent(LoadActivity.this, MainActivity.class);
            System.out.println("da");
        }
    }
}

