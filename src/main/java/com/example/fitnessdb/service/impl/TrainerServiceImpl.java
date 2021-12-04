package com.example.fitnessdb.service.impl;

import com.example.fitnessdb.exceptions.ResourceNotFoundException;
import com.example.fitnessdb.model.dto.SeedTrainersDto;
import com.example.fitnessdb.model.binding.AddNewTrainerBindingModel;
import com.example.fitnessdb.model.entity.TrainerEntity;
import com.example.fitnessdb.repo.TrainerRepo;
import com.example.fitnessdb.service.TrainerService;
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
import java.util.stream.Collectors;

@Service
public class TrainerServiceImpl implements TrainerService {

    private static final String FILES_PATH = "src/main/resources/init/";
    private static final String TRAINERS_FILE = "trainers.json";

    private final ModelMapper modelMapper;
    private final Gson gson;
    private final TrainerRepo trainerRepo;


    public TrainerServiceImpl(ModelMapper modelMapper, Gson gson,
                              TrainerRepo trainerRepo) {
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.trainerRepo = trainerRepo;
    }



    @Transactional
    @Override
    public void seedTrainers() throws IOException {
        SeedTrainersDto[] seedTrainersDtos = gson.fromJson(Files.readString
                (Path.of(FILES_PATH + TRAINERS_FILE)), SeedTrainersDto[].class);

        Arrays.stream(seedTrainersDtos)
                .map(currTrainer -> this.modelMapper.map(currTrainer, TrainerEntity.class))
                .forEach(this.trainerRepo::save);
    }

    @Override
    public TrainerEntity findById(Long trainerId) {
        return this.trainerRepo
                .findById(trainerId)
              .orElseThrow(() -> new ResourceNotFoundException("Trainer with id " + trainerId +" not found"));
    }

    @Override
    public TrainerEntity findByName(String trainerName) {
        return this.trainerRepo
                .findByName(trainerName)
                .orElseThrow(() -> new ResourceNotFoundException("Trainer with name " + trainerName +" not found"));
    }

    @Override
    public List<String> getAllTrainerNames() {
        return this.trainerRepo.findAll()
                .stream()
                .map(TrainerEntity::getName)
                .collect(Collectors.toList());
    }

    @Override
    public boolean trainerAlreadyExists(String name) {
        return this.trainerRepo.findByName(name).isPresent();
    }

    @Override
    public void addTrainer(AddNewTrainerBindingModel addNewTrainerBindingModel) {
        TrainerEntity trainerEntity = this.modelMapper
                .map(addNewTrainerBindingModel, TrainerEntity.class);
        this.trainerRepo.save(trainerEntity);
    }

    @Override
    public List<String> getAllFreeTrainersNames() {
//        System.out.println(this.trainerRepo.findFreeTrainers());
//        List<String> arrL = new ArrayList<>();
//        this.trainerRepo.findFreeTrainers().stream()
//                .forEach(arrL::add);
//        System.out.println(arrL);
        return this.trainerRepo.findFreeTrainers();
    }
}
