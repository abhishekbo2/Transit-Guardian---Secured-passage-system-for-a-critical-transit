package com.aibhi.we_will;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class vehicle_registration extends AppCompatActivity {

    private TextInputEditText ownerName, vehicleNumber, vehicleRCNumber;
    private Button nextButton;
    private TextView skipButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_registration);

        // Initialize views
        ownerName = findViewById(R.id.ownerName);
        vehicleNumber = findViewById(R.id.vehicleNumber);
        vehicleRCNumber = findViewById(R.id.vehicleRCNumber);
        nextButton = findViewById(R.id.nextButton);
        skipButton = findViewById(R.id.skipButton);

        // Skip button click listener
        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(vehicle_registration.this, home_page.class);
                startActivity(intent);
                finish();
            }
        });

        // Next button click listener
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ownerNameInput = ownerName.getText().toString().trim();
                String vehicleNumberInput = vehicleNumber.getText().toString().trim();
                String vehicleRCInput = vehicleRCNumber.getText().toString().trim();

                if (ownerNameInput.isEmpty() || vehicleNumberInput.isEmpty() || vehicleRCInput.isEmpty()) {
                    Toast.makeText(vehicle_registration.this, "Please fill all fields!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(vehicle_registration.this, "Vehicle Registered Successfully!", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(vehicle_registration.this, home_page.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
    public void setOwnerName(TextInputEditText ownerName) {
        this.ownerName = ownerName;
    }

    public void setVehicleNumber(TextInputEditText vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }
}
