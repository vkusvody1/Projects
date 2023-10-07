package com.example.barcelonatraining;

import org.json.JSONArray;
import org.json.JSONException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

public class TrainingActivity extends AppCompatActivity {
    String dayOfWeek;
    JSONArray arr;
    String url;
    TextView textTrain,textHeader;
    Button btn;
    ScrollView scrollView;
    Calendar calendar;
    int day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planing);
        textTrain = findViewById(R.id.texttrain);
        scrollView = findViewById(R.id.scrollView);
        textHeader = findViewById(R.id.textHeader);
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
        calendar = Calendar.getInstance();
        day = calendar.get(Calendar.DAY_OF_WEEK);
        switch (day) {
            case Calendar.SUNDAY:
                dayOfWeek = "sunday";
                textHeader.setText("ВОСКРЕСЕНЬЕ");
                break;
            case Calendar.MONDAY:
                dayOfWeek = "monday";
                textHeader.setText("ПОНЕДЕЛЬНИК");
                break;
            case Calendar.TUESDAY:
                dayOfWeek = "tuesday";
                textHeader.setText("ВТОРНИК");
                break;
            case Calendar.WEDNESDAY:
                dayOfWeek = "wednesday";
                textHeader.setText("СРЕДА");
                break;
            case Calendar.THURSDAY:
                dayOfWeek = "thursday";
                textHeader.setText("ЧЕТВЕРГ");
                break;
            case Calendar.FRIDAY:
                dayOfWeek = "friday";
                textHeader.setText("ПЯТНИЦА");
                break;
            case Calendar.SATURDAY:
                dayOfWeek = "saturday";
                textHeader.setText("СУББОТА");
                break;
        }
        try {
            url = "http://116.203.114.103/BarcelonaTraining/" + dayOfWeek + ".json";
            run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView gImage;
        public DownloadImageTask(ImageView gImage) {
            this.gImage = gImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap ico = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                ico = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return ico;
        }
        protected void onPostExecute(Bitmap result) {
            gImage.setImageBitmap(result);
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
                TrainingActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            arr = new JSONArray(myResponse);
                            textTrain.setText(arr.getJSONObject(0).optString("text"));
                            new DownloadImageTask((ImageView) findViewById(R.id.imageView))
                                    .execute(arr.getJSONObject(0).optString("img"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }
    public void Btn2(View v) {
        Intent intent = new Intent(TrainingActivity.this, MenuActivity.class);
        startActivity(intent);
    }

    public void Btn(View v) {
        btn.setLayoutParams(new LinearLayout.LayoutParams(0,0));
        scrollView.setOnTouchListener(null);
    }
}