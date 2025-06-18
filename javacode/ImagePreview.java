package com.aibhi.we_will;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

class imagepreview extends AppCompatActivity {

    private ImageView fullImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_preview);

        // Initialize the ImageView
        fullImageView = findViewById(R.id.fullImageView);

        // Get the image URI passed from the previous activity
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("imageUri")) {
            String imageUriString = intent.getStringExtra("imageUri");
            if (imageUriString != null) {
                Uri imageUri = Uri.parse(imageUriString);
                fullImageView.setImageURI(imageUri); // Display the image in the ImageView
            } else {
                Toast.makeText(this, "Failed to load image!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "No image available!", Toast.LENGTH_SHORT).show();
        }
    }
}
