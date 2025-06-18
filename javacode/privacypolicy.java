package com.aibhi.we_will;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class privacypolicy extends Activity {

    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacypolicy);

        // Initialize views
        backButton = findViewById(R.id.back_button);

        // Set up back button to navigate to the Profile page
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(privacypolicy.this, profile_page.class);
            startActivity(intent);
            finish(); // Close the current activity
        });
    }
}
