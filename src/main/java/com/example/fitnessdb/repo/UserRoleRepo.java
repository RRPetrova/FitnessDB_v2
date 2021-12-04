package com.example.fitnessdb.repo;

import com.example.fitnessdb.model.entity.UserRole;
import com.example.fitnessdb.model.entity.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRoleRepo extends JpaRepository<UserRole, Long> {

    Optional<UserRole> findByRole(UserRoleEnum userRoleEnum);
}
