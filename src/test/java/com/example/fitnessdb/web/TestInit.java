package com.example.fitnessdb.web;

import com.example.fitnessdb.model.entity.*;
import com.example.fitnessdb.repo.*;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TestInit {
    private Long TEST_TRAINER_1_ID, TEST_TRAINER_2_ID;
    private Long TEST_WORKOUT_1_ID, TEST_WORKOUT_2_ID;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TrainerRepo trainerRepo;

    @Autowired
    private TimeCourseRepo timeCourseRepo;

    @Autowired
    private WorkoutRepo workoutRepo;

    @Autowired
    private PostRepo postRepo;


    @Autowired
    private MockMvc mockMvc;



    @BeforeEach
    public void setUpInit() {
        if (workoutRepo.count() != 0) {
            workoutRepo.deleteAll();
        }
        if (trainerRepo.count() != 0) {
            trainerRepo.deleteAll();
        }

        // users
        UserEntity userEntityTest = new UserEntity();
        userEntityTest.setUsername("userTest").setPassword("passwordTest");
        userEntityTest = userRepo.save(userEntityTest);


        // trainers
        TrainerEntity trainerEntity1 = new TrainerEntity();
        trainerEntity1
                .setName("Pesho")
                .setExperience("Long exp")
                .setHeadingTrainer("Best trainer ever")
                .setTrainerPictureUrl("pic1.jpg");
        trainerEntity1 = trainerRepo.save(trainerEntity1);
        TEST_TRAINER_1_ID = trainerEntity1.getId();

        TrainerEntity trainerEntity2 = new TrainerEntity();
        trainerEntity2
                .setName("Ivancho")
                .setExperience("Long exp2")
                .setHeadingTrainer("Best trainer2 ever")
                .setTrainerPictureUrl("pic2.jpg");

        trainerEntity2 = trainerRepo.save(trainerEntity2);
        TEST_TRAINER_2_ID = trainerEntity2.getId();


        //timeCourse
        TimeCourseEntity timeCourseEntity1 = new TimeCourseEntity();
        timeCourseEntity1
                .setTime("17:30:00")
                .setDayOfWeek("SATURDAY");
        timeCourseRepo.save(timeCourseEntity1);

        TimeCourseEntity timeCourseEntity2 = new TimeCourseEntity();
        timeCourseEntity2
                .setTime("11:15:00")
                .setDayOfWeek("MONDAY");

        timeCourseRepo.save(timeCourseEntity2);

        //workouts
        WorkoutEntity workoutEntity1 = new WorkoutEntity();
        workoutEntity1
                .setName("Boxing")
                .setTrainer(trainerEntity1)
                .setTimeCourse(timeCourseEntity1)
                .setDescription("Box and box")
                .setDuration(30)
                .setHeading("Best box ever")
                .setImageUrl("workout.jpg")
                .setPrice(BigDecimal.valueOf(60));

        workoutEntity1 = workoutRepo.save(workoutEntity1);
        TEST_WORKOUT_1_ID = workoutEntity1.getId();

        WorkoutEntity workoutEntity2 = new WorkoutEntity();
        workoutEntity2
                .setName("Running")
                .setTrainer(trainerEntity2)
                .setTimeCourse(timeCourseEntity2)
                .setDescription("Running is the best")
                .setDuration(60)
                .setHeading("run your guts")
                .setImageUrl("work2.jpg")
                .setPrice(BigDecimal.valueOf(55));
        workoutEntity2 = workoutRepo.save(workoutEntity2);
        TEST_WORKOUT_2_ID = workoutEntity2.getId();

        //posts
        PostEntity postEntity1 = new PostEntity();
        postEntity1.setDate(LocalDateTime.now())
                .setStars(5.5F)
                .setWorkout(workoutEntity1)
                .setMessage("Message of post")
                .setAuthor(userEntityTest);
    }
}
