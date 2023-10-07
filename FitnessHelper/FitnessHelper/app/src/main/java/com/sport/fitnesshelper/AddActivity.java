package com.sport.fitnesshelper;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import android.media.Image;
import android.media.ImageReader;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sport.fitnesshelper.list_classes.State;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class AddActivity extends AppCompatActivity {
    String fileName;
    ArrayList<State> states = new ArrayList<State>();
    EditText editTextName, editTextKkal;
    ArrayList<String> arr = new ArrayList<String>();


    private HandlerThread mBackgroundThread;
    private Handler mBackgroundHandler = null;


    private void startBackgroundThread() {
        mBackgroundThread = new HandlerThread("CameraBackground");
        mBackgroundThread.start();
        mBackgroundHandler = new Handler(mBackgroundThread.getLooper());
    }

    private void stopBackgroundThread() {
        mBackgroundThread.quitSafely();
        try {
            mBackgroundThread.join();
            mBackgroundThread = null;
            mBackgroundHandler = null;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (getArrayList("arr") != null) {
            arr = getArrayList("arr");
        }

    }

    public void onClick(View v) {

        String name = String.valueOf(editTextName.getText());
        String kkal = String.valueOf(editTextKkal.getText());

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = prefs.edit();
        System.out.println("---------------------------------");
        System.out.println(prefs.getInt("imgName", 0));

        arr.add("test"+ prefs.getInt("imgName", 0)+".jpg" + "_" + Calendar.getInstance().getTime() + "_" + name + "_" + kkal);
        saveArrayList(arr,"arr");
        System.out.println("-------------------" + arr);
        editor.putInt("imgName", prefs.getInt("imgName", 0 ) + 1);
        editor.apply();
        Intent intent = new Intent(AddActivity.this, DietActivity.class);
        startActivity(intent);

    }


    public void saveArrayList(ArrayList<String> list, String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();

    }

    public ArrayList<String> getArrayList(String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        return gson.fromJson(json, type);
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        editTextName = findViewById(R.id.editTextTextPersonName);
        editTextKkal = findViewById(R.id.editTextNumber);

    }

    @Override
    public void onPause() {
        stopBackgroundThread();
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        startBackgroundThread();


    }
}