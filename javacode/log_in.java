

package com.aibhi.we_will;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class log_in extends AppCompatActivity {

    private TextInputEditText emailEditText, passwordEditText;
    private CheckBox checkBoxRememberMe;
    private Button logInButton;
    private TextView forgetPasswordTextView, registerNowTextView;

    private boolean isPasswordVisible = false; // Toggle password visibility

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        // Initialize UI components
        emailEditText = findViewById(R.id.editTextEmail);
        passwordEditText = findViewById(R.id.editTextPassword);
        checkBoxRememberMe = findViewById(R.id.checkBoxRememberMe);
        logInButton = findViewById(R.id.buttonLogIn);
        forgetPasswordTextView = findViewById(R.id.textForgetPassword);
        registerNowTextView = findViewById(R.id.textRegisterNow);

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
        logInButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            // Input validation
            if (email.isEmpty()) {
                Toast.makeText(this, "Please enter your email!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (password.isEmpty()) {
                Toast.makeText(this, "Please enter your password!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Simulate login logic
            Intent verificationIntent = new Intent(log_in.this, verification.class);
            startActivity(verificationIntent);
        });

        // Forget Password Click Listener
        forgetPasswordTextView.setOnClickListener(v ->
                Toast.makeText(this, "Redirecting to Forgot Password Page", Toast.LENGTH_SHORT).show()
        );

        // Register Now Click Listener
        registerNowTextView.setOnClickListener(v -> {
            Intent registerIntent = new Intent(log_in.this, MainActivity.class); // Navigate to registration page
            startActivity(registerIntent);
        });
    }

    // Toggle password visibility
    private void togglePasswordVisibility() {
        if (isPasswordVisible) {
            // Hide password
            passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            passwordEditText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.lock, 0); // Use your lock icon drawable
        } else {
            // Show password
            passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT);
            passwordEditText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.lock, 0); // Use your unlock icon drawable
        }
        isPasswordVisible = !isPasswordVisible;
        passwordEditText.setSelection(passwordEditText.getText().length()); // Keep cursor at the end
    }
}

