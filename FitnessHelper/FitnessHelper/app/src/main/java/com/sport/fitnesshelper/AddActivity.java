package com.sport.fitnesshelper;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {
    private EditText nameEditText;
    private EditText caloriesEditText;
    private Button doneButton;
    private Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        nameEditText = findViewById(R.id.nameEditText);
        caloriesEditText = findViewById(R.id.caloriesEditText);
        doneButton = findViewById(R.id.doneButton);
        cancelButton = findViewById(R.id.cancelButton);

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameEditText.getText().toString();
                int calories = Integer.parseInt(caloriesEditText.getText().toString());

                Intent resultIntent = new Intent();
                resultIntent.putExtra("name", name);
                resultIntent.putExtra("calories", calories);
                setResult(RESULT_OK, resultIntent);

                finish();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
