package com.example.fitnessdb.init;

import com.example.fitnessdb.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component

public class DBInit implements CommandLineRunner {

    private final UserService userService;
    private final UserRoleService userRoleService;
    private final TrainerService trainerService;
    private final WorkoutService workoutService;
    private final PostService postService;
    private final TimeCourseService timeCourseService;


    public DBInit(UserService userService, UserRoleService userRoleService,
                  TrainerService trainerService, WorkoutService workoutService,
                  PostService postService, TimeCourseService timeCourseService) {
        this.userService = userService;
        this.userRoleService = userRoleService;
        this.trainerService = trainerService;
        this.workoutService = workoutService;
        this.postService = postService;
        this.timeCourseService = timeCourseService;
    }

    @Override
    public void run(String... args) throws Exception {

        this.userRoleService.seedRoles();
        this.timeCourseService.seedSchedule();
        this.trainerService.seedTrainers();
        this.workoutService.seedWorkouts();

        this.userService.createAdmin();
        this.userService.createUser1();
        this.postService.createPost();
    }
}
