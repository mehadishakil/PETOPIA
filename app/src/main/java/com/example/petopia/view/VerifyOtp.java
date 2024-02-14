package com.example.petopia.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.petopia.R;
import com.example.petopia.controller.VerifyOtpController;

public class VerifyOtp extends AppCompatActivity implements IVerifiyOtp {

    private EditText etDigit1, etDigit2, etDigit3, etDigit4;
    private Button btnSubmitOTP;
    private String otp1, email, user_id;
    private TextView emailTv;

    private VerifyOtpController verifyOtpController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);

        Intent receivedIntent = getIntent();
        otp1 = receivedIntent.getStringExtra("OTP");
        email = receivedIntent.getStringExtra("email");
        user_id = receivedIntent.getStringExtra("user_id");

        verifyOtpController = new VerifyOtpController(this);

        emailTv = findViewById(R.id.tvEmailID);
        etDigit1 = findViewById(R.id.etDigit1);
        etDigit2 = findViewById(R.id.etDigit2);
        etDigit3 = findViewById(R.id.etDigit3);
        etDigit4 = findViewById(R.id.etDigit4);
        btnSubmitOTP = findViewById(R.id.btnSubmitOTP);



        setEditTextListeners();
        emailTv.setText(emailTv.getText().toString()+"\n"+email);



        btnSubmitOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the OTP text from the EditText fields
                String digit1 = etDigit1.getText().toString();
                String digit2 = etDigit2.getText().toString();
                String digit3 = etDigit3.getText().toString();
                String digit4 = etDigit4.getText().toString();

                // Concatenate the OTP digits
                String otp2 = digit1 + digit2 + digit3 + digit4;
                Toast.makeText(VerifyOtp.this, otp2+" your + get "+otp1 , Toast.LENGTH_SHORT).show();

                if (otp1.equals(otp2)){
                    verifyOtpController.onSignup(true, email, user_id);
                } else {
                    Toast.makeText(VerifyOtp.this, "OTP is not verified", Toast.LENGTH_SHORT).show();
                }


                Toast.makeText(VerifyOtp.this, otp1, Toast.LENGTH_SHORT).show();
            }
        });












    }




    @Override
    public void OnSuccess(@NonNull String message) {
        Toast.makeText(this, "OTP Verified"+message, Toast.LENGTH_SHORT).show();
        SharedPreferences sharedPrefs = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putBoolean("isLoggedIn", true);
        editor.apply();

        navigateToMainActivity();
    }

    @Override
    public void OnFailed(@NonNull String message) {
        Toast.makeText(this, "Wrong OTP "+message, Toast.LENGTH_SHORT).show();
    }

    private void navigateToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
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