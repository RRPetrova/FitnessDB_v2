package com.example.fitnessdb.repo;

import com.example.fitnessdb.model.entity.TimeCourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TimeCourseRepo extends JpaRepository<TimeCourseEntity, Long> {


    Optional<TimeCourseEntity> findByDayOfWeekAndTime(String dayOfWeek, String time);
}
