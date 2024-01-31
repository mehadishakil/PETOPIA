package com.example.petopia.view;

import static java.util.Arrays.*;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.petopia.R;
import com.example.petopia.adapter.PetAdoptionCatgAdapter;
import com.example.petopia.model.pojo.AdoptPet;
import com.example.petopia.model.pojo.ShopCategory;

import java.util.Arrays;

public class FragmentShop extends Fragment implements IFragmentShop{

    View view;
    RecyclerView rvShopCategory;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_shop, container, false);

        rvShopCategory = view.findViewById(R.id.recyclerViewShopCategoryID);



        return view;
    }


    private void displayShopCategory() {
        ShopCategory[] shopCategories = {
          new ShopCategory("Food", R.drawable.pet_food);
          new ShopCategory("Toys", R.drawable.pet_toys);
          new ShopCategory("Accessories", R.drawable.pet_accessories);
          new ShopCategory("Health & Wellness", R.drawable.pet_health_wellness);
          new ShopCategory("Training", R.drawable.pet_training);
          new ShopCategory("Housing", R.drawable.pet_housing);
          new ShopCategory("Pet Care", R.drawable.pet_care);
          new ShopCategory("Travel", R.drawable.pet_travel);
        };
        rvShopCategory.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        PetAdoptionCatgAdapter petAdoptionCatgAdapter = new PetAdoptionCatgAdapter(shopCategories);
        rvShopCategory.setAdapter(petAdoptionCatgAdapter);
    }



}
