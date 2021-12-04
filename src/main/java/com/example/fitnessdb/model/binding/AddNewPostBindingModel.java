package com.example.fitnessdb.model.binding;

import com.example.fitnessdb.model.entity.UserEntity;
import com.example.fitnessdb.model.entity.WorkoutEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;

public class AddNewPostBindingModel {
   // private String title;
    private String message;

    private float stars;
    private UserEntity author;
    private WorkoutEntity workout;

    public AddNewPostBindingModel() {
    }


//    public String getTitle() {
//        return title;
//    }
//
//    public AddNewPostBindingModel setTitle(String title) {
//        this.title = title;
//        return this;
//    }

    @Size(min = 10)
    public String getMessage() {
        return message;
    }

    public AddNewPostBindingModel setMessage(String message) {
        this.message = message;
        return this;
    }



    public float getStars() {
        return stars;
    }

    public AddNewPostBindingModel setStars(float stars) {
        this.stars = stars;
        return this;
    }


    public UserEntity getAuthor() {
        return author;
    }

    public AddNewPostBindingModel setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }


    public WorkoutEntity getWorkout() {
        return workout;
    }

    public AddNewPostBindingModel setWorkout(WorkoutEntity workout) {
        this.workout = workout;
        return this;
    }
}
