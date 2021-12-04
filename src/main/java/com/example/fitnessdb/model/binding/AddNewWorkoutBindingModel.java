package com.example.fitnessdb.model.binding;

import com.example.fitnessdb.model.entity.TrainerEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.sql.Time;

public class AddNewWorkoutBindingModel {
    public String name;
    public Integer duration;
    public Time time;
    public String description;
    public String heading;
    public String trainerName;
    public String imageUrl;
    public BigDecimal price;
    public String dayOfWeek;

    public AddNewWorkoutBindingModel() {

    }

    @Size(min = 3, max = 20, message = "Name must be between 3 and 20 symbols.")
    @NotBlank(message = "Field cannot be blank.")
    public String getName() {
        return name;
    }


    public Time getTime() {
        return time;
    }

    public AddNewWorkoutBindingModel setTime(Time time) {
        this.time = time;
        return this;
    }

    public AddNewWorkoutBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    @Positive
    @NotNull
    public Integer getDuration() {
        return duration;
    }

    public AddNewWorkoutBindingModel setDuration(Integer duration) {
        this.duration = duration;
        return this;
    }

    @Size(min = 10, max = 200, message = "Description must be between 10 and 200 symbols.")
    @NotBlank(message = "Field cannot be blank.")
    public String getDescription() {
        return description;
    }

    public AddNewWorkoutBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    @Size(min = 3, max = 20, message = "Heading must be between 3 and 20 symbols.")
    @NotBlank(message = "Field cannot be blank.")
    public String getHeading() {
        return heading;
    }

    public AddNewWorkoutBindingModel setHeading(String heading) {
        this.heading = heading;
        return this;
    }



    @NotNull(message = "Please select trainer.")
    @NotBlank(message = "Field cannot be blank.")
    public String getTrainerName() {
        return trainerName;
    }

    public AddNewWorkoutBindingModel setTrainerName(String trainerName) {
        this.trainerName = trainerName;
        return this;
    }

    @Size(min = 3, max = 255, message = "Image Url must be between 3 and 255 symbols.")
    @NotBlank(message = "Field cannot be blank.")
    public String getImageUrl() {
        return imageUrl;
    }

    public AddNewWorkoutBindingModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }


    @Positive
    @NotNull
    public BigDecimal getPrice() {
        return price;
    }

    public AddNewWorkoutBindingModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    @NotNull
    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public AddNewWorkoutBindingModel setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
        return this;
    }
}
