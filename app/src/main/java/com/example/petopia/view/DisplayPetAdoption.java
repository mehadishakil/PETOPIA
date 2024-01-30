package com.example.petopia.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.petopia.R;

public class DisplayPetAdoption extends AppCompatActivity {

    ImageView adoptPetImage, OwnerProfileImage;
    Button btnContact;
    TextView adoptPetNameID, adoptPetCategoryID, adoptPetGenderID, adoptionPetAgeID,
            adoptionPetWeightID, adoptionPetLocation, OnwerName, adoptionPetDetails, textView4;
    Button addAdoptionPetToCartId;
    ConstraintLayout petDetails, constraintLayout;
    String name, category, breed, gender, age, weight, location, description, owner, contact, image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_pet_adoption);


        adoptPetImage = findViewById(R.id.adoptionPetImage);
        OwnerProfileImage = findViewById(R.id.OwnerProfileImage);
        btnContact = findViewById(R.id.adoptionPetContact);
        adoptPetNameID = findViewById(R.id.adoptPetNameID);
        adoptPetCategoryID = findViewById(R.id.adoptPetCategoryID);
        adoptPetGenderID = findViewById(R.id.adoptPetGenderID);
        adoptionPetAgeID = findViewById(R.id.adoptionPetAgeID);
        adoptionPetWeightID = findViewById(R.id.adoptionPetWeightID);
        adoptionPetLocation = findViewById(R.id.adoptionPetLocation);
        OnwerName = findViewById(R.id.OnwerName);
        adoptionPetDetails = findViewById(R.id.adoptionPetDetails);
        textView4 = findViewById(R.id.textView4);
        addAdoptionPetToCartId = findViewById(R.id.addAdoptionPetToCartId);
        constraintLayout = findViewById(R.id.constraintLayout);




        // Retrieve intent extras
        Intent intent = getIntent();
        if (intent != null) {
            name = intent.getStringExtra("name");
            category = intent.getStringExtra("category");
            breed = intent.getStringExtra("breed");
            gender = intent.getStringExtra("gender");
            age = intent.getStringExtra("age");
            weight = intent.getStringExtra("weight");
            location = intent.getStringExtra("location");
            description = intent.getStringExtra("description");
            owner = intent.getStringExtra("owner");
            contact = intent.getStringExtra("contact");
            image = intent.getStringExtra("image"); // You may handle image separately if needed

            if (image.length() > 0){
                String imageUrl = "https://petopia-pet.000webhostapp.com/PetAdoptionImage/" +image;
                Glide.with(getApplicationContext())
                        .load(imageUrl)
                        .placeholder(R.drawable.loading_image)
                        .error(R.drawable.error_image)
                        .into(adoptPetImage);
            }

            // Set values to UI elements
            adoptPetNameID.setText(name);
            adoptPetCategoryID.setText("("+category+")");
            adoptPetGenderID.setText(gender);
            adoptionPetAgeID.setText(age+" mo");
            adoptionPetWeightID.setText(weight + " gm");
            adoptionPetLocation.setText(location);
            OnwerName.setText(owner);
            adoptionPetDetails.setText(description);
        }

    }
}