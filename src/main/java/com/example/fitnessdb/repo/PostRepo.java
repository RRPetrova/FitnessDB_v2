package com.example.fitnessdb.repo;

import com.example.fitnessdb.model.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<PostEntity, Long> {


    @Query("SELECT p FROM PostEntity p " +
            "WHERE p.workout.id = :id ")
    List<PostEntity> findAllByWorkoutId(@Param("id") Long id);

//    @Query(nativeQuery = true,
//            value = "select * from posts p " +
//                    "where p.workout_id = ?1 " +
//                    "order by p.creation_date desc " +
//                    "limit 5;")

    @Query("SELECT p FROM PostEntity p " +
            "WHERE p.workout.id = :id " +
            "order by p.date desc ")
    List<PostEntity> findLastPostsByWorkoutId(@Param("id") Long id);




}
