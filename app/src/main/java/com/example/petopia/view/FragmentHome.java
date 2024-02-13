package com.example.petopia.view;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.petopia.R;
import com.example.petopia.adapter.ArticleAdapter;
import com.example.petopia.adapter.PetAdapter;
import com.example.petopia.adapter.ServiceAdapter;
import com.example.petopia.controller.FragmentHomeController;
import com.example.petopia.controller.IFragmentHomeController;
import com.example.petopia.model.pojo.Article;
import com.example.petopia.model.pojo.Event;
import com.example.petopia.model.pojo.Services;
import com.example.petopia.model.pojo.YourPet;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FragmentHome extends Fragment implements IFragmentHome {

    private ImageSlider imageSlider;
    IFragmentHomeController fragmentHomeController;
    RecyclerView rvYourPet, rvServices, rvArticle;
    PetAdapter petAdapter;
    View view;
    TextView yourPetTxt;

    MaterialCardView addYourPetBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        imageSlider = view.findViewById(R.id.productImageSlider);
        rvYourPet = view.findViewById(R.id.RvYourPets);
        rvServices = view.findViewById(R.id.RvServiceID);
        rvArticle = view.findViewById(R.id.RvArticleID);
        addYourPetBtn = view.findViewById(R.id.addYourPetLayout);
        yourPetTxt = view.findViewById(R.id.YourPetTxt);

        fragmentHomeController = new FragmentHomeController(this, getContext());

        showServices();

        try {
            SharedPreferences sharedPreferences = getContext().getSharedPreferences("MyPrefs", MODE_PRIVATE);
            String userID = sharedPreferences.getString("user_id", "");
            fragmentHomeController.onGetEvents();
            fragmentHomeController.onGetYourPet(userID);
            fragmentHomeController.onGetArticle();
        } catch (Exception e) {
            Toast.makeText(getContext(), "" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }


        addYourPetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AddYourPet.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void slideImage(ArrayList<SlideModel> imageList) {
        imageSlider.setImageList(imageList);
    }


    @Override
    public void onGetEvents(List<Event> eventList) {
        ArrayList<SlideModel> imageList = new ArrayList<>();
        for (Event event : eventList) {
            String imageUrl = "https://petopia-pet.000webhostapp.com/images/" + event.getImage();
            imageList.add(new SlideModel(imageUrl, ScaleTypes.CENTER_CROP));
        }
        slideImage(imageList);
    }


    @Override
    public void onErrorEvents(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onGetYourPetError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onGetYourPetSuccess(List<YourPet> yourPetList) {
        Toast.makeText(getContext(), ""+yourPetList.size(), Toast.LENGTH_SHORT).show();

        rvYourPet.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        petAdapter = new PetAdapter(yourPetList, getContext());
        rvYourPet.setAdapter(petAdapter);
        fragmentHomeController.addObserver(petAdapter);
    }

    public void showServices() {
        Services[] services = {
                new Services("Grooming", R.drawable.ic_grooming),
                new Services("Veterinary", R.drawable.ic_veterinary),
                new Services("Day Care", R.drawable.ic_daycare),
        };
        rvServices.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        ServiceAdapter serviceAdapter = new ServiceAdapter(getContext(), Arrays.asList(services));
        rvServices.setAdapter(serviceAdapter);
    }

    @Override
    public void onGetArticleError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void OnGetArticleSuccess(List<Article> articles) {
        if (articles.size() != 0) {
            rvArticle.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            ArticleAdapter articleAdapter= new ArticleAdapter(articles, getContext());
            rvArticle.setAdapter(articleAdapter);
        }
    }


    @Override
    public void onDestroy() {
        fragmentHomeController.removeObserver(petAdapter);
        super.onDestroy();
    }
}