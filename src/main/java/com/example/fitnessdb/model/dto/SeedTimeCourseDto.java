package com.example.fitnessdb.model.dto;

import com.google.gson.annotations.Expose;

public class SeedTimeCourseDto {
    @Expose
    private String dayOfWeek;
    @Expose
    private String time;

    public SeedTimeCourseDto() {
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public SeedTimeCourseDto setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
        return this;
    }

    public String getTime() {
        return time;
    }

    public SeedTimeCourseDto setTime(String time) {
        this.time = time;
        return this;
    }
}
