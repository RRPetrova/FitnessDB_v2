package com.example.fitnessdb.service;



import com.example.fitnessdb.model.dto.MostlyChosenWorkoutDto;
import com.example.fitnessdb.model.dto.UserCredentialsDto;
import com.example.fitnessdb.model.dto.WorkoutDetailsViewDto;
import com.example.fitnessdb.model.entity.UserEntity;

import javax.management.relation.RoleNotFoundException;
import java.util.List;

public interface UserService {
    void createAdmin() ;

    void registerUser(UserCredentialsDto userCredentialsDto);

    void createUser1();

    void addToMyWorkoutsList(String username, Long id);

    UserCredentialsDto findByUsername(String username);

    boolean usernameAlreadyExists(String username);

    MostlyChosenWorkoutDto mostlyChosen();

    void removeFromMyWorkoutsList(String name, Long id);


//
//    UserCredentialsDto findByUsernameAndPassword(String username, String password);
//
//
//    UserEntity findByUsername(String username);
//
//    List<UserEntity> findAllUsersOrderedByOrdersCountDesc();
}
