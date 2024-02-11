package com.example.petopia.view;

import static android.content.Context.MODE_PRIVATE;
import static java.util.Arrays.*;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.petopia.R;
import com.example.petopia.adapter.PetAdapter;
import com.example.petopia.adapter.PetAdoptionCatgAdapter;
import com.example.petopia.adapter.ProductAdapter;
import com.example.petopia.adapter.ShopCategoryAdapter;
import com.example.petopia.controller.FragmentShopController;
import com.example.petopia.controller.IFragmentShopController;
import com.example.petopia.model.pojo.AdoptPet;
import com.example.petopia.model.pojo.Event;
import com.example.petopia.model.pojo.Product;
import com.example.petopia.model.pojo.ShopCategory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FragmentShop extends Fragment implements IFragmentShop {

    View view;
    RecyclerView rvShopCategory, rvPopularProduct;
    IFragmentShopController fragmentShopController;
    ProductAdapter productAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_shop, container, false);

        rvShopCategory = view.findViewById(R.id.recyclerViewShopCategoryID);
        rvPopularProduct = view.findViewById(R.id.RvPopularItem);
        fragmentShopController = new FragmentShopController(this, getContext());

        displayShopCategory();

        try {
            fragmentShopController.onGetPopularProducts();
        } catch (Exception e) {
            Toast.makeText(getContext(), "" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return view;
    }


    private void displayShopCategory() {
        ShopCategory[] shopCategories = {
                new ShopCategory("Food", R.drawable.pet_food),
                new ShopCategory("Toys", R.drawable.pet_toys),
                new ShopCategory("Accessories", R.drawable.pet_accessories),
                new ShopCategory("Health & Wellness", R.drawable.pet_health_wellness),
                new ShopCategory("Training", R.drawable.pet_training),
                new ShopCategory("Housing", R.drawable.pet_housing),
                new ShopCategory("Pet Care", R.drawable.pet_care),
                new ShopCategory("Travel", R.drawable.pet_travel),
        };
        rvShopCategory.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        ShopCategoryAdapter shopCategoryAdapter = new ShopCategoryAdapter(asList(shopCategories));
        rvShopCategory.setAdapter(shopCategoryAdapter);
    }

    @Override
    public void onGetTrendingProductSuccess(List<Product> products) {
        rvPopularProduct.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        productAdapter = new ProductAdapter(products, getContext());
        rvPopularProduct.setAdapter(productAdapter);

        Toast.makeText(getContext(), ""+products.size(), Toast.LENGTH_LONG).show();
    }


    @Override
    public void onGetTrendingProductError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }




}
