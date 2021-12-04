package com.example.fitnessdb.service.impl;

import com.example.fitnessdb.exceptions.ResourceNotFoundException;
import com.example.fitnessdb.model.entity.TrainerEntity;
import com.example.fitnessdb.repo.TrainerRepo;
import com.google.gson.Gson;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TrainerServiceImplTest {

    private TrainerEntity trainerEntity1;
    private TrainerEntity trainerEntity2;

    private TrainerServiceImpl serviceToTest;

    @Mock
    TrainerRepo mockedTrainerRepo;


    @BeforeEach
    public void init() {
        this.trainerEntity1 = new TrainerEntity();
        trainerEntity1.setId(1L);
        trainerEntity1
                .setName("Pesho")
                .setExperience("Long exp")
                .setHeadingTrainer("Best trainer ever")
                .setTrainerPictureUrl("pic1.jpg");

        this.trainerEntity2 = new TrainerEntity();
        trainerEntity2.setId(2L);
        trainerEntity2
                .setName("Ivancho")
                .setExperience("Long exp2")
                .setHeadingTrainer("Best trainer2 ever")
                .setTrainerPictureUrl("pic2.jpg");
        this.mockedTrainerRepo = Mockito.mock(TrainerRepo.class);

        serviceToTest = new TrainerServiceImpl(new ModelMapper(),
                new Gson(), mockedTrainerRepo);
    }

    @AfterEach
    public void reset() {
        Mockito.reset(mockedTrainerRepo);
    }

    @Test
    public void trainerByNameTest() {

        Mockito.when(this.mockedTrainerRepo.findByName("Pesho")).thenReturn(Optional.of(this.trainerEntity1));
        TrainerEntity trainerEnt1 = serviceToTest.findByName("Pesho");
        Assertions.assertEquals(trainerEntity1.getId(), trainerEnt1.getId());

        Mockito.when(mockedTrainerRepo.findByName("Ivancho")).thenReturn(Optional.of(this.trainerEntity2));
        TrainerEntity trainerEnt2 = serviceToTest.findByName("Ivancho");
        Assertions.assertEquals(trainerEntity2.getId(), trainerEnt2.getId());
    }

    @Test
    public void trainerByIdTest() {
        when(mockedTrainerRepo.findById(1L)).thenReturn(Optional.of(trainerEntity1));
        TrainerEntity trainerEnt1 = serviceToTest.findById(1L);
        Assertions.assertEquals(trainerEntity1.getId(), trainerEnt1.getId());


        when(mockedTrainerRepo.findById(2L)).thenReturn(Optional.of(trainerEntity2));
        TrainerEntity trainerEnt2 = serviceToTest.findById(2L);
        Assertions.assertEquals(trainerEntity2.getId(), trainerEnt2.getId());
    }

    @Test
    void trainerNotFoundByName() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> serviceToTest.findByName("Mitkoo"));
    }

    @Test
    void trainerNotFoundById() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> serviceToTest.findById(600L));
    }
}
