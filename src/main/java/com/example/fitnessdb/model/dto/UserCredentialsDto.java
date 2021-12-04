package com.example.fitnessdb.model.dto;

public class UserCredentialsDto {
    public String username;
    public String firstName;
    public String lastName;
    public String phoneNumber;
    public String email;
    public String password;

    public UserCredentialsDto() {
    }

    public String getUsername() {
        return username;
    }

    public UserCredentialsDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserCredentialsDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserCredentialsDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserCredentialsDto setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserCredentialsDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserCredentialsDto setPassword(String password) {
        this.password = password;
        return this;
    }
}
