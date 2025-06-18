package com.aibhi.we_will;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

public class notification extends Activity {

    private Switch switchGeneralNotification, switchSound, switchVibrate;
    private Switch switchAppUpdates, switchBillReminder, switchPromotion;
    private Switch switchDiscountAvailable, switchPaymentRequest;
    private Switch switchNewServiceAvailable, switchNewTipsAvailable;
    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        // Initialize views
        initializeViews();

        // Set up the back button functionality
        setupBackButton();

        // Load the saved state of switches (for demonstration purposes, we use a simple approach)
        loadNotificationSettings();

        // Set up listeners for the switches
        setupSwitchListeners();
    }

    private void initializeViews() {
        switchGeneralNotification = findViewById(R.id.switch_general_notification);
        switchSound = findViewById(R.id.switch_sound);
        switchVibrate = findViewById(R.id.switch_vibrate);
        switchAppUpdates = findViewById(R.id.switch_app_updates);
        switchBillReminder = findViewById(R.id.switch_bill_reminder);
        switchPromotion = findViewById(R.id.switch_promotion);
        switchDiscountAvailable = findViewById(R.id.switch_discount_available);
        switchPaymentRequest = findViewById(R.id.switch_payment_request);
        switchNewServiceAvailable = findViewById(R.id.switch_new_service_available);
        switchNewTipsAvailable = findViewById(R.id.switch_new_tips_available);
        backButton = findViewById(R.id.back_button);
    }

    private void setupBackButton() {
        backButton.setOnClickListener(v -> {
            // Navigate to ProfileActivity
            Intent intent = new Intent(notification.this, profile_page.class);
            startActivity(intent);
            finish(); // Optional: Close this activity
        });
    }

    private void loadNotificationSettings() {
        // Example: Setting switches to a specific state based on saved preferences or default values.
        // In a real-world app, you should load these states from SharedPreferences or a database.

        // Set initial states (you can replace these with actual data from SharedPreferences)
        switchGeneralNotification.setChecked(true);
        switchSound.setChecked(false);
        switchVibrate.setChecked(true);
        switchAppUpdates.setChecked(false);
        switchBillReminder.setChecked(true);
        switchPromotion.setChecked(true);
        switchDiscountAvailable.setChecked(false);
        switchPaymentRequest.setChecked(false);
        switchNewServiceAvailable.setChecked(true);
        switchNewTipsAvailable.setChecked(true);
    }

    private void setupSwitchListeners() {
        // Listeners for each switch
        switchGeneralNotification.setOnCheckedChangeListener((buttonView, isChecked) -> {
            showToast("General Notification: " + (isChecked ? "Enabled" : "Disabled"));
        });

        switchSound.setOnCheckedChangeListener((buttonView, isChecked) -> {
            showToast("Sound: " + (isChecked ? "Enabled" : "Disabled"));
        });

        switchVibrate.setOnCheckedChangeListener((buttonView, isChecked) -> {
            showToast("Vibrate: " + (isChecked ? "Enabled" : "Disabled"));
        });

        switchAppUpdates.setOnCheckedChangeListener((buttonView, isChecked) -> {
            showToast("App Updates: " + (isChecked ? "Enabled" : "Disabled"));
        });

        switchBillReminder.setOnCheckedChangeListener((buttonView, isChecked) -> {
            showToast("Bill Reminder: " + (isChecked ? "Enabled" : "Disabled"));
        });

        switchPromotion.setOnCheckedChangeListener((buttonView, isChecked) -> {
            showToast("Promotion: " + (isChecked ? "Enabled" : "Disabled"));
        });

        switchDiscountAvailable.setOnCheckedChangeListener((buttonView, isChecked) -> {
            showToast("Discount Available: " + (isChecked ? "Enabled" : "Disabled"));
        });

        switchPaymentRequest.setOnCheckedChangeListener((buttonView, isChecked) -> {
            showToast("Payment Request: " + (isChecked ? "Enabled" : "Disabled"));
        });

        switchNewServiceAvailable.setOnCheckedChangeListener((buttonView, isChecked) -> {
            showToast("New Service Available: " + (isChecked ? "Enabled" : "Disabled"));
        });

        switchNewTipsAvailable.setOnCheckedChangeListener((buttonView, isChecked) -> {
            showToast("New Tips Available: " + (isChecked ? "Enabled" : "Disabled"));
        });
    }

    // Method to display toast messages
    private void showToast(String message) {
        Toast.makeText(notification.this, message, Toast.LENGTH_SHORT).show();
    }
}
