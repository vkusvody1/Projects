package com.sport.wintersports;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class GuessActivity extends AppCompatActivity {

    private ImageView sportImageView;
    private Button optionButton1, optionButton2, optionButton3, optionButton4;
    private LinearLayout bglay;

    private List<Sport> sportsList;
    private Sport currentSport;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess);

        bglay = findViewById(R.id.bglay);
        sportImageView = findViewById(R.id.sportImageView);
        optionButton1 = findViewById(R.id.optionButton1);
        optionButton2 = findViewById(R.id.optionButton2);
        optionButton3 = findViewById(R.id.optionButton3);
        optionButton4 = findViewById(R.id.optionButton4);

        Glide.with(GuessActivity.this)
                .load("http://116.203.114.103/WinterSports/guess_background.jpg")
                .into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        bglay.setBackground(resource);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                    }
                });

        sportsList = new ArrayList<>();
        sportsList.add(new Sport("Biathlon", "http://116.203.114.103/WinterSports/Biathlon.jpg"));
        sportsList.add(new Sport("Bobsleigh", "http://116.203.114.103/WinterSports/Bobsleigh.jpg"));
        sportsList.add(new Sport("Alpine Skiing", "http://116.203.114.103/WinterSports/Alpine_Skiing.jpg"));
        sportsList.add(new Sport("Curling", "http://116.203.114.103/WinterSports/Curling.jpg"));
        sportsList.add(new Sport("Speed Skating", "http://116.203.114.103/WinterSports/Speed_Skating.jpg"));
        sportsList.add(new Sport("Biathlon Combined", "http://116.203.114.103/WinterSports/Biathlon_Combined.jpg"));
        sportsList.add(new Sport("Cross Country Skiing", "http://116.203.114.103/WinterSports/Cross_Country_Skiing.jpg"));
        sportsList.add(new Sport("Nordic Combined", "http://116.203.114.103/WinterSports/Nordic_Combined.jpg"));
        sportsList.add(new Sport("Skeleton", "http://116.203.114.103/WinterSports/Skeleton.jpg"));
        sportsList.add(new Sport("Snowboarding", "http://116.203.114.103/WinterSports/Snowboarding.jpg"));
        sportsList.add(new Sport("Figure Skating", "http://116.203.114.103/WinterSports/Figure_Skating.jpg"));
        sportsList.add(new Sport("Freestyle Skiing", "http://116.203.114.103/WinterSports/Freestyle_Skiing.jpg"));
        sportsList.add(new Sport("Ice Hockey", "http://116.203.114.103/WinterSports/Ice_Hockey.jpg"));
        sportsList.add(new Sport("Short Track Speed Skating", "http://116.203.114.103/WinterSports/Short_Track_Speed_Skating.jpg"));
        sportsList.add(new Sport("Relay Race", "http://116.203.114.103/WinterSports/Relay_Race.jpg"));

        Random random = new Random();
        currentSport = sportsList.get(random.nextInt(sportsList.size()));

        Picasso.get().load(currentSport.getImageUrl()).into(sportImageView);

        List<Button> optionButtons = new ArrayList<>();
        optionButtons.add(optionButton1);
        optionButtons.add(optionButton2);
        optionButtons.add(optionButton3);
        optionButtons.add(optionButton4);

        Integer f = (int)(Math.random() * 3);

        Set<String> usedSportNames = new HashSet<>();

        for (int i = 0; i < 4; i++) {
            String buttonText;
            if (i == f) {
                buttonText = currentSport.getName();
            } else {
                Sport wrongSport;
                do {
                    wrongSport = sportsList.get(random.nextInt(sportsList.size()));
                } while (usedSportNames.contains(wrongSport.getName()));
                buttonText = wrongSport.getName();
            }
            optionButtons.get(i).setText(buttonText);
            usedSportNames.add(buttonText);
        }

        optionButton1.setOnClickListener(v -> checkAnswer(optionButton1));
        optionButton2.setOnClickListener(v -> checkAnswer(optionButton2));
        optionButton3.setOnClickListener(v -> checkAnswer(optionButton3));
        optionButton4.setOnClickListener(v -> checkAnswer(optionButton4));
    }

    private void checkAnswer(Button selectedButton) {
        if (selectedButton.getText().equals(currentSport.getName())) {
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            recreate(); // Начать новый раунд
        } else {
            Toast.makeText(this, "Wrong choice. Try Again.", Toast.LENGTH_SHORT).show();
        }
    }
}
