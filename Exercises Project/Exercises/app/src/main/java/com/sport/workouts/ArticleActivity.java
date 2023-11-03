package com.sport.workouts;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import pl.droidsonroids.gif.GifImageView;

public class ArticleActivity extends AppCompatActivity {

    private LinearLayout articleContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        articleContainer = findViewById(R.id.articleContainer);

        String groupName = getIntent().getStringExtra("GROUP_NAME");

        loadArticles(groupName);
    }

    private void loadArticles(String groupName) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("http://116.203.114.103/Exercises/data.json");
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                    try {
                        InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                        StringBuilder result = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            result.append(line);
                        }

                        JSONObject jsonObject = new JSONObject(result.toString());
                        JSONArray groupArray = jsonObject.getJSONArray(groupName);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    for (int i = 0; i < groupArray.length(); i++) {
                                        JSONObject article = groupArray.getJSONObject(i);
                                        String header = article.getString("header");
                                        String imageURL = article.getString("img");
                                        String content = article.getString("text");

                                        // Создаем новые элементы интерфейса для каждой статьи
                                        LinearLayout articleLayout = new LinearLayout(ArticleActivity.this);
                                        articleLayout.setOrientation(LinearLayout.VERTICAL);

                                        TextView headerTextView = new TextView(ArticleActivity.this);
                                        headerTextView.setText(header);
                                        headerTextView.setTextSize(24);
                                        headerTextView.setPadding(0, 0, 0, 16);

                                        GifImageView imageView = new GifImageView(ArticleActivity.this);
                                        imageView.setMaxHeight(100);
                                        // Загружаем изображение с помощью библиотеки, например, Picasso
                                        Ion.with(imageView).load(imageURL);

                                        TextView contentTextView = new TextView(ArticleActivity.this);
                                        contentTextView.setText(content);
                                        contentTextView.setTextSize(16);

                                        // Добавляем элементы в макет
                                        articleLayout.addView(headerTextView);
                                        articleLayout.addView(imageView);
                                        articleLayout.addView(contentTextView);

                                        // Добавляем макет статьи в контейнер
                                        articleContainer.addView(articleLayout);
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });

                    } finally {
                        urlConnection.disconnect();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
