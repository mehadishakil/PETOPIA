package com.example.petopia.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.petopia.R;
import com.example.petopia.controller.ILogController;
import com.example.petopia.controller.LoginController;

public class Signup extends AppCompatActivity implements ISignView {

    private EditText SignupEmail, SignupPassword, SignupConfirmPassword;
    private String strSignupEmail, strSignupPassword, strSignupConfirmPassword;
    private Button SignupBtn;
    private TextView goToLogin;
    ILogController signupController;
    private SharedPreferences sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        signupController = new LoginController(this);

        SignupEmail = findViewById(R.id.SignUpEmailID);
        SignupPassword = findViewById(R.id.newPasswordSignUpId);
        SignupConfirmPassword = findViewById(R.id.confirmNewPasswordSignUpId);
        SignupBtn = findViewById(R.id.signupButtonID);
        goToLogin = findViewById(R.id.goToLoginPageID);



        // Initialize sharedPrefs in an appropriate method, such as onCreate() for an Activity
        sharedPrefs = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        // Retrieve the boolean value from sharedPrefs
        boolean isLoggedIn = sharedPrefs.getBoolean("isLoggedIn", false);



        goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });




        SignupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strSignupEmail = SignupEmail.getText().toString().trim();
                strSignupPassword = SignupPassword.getText().toString().trim();
                strSignupConfirmPassword = SignupConfirmPassword.getText().toString().trim();

                signupController.onSignup(strSignupEmail, strSignupPassword, strSignupConfirmPassword);
            }
        });


    }

    @Override
    public void onSignupSuccess(String message, String otp) {

//        // Get the SharedPreferences editor
//        SharedPreferences.Editor editor = sharedPrefs.edit();
//        // Modify the preferences using the editor
//        editor.putBoolean("isLoggedIn", true);
//        editor.putString("user_id", userID);
//        editor.apply();



        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, VerifyOtp.class);
        intent.putExtra("OTP", otp);
        startActivity(intent);
    }

    @Override
    public void onSignupError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}