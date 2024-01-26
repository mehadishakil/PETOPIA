package com.example.petopia.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.petopia.Observer.DataObserver;
import com.example.petopia.R;
import com.example.petopia.model.pojo.YourPet;

import java.util.ArrayList;
import java.util.List;

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.PetViewHolder> implements DataObserver{


    private List<YourPet> petList = new ArrayList<>();
    private LayoutInflater inflater;
    Context context;



    public PetAdapter(Context context) {
        this.petList = petList;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }




    @NonNull
    @Override
    public PetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.your_pet_layout, parent, false);
        return new PetViewHolder(itemView);
    }







    @Override
    public void onBindViewHolder(@NonNull PetViewHolder holder, int position) {
        YourPet pet = petList.get(position);
        String imageUrl = "https://petopia-pet.000webhostapp.com/your_pet_image/"+pet.getImage();

        Glide.with(context)
                .load(imageUrl)
                .placeholder(R.drawable.loading_image)
                .into(holder.yourPetImage);
        holder.yourPetName.setText(pet.getPetName());
    }




    @Override
    public int getItemCount() {
        return petList.size();
    }

    public static class PetViewHolder extends RecyclerView.ViewHolder {
        public ImageView yourPetImage;
        public TextView yourPetName;
        public PetViewHolder(View itemView) {
            super(itemView);
            yourPetImage = itemView.findViewById(R.id.YourPetImage);
            yourPetName = itemView.findViewById(R.id.YourPetName);
        }
    }



    @Override
    public void onDataChanged(List<YourPet> newData) {
        petList.clear();
        petList.addAll(newData);
        notifyDataSetChanged();
    }

}




