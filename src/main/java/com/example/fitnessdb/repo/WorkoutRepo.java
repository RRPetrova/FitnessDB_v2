package com.example.fitnessdb.repo;

import com.example.fitnessdb.model.entity.WorkoutEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkoutRepo extends JpaRepository<WorkoutEntity, Long> {

    Optional<WorkoutEntity> findByName(String name);


}
