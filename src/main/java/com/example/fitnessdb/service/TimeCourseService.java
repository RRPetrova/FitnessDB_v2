package com.example.fitnessdb.service;

import com.example.fitnessdb.model.dto.TimeCourseDto;

import java.io.IOException;

public interface TimeCourseService {
    void seedSchedule() throws IOException;


    void addNewSchedule(TimeCourseDto timeCourseDto);
}
