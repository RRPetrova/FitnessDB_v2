package com.example.fitnessdb.model.dto;

import com.example.fitnessdb.model.entity.PostEntity;
import com.example.fitnessdb.model.entity.TrainerEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WorkoutDetailsViewDto {
    public Long id;
    public String name;
    public String description;
    public String imageUrl;
    public BigDecimal price;
    public TimeCourseDto timeCourse;
    public List<PostEntity> reviews = new ArrayList();


    public WorkoutDetailsViewDto() {
    }

    public Long getId() {
        return id;
    }

    public WorkoutDetailsViewDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public WorkoutDetailsViewDto setName(String name) {
        this.name = name;
        return this;
    }


    public String getDescription() {
        return description;
    }

    public WorkoutDetailsViewDto setDescription(String description) {
        this.description = description;
        return this;
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public WorkoutDetailsViewDto setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public WorkoutDetailsViewDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }


    public TimeCourseDto getTimeCourse() {
        return timeCourse;
    }

    public WorkoutDetailsViewDto setTimeCourse(TimeCourseDto timeCourse) {
        this.timeCourse = timeCourse;
        return this;
    }

    public List<PostEntity> getReviews() {
        return reviews;
    }

    public WorkoutDetailsViewDto setReviews(List<PostEntity> reviews) {
        this.reviews = reviews;
        return this;
    }

    public Double avgRate() {
        return this.reviews
                .stream()
                .mapToDouble(PostEntity::getStars)
                .average()
                .orElse(Double.NaN);
    }
}
