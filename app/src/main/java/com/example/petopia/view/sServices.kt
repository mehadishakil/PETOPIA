package com.example.petopia.view

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.petopia.R



class sServices : AppCompatActivity() {

    private lateinit var spinnerServiceType: Spinner
    private lateinit var editTextPetName: EditText
    private lateinit var datePicker: DatePicker
    private lateinit var btnBookService: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_services)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        // Initialize views
        spinnerServiceType = findViewById(R.id.spinnerService)
        editTextPetName = findViewById(R.id.editTextPetName)
        datePicker = findViewById(R.id.datePicker)
        btnBookService = findViewById(R.id.btnBookService)

        // Initialize spinner adapter
        val serviceTypes = listOf("Grooming", "Veterinary", "Daycare")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, serviceTypes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerServiceType.adapter = adapter

        // Handle button click event
        btnBookService.setOnClickListener {
            // Get user inputs
            val selectedServiceType = spinnerServiceType.selectedItem.toString()
            val petName = editTextPetName.text.toString()
            val day = datePicker.dayOfMonth
            val month = datePicker.month + 1 // month is zero-based
            val year = datePicker.year

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }



    }
}