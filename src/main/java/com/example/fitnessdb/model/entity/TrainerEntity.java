package com.example.fitnessdb.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "trainers")
public class TrainerEntity extends BaseEntity{
    public String name;
    public String experience;
    public String headingTrainer;
    public String trainerPictureUrl;
    public LocalDate dateOfBirth;


    public TrainerEntity() {
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public TrainerEntity setName(String name) {
        this.name = name;
        return this;
    }

    @Column(name = "experience", nullable = false, columnDefinition = "TEXT")
    public String getExperience() {
        return experience;
    }

    public TrainerEntity setExperience(String experience) {
        this.experience = experience;
        return this;
    }

    @Column(name = "heading_trainer", nullable = false, columnDefinition = "TEXT")
    public String getHeadingTrainer() {
        return headingTrainer;
    }

    public TrainerEntity setHeadingTrainer(String headingTrainer) {
        this.headingTrainer = headingTrainer;
        return this;
    }

    @Column(name = "trainer_picture_url", nullable = false, columnDefinition = "TEXT")
    public String getTrainerPictureUrl() {
        return trainerPictureUrl;
    }

    public TrainerEntity setTrainerPictureUrl(String trainerPictureUrl) {
        this.trainerPictureUrl = trainerPictureUrl;
        return this;
    }

    @Column(name = "date_of_birth", nullable = true)
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public TrainerEntity setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }


}
