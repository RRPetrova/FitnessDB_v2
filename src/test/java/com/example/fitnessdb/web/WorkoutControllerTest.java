package com.example.fitnessdb.web;

import com.example.fitnessdb.model.entity.*;
import com.example.fitnessdb.repo.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class WorkoutControllerTest {

    private Long TEST_TRAINER_5_ID;
    private Long TEST_TRAINER_6_ID;
    private Long TEST_WORKOUT_1_ID;
    private Long TEST_WORKOUT_2_ID;
    private Long TEST_WORKOUT_5_ID;
    private Long TEST_WORKOUT_6_ID;


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
    public void setUp() {

   //    setUpInit();
        TEST_WORKOUT_1_ID = workoutRepo.findByName("Pilates").get().id;
        TEST_WORKOUT_2_ID = workoutRepo.findByName("Spinning").get().id;
    }

    @AfterEach
    public void tearDown() {
//        this.workoutRepo.deleteById(TEST_WORKOUT_5_ID);
//        this.workoutRepo.deleteById(TEST_WORKOUT_6_ID);
//        this.userRepo.deleteById(3L);
//        this.userRepo.deleteById(4L);
//        this.trainerRepo.deleteById(TEST_TRAINER_5_ID);
//        this.trainerRepo.deleteById(TEST_TRAINER_6_ID);
//        this.timeCourseRepo.deleteById(5L);
//        this.timeCourseRepo.deleteById(6L);

    }


    @Test
    @WithMockUser(value = "admin", roles = {"USER", "ADMIN"})
    public void testWorkoutReturnsCorrectStatusCode() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(
                        "/workouts/details/{id}", TEST_WORKOUT_1_ID
                )).
                andExpect(status().isOk()).
                andExpect(view().name("workout-details")).
                andExpect(model().attributeExists("workout"));

        mockMvc.perform(MockMvcRequestBuilders.get(
                        "/workouts/details/{id}", TEST_WORKOUT_2_ID
                )).
                andExpect(status().isOk()).
                andExpect(view().name("workout-details")).
                andExpect(model().attributeExists("workout"));
    }


    @Test
    @WithMockUser(value = "admin", roles = {"USER", "ADMIN"})
    public void testAddWorkoutWhenAuthorized() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/workouts/add"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("allFreeTrainers"));
    }


    @Test
    @WithMockUser(value = "user1", roles = {"USER"})
    public void testAddWorkoutWhenNotAuthorized() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/workouts/add"))
                .andExpect(status().is4xxClientError());
    }


    @Test
    @WithMockUser(value = "admin", roles = {"USER", "ADMIN"})
    public void addWorkoutTest() throws Exception {

        TrainerEntity trainerEntity1 = new TrainerEntity();
        trainerEntity1
                .setName("Pulev")
                .setExperience("Long exp")
                .setHeadingTrainer("Best trainer ever")
                .setTrainerPictureUrl("pic1.jpg");
        trainerEntity1 = trainerRepo.save(trainerEntity1);

        mockMvc.perform(post("/workouts/add")
                        .param("name", "Boxing")
                        .param("duration", String.valueOf(60))
                        .param("time", "18:50:00")
                        .param("heading", "Box the best test")
                        .param("trainerName", "Pulev")
                        .param("imageUrl", "testBoxPic.jpg")
                        .param("description", "Box Workouts to Get Your Blood Pumping")
                        .param("price", String.valueOf(BigDecimal.valueOf(555.55)))
                        .param("dayOfWeek", "SUNDAY")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection());


        Assertions.assertEquals(5, workoutRepo.count());
        Optional<WorkoutEntity> newlyCreatedWorkout = workoutRepo.findByName("Boxing");
        Assertions.assertTrue(newlyCreatedWorkout.isPresent());
        Assertions.assertEquals("Box Workouts to Get Your Blood Pumping", newlyCreatedWorkout.get().getDescription());
        Assertions.assertEquals("Pulev", newlyCreatedWorkout.get().getTrainer().name);
        Assertions.assertEquals("Box the best test", newlyCreatedWorkout.get().getHeading());
        Assertions.assertEquals(BigDecimal.valueOf(555.55), newlyCreatedWorkout.get().getPrice());
        Assertions.assertEquals("SUNDAY", newlyCreatedWorkout.get().getTimeCourse().getDayOfWeek());
        Assertions.assertEquals("18:50:00", newlyCreatedWorkout.get().getTimeCourse().getTime());
    }


    public void setUpInit() {
//        if (workoutRepo.count() != 0) {
//            workoutRepo.deleteAll();
//        }
//        if (trainerRepo.count() != 0) {
//            trainerRepo.deleteAll();
//        }

        // users
        UserEntity userEntityTest = new UserEntity();
        userEntityTest
                .setUsername("userTest")
                .setPassword("passwordTest")
                .setPhoneNumber("111-45-778")
                .setEmail("test@t2.com")
        //    .setUserRoles(List.of(new UserRole(UserRoleEnum.USER)))
        ;
        userEntityTest = userRepo.save(userEntityTest);

        UserEntity userAdminTest = new UserEntity();
        userAdminTest
                .setUsername("adminTest")
                .setPassword("passTest")
                .setPhoneNumber("222-45-678")
                .setEmail("test@test.com")
        //   .setUserRoles(List.of(new UserRole(UserRoleEnum.USER),
        //           new UserRole(UserRoleEnum.ADMIN)))
        ;
        userAdminTest = userRepo.save(userAdminTest);

        // trainers
        TrainerEntity trainerEntity1 = new TrainerEntity();
        trainerEntity1
                .setName("Pesho")
                .setExperience("Long exp")
                .setHeadingTrainer("Best trainer ever")
                .setTrainerPictureUrl("pic1.jpg");
        trainerEntity1 = trainerRepo.save(trainerEntity1);
        TEST_TRAINER_5_ID = trainerEntity1.getId();

        TrainerEntity trainerEntity2 = new TrainerEntity();
        trainerEntity2
                .setName("Ivancho")
                .setExperience("Long exp2")
                .setHeadingTrainer("Best trainer2 ever")
                .setTrainerPictureUrl("pic2.jpg");

        trainerEntity2 = trainerRepo.save(trainerEntity2);
        TEST_TRAINER_6_ID = trainerEntity2.getId();


        //timeCourse
        TimeCourseEntity timeCourseEntity1 = new TimeCourseEntity();
        timeCourseEntity1
                .setTime("17:30:00")
                .setDayOfWeek("SATURDAY");
        timeCourseEntity1 = timeCourseRepo.save(timeCourseEntity1);

        TimeCourseEntity timeCourseEntity2 = new TimeCourseEntity();
        timeCourseEntity2
                .setTime("11:15:00")
                .setDayOfWeek("MONDAY");

        timeCourseEntity2 = timeCourseRepo.save(timeCourseEntity2);

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
        TEST_WORKOUT_5_ID = workoutEntity1.getId();

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
        TEST_WORKOUT_6_ID = workoutEntity2.getId();

        //posts
        PostEntity postEntity1 = new PostEntity();
        postEntity1.setDate(LocalDateTime.now())
                .setStars(5.5F)
                .setWorkout(workoutEntity1)
                .setMessage("Message of post")
                .setAuthor(userEntityTest);
    }

}
