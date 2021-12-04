package com.example.fitnessdb.model.binding;

import com.example.fitnessdb.model.validator.PasswordsMatch;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@PasswordsMatch(firstF = "password", secondF = "confirmPassword")
public class UserRegisterBindingModel {
    public String username;
    public String firstName;
    public String phoneNumber;
    public String email;
    public String password;
    public String confirmPassword;

    public UserRegisterBindingModel() {
    }
//TODO field match validation from workshop 1:52 02.2021 lecture 6
    @Size(min = 5, max = 20, message = "Username must be between 5 and 20 symbols.")
    @NotBlank(message = "Field cannot be blank.")
    public String getUsername() {
        return username;
    }

    public UserRegisterBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }


    public String getFirstName() {
        return firstName;
    }

    public UserRegisterBindingModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    @NotBlank(message = "Field cannot be blank.")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserRegisterBindingModel setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    @Email(message = "Enter valid email address.")
    @NotNull(message = "Please enter email address.")
    @NotBlank(message = "Field cannot be blank.")
    public String getEmail() {
        return email;
    }

    public UserRegisterBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }

    @NotBlank(message = "Field cannot be blank.")
    @Size(min = 3, message = "Password must be at lest 3 characters.")
    public String getPassword() {
        return password;
    }

    public UserRegisterBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }

    @NotBlank(message = "Field cannot be blank.")
    @Size(min = 3,  message = "Password must be at lest 3 characters.")
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegisterBindingModel setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}
