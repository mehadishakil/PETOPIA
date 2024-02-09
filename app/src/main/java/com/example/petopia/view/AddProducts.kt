package com.example.petopia.view

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
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
import com.example.petopia.R
import com.example.petopia.controller.AddProductController
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException

class AddProducts : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    var selectedUri: Uri? = null
    lateinit var bitmap: Bitmap
    lateinit var productImageView: ImageView
    lateinit var productTitleEdt: EditText
    lateinit var productDescriptionEdt: EditText
    lateinit var productPriceEdt: EditText
    lateinit var productCategorySpinner : Spinner
    lateinit var submit: Button
    lateinit var selectProductBtn: Button
    lateinit var productTitle: String
    lateinit var productDescription: String
    lateinit var productPrice: String
    lateinit var productCategory: String

    var image: String = ""

    lateinit var addProductController: AddProductController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_products)


        productImageView = findViewById(R.id.productImageId);
        productTitleEdt = findViewById(R.id.productTitleId);
        productDescriptionEdt = findViewById(R.id.productDescriptionId);
        productPriceEdt = findViewById(R.id.productDescriptionId);
        productCategorySpinner = findViewById(R.id.spinnerProductCategory)
        submit = findViewById(R.id.btnUploadProductId)
        selectProductBtn = findViewById(R.id.selectProductBtn);
        addProductController = AddProductController(this)

        selectProductBtn?.setOnClickListener {
            chooseImage()
        }

        val categoryAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.ProductCategory, android.R.layout.simple_spinner_item
        )
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        productCategorySpinner.setAdapter(categoryAdapter)
        productCategorySpinner.setOnItemSelectedListener(this)


        submit?.setOnClickListener {
            try {
                productTitle = productTitleEdt.text.toString()
                productDescription = productDescriptionEdt.text.toString()
                productPrice = productPriceEdt.text.toString()


                if(image != null){
                    addProductController.onAddProduct(productCategory, productTitle, productDescription, productPrice, image, "")
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

        if (resultCode == RESULT_OK && requestCode == 1 && data != null) {
            selectedUri = data.data
            try {
                val inputStream = contentResolver.openInputStream(selectedUri!!)
                bitmap = BitmapFactory.decodeStream(inputStream)
                productImageView.setImageBitmap(bitmap)
                bitmap?.let { imageStore(it) }
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
        } else {
            // Handle the case where the user cancels the image selection process
            Toast.makeText(applicationContext, "Image selection canceled", Toast.LENGTH_SHORT).show()
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
            R.id.spinnerProductCategory -> {
                productCategory = parent.getItemAtPosition(position).toString()
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        if (parent != null) {
            productCategory = parent.getItemAtPosition(0).toString()
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

