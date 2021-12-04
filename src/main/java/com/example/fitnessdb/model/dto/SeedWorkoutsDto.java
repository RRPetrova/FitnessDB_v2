package com.example.fitnessdb.model.dto;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class SeedWorkoutsDto {
    @Expose
    public String name;
    @Expose
    public Integer duration;
    @Expose
    public String description;
    @Expose
    public String heading;
    @Expose
    public String trainerName;
    @Expose
    public String imageUrl;
    @Expose
    public BigDecimal price;


    public SeedWorkoutsDto() {
    }

    public String getName() {
        return name;
    }

    public SeedWorkoutsDto setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getDuration() {
        return duration;
    }

    public SeedWorkoutsDto setDuration(Integer duration) {
        this.duration = duration;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SeedWorkoutsDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getHeading() {
        return heading;
    }

    public SeedWorkoutsDto setHeading(String heading) {
        this.heading = heading;
        return this;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public SeedWorkoutsDto setTrainerName(String trainerName) {
        this.trainerName = trainerName;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public SeedWorkoutsDto setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public SeedWorkoutsDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}
