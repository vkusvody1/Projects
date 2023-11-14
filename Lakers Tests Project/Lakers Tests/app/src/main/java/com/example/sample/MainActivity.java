package com.example.sample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sample.catalogClasses.CatalogState;
import com.example.sample.catalogClasses.CatalogStateAdapter;
import com.example.sample.question_classes.QuestionActivity;

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

public class MainActivity extends AppCompatActivity {
    ArrayList<CatalogState> states = new ArrayList<CatalogState>();
    ImageView img;
    TextView text;
    LinearLayout lin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = findViewById(R.id.imageView);
        text = findViewById(R.id.textView3);
        lin = findViewById(R.id.popular);
        try {
            run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setInitialData(String head, String about, String img, String url){
        states.add(new CatalogState(head, about, img, url));
    }

    void run() throws IOException {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://159.69.90.204/api/SportTestsApp/menu/catalog.json")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String myResponse = response.body().string();

                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            System.out.println(myResponse);
                            JSONArray arr = new JSONArray(myResponse);
                            System.out.println(arr.get(0));
                            JSONObject jsonObject;
                            for (int i = 0 ; arr.length() > i ; i++) {
                                jsonObject = new JSONObject(arr.get(i).toString());
                                setInitialData(jsonObject.optString("name"),
                                        jsonObject.optString("about"),
                                        jsonObject.optString("about"),
                                        jsonObject.optString("url"));
                            }
                            jsonObject = new JSONObject(arr.get(3).toString());
                            String popularUrl = jsonObject.optString("url");
                            text.setText(jsonObject.optString("name"));
                            new DownloadImageTask(img).execute(jsonObject.optString("about"));
                            lin.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent(MainActivity.this, QuestionActivity.class);
                                    intent.putExtra("url",popularUrl);
                                    startActivity(intent);
                                }
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                        RecyclerView recyclerView = findViewById(R.id.list);
                        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                        // создаем адаптер
                        CatalogStateAdapter adapter = new CatalogStateAdapter(MainActivity.this, states);
                        // устанавливаем для списка адаптер
                        recyclerView.setAdapter(adapter);




                    }
                });

            }
        });
    }



}