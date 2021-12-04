package com.example.fitnessdb.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "workouts")
public class WorkoutEntity extends BaseEntity{
    public String name;
    public Integer duration;
    public String description;
    public String heading;
    public TrainerEntity trainer;
    public String imageUrl;
    public BigDecimal price;
   // public Set<PostEntity> reviews = new HashSet<PostEntity>(0);
    public List<PostEntity> reviews = new ArrayList<>();
    public TimeCourseEntity timeCourse;

    public WorkoutEntity() {
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public WorkoutEntity setName(String name) {
        this.name = name;
        return this;
    }

    @Column(name = "duration", nullable = false)
    public Integer getDuration() {
        return duration;
    }

    public WorkoutEntity setDuration(Integer duration) {
        this.duration = duration;
        return this;
    }

    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    public String getDescription() {
        return description;
    }

    public WorkoutEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    @Column(name = "heading", columnDefinition = "TEXT", nullable = false)
    public String getHeading() {
        return heading;
    }

    public WorkoutEntity setHeading(String heading) {
        this.heading = heading;
        return this;
    }

    @OneToOne
    public TrainerEntity getTrainer() {
        return trainer;
    }

    public WorkoutEntity setTrainer(TrainerEntity trainer) {
        this.trainer = trainer;
        return this;
    }

    @Column(name = "imageUrl", nullable = false, columnDefinition = "TEXT")
    public String getImageUrl() {
        return imageUrl;
    }

    public WorkoutEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    @Column(name = "price", nullable = false)
    public BigDecimal getPrice() {
        return price;
    }

    public WorkoutEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    @OneToMany(mappedBy = "workout", cascade = CascadeType.ALL)
    public List<PostEntity> getReviews() {
        return reviews;
    }

    public WorkoutEntity setReviews(List<PostEntity> reviews) {
        this.reviews = reviews;
        return this;
    }

    @OneToOne
    public TimeCourseEntity getTimeCourse() {
        return timeCourse;
    }

    public WorkoutEntity setTimeCourse(TimeCourseEntity timeCourse) {
        this.timeCourse = timeCourse;
        return this;
    }
}
