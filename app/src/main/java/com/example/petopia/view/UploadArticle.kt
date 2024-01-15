package com.example.petopia.view

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.health.connect.datatypes.units.Length
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
import com.example.petopia.controller.UploadArticleController
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException

class UploadArticle : AppCompatActivity() {

    var selectedUri: Uri? = null
    lateinit var bitmap: Bitmap
    lateinit var imageView: ImageView
    lateinit var title: EditText
    lateinit var content: EditText
    var encodeImage: String = ""
    lateinit var chooseImg : Button
    lateinit var submit: Button
    lateinit var uploadArticleController : UploadArticleController
    lateinit var strTitle : String
    lateinit var strContent : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_upload_article)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        uploadArticleController = UploadArticleController(this)
        imageView = findViewById(R.id.articleImage)
        title = findViewById(R.id.articleTitle)
        content = findViewById(R.id.articleContent)
        chooseImg = findViewById(R.id.BtnChooseImg)
        submit = findViewById(R.id.idsubmit)


        chooseImg?.setOnClickListener {
            chooseImage()
        }

        submit?.setOnClickListener {
            try {
                strTitle = title.text.toString()
                strContent = content.text.toString()
                if(encodeImage != null){
                    uploadArticleController.onAddArticle(strTitle, strContent, encodeImage)
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