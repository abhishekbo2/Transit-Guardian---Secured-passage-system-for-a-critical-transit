package com.aibhi.we_will;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText nameEditText, emailEditText, phoneEditText, passwordEditText;
    private CheckBox termsCheckBox;
    private Button createButton;
    private TextView loginTextView;

    private boolean isPasswordVisible = false; // Toggle password visibility

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.editTextEmail);
        phoneEditText = findViewById(R.id.editTextPhone);
        passwordEditText = findViewById(R.id.editTextPassword);
        termsCheckBox = findViewById(R.id.checkBoxTerms);
        createButton = findViewById(R.id.buttonCreate);
        loginTextView = findViewById(R.id.textLogin);

        // Add toggle visibility for password field
        passwordEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    int drawableEndIndex = 2; // Position of the drawableRight
                    if (passwordEditText.getCompoundDrawables()[drawableEndIndex] != null) {
                        // Check if touch is on drawableRight
                        if (event.getRawX() >= (passwordEditText.getRight() - passwordEditText.getCompoundDrawables()[drawableEndIndex].getBounds().width())) {
                            togglePasswordVisibility();
                            return true;
                        }
                    }
                }
                return false;
            }
        });

        // Button Click Listener
        createButton.setOnClickListener(v -> {
            String name = nameEditText.getText().toString().trim();
            String email = emailEditText.getText().toString().trim();
            String phone = phoneEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            // Input validation
            if (name.isEmpty()) {
                Toast.makeText(this, "Please enter your name!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (email.isEmpty()) {
                Toast.makeText(this, "Please enter your email!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (phone.isEmpty()) {
                Toast.makeText(this, "Please enter your phone number!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (password.isEmpty()) {
                Toast.makeText(this, "Please enter your password!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!termsCheckBox.isChecked()) {
                Toast.makeText(this, "You must agree to the Terms and Conditions!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Proceed with account creation logic
            Toast.makeText(this, "Account created successfully!", Toast.LENGTH_SHORT).show();
            Intent loginIntent = new Intent(MainActivity.this, log_in.class); // Replace 'log_in' with your actual sign_in activity
            startActivity(loginIntent);
        });

        // Login Text Click Listener
        loginTextView.setOnClickListener(v -> {
            // Navigate to login page logic
            Intent logIntent = new Intent(MainActivity.this, log_in.class); // Replace 'log_in' with your actual sign_in activity
            startActivity(logIntent);
        });
    }

    // Toggle password visibility
    private void togglePasswordVisibility() {
        if (isPasswordVisible) {
            // Hide password
            passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            passwordEditText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.lock, 0); // Replace with your lock icon drawable
        } else {
            // Show password
            passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT);
            passwordEditText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.lock, 0); // Replace with your unlock icon drawable
        }
        isPasswordVisible = !isPasswordVisible;
        passwordEditText.setSelection(passwordEditText.getText().length()); // Keep cursor at the end
    }
}