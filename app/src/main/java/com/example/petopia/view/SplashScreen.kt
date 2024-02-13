package com.example.petopia.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.airbnb.lottie.LottieAnimationView
import com.example.petopia.R

class SplashScreen : AppCompatActivity() {

    private lateinit var lottieAnimationView: LottieAnimationView
    private lateinit var petopiaTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash_screen)

        lottieAnimationView = findViewById(R.id.lottieAnimationView)
        petopiaTextView = findViewById(R.id.petopiaTextView)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(baseContext, Login::class.java)
            startActivity (intent)
            finish()
        }, 3000)


    }
}