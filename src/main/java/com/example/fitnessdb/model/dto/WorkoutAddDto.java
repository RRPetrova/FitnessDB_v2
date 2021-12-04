package com.example.fitnessdb.model.dto;

import java.math.BigDecimal;

public class WorkoutAddDto {
    public Long id;
    public String name;
    public Integer duration;
    public String description;
    public String heading;
    public TrainerDto trainerDto;
    public String imageUrl;
    public BigDecimal price;
    public SeedTimeCourseDto time;

    public WorkoutAddDto() {
    }

    public Long getId() {
        return id;
    }

    public WorkoutAddDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public WorkoutAddDto setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getDuration() {
        return duration;
    }

    public WorkoutAddDto setDuration(Integer duration) {
        this.duration = duration;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public WorkoutAddDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getHeading() {
        return heading;
    }

    public WorkoutAddDto setHeading(String heading) {
        this.heading = heading;
        return this;
    }

    public TrainerDto getTrainerDto() {
        return trainerDto;
    }

    public WorkoutAddDto setTrainerDto(TrainerDto trainerDto) {
        this.trainerDto = trainerDto;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public WorkoutAddDto setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public WorkoutAddDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public SeedTimeCourseDto getTime() {
        return time;
    }

    public WorkoutAddDto setTime(SeedTimeCourseDto time) {
        this.time = time;
        return this;
    }
}
