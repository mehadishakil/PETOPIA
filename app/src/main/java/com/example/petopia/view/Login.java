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

public class Login extends AppCompatActivity implements ILogin {

    private EditText loginEmail, loginPassword;
    private String logEmail, logPass;
    private Button loginBtn;
    private TextView goToSignup;
    ILogController loginController;
    private boolean isLoggedIn;
    private SharedPreferences sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize sharedPrefs in an appropriate method, such as onCreate() for an Activity
        sharedPrefs = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        // Retrieve the boolean value from sharedPrefs
        isLoggedIn = sharedPrefs.getBoolean("isLoggedIn", false);

        // Check the value of isLoggedIn and navigate accordingly
        if (isLoggedIn) {
            navigateToMainActivity();
        }


        loginController = new LoginController(this);

        loginEmail = findViewById(R.id.email_loginID);
        loginPassword = findViewById(R.id.password_loginID);
        loginBtn = findViewById(R.id.loginButtonID);
        goToSignup = findViewById(R.id.goToSignUpPageID);

        goToSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Signup.class);
                startActivity(intent);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logEmail = loginEmail.getText().toString().trim();
                logPass = loginPassword.getText().toString().trim();

                loginController.onLogin(logEmail, logPass);
            }
        });


    }


    @Override
    public void onLoginSuccess(String message, String userID) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putBoolean("isLoggedIn", true);
        editor.putString("user_id", userID);
        editor.apply();

        navigateToMainActivity();
    }

    @Override
    public void onLoginError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private void navigateToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }



}