package com.example.petopia.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.petopia.R;

public class VerifyOtp extends AppCompatActivity {

    private EditText etDigit1, etDigit2, etDigit3, etDigit4;
    private Button btnSubmitOTP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);

        // Find references to EditText views
        etDigit1 = findViewById(R.id.etDigit1);
        etDigit2 = findViewById(R.id.etDigit2);
        etDigit3 = findViewById(R.id.etDigit3);
        etDigit4 = findViewById(R.id.etDigit4);

        // Find reference to the submit button
        btnSubmitOTP = findViewById(R.id.btnSubmitOTP);

        // Set up TextWatcher for each EditText to move focus to the next EditText
        setEditTextListeners();

        // Set click listener for the submit button
        btnSubmitOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the OTP text from the EditText fields
                String digit1 = etDigit1.getText().toString();
                String digit2 = etDigit2.getText().toString();
                String digit3 = etDigit3.getText().toString();
                String digit4 = etDigit4.getText().toString();

                // Concatenate the OTP digits
                String otp = digit1 + digit2 + digit3 + digit4;
                Intent receivedIntent = getIntent();
                String stringValue = receivedIntent.getStringExtra("OTP");
                Toast.makeText(VerifyOtp.this, otp+" "+stringValue, Toast.LENGTH_SHORT).show();
            }
        });












    }

    private void setEditTextListeners() {
        etDigit1.addTextChangedListener((TextWatcher) new GenericTextWatcher(etDigit1, etDigit2));
        etDigit2.addTextChangedListener(new GenericTextWatcher(etDigit2, etDigit3));
        etDigit3.addTextChangedListener(new GenericTextWatcher(etDigit3, etDigit4));
        etDigit4.addTextChangedListener(new GenericTextWatcher(etDigit4, null));
    }

    private class GenericTextWatcher implements TextWatcher {
        private View currentView, nextView;

        public GenericTextWatcher(View currentView, View nextView) {
            this.currentView = currentView;
            this.nextView = nextView;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            // Not used
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            // Not used
        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (editable.length() == 1 && nextView != null) {
                nextView.requestFocus();
            }
        }
    }
}