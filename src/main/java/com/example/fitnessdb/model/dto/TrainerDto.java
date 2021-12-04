package com.example.fitnessdb.model.dto;

import java.time.LocalDate;

public class TrainerDto {
    public String name;
    public String experience;
    public String headingTrainer;
    public String trainerPictureUrl;
    public LocalDate dateOfBirth;

    public TrainerDto() {
    }

    public String getName() {
        return name;
    }

    public TrainerDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getExperience() {
        return experience;
    }

    public TrainerDto setExperience(String experience) {
        this.experience = experience;
        return this;
    }

    public String getHeadingTrainer() {
        return headingTrainer;
    }

    public TrainerDto setHeadingTrainer(String headingTrainer) {
        this.headingTrainer = headingTrainer;
        return this;
    }

    public String getTrainerPictureUrl() {
        return trainerPictureUrl;
    }

    public TrainerDto setTrainerPictureUrl(String trainerPictureUrl) {
        this.trainerPictureUrl = trainerPictureUrl;
        return this;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public TrainerDto setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }
}
