package com.example.petopia.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.petopia.R;
import com.example.petopia.model.pojo.Article;

public class ReadArticle extends AppCompatActivity {

    private String title, content, image;
    TextView articleTitle, articleContent;
    ImageView articleImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_article);


        articleTitle = findViewById(R.id.tvArticleTitle);
        articleContent = findViewById(R.id.tvArticleContent);
        articleImage = findViewById(R.id.ivArticleImage);



        Intent data = getIntent();
        if (data != null) {
            title = data.getStringExtra("title");
            content = data.getStringExtra("content");
            image = data.getStringExtra("imageData");
            // Use the title...
        } else {
            // Handle the case where the article is null
            Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
        }




        articleTitle.setText(title);
        articleContent.setText(content);

        if (image.length() > 0){
            String imageUrl = "https://petopia-pet.000webhostapp.com/article_image/" +image;
            Glide.with(getApplicationContext())
                    .load(imageUrl)
                    .placeholder(R.drawable.loading_image)
                    .into(articleImage);

            articleImage.setVisibility(View.VISIBLE);
        }



    }
}