package com.example.fitnessdb.repo;

import com.example.fitnessdb.model.entity.ExceptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExceptionRepo extends JpaRepository<ExceptionEntity, Long> {
}
