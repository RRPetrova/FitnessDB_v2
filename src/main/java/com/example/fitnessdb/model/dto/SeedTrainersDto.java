package com.example.fitnessdb.model.dto;

import com.google.gson.annotations.Expose;

import java.time.LocalDate;

public class SeedTrainersDto {
    @Expose
    public String name;
    @Expose
    public String experience;
    @Expose
    public String headingTrainer;
    @Expose
    public String trainerPictureUrl;
    @Expose
    public LocalDate dateOfBirth;

    public SeedTrainersDto() {

    }

    public String getName() {
        return name;
    }

    public SeedTrainersDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getExperience() {
        return experience;
    }

    public SeedTrainersDto setExperience(String experience) {
        this.experience = experience;
        return this;
    }

    public String getHeadingTrainer() {
        return headingTrainer;
    }

    public SeedTrainersDto setHeadingTrainer(String headingTrainer) {
        this.headingTrainer = headingTrainer;
        return this;
    }

    public String getTrainerPictureUrl() {
        return trainerPictureUrl;
    }

    public SeedTrainersDto setTrainerPictureUrl(String trainerPictureUrl) {
        this.trainerPictureUrl = trainerPictureUrl;
        return this;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public SeedTrainersDto setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }
}
