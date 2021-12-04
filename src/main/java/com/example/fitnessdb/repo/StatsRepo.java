package com.example.fitnessdb.repo;

import com.example.fitnessdb.model.entity.StatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatsRepo extends JpaRepository<StatEntity,Long> {
}
