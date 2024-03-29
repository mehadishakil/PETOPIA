package com.example.petopia.view

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room.databaseBuilder
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.petopia.R
import com.example.petopia.db.AppDatabase
import com.example.petopia.db.Item
import com.example.petopia.db.ItemDAO
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
    private lateinit var addToCart: Button
    private var productQuantity: Int? = null
    private var productPrice: String? = null
    private var productTitle: String? = null

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
        addToCart = findViewById(R.id.btn_add_to_cart)
        descriptionTextView = findViewById(R.id.tvProductDescription)


        val intent = intent
        if (intent != null) {
            val currentProduct = intent.getSerializableExtra("object") as Product?
            if (currentProduct != null) {
                productPrice = currentProduct.price
                productTitle = currentProduct.title


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











        plusButton.setOnClickListener(View.OnClickListener {
            val currentValue: String = quantityTextView.getText().toString()
            var value = currentValue.toInt()
            value++
            val yourPrice: Int = value * productPrice!!
            priceTextView.setText(yourPrice.toString())
            quantityTextView.setText(value.toString())
        })

        minusButton.setOnClickListener(View.OnClickListener {
            val currentValue: String = quantityTextView.getText().toString()
            var value = currentValue.toInt()
            if (value > 1) {
                value--
                val yourPrice: Int = value * productPrice!!
                priceTextView.setText(yourPrice.toString())
                quantityTextView.setText(value.toString())
            }
        })





        addToCart.setOnClickListener(View.OnClickListener {
            try {
                productQuantity = quantityTextView.getText().toString().toInt()
                val id = Math.random().toInt()

                val db: AppDatabase = databaseBuilder(
                    applicationContext,
                    AppDatabase::class.java, "cart_db"
                ).allowMainThreadQueries().build()
                val itemDAO: ItemDAO = db.itemDAO()
                val check: Boolean = itemDAO.is_exist(productTitle)
                if (check == false) {
                    try {
                        itemDAO.insertRecord(Item(id, productTitle, productPrice, productQuantity.toString()))
                        Toast.makeText(this@ItemView, "Item added to cart", Toast.LENGTH_SHORT)
                            .show()
                    } catch (e: Exception) {
                        Toast.makeText(this@ItemView, "" + e, Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@ItemView, "Item Already exist in cart", Toast.LENGTH_SHORT)
                        .show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@ItemView, "" + e, Toast.LENGTH_SHORT).show()
            }
        })





    }
}