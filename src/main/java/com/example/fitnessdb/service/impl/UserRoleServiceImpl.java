package com.example.fitnessdb.service.impl;

import com.example.fitnessdb.model.entity.UserRole;
import com.example.fitnessdb.model.entity.UserRoleEnum;
import com.example.fitnessdb.repo.UserRoleRepo;
import com.example.fitnessdb.service.UserRoleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepo userRoleRepo;
    private final ModelMapper modelMapper;

    public UserRoleServiceImpl(UserRoleRepo userRoleRepo, ModelMapper modelMapper) {
        this.userRoleRepo = userRoleRepo;
        this.modelMapper = modelMapper;
    }

    @Transactional
    @Override
    public void seedRoles() {
        if (this.userRoleRepo.count() == 0) {
            Arrays.stream(UserRoleEnum.values())
                    .forEach(ur -> {
                        UserRole userRole = new UserRole();
                        userRole.setRole(ur);
                        this.userRoleRepo.save(userRole);
                    });
        }
    }
}
