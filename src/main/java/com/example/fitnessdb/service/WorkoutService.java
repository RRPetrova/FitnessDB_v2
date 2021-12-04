package com.example.fitnessdb.service;

import com.example.fitnessdb.model.dto.WorkoutAddDto;
import com.example.fitnessdb.model.dto.WorkoutDetailsViewDto;
import com.example.fitnessdb.model.dto.WorkoutsWithDetailsDto;
import com.example.fitnessdb.model.binding.AddNewWorkoutBindingModel;
import com.example.fitnessdb.model.entity.WorkoutEntity;

import java.io.IOException;
import java.util.List;

public interface WorkoutService {

    void seedWorkouts() throws IOException;

    List<WorkoutDetailsViewDto> getAllWorkouts();

    List<String> getAllWorkoutsNames();

    void deleteWorkoutById(Long id);

    WorkoutsWithDetailsDto findById(Long id);

    boolean workoutNameAlreadyExists(String name);

    List<WorkoutDetailsViewDto> getUserWorkouts(String name);

    void addWorkout(AddNewWorkoutBindingModel addNewWorkoutBindingModel);


}
