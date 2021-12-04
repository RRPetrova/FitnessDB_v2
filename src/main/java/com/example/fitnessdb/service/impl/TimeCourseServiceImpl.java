package com.example.fitnessdb.service.impl;

import com.example.fitnessdb.model.dto.SeedTimeCourseDto;
import com.example.fitnessdb.model.dto.TimeCourseDto;
import com.example.fitnessdb.model.entity.TimeCourseEntity;
import com.example.fitnessdb.repo.TimeCourseRepo;
import com.example.fitnessdb.service.TimeCourseService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
public class TimeCourseServiceImpl implements TimeCourseService {
    private static final String FILES_PATH = "src/main/resources/init/";
    private static final String WORKOUTS_SCHEDULE = "scheduleWorkouts.json";

    private final ModelMapper modelMapper;
    private final Gson gson;
    private final TimeCourseRepo timeCourseRepo;

    public TimeCourseServiceImpl(ModelMapper modelMapper, Gson gson, TimeCourseRepo timeCourseRepo) {
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.timeCourseRepo = timeCourseRepo;
    }


    @Override
    public void seedSchedule() throws IOException {
        SeedTimeCourseDto[] seedTimeCourseDtos = gson.fromJson(Files.readString
                (Path.of(FILES_PATH + WORKOUTS_SCHEDULE)), SeedTimeCourseDto[].class);

        Arrays.stream(seedTimeCourseDtos).
                map(currTimeCourse -> this.modelMapper.map(currTimeCourse, TimeCourseEntity.class))
                .forEach(this.timeCourseRepo::save);

    }

    @Override
    public void addNewSchedule(TimeCourseDto timeCourseDto) {
       this.timeCourseRepo.save(this.modelMapper.map(timeCourseDto, TimeCourseEntity.class));

    }


}
