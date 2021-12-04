package com.example.fitnessdb.service.impl;

import com.example.fitnessdb.model.entity.UserEntity;
import com.example.fitnessdb.repo.UserRepo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FitnessDBUserService implements UserDetailsService {

    private final UserRepo userRepo;

    public FitnessDBUserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Transactional
    @Override
    public  UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //map User entity to user representaition in security USer details with the given username.
        UserEntity userEntity = this.userRepo
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with name " + username +" was not found!"));

        //GA is representation of user role. SGA is an impl of GA that spring provides.
        List<GrantedAuthority> authorityList = userEntity
                .getUserRoles()
                .stream()
                .map(ur -> new SimpleGrantedAuthority("ROLE_" + ur.getRole().name()))
                .collect(Collectors.toList());

        return new User(userEntity.getUsername(),
                userEntity.getPassword(),
                authorityList);
    }
}
