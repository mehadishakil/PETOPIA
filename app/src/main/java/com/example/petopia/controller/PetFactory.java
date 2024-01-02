package com.example.petopia.controller;

import com.example.petopia.model.pojo.YourPet;

// PetFactory interface
public interface PetFactory {
    YourPet createPet(String userId, String petName, String petAge, String petWeight, String gender, String encodeImage);
}

// Concrete PetFactories
class DogFactory implements PetFactory {
    @Override
    public YourPet createPet(String userId, String petName, String petAge, String petWeight, String gender, String encodeImage) {
        return new YourPet(userId, petName, "Dog", petAge, petWeight, gender, encodeImage);
    }
}

class CatFactory implements PetFactory {
    @Override
    public YourPet createPet(String userId, String petName, String petAge, String petWeight, String gender, String encodeImage) {
        return new YourPet(userId, petName, "Cat", petAge, petWeight, gender, encodeImage);
    }
}


class BirdFactory implements PetFactory {
    @Override
    public YourPet createPet(String userId, String petName, String petAge, String petWeight, String gender, String encodeImage) {
        return new YourPet(userId, petName, "Bird", petAge, petWeight, gender, encodeImage);
    }
}

class RabbitFactory implements PetFactory {
    @Override
    public YourPet createPet(String userId, String petName, String petAge, String petWeight, String gender, String encodeImage) {
        return new YourPet(userId, petName, "Rabbit", petAge, petWeight, gender, encodeImage);
    }
}


class HamsterFactory implements PetFactory {
    @Override
    public YourPet createPet(String userId, String petName, String petAge, String petWeight, String gender, String encodeImage) {
        return new YourPet(userId, petName, "Hamster", petAge, petWeight, gender, encodeImage);
    }
}