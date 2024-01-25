package com.example.petopia.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.petopia.R;
import com.example.petopia.adapter.PetAdapter;
import com.example.petopia.adapter.PetAdoptionCatgAdapter;
import com.example.petopia.controller.FragmentAdoptionController;
import com.example.petopia.controller.IFragmentAdoptionController;
import com.example.petopia.model.pojo.AdoptPet;
import com.example.petopia.model.pojo.PetAdoption;
import com.example.petopia.model.pojo.YourPet;

import java.util.Arrays;
import java.util.List;

public class FragmentAdoptions extends Fragment implements IFragmentAdoptions {

    View view;
    RecyclerView rvAdoptPetCatg;
    IFragmentAdoptionController fragmentAdoptionController;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_adoption, container, false);

        rvAdoptPetCatg = view.findViewById(R.id.RvAdoptionCategoryID);

        fragmentAdoptionController = new FragmentAdoptionController(this, getContext());


        showAdotionPetCatg();


        return view;
    }

    private void showAdotionPetCatg() {
        AdoptPet[] adoptPets = {
                new AdoptPet("Cat", R.drawable.ic_cat2),
                new AdoptPet("Dog", R.drawable.ic_dog),
                new AdoptPet("Bird", R.drawable.ic_bird),
                new AdoptPet("Fish", R.drawable.ic_fish2),
                new AdoptPet("Rabbit", R.drawable.ic_rabbit),
                new AdoptPet("Others", R.drawable.ic_animals),
                // Add more as needed
        };
        rvAdoptPetCatg.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        PetAdoptionCatgAdapter petAdoptionCatgAdapter = new PetAdoptionCatgAdapter(Arrays.asList(adoptPets));
        rvAdoptPetCatg.setAdapter(petAdoptionCatgAdapter);
    }


    @Override
    public void onError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSuccess(List<PetAdoption> petList) {
//        rvYourPet.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
//        petAdapter = new PetAdapter(getContext());
//        rvYourPet.setAdapter(petAdapter);
//        fragmentHomeController.addObserver(petAdapter);
    }
}