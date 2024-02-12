package com.example.petopia.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.petopia.R;
import com.example.petopia.model.pojo.Product;

public class DisplayProducts extends AppCompatActivity {

    private ScrollView scrollView;
    private ImageView ivProductImage;
    private TextView tvProductTitle;
    private RatingBar rbProductRating;
    private TextView tvProductRating;
    private ImageButton minusButton;
    private TextView quantityTextView;
    private ImageButton plusButton;
    private TextView tvProductPrice;
    private TextView tvProductDetails;
    private Button addProductToCartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_products);





//
//        // Retrieve intent data
//        Intent intent = getIntent();
//        if (intent != null) {
//            Product currentProduct = (Product) intent.getSerializableExtra("object");
//            if (currentProduct != null) {
//                // Now you have access to the currentProduct object, you can use it as needed
//                // For example, you can display its properties in TextViews or ImageViews
//                tvProductTitle.setText(currentProduct.getTitle());
//                tvProductPrice.setText(currentProduct.getPrice()+" tk");
//                tvProductDetails.setText(currentProduct.getDescription());
//                tvProductRating.setText(currentProduct.getRatings());
//                // rbProductRating.setRating(Float.parseFloat(currentProduct.getRatings()));
//
//                var imageUrl = "https://petopia-pet.000webhostapp.com/product_images/" + currentProduct.getImage();
//                Glide.with(getApplicationContext())
//                        .load(imageUrl)
//                        .placeholder(R.drawable.loading_image)
//                        .error(R.drawable.error_image)
//                        .into(ivProductImage);
//
//                String title = currentProduct.getTitle();
//                String price = currentProduct.getPrice();
//                String ratings = currentProduct.getRatings();
//
//                Toast.makeText(this, title+" "+price+" "+ratings, Toast.LENGTH_SHORT).show();
//
//                // Display the data or perform actions based on the product information
//            } else {
//                Toast.makeText(this, "No product data found", Toast.LENGTH_SHORT).show();
//            }
//        } else {
//            Toast.makeText(this, "No intent data found", Toast.LENGTH_SHORT).show();
//        }





    }
}