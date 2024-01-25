package com.example.petopia.view

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.petopia.R
import com.example.petopia.controller.AddAdoptionController
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException

class AddAdoption : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    var selectedUri: Uri? = null
    lateinit var bitmap: Bitmap
    lateinit var imageView: ImageView
    lateinit var petName: EditText
    lateinit var petBreed: EditText
    lateinit var petAge: EditText
    lateinit var petWeight: EditText
    lateinit var petLocation: EditText
    lateinit var petDescription: EditText
    lateinit var petOwner: EditText
    lateinit var contactInfo: EditText
    lateinit var sCategory : Spinner
    lateinit var sGender : Spinner
    lateinit var chooseImg : Button
    lateinit var submit: Button
    var image : String = ""
    lateinit var name : String
    lateinit var category : String
    lateinit var breed : String
    lateinit var gender : String
    lateinit var age : String
    lateinit var weight : String
    lateinit var location : String
    lateinit var description : String
    lateinit var owner : String
    lateinit var contact : String
    lateinit var addAdoptionController: AddAdoptionController
    lateinit var sharedPrefs : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_adoption)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        petName = findViewById(R.id.editTextPetName)
        sCategory = findViewById(R.id.SpinnerPetType)
        petBreed = findViewById(R.id.EtPetBreed)
        sGender = findViewById(R.id.SpinnerPetGender)
        petAge = findViewById(R.id.editTextPetAge)
        petWeight = findViewById(R.id.editTextPetWeight)
        petLocation = findViewById(R.id.editTextPetLocation)
        petDescription = findViewById(R.id.editTextPetDescription)
        petOwner = findViewById(R.id.editTextOwnerName)
        contactInfo = findViewById(R.id.editTextContactInfo)
        imageView = findViewById(R.id.adoptPetImage)
        chooseImg = findViewById(R.id.BtnChooseImg)
        submit = findViewById(R.id.btnAddPet)
        addAdoptionController = AddAdoptionController(this)


        chooseImg?.setOnClickListener {
            chooseImage()
        }


        val adapter1 = ArrayAdapter.createFromResource(
            this,
            R.array.PetTypes, android.R.layout.simple_spinner_item
        )
        val adapter2 = ArrayAdapter.createFromResource(
            this,
            R.array.Gender, android.R.layout.simple_spinner_item
        )
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sCategory.setAdapter(adapter1)
        sCategory.setOnItemSelectedListener(this)
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sGender.setAdapter(adapter2)
        sGender.setOnItemSelectedListener(this)





        submit?.setOnClickListener {
            try {
                name = petName.text.toString()
                breed = petBreed.text.toString()
                age = petAge.text.toString()
                weight = petWeight.text.toString()
                location = petLocation.text.toString()
                description = petDescription.text.toString()
                owner = petOwner.text.toString()
                contact = contactInfo.text.toString()


                sharedPrefs = getSharedPreferences("MyPrefs", MODE_PRIVATE)
                owner = sharedPrefs.getString("user_id", petOwner.text.toString()) ?: ""


                if(image != null){
                    addAdoptionController.onAddAdoption(name, category, breed, gender, age, weight, location, description, owner, contact, image)
                }else{
                    Toast.makeText(this, "Please select an image", Toast.LENGTH_SHORT).show()
                }
            }catch (e : Exception){
                Toast.makeText(this, "Error: $e", Toast.LENGTH_SHORT).show()
            }

        }



    }



    fun chooseImage() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.setType("image/*")
        startActivityForResult(intent, 1)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        if (requestCode == 1 || requestCode == RESULT_OK || data != null) {
            selectedUri = data?.data
            try {
                val inputStream = contentResolver.openInputStream(selectedUri!!)
                bitmap = BitmapFactory.decodeStream(inputStream)
                imageView?.setImageBitmap(bitmap)
                bitmap?.let { imageStore(it) }
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
        }


        try {
            val uri = data!!.data
            imageView!!.setImageURI(uri)
            imageView!!.visibility = View.VISIBLE
        } catch (e: Exception) {
            Toast.makeText(applicationContext, "Error: $e", Toast.LENGTH_SHORT).show()
        }
    }


    private fun imageStore(bitmap: Bitmap) {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
        val imagebyte = stream.toByteArray()
        image = Base64.encodeToString(imagebyte, Base64.DEFAULT)
        Toast.makeText(applicationContext, "Image: $image", Toast.LENGTH_SHORT).show()
    }


    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (parent?.id) {
            R.id.SpinnerPetType -> {
                category = parent.getItemAtPosition(position).toString()
            }
            R.id.SpinnerPetGender -> {
                gender = parent.getItemAtPosition(position).toString()
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        if (parent != null) {
            category = parent.getItemAtPosition(0).toString()
            gender = parent.getItemAtPosition(0).toString()
        }
    }









    fun onSuccess(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }


    fun onError(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


}

