package com.example.petopia.model.pojo;

public class YourPet {

    String userId, petName, petType, petAge, petWeight, gender, image;

    public YourPet(String userId, String petName, String petType, String petAge, String petWeight, String gender, String image) {
        this.userId = userId;
        this.petName = petName;
        this.petType = petType;
        this.petAge = petAge;
        this.petWeight = petWeight;
        this.gender = gender;
        this.image = image;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public String getPetAge() {
        return petAge;
    }

    public void setPetAge(String petAge) {
        this.petAge = petAge;
    }

    public String getPetWeight() {
        return petWeight;
    }

    public void setPetWeight(String petWeight) {
        this.petWeight = petWeight;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
