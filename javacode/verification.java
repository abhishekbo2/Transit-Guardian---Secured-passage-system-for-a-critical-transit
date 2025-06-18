package com.aibhi.we_will;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class verification extends AppCompatActivity {
    private ImageButton back_arrow;
    private EditText otp1, otp2, otp3, otp4;
    private Button verifyButton;
    private TextView resendCode, requestCodeTimer;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        otp1 = findViewById(R.id.otp1);
        otp2 = findViewById(R.id.otp2);
        otp3 = findViewById(R.id.otp3);
        otp4 = findViewById(R.id.otp4);
        verifyButton = findViewById(R.id.verify_button);
        resendCode = findViewById(R.id.resend_code);
        requestCodeTimer = findViewById(R.id.request_code_timer);
        back_arrow = findViewById(R.id.back_arrow);

        // Initially hide the countdown timer
        requestCodeTimer.setVisibility(View.GONE);

        // Back button functionality
        back_arrow.setOnClickListener(v -> {
            Intent signinIntent = new Intent(verification.this, log_in.class);
            startActivity(signinIntent);
        });

        // OTP Input Auto-Move
        otp1.addTextChangedListener(new GenericTextWatcher(otp1, otp2));
        otp2.addTextChangedListener(new GenericTextWatcher(otp2, otp3));
        otp3.addTextChangedListener(new GenericTextWatcher(otp3, otp4));

        // Verify Button Click
        verifyButton.setOnClickListener(v -> {
            String code = otp1.getText().toString() +
                    otp2.getText().toString() +
                    otp3.getText().toString() +
                    otp4.getText().toString();

            if (code.length() == 4) {
                Toast.makeText(this, "Code Verified: " + code, Toast.LENGTH_SHORT).show();
                Intent homeIntent = new Intent(verification.this, vehicle_registration.class);
                startActivity(homeIntent);
            } else {
                Toast.makeText(this, "Please enter all digits", Toast.LENGTH_SHORT).show();
            }
        });

        // Resend Code Button
        resendCode.setOnClickListener(v -> {
            Toast.makeText(this, "Resend Code Clicked!", Toast.LENGTH_SHORT).show();
            startCountdownTimer();
        });
    }

    // Countdown Timer Logic
    private void startCountdownTimer() {
        resendCode.setVisibility(View.GONE);
        requestCodeTimer.setVisibility(View.VISIBLE);

        countDownTimer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                requestCodeTimer.setText("Request new code in 00:" + millisUntilFinished / 1000 + "s");
            }

            @Override
            public void onFinish() {
                requestCodeTimer.setVisibility(View.GONE);
                resendCode.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    // Generic TextWatcher for OTP input
    private class GenericTextWatcher implements TextWatcher {
        private final EditText currentView;
        private final EditText nextView;

        public GenericTextWatcher(EditText currentView, EditText nextView) {
            this.currentView = currentView;
            this.nextView = nextView;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (!s.toString().isEmpty() && nextView != null) {
                nextView.requestFocus();
            }
        }

        @Override
        public void afterTextChanged(Editable s) {}
    }
}
