package com.example.fitnessdb.repo;

import com.example.fitnessdb.model.entity.TrainerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainerRepo extends JpaRepository<TrainerEntity, Long> {



    Optional<TrainerEntity> findByName(String name);

    @Query("SELECT t.name FROM TrainerEntity t " +
            "LEFT JOIN WorkoutEntity  w " +
            "ON t.id = w.trainer.id " +
            "WHERE w.trainer.id IS NULL " )
    List<String> findFreeTrainers();
}
