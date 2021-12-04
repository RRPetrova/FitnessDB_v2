//package com.example.fitnessdb.web;
//
//import com.example.fitnessdb.model.entity.TrainerEntity;
//import com.example.fitnessdb.model.entity.WorkoutEntity;
//import com.example.fitnessdb.repo.TrainerRepo;
//import com.example.fitnessdb.repo.WorkoutRepo;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class TrainerControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private TrainerRepo trainerRepo;
//
//    @Autowired
//    private WorkoutRepo workoutRepo;
//
//    private Long TEST_TRAINER_1_ID, TEST_TRAINER_2_ID;
//    private String TEST_TRAINER_1_NAME = "Ivan", TEST_TRAINER_2_NAME = "Petranka";
//
//
//
//    @BeforeEach
//    public void setUp() {
//        workoutRepo.deleteAll();
//        trainerRepo.deleteAll();
//
//        TrainerEntity trainerEntity1 = new TrainerEntity();
//        trainerEntity1
//                .setName(TEST_TRAINER_1_NAME)
//                .setExperience("Long exp")
//                .setHeadingTrainer("Best trainer ever")
//                .setTrainerPictureUrl("pic1.jpg");
//        TEST_TRAINER_1_ID = trainerRepo.save(trainerEntity1).getId();
//
//        TrainerEntity trainerEntity2 = new TrainerEntity();
//        trainerEntity2
//                .setName(TEST_TRAINER_2_NAME)
//                .setExperience("Long exp2")
//                .setHeadingTrainer("Best trainer2 ever")
//                .setTrainerPictureUrl("pic2.jpg2");
//
//        TEST_TRAINER_2_ID = trainerRepo.save(trainerEntity2).getId();
//    }
//
//    @AfterEach
//    public void tearDown() {
//        trainerRepo.deleteAll();
//    }
//
//
//    @Test
//    public void testTrainerReturnsCorrectStatusCode() throws Exception {
//        this.mockMvc.perform(get("/trainers"))
//                .andExpect(status().isOk());
//    }
//
//
//}
