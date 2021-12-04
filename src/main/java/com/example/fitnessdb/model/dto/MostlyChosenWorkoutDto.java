package com.example.fitnessdb.model.dto;

import java.text.DecimalFormat;

public class MostlyChosenWorkoutDto {
    String name;
    Float percentage;

    public MostlyChosenWorkoutDto() {
    }

    public String getName() {
        return name;
    }

    public MostlyChosenWorkoutDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getPercentage() {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        return (df.format(percentage));

    }

    public MostlyChosenWorkoutDto setPercentage(Float percentage) {
        this.percentage = percentage;
        return this;
    }
}
