package com.aibhi.we_will;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class languages extends AppCompatActivity {

    private ImageView backButton;
    private RadioButton englishUS, englishUK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_languages);

        // Initialize Views
        backButton = findViewById(R.id.back_button);
        englishUS = findViewById(R.id.englishUS);
        englishUK = findViewById(R.id.englishUK);

        // Back Button Click Listener
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(languages.this, profile_page.class);
            startActivity(intent);
            finish();
        });

        // Radio Button Listeners
        englishUS.setOnClickListener(v -> {
            englishUK.setChecked(false); // Deselect the other radio button
            Toast.makeText(this, "Selected: English (US)", Toast.LENGTH_SHORT).show();
        });

        englishUK.setOnClickListener(v -> {
            englishUS.setChecked(false); // Deselect the other radio button
            Toast.makeText(this, "Selected: English (UK)", Toast.LENGTH_SHORT).show();
        });
    }
}
