package com.example.fitnessdb.model.dto;

import com.example.fitnessdb.model.entity.PostEntity;
import com.example.fitnessdb.model.entity.TrainerEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WorkoutsWithDetailsDto {
    public Long id;
    public String name;
    public Integer duration;
    public String description;
    public TrainerEntity trainer;
    public String heading;
    public String imageUrl;
    public BigDecimal price;
    public List<PostEntity> reviews = new ArrayList<>();

    public WorkoutsWithDetailsDto() {
    }

    public Long getId() {
        return id;
    }

    public WorkoutsWithDetailsDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public WorkoutsWithDetailsDto setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getDuration() {
        return duration;
    }

    public WorkoutsWithDetailsDto setDuration(Integer duration) {
        this.duration = duration;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public WorkoutsWithDetailsDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public TrainerEntity getTrainer() {
        return trainer;
    }

    public WorkoutsWithDetailsDto setTrainer(TrainerEntity trainer) {
        this.trainer = trainer;
        return this;
    }

    public String getHeading() {
        return heading;
    }

    public WorkoutsWithDetailsDto setHeading(String heading) {
        this.heading = heading;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public WorkoutsWithDetailsDto setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public WorkoutsWithDetailsDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public List<PostEntity> getReviews() {
        return reviews;
    }

    public WorkoutsWithDetailsDto setReviews(List<PostEntity> reviews) {
        this.reviews = reviews;
        return this;
    }
}
