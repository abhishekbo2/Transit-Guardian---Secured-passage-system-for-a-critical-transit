package com.aibhi.we_will;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class destination extends AppCompatActivity {

    private ImageView backButton;
    private EditText myLocationInput, destinationInput;
    private Button startJourneyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);

        // Initialize Views
        backButton = findViewById(R.id.back_button);
        myLocationInput = findViewById(R.id.my_location);
        destinationInput = findViewById(R.id.destination_location);
        startJourneyButton = findViewById(R.id.start);

        // Back Button Functionality
        backButton.setOnClickListener(v -> {
            // Navigate to home page
            Intent homeIntent = new Intent(destination.this, home_page.class);
            startActivity(homeIntent);
            finish(); // Finish the current activity to prevent going back
        });

        // Start Journey Button Functionality
        startJourneyButton.setOnClickListener(v -> {
            // Get the user input for My Location and Destination
            String myLocation = myLocationInput.getText().toString().trim();
            String destination = destinationInput.getText().toString().trim();

            // Ensure inputs are not empty before proceeding
            if (myLocation.isEmpty()) {
                myLocationInput.setError("Please enter your location");
                myLocationInput.requestFocus();
                return;
            }

            if (destination.isEmpty()) {
                destinationInput.setError("Please enter your destination");
                destinationInput.requestFocus();
                return;
            }

            // Navigate to the route page
            Intent routeIntent = new Intent(destination.this, routes.class);

            // Pass starting and destination points to the route page
            routeIntent.putExtra("MY_LOCATION", myLocation);
            routeIntent.putExtra("DESTINATION", destination);

            startActivity(routeIntent);
        });
    }
}
