package com.example.fitnessdb.model.dto;


public class TimeCourseDto {
    private String dayOfWeek;
    private String time;

    public TimeCourseDto() {
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public TimeCourseDto setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
        return this;
    }

    public String getTime() {
        return time;
    }

    public TimeCourseDto setTime(String time) {
        this.time = time;
        return this;
    }
}
