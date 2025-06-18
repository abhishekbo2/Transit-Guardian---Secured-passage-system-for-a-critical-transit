package com.aibhi.we_will;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashMap;
import java.util.Map;

public class routes extends AppCompatActivity {

    private ImageView homeButton, callButton, profileButton;
    private Button route1, route2;

    // Map for traffic signal IDs
    private final Map<String, Integer> trafficSignalIDs = new HashMap<String, Integer>() {{
        put("Traffic signal 1", 101); // Unique ID for TF1
        put("Traffic signal 2", 102); // Unique ID for TF2
        put("Traffic signal 3", 103); // Unique ID for TF3
        put("Traffic signal 4", 104); // Unique ID for TF4
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routes);

        // Initialize Views
        route1 = findViewById(R.id.route1);
        route2 = findViewById(R.id.route2);
        homeButton = findViewById(R.id.home_button);
        callButton = findViewById(R.id.call_button);
        profileButton = findViewById(R.id.profile_button);

        // Call Button Functionality
        callButton.setOnClickListener(v -> {
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:8296377884")); // Replace with actual number
            startActivity(callIntent);
        });

        // Profile Button Functionality
        profileButton.setOnClickListener(v -> {
            Intent profileIntent = new Intent(routes.this, profile_page.class); // Replace 'profile_page' with your actual profile activity
            startActivity(profileIntent);
        });

        // Home Button Functionality
        homeButton.setOnClickListener(v -> {
            Intent homeIntent = new Intent(routes.this, home_page.class);
            startActivity(homeIntent);
        });

        // Route 1 Button Functionality
        route1.setOnClickListener(v -> handleRouteSelection(
                new String[]{"Traffic signal 1", "Traffic signal 2"}, // Traffic signals for Route 1
                "Route 1"
        ));

        // Route 2 Button Functionality
        route2.setOnClickListener(v -> handleRouteSelection(
                new String[]{"Traffic signal 1", "Traffic signal 2", "Traffic signal 3"}, // Traffic signals for Route 2
                "Route 2"
        ));
    }

    /**
     * Handle the route selection and traffic signal crossing prompts.
     *
     * @param trafficSignals Array of traffic signal names for the selected route.
     * @param routeName      Name of the selected route.
     */
    private void handleRouteSelection(String[] trafficSignals, String routeName) {
        showTrafficSignalDialog(trafficSignals, 0, routeName);
    }

    /**
     * Show a dialog for the current traffic signal.
     *
     * @param trafficSignals Array of traffic signal names.
     * @param index          Current traffic signal index.
     * @param routeName      Name of the selected route.
     */
    private void showTrafficSignalDialog(String[] trafficSignals, int index, String routeName) {
        if (index < trafficSignals.length) {
            String currentSignal = trafficSignals[index];

            // Construct the message dynamically to include the signal number
            String message = "Did you cross traffic signal " + (index + 1)+ " in " + routeName + " ?";

            // Show dialog for the current traffic signal
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setTitle("Traffic Signal Confirmation")
                    .setMessage(message)
                    .setPositiveButton("Yes", (dialogInterface, which) -> {
                        // Proceed to the next traffic signal
                        showTrafficSignalDialog(trafficSignals, index + 1, routeName);
                    })
                    .setNegativeButton("End journey", (dialogInterface, which) -> {
                        // Handle end journey action here
                        Toast.makeText(this, "Journey ended at signal " + (index + 1), Toast.LENGTH_SHORT).show();
                    })
                    .create();

            // Prevent dialog from closing on outside touch
            dialog.setCancelable(false);
            dialog.show();
        } else {
            // Final confirmation dialog for reaching the destination
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setTitle("Reached Destination")
                    .setMessage("Did you reach your destination?")
                    .setPositiveButton("Yes", (dialogInterface, which) -> {
                        // Redirect to home page
                        Intent homeIntent = new Intent(routes.this, home_page.class);
                        startActivity(homeIntent);
                        finish(); // Close the current activity
                    })
                    .setNegativeButton("End journey", (dialogInterface, which) -> {
                        Toast.makeText(this, "Journey ended without reaching destination.", Toast.LENGTH_SHORT).show();
                    })
                    .create();

            // Prevent dialog from closing on outside touch
            dialog.setCancelable(false);
            dialog.show();
        }
    }
}
