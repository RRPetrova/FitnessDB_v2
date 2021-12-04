package com.example.fitnessdb.repo;

import com.example.fitnessdb.model.dto.BestWorkout;
import com.example.fitnessdb.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

//    @Query(nativeQuery = true,
//            value = "select  " +
//                    "(my_workouts_id) as id, (count(*) / CAST( SUM(count(*)) over () as float)*100) as percentage " +
//                    "from users_my_workouts " +
//                    "group by my_workouts_id " +
//                    "order by percentage desc " +
//                    "limit 1;")

@Query("SELECT u.myWorkouts FROM UserEntity u ")
    List<UserEntity> mostChosenWorkout();




}
