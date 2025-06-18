package com.aibhi.we_will;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class profileedit extends Activity {

    private TextInputEditText fullName, nickName, editTextEmail, phoneNumber, address, vehicleRegistrationNumber,
            vehicleType, vehicleRegisteredCity, gender_label, country_label;
    private Button submitButton;
    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        // Initialize views
        initializeViews();

        // Set up back button functionality
        setupBackButton();

        // Set up submit button functionality
        setupSubmitButton();

        // Load profile data (this would typically come from a database or server)
        loadProfileInfo();
    }

    private void initializeViews() {
        fullName = findViewById(R.id.full_name);
        nickName = findViewById(R.id.nick_name);
        editTextEmail = findViewById(R.id.editTextEmail);
        phoneNumber = findViewById(R.id.phone_number);
        address = findViewById(R.id.address);
        vehicleRegistrationNumber = findViewById(R.id.vehicle_registration_number);
        vehicleType = findViewById(R.id.vehicle_type);
        vehicleRegisteredCity = findViewById(R.id.vehicle_registered_city);
        gender_label = findViewById(R.id.gender_label); // Gender input
        country_label = findViewById(R.id.country_label); // Country input
        submitButton = findViewById(R.id.submit_button);
        backButton = findViewById(R.id.back_button);
    }

    private void setupBackButton() {
        backButton.setOnClickListener(view -> {
            Intent intent = new Intent(profileedit.this, profile_page.class);
            startActivity(intent);
            finish(); // Close the current activity
        });
    }

    private void setupSubmitButton() {
        submitButton.setOnClickListener(v -> {
            saveProfileInfo();
        });
    }

    private void loadProfileInfo() {
        // Dummy data for demonstration purposes
        fullName.setText("Naruto Uzumaki");
        nickName.setText("Naruto");
        editTextEmail.setText("naruto@example.com");
        phoneNumber.setText("1234567890");
        address.setText("Hidden Leaf Village");
        vehicleRegistrationNumber.setText("KLV 12345");
        vehicleType.setText("Ninja Vehicle");
        vehicleRegisteredCity.setText("Konoha");
        gender_label.setText("Male");
        country_label.setText("Japan");

        // Replace the above with logic to fetch real data from a database or API.
    }

    private void saveProfileInfo() {
        // Collect user input
        String name = fullName.getText().toString();
        String nick = nickName.getText().toString();
        String email = editTextEmail.getText().toString();
        String phone = phoneNumber.getText().toString();
        String addr = address.getText().toString();
        String regNumber = vehicleRegistrationNumber.getText().toString();
        String type = vehicleType.getText().toString();
        String regCity = vehicleRegisteredCity.getText().toString();
        String genderInput = gender_label.getText().toString();
        String countryInput = country_label.getText().toString();

        // Save logic here (e.g., send to a database or API)
        // Display confirmation message
        Toast.makeText(this, "Profile updated successfully!", Toast.LENGTH_SHORT).show();

        // Navigate back to profile page or another activity
        Intent intent = new Intent(profileedit.this, profile_page.class);
        startActivity(intent);
        finish();
    }
}
