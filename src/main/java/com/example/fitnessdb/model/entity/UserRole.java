package com.example.fitnessdb.model.entity;


import javax.persistence.*;

@Entity
@Table(name = "roles")
public class UserRole extends BaseEntity{

    public UserRoleEnum role;


    public UserRole() {
    }

    public UserRole(UserRoleEnum role) {
        this.role = role;
    }

    @Enumerated(EnumType.STRING)
    public UserRoleEnum getRole() {
        return role;
    }

    public UserRole setRole(UserRoleEnum role) {
        this.role = role;
        return this;
    }
}
