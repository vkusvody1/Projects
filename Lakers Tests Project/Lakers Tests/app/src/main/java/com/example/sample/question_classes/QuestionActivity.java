package com.example.sample.question_classes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sample.R;
import com.example.sample.finish_activity.FinishActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class QuestionActivity extends AppCompatActivity {
    TextView textView, question;
    Button btn1, btn2, btn3;
    String url;
    int count,score;
    int Qcount = 1;
    int lenght;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        textView = findViewById(R.id.textView5);
        textView.setText(url);
        question = findViewById(R.id.textView6);
        btn1 = findViewById(R.id.button);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
    }

    @Override
    protected void onStart() {
        super.onStart();
        count = 0;
        score = 0;
        Qcount = 1;

        try {
            run();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void resetData(String question, String btn1Text, String btn2Text, String btn3Text) {
        this.question.setText(question);
        btn1.setText(btn1Text);
        btn2.setText(btn2Text);
        btn3.setText(btn3Text);
    }
    public void finish(int q , int a) {
        System.out.println(score);
        if (q == a) {
            Intent intent = new Intent(QuestionActivity.this, FinishActivity.class);
            intent.putExtra("score", score + "");
            intent.putExtra("Qcount",lenght +"");
            startActivity(intent);
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

                QuestionActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            System.out.println(myResponse);
                            JSONArray arr = new JSONArray(myResponse);
                            System.out.println(arr.get(0));
                            lenght = arr.length();
                            textView.setText("Вопрос " + 1 + " из " + arr.length());
                            JSONObject jsonObject= new JSONObject(arr.get(0).toString());;
                            JSONArray answersArr = new JSONArray(jsonObject.optString("answers"));
                            resetData(jsonObject.optString("question"),
                                    answersArr.get(0).toString(),
                                    answersArr.get(1).toString(),
                                    answersArr.get(2).toString());
                            btn1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    count =  Qcount +1;
                                    textView.setText("Вопрос " + count + " из " + arr.length());
                                    try {
                                        JSONObject jsonObject= new JSONObject(arr.get(Qcount).toString());

                                        JSONArray answersArr = new JSONArray(jsonObject.optString("answers"));
                                        resetData(jsonObject.optString("question"),
                                                answersArr.get(0).toString(),
                                                answersArr.get(1).toString(),
                                                answersArr.get(2).toString());
                                        if (jsonObject.optString("trueanswer").equals("0")) {
                                            score+=1;
                                        }

                                    }catch (JSONException e) {
                                        e.printStackTrace();

                                    }
                                    Qcount+=1;
                                    finish(Qcount,arr.length() );

                                }
                            });
                            btn2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    count =  Qcount +1;

                                    textView.setText("Вопрос " + count + " из " + arr.length());
                                    try {
                                        JSONObject jsonObject= new JSONObject(arr.get(Qcount).toString());

                                        JSONArray answersArr = new JSONArray(jsonObject.optString("answers"));
                                        resetData(jsonObject.optString("question"),
                                                answersArr.get(0).toString(),
                                                answersArr.get(1).toString(),
                                                answersArr.get(2).toString());
                                        if (jsonObject.optString("trueanswer").equals("1")) {
                                            score+=1;
                                        }

                                    }catch (JSONException e) {
                                        e.printStackTrace();

                                    }
                                    Qcount+=1;

                                    finish(Qcount,arr.length() );

                                }
                            });
                            btn3.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    count =  Qcount +1;

                                    textView.setText("Вопрос " + count + " из " + arr.length());

                                    try {
                                        JSONObject jsonObject= new JSONObject(arr.get(Qcount).toString());

                                        JSONArray answersArr = new JSONArray(jsonObject.optString("answers"));
                                        resetData(jsonObject.optString("question"),
                                                answersArr.get(0).toString(),
                                                answersArr.get(1).toString(),
                                                answersArr.get(2).toString());
                                        if (jsonObject.optString("trueanswer").equals("2")) {
                                            score+=1;
                                        }

                                    }catch (JSONException e) {
                                        e.printStackTrace();

                                    }
                                    Qcount+=1;

                                    finish(Qcount,arr.length() );

                                }
                            });


                        } catch (JSONException e) {
                            e.printStackTrace();

                        }





                    }
                });

            }
        });
    }


}