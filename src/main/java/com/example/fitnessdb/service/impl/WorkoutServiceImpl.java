package com.example.fitnessdb.service.impl;

import com.example.fitnessdb.exceptions.ResourceNotFoundException;
import com.example.fitnessdb.model.binding.AddNewWorkoutBindingModel;
import com.example.fitnessdb.model.dto.*;
import com.example.fitnessdb.model.entity.TimeCourseEntity;
import com.example.fitnessdb.model.entity.TrainerEntity;
import com.example.fitnessdb.model.entity.UserEntity;
import com.example.fitnessdb.model.entity.WorkoutEntity;
import com.example.fitnessdb.repo.TimeCourseRepo;
import com.example.fitnessdb.repo.UserRepo;
import com.example.fitnessdb.repo.WorkoutRepo;
import com.example.fitnessdb.service.TrainerService;
import com.example.fitnessdb.service.WorkoutService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WorkoutServiceImpl implements WorkoutService {

    private static final String FILES_PATH = "src/main/resources/init/";
    private static final String WORKOUTS_FILE = "workouts.json";

    private final ModelMapper modelMapper;
    private final Gson gson;
    private final WorkoutRepo workoutRepo;
    private final TrainerService trainerService;
    private final UserRepo userRepo;
    private final TimeCourseRepo timeCourseRepo;

    public WorkoutServiceImpl(ModelMapper modelMapper, Gson gson,
                              WorkoutRepo workoutRepo, TrainerService trainerService,
                              UserRepo userRepo, TimeCourseRepo timeCourseRepo) {
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.workoutRepo = workoutRepo;
        this.trainerService = trainerService;
        this.userRepo = userRepo;
        this.timeCourseRepo = timeCourseRepo;
    }

    @Transactional
    @Override
    public void seedWorkouts() throws IOException {
        SeedWorkoutsDto[] seedWorkouts = gson.fromJson(Files.readString
                (Path.of(FILES_PATH + WORKOUTS_FILE)), SeedWorkoutsDto[].class);


        List<TimeCourseEntity> timeCourseEntities = this.timeCourseRepo.findAll();

        int index = 0;
//        Arrays.stream(seedWorkouts)
//                .map(currWorkout -> {
//                    WorkoutEntity workout = this.modelMapper.map(currWorkout, WorkoutEntity.class);
//                    TrainerEntity trainerEntity = this.trainerService.findByName(currWorkout.getTrainerName());
//                    workout.setTrainer(trainerEntity);
//                    workout.setTimeCourse(timeCourseEntities.get(index));
//                    index++;
//                    return workout;
//                })
//                .forEach(this.workoutRepo::save);


        for (SeedWorkoutsDto currWorkout : seedWorkouts) {
            WorkoutEntity workout = this.modelMapper.map(currWorkout, WorkoutEntity.class);
            TrainerEntity trainerEntity = this.trainerService.findByName(currWorkout.getTrainerName());
            workout.setTrainer(trainerEntity);
            workout.setTimeCourse(timeCourseEntities.get(index));
            index++;
            this.workoutRepo.save(workout);
        }


    }

    @Transactional
    @Override
    public List<WorkoutDetailsViewDto> getAllWorkouts() {
        return this.workoutRepo.findAll()
                .stream()
                .map(currWorkoutEntity -> this.modelMapper.map(currWorkoutEntity, WorkoutDetailsViewDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public List<String> getAllWorkoutsNames() {
        return this.workoutRepo
                .findAll()
                .stream()
                .map(WorkoutEntity::getName)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteWorkoutById(Long id) {
        this.workoutRepo.deleteById(id);
    }

    @Transactional
    @Override
    public WorkoutsWithDetailsDto findById(Long id) {
        Optional<WorkoutEntity> byId = this.workoutRepo.findById(id);

        return this.workoutRepo.findById(id)
                .map(currWorkout -> {
                    WorkoutsWithDetailsDto workoutsWithDetailsDto = this.modelMapper
                            .map(currWorkout, WorkoutsWithDetailsDto.class);
                    workoutsWithDetailsDto.setTrainer(currWorkout.getTrainer());
                    return workoutsWithDetailsDto;
                }).orElseThrow(() -> new ResourceNotFoundException("Workout with id " + id + " not found"));
    }

    @Override
    public boolean workoutNameAlreadyExists(String name) {
        return this.workoutRepo.findByName(name).isPresent();
    }

    @Transactional
    @Override
    public List<WorkoutDetailsViewDto> getUserWorkouts(String name) {
        UserEntity userEntity = this.userRepo
                .findByUsername(name)
                .orElseThrow(() -> new ResourceNotFoundException("User with username " + name + " not found."));

        List<WorkoutDetailsViewDto> workoutDetailsViewDtos = userEntity.getMyWorkouts()
                .stream()
                .map(workout -> {
                    WorkoutDetailsViewDto workoutDetailsViewDto = this.modelMapper.map(workout, WorkoutDetailsViewDto.class);
                  //  TimeCourseDto timeCourseDto = this.modelMapper.map(workout, TimeCourseDto.class);

                    return workoutDetailsViewDto;
                })
                .collect(Collectors.toList());

        System.out.println("r");
        return workoutDetailsViewDtos;

    }

    @Transactional
    @Override
    public void addWorkout(AddNewWorkoutBindingModel addNewWorkoutBindingModel) {
        WorkoutAddDto workoutAddDto = this.modelMapper.map(addNewWorkoutBindingModel, WorkoutAddDto.class);
        String trainerName = addNewWorkoutBindingModel.getTrainerName();

        TimeCourseEntity timeCourseEntity = this.timeCourseRepo
                .findByDayOfWeekAndTime(addNewWorkoutBindingModel.getDayOfWeek(),
                        String.valueOf(addNewWorkoutBindingModel.getTime()))
                .orElseThrow(() -> new ResourceNotFoundException("This schedule doesn't exists!"));


        WorkoutEntity workoutEntity = this.modelMapper.map(workoutAddDto, WorkoutEntity.class);
        workoutEntity.setTrainer(this.trainerService.findByName(trainerName));
        workoutEntity.setTimeCourse(timeCourseEntity);
        this.workoutRepo.save(workoutEntity);
    }


}