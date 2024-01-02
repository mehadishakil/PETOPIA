package com.example.petopia.controller;

import android.widget.Toast;

import com.example.petopia.model.pojo.ServerResponse;
import com.example.petopia.model.pojo.YourPet;
import com.example.petopia.model.repository.Repository;
import com.example.petopia.view.AddYourPet;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddYourPetController {
    AddYourPet addYourPet;
    Repository repository;

    public AddYourPetController(AddYourPet addYourPet) {
        this.addYourPet = addYourPet;
        this.repository = new Repository();
    }

    public void onAddYourPet(String userId, String petName, String petType, String petAge, String petWeight, String gender, String encodeImage) {
        // YourPet yourPet = createYourPet(userId, petName, petAge, petWeight, gender, encodeImage);
        YourPet yourPet = new YourPet(userId, petName, petType,petAge, petWeight, gender, encodeImage);

        if (yourPet!= null){
            repository.addYourPet(yourPet, new Callback<ServerResponse>() {
                @Override
                public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                    if (response.isSuccessful()) {
                        ServerResponse serverResponse = response.body();
                        if (serverResponse != null && "Successful".equals(serverResponse.getMessage())) {
                            addYourPet.onSuccess(serverResponse.getMessage());
                        } else {
                            addYourPet.onError("Error on Adding you pet" + serverResponse.getMessage());
                        }
                    } else {
                        addYourPet.onError("HTTP Error: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<ServerResponse> call, Throwable t) {
                    addYourPet.onError("Exception: " + t.getMessage());
                }
            });
        }else {
            Toast.makeText(addYourPet.getApplication(), "Null Class", Toast.LENGTH_SHORT).show();
        }


    }



// factory design pattern
    public YourPet createYourPet(String userId, String petName, String petAge, String petWeight, String gender, String encodeImage) {
        String petType = addYourPet.getSelectedPetType();
        YourPet yourPet = null;

        switch (petType) {
            case "Cat":
                PetFactory catFactory = new CatFactory();
                yourPet = catFactory.createPet(userId, petName, petAge, petWeight, gender, encodeImage);
                break;
            case "Dog":
                PetFactory dogFactory = new DogFactory();
                yourPet = dogFactory.createPet(userId, petName, petAge, petWeight, gender, encodeImage);
                break;
            case "Bird":
                PetFactory birdFactory = new BirdFactory();
                yourPet = birdFactory.createPet(userId, petName, petAge, petWeight, gender, encodeImage);
                break;
            case "Rabbit":
                PetFactory rabbitFactory = new RabbitFactory();
                yourPet = rabbitFactory.createPet(userId, petName, petAge, petWeight, gender, encodeImage);
                break;
            case "Hamster":
                PetFactory hamsterFactory = new HamsterFactory();
                yourPet = hamsterFactory.createPet(userId, petName, petAge, petWeight, gender, encodeImage);
                break;
        }

        return yourPet;
    }


}
