package com.example.petopia.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.petopia.R;
import com.example.petopia.adapter.PetAdoptionAdapter;
import com.example.petopia.adapter.PetAdoptionCatgAdapter;
import com.example.petopia.controller.FragmentAdoptionController;
import com.example.petopia.controller.IFragmentAdoptionController;
import com.example.petopia.model.pojo.AdoptPet;
import com.example.petopia.model.pojo.PetAdoption;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Arrays;
import java.util.List;

public class FragmentAdoptions extends Fragment implements IFragmentAdoptions {

    View view;
    RecyclerView rvAdoptPetCatg, rvPetAdoption;
    IFragmentAdoptionController fragmentAdoptionController;
    PetAdoptionAdapter petAdoptionAdapter;
    private FloatingActionButton floatingActionButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_adoption, container, false);

        rvAdoptPetCatg = view.findViewById(R.id.RvAdoptionCategoryID);
        rvPetAdoption = view.findViewById(R.id.RvPetAdoptionID);
        floatingActionButton = view.findViewById(R.id.floating_action_button);


        fragmentAdoptionController = new FragmentAdoptionController(this, getContext());
        showAdotionPetCatg();
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the activity you want to navigate to
                Intent intent = new Intent(getActivity(), AddAdoption.class);
                startActivity(intent);
            }
        });


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

        fragmentAdoptionController.onGetPetAdoption();
    }


    @Override
    public void onError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSuccess(List<PetAdoption> petList) {
        try {
            if (getContext() != null) {
                Toast.makeText(getContext(), "" + petList.size(), Toast.LENGTH_SHORT).show();

                int spanCount = 2;
                rvPetAdoption.setLayoutManager(new GridLayoutManager(getContext(), spanCount));

                petAdoptionAdapter = new PetAdoptionAdapter(petList, getContext());
                rvPetAdoption.setAdapter(petAdoptionAdapter);
            } else {
                // Handle null context
                Log.e("FragmentAdoptions", "Context is null");
            }
        } catch (Exception e) {
            Toast.makeText(getContext(), "" + e, Toast.LENGTH_SHORT).show();
        }
    }

}