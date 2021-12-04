package com.example.fitnessdb.model.dto;


import java.time.LocalDateTime;
import java.util.Date;

public class PostDetailsDto {
   // private String title;
    private String message;
    private LocalDateTime date = LocalDateTime.now();
    private float stars;
    private UserCredentialsDto userCredentialsDto;
    private WorkoutsWithDetailsDto workoutsWithDetailsDto;

//    public String getTitle() {
//        return title;
//    }
//
//    public PostDetailsDto setTitle(String title) {
//        this.title = title;
//        return this;
//    }

    public String getMessage() {
        return message;
    }

    public PostDetailsDto setMessage(String message) {
        this.message = message;
        return this;
    }


    public LocalDateTime getDate() {
        return date;
    }

    public PostDetailsDto setDate(LocalDateTime date) {
        this.date = date;
        return this;
    }

    public float getStars() {
        return stars;
    }

    public PostDetailsDto setStars(float stars) {
        this.stars = stars;
        return this;
    }

    public UserCredentialsDto getUserCredentialsDto() {
        return userCredentialsDto;
    }

    public PostDetailsDto setUserCredentialsDto(UserCredentialsDto userCredentialsDto) {
        this.userCredentialsDto = userCredentialsDto;
        return this;
    }

    public WorkoutsWithDetailsDto getWorkoutsWithDetailsDto() {
        return workoutsWithDetailsDto;
    }

    public PostDetailsDto setWorkoutsWithDetailsDto(WorkoutsWithDetailsDto workoutsWithDetailsDto) {
        this.workoutsWithDetailsDto = workoutsWithDetailsDto;
        return this;
    }
}
