package com.example.fitnessdb.service.impl;

import com.example.fitnessdb.exceptions.ObjectAlreadyExistsException;
import com.example.fitnessdb.exceptions.ResourceNotFoundException;
import com.example.fitnessdb.model.dto.MostlyChosenWorkoutDto;
import com.example.fitnessdb.model.dto.UserCredentialsDto;
import com.example.fitnessdb.model.entity.UserEntity;
import com.example.fitnessdb.model.entity.UserRole;
import com.example.fitnessdb.model.entity.UserRoleEnum;
import com.example.fitnessdb.model.entity.WorkoutEntity;
import com.example.fitnessdb.repo.UserRepo;
import com.example.fitnessdb.repo.UserRoleRepo;
import com.example.fitnessdb.repo.WorkoutRepo;
import com.example.fitnessdb.service.UserService;

import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final UserRoleRepo userRoleRepo;
    private final WorkoutRepo workoutRepo;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final FitnessDBUserService fitnessDBUserService;
   // private final EventPublisher eventPublisher;

    public UserServiceImpl(UserRepo userRepo, UserRoleRepo userRoleRepo,
                           WorkoutRepo workoutRepo, ModelMapper modelMapper,
                           PasswordEncoder passwordEncoder, FitnessDBUserService fitnessDBUserService){
                    //       EventPublisher eventPublisher) {
        this.userRepo = userRepo;
        this.userRoleRepo = userRoleRepo;
        this.workoutRepo = workoutRepo;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.fitnessDBUserService = fitnessDBUserService;
       // this.eventPublisher = eventPublisher;
    }

    @Override
    @Transactional
    public void createAdmin() {

        if (this.userRepo.count() == 0) {

            List<UserRole> listOfRoles = new ArrayList<>();


            UserRole userRole = this.userRoleRepo.findByRole(UserRoleEnum.USER)
                    .orElseThrow(() -> new ResourceNotFoundException("User role not found."));
            listOfRoles.add(userRole);
            UserRole adminRole = this.userRoleRepo.findByRole(UserRoleEnum.ADMIN)
                    .orElseThrow(() -> new ResourceNotFoundException("Admin role not found."));
            listOfRoles.add(adminRole);

            UserEntity adminUser = new UserEntity();
            adminUser
                    .setEmail("admin@gmail.com")
                    .setFirstName("Admin")
                    .setPassword(passwordEncoder.encode("topsecret"))
                    .setUsername("admin")
                    .setPhoneNumber("3598-99-99-99")
                    .setUserRoles(listOfRoles);

            this.userRepo.save(adminUser);
        }
    }


    @Override
    @Transactional
    public void registerUser(UserCredentialsDto userCredentialsDto) {
        UserEntity userEntity = this.modelMapper.map(userCredentialsDto, UserEntity.class);
        userEntity.setPassword(passwordEncoder.encode(userCredentialsDto.getPassword()));
        UserRole userRole = this.userRoleRepo
                .findByRole(UserRoleEnum.USER)
                .orElseThrow(() -> new ResourceNotFoundException("User role not found."));
        if (userRole != null) {
            userEntity.setUserRoles(List.of(userRole))
                    .setMyWorkouts(new ArrayList<>());
        }

        this.userRepo.save(userEntity);
      //  this.eventPublisher.publishEvent(userCredentialsDto);
        UserDetails principal = this.fitnessDBUserService.loadUserByUsername(userEntity.getUsername());

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principal,
                userEntity.getPassword(),
                principal.getAuthorities()
        );

        SecurityContextHolder
                .getContext()
                .setAuthentication(authentication);
    }

    @Override
    @Transactional
    public void createUser1() {
        List<UserRole> setOfRoles = new ArrayList<>();

        UserRole userRole = this.userRoleRepo.findByRole(UserRoleEnum.USER)
                .orElseThrow(() -> new ResourceNotFoundException("User role not found."));
        setOfRoles.add(userRole);

        UserEntity user = new UserEntity();
        user
                .setEmail("user@gmail.com")
                .setFirstName("User")
                .setPassword(passwordEncoder.encode("topsecret"))
                .setUsername("user1")
                .setPhoneNumber("3597-99-78-79")
                .setUserRoles(setOfRoles)
                .setMyWorkouts(List.of(this.workoutRepo.findById(4L)
                        .orElseThrow(() -> new ResourceNotFoundException("Workout with id 4 doesnt exists."))));
        this.userRepo.save(user);
    }

    @Transactional
    @Override
    public void addToMyWorkoutsList(String username, Long id) {
        UserEntity userEntity = this.userRepo.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User with username " + username + " not found."));

        WorkoutEntity workoutEntity = this.workoutRepo
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Workout with id " + id + " not found"));

        List<WorkoutEntity> myWorkouts = userEntity.getMyWorkouts();
        System.out.println(myWorkouts);
        System.out.println(myWorkouts.contains(workoutEntity));
        if (myWorkouts.contains(workoutEntity)) {

            throw new ObjectAlreadyExistsException("Workout already added.");
        }
        myWorkouts.add(workoutEntity);
        userEntity.setMyWorkouts(myWorkouts);
        this.userRepo.saveAndFlush(userEntity);
    }

    @Transactional
    @Override
    public void removeFromMyWorkoutsList(String username, Long id) {
        UserEntity userEntity = this.userRepo.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User with username " + username + " not found."));
        WorkoutEntity workoutEntity = this.workoutRepo
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Workout with id " + id + " not found"));
        List<WorkoutEntity> myWorkouts = userEntity.getMyWorkouts();
        myWorkouts.remove(workoutEntity);
        userEntity.setMyWorkouts(myWorkouts);
        this.userRepo.saveAndFlush(userEntity);
    }


    @Override
    public UserCredentialsDto findByUsername(String username) {
        UserEntity userEntity = this.userRepo
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " not found."));
        return this.modelMapper.map(userEntity, UserCredentialsDto.class);

    }

    @Override
    public boolean usernameAlreadyExists(String username) {
        return this.userRepo
                .findByUsername(username)
                .isPresent();
    }

    @Transactional
    @Scheduled(cron = "0 0 * * 0")
    public MostlyChosenWorkoutDto mostlyChosen() {

        List<UserEntity> userEntities = this.userRepo.findAll();

        List<String> allWorkoutNames = new ArrayList<>();
        List<WorkoutEntity> myWorkouts = new ArrayList<>();

        if (!userEntities.isEmpty()) {
            for (UserEntity userEntity : userEntities) {
                if (!userEntity.getMyWorkouts().isEmpty()) {
                    userEntity.getMyWorkouts()
                            .stream()
                            .map(WorkoutEntity::getName)
                            .forEach(allWorkoutNames::add);
                }
            }
        }

        if (!allWorkoutNames.isEmpty()) {
            Map.Entry<String, Integer> workoutMostlyChosen = mostCommon(allWorkoutNames);

            MostlyChosenWorkoutDto mostlyChosenWorkoutDto = new MostlyChosenWorkoutDto();
            mostlyChosenWorkoutDto.setPercentage((float) (100 / allWorkoutNames.size() * workoutMostlyChosen.getValue()));
//            WorkoutEntity byId = this.workoutRepo.findById(bestWorkout.getId())
//                    .orElseThrow(() -> new ResourceNotFoundException("Workout with this " + bestWorkout.getId() + " id not found."));
            WorkoutEntity byName = workoutRepo.findByName(workoutMostlyChosen.getKey())
                    .orElseThrow(() -> new ResourceNotFoundException("Workout with name " + workoutMostlyChosen.getKey() + " not found."));

            mostlyChosenWorkoutDto.setName(workoutMostlyChosen.getKey());

            return mostlyChosenWorkoutDto;
        }
        return null;
    }

    public static Map.Entry<String, Integer>  mostCommon(List<String> allNames) {
        Map<String, Integer> map = new HashMap<>();

        for (String currName : allNames) {
            Integer count = map.get(currName);
            map.put(currName, count == null ? 1 : count + 1);
        }

        Map.Entry<String, Integer> mostOccurances = null;

        for (Map.Entry<String, Integer> e : map.entrySet()) {
            if (mostOccurances == null || e.getValue() > mostOccurances.getValue())
                mostOccurances = e;
        }

        return mostOccurances;
    }
}
