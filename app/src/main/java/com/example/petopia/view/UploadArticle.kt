package com.example.petopia.view

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.petopia.R
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException

class UploadArticle : AppCompatActivity() {

    var selectedUri: Uri? = null
    var bitmap: Bitmap? = null
    var imageView: ImageView? = null
    var title: EditText? = null;
    var content: EditText? = null
    var encodeImage: String? = null
    var chooseImg : Button? = null
    var submit: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_upload_article)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        imageView = findViewById(R.id.articleImage)
        title = findViewById(R.id.articleTitle)
        content = findViewById(R.id.articleContent)
        chooseImg = findViewById(R.id.BtnChooseImg)
        submit = findViewById(R.id.idsubmit)


        chooseImg?.setOnClickListener {
            chooseImage()
        }

        submit?.setOnClickListener {

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
        encodeImage = Base64.encodeToString(imagebyte, Base64.DEFAULT)
        Toast.makeText(applicationContext, "Image: $encodeImage", Toast.LENGTH_SHORT).show()
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