package com.example.fitnessdb.model.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
public class PostEntity extends BaseEntity{
   // private String title;
    private String message;
    private LocalDateTime date;
    private float stars;
    private UserEntity author;
    private WorkoutEntity workout;

    public PostEntity() {
    }

//    @Column(nullable = false, length = 300)
//    public String getTitle() {
//        return title;
//    }
//
//    public PostEntity setTitle(String title) {
//        this.title = title;
//        return this;
//    }


    @Column(name = "creation_date",nullable = false)
    public LocalDateTime getDate() {
        return date;
    }

    public PostEntity setDate(LocalDateTime date) {
        this.date = date;
        return this;
    }

    @Column(name = "message", nullable = false, columnDefinition = "TEXT")
    public String getMessage() {
        return message;
    }

    public PostEntity setMessage(String message) {
        this.message = message;
        return this;
    }

    @ManyToOne
    public UserEntity getAuthor() {
        return author;
    }

    public PostEntity setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }

    @Column(name = "stars", nullable = false)
            public float getStars() {
        return stars;
    }

    public PostEntity setStars(float stars) {
        this.stars = stars;
        return this;
    }

    @ManyToOne
    public WorkoutEntity getWorkout() {
        return workout;
    }

    public PostEntity setWorkout(WorkoutEntity workout) {
        this.workout = workout;
        return this;
    }
}
