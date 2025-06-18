package com.aibhi.we_will;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class home_page extends AppCompatActivity {

    private ImageView ivProfilePicture;
    private ImageView ivEmergencyButton;
    private ImageView homeButton, callButton, profileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        // Initialize Views
        ivProfilePicture = findViewById(R.id.ivProfilePicture);
        ivEmergencyButton = findViewById(R.id.ivEmergencyButton);
        homeButton = findViewById(R.id.home_button);
        callButton = findViewById(R.id.call_button);
        profileButton = findViewById(R.id.profile_button);

        // Emergency Button Functionality
        ivEmergencyButton.setOnClickListener(v -> {
            Toast.makeText(home_page.this, "Emergency button pressed!", Toast.LENGTH_SHORT).show();
            // Add emergency action logic here if needed
            Intent route = new Intent(home_page.this, destination.class); // Replace 'profile_page' with your actual profile activity
            startActivity(route);
        });

        // Home Button Functionality
        homeButton.setOnClickListener(v -> {
            Toast.makeText(home_page.this, "You are already on the Home Page.", Toast.LENGTH_SHORT).show();
        });

        // Call Button Functionality
        callButton.setOnClickListener(v -> {
            // Intent to make a call
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:8296377884")); // Replace with actual number
            startActivity(callIntent);
        });

        // Profile Button Functionality
        profileButton.setOnClickListener(v -> {
            // Redirect to Profile Activity
            Intent profileIntent = new Intent(home_page.this, profile_page.class); // Replace 'profile_page' with your actual profile activity
            startActivity(profileIntent);
        });

        // Profile Picture Functionality (Optional)
        ivProfilePicture.setOnClickListener(v -> {
            Toast.makeText(home_page.this, "Profile logo clicked!", Toast.LENGTH_SHORT).show();
            // Add logic to update profile picture if needed
        });
    }
}
