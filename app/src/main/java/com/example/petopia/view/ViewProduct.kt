package com.example.petopia.view

import android.os.Bundle
import android.widget.ImageButton
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.petopia.R
import com.example.petopia.model.pojo.Product

class ViewProduct : AppCompatActivity() {

    private lateinit var productImageSlider: com.denzcoskun.imageslider.ImageSlider
    private lateinit var titleTextView: TextView
    private lateinit var ratingBar: RatingBar
    private lateinit var ratingTextView: TextView
    private lateinit var minusButton: ImageButton
    private lateinit var quantityTextView: TextView
    private lateinit var plusButton: ImageButton
    private lateinit var priceTextView: TextView
    private lateinit var descriptionTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_view_product)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        productImageSlider = findViewById(R.id.productImageSlider)
        titleTextView = findViewById(R.id.id_title)
        ratingBar = findViewById(R.id.ratingBar)
        ratingTextView = findViewById(R.id.tv_item_rating)
        minusButton = findViewById(R.id.btn_minus)
        quantityTextView = findViewById(R.id.id_quantity)
        plusButton = findViewById(R.id.btn_plus)
        priceTextView = findViewById(R.id.id_price)
        descriptionTextView = findViewById(R.id.tvProductDescription)


        val intent = intent
        if (intent != null) {
            val currentProduct = intent.getSerializableExtra("object") as Product?
            if (currentProduct != null) {
                titleTextView.text = currentProduct.title
                priceTextView.text = currentProduct.price+" tk"
                descriptionTextView.text = currentProduct.description
                ratingTextView.text = currentProduct.rating
                ratingBar.rating = currentProduct.rating.toFloat()

                val images = mutableListOf(currentProduct.image)

                val imageList = ArrayList<SlideModel>()
                for (image in images) {
                    val imageUrl = "https://petopia-pet.000webhostapp.com/product_images/" + image
                    imageList.add(SlideModel(imageUrl, ScaleTypes.CENTER_INSIDE))
                }
                productImageSlider.setImageList(imageList, ScaleTypes.CENTER_INSIDE)




            } else {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
        }

    }
}