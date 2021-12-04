package com.example.fitnessdb.model.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

public class PostDetailsViewDto {
    private Long id;
    private String message;
    private LocalDateTime date;
    private float stars;
    private UserCredentialsDto userCredentialsDto;


    public PostDetailsViewDto() {
    }


    public Long getId() {
        return id;
    }

    public PostDetailsViewDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getMessage() {
        return message;
    }


    public PostDetailsViewDto setMessage(String message) {
        this.message = message;
        return this;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public PostDetailsViewDto setDate(LocalDateTime date) {
        this.date = date;
        return this;
    }

    public float getStars() {
        return stars;
    }

    public PostDetailsViewDto setStars(float stars) {
        this.stars = stars;
        return this;
    }

    public UserCredentialsDto getUserCredentialsDto() {
        return userCredentialsDto;
    }

    public PostDetailsViewDto setUserCredentialsDto(UserCredentialsDto userCredentialsDto) {
        this.userCredentialsDto = userCredentialsDto;
        return this;
    }
}
