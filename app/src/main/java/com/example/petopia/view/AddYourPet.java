package com.example.petopia.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.petopia.R;
import com.example.petopia.controller.AddYourPetController;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddYourPet extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    AddYourPetController addYourPetController;
    Spinner spinner;
    String petType="", gender="", encodeImage="", petName="", petAge="", petWeight="";
    RadioGroup radioGroup;
    RadioButton radioButton;
    Button btnChooseImage, btnUploadImage;
    Uri selectedUri;
    Bitmap bitmap;
    ImageView petImage;
    EditText name, age, weight;
    private Dialog customDialog;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_your_pet);

        addYourPetController = new AddYourPetController(this);
        spinner = findViewById(R.id.SpinnerPetType);
        btnChooseImage = findViewById(R.id.BtnChooseImg);
        btnUploadImage = findViewById(R.id.BtnAddYourPet);
        petImage = findViewById(R.id.PetImage);
        radioGroup = findViewById(R.id.radioGroupGender);
        name = findViewById(R.id.articleTitle);
        age = findViewById(R.id.articleContent);
        weight = findViewById(R.id.editTextWeight);



        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.PetTypes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        btnChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
            }
        });

        btnUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                String userID =  sharedPreferences.getString("user_id", "");

                showCustomProgressDialog();
                checkButton();
                petName = name.getText().toString();
                petAge = age.getText().toString();
                petWeight = weight.getText().toString();
                addYourPetController.onAddYourPet(userID, petName, petType, petAge, petWeight, gender, encodeImage);
            }
        });


    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        petType = parent.getItemAtPosition(position).toString();
        //Toast.makeText(parent.getContext(), petType, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        petType = parent.getItemAtPosition(0).toString();
    }

    public void checkButton() {
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        gender = radioButton.getText().toString();
        //Toast.makeText(getApplication(), gender, Toast.LENGTH_SHORT).show();

    }

    public void chooseImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, 1);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == 1 || requestCode == RESULT_OK || data != null || data.getData() != null) {
            selectedUri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(selectedUri);
                bitmap = BitmapFactory.decodeStream(inputStream);
                petImage.setImageBitmap(bitmap);
                imageStore(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }


        try {
            Uri uri = data.getData();
            petImage.setImageURI(uri);
            petImage.setVisibility(View.VISIBLE);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error: " + e, Toast.LENGTH_SHORT).show();
        }


    }



    private void imageStore(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] imagebyte = stream.toByteArray();
        encodeImage = Base64.encodeToString(imagebyte, Base64.DEFAULT);
        Toast.makeText(getApplicationContext(), "Image: "+encodeImage, Toast.LENGTH_SHORT).show();
    }


    private void showCustomProgressDialog() {
        customDialog = new Dialog(this);
        customDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        customDialog.setContentView(R.layout.loading_layout);
        customDialog.setCancelable(false);
        customDialog.show();
    }


    public String getSelectedPetType() {
        return petType;
    }










    public void onSuccess(String message) {
        if (customDialog != null && customDialog.isShowing()) {
            customDialog.dismiss();
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    public void onError(String message) {
        if (customDialog != null && customDialog.isShowing()) {
            customDialog.dismiss();
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}


