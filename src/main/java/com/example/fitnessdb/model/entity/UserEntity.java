package com.example.fitnessdb.model.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {
    public String username;
    public String firstName;
    public String phoneNumber;
    public String email;
    public String password;
    public List<WorkoutEntity> myWorkouts;
    private List<UserRole> userRoles;
    //public Set<PostEntity> reviews = new HashSet<PostEntity>(0);

    public UserEntity() {
    }

    @Column(name = "username", nullable = false, unique = true)
    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public UserEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    @Column(name = "phone_number", nullable = false, unique = true)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserEntity setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    @Column(name = "email", nullable = false)
    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    public List<WorkoutEntity> getMyWorkouts() {
        return myWorkouts;
    }

    public UserEntity setMyWorkouts(List<WorkoutEntity> myWorkouts) {
        this.myWorkouts = myWorkouts;
        return this;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    public List<UserRole> getUserRoles() {
        return userRoles;
    }

    public UserEntity setUserRoles(List<UserRole> userRoles) {
        this.userRoles = userRoles;
        return this;
    }


//    @OneToMany
//    public Set<PostEntity> getReviews() {
//        return reviews;
//    }
//
//    public UserEntity setReviews(Set<PostEntity> reviews) {
//        this.reviews = reviews;
//        return this;
//    }
}
