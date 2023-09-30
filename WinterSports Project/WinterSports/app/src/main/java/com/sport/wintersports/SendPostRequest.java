package com.sport.wintersports;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URLEncoder;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SendPostRequest extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... params) {
        String phoneName = params[0];
        String locale = params[1];
        String uniqueId = params[2];

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
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        try {
            JSONObject jsonObject = new JSONObject(result);
            String url = jsonObject.getString("url");
            if (url.equals("https://example.com")) {
                // Открыть WebView с ссылкой
            } else if (url.equals("no")) {
                // Перейти на основной функционал приложения
            } else if (url.equals("nopush")) {
                // Перейти на основной функционал и отписать пользователя от пушей
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
