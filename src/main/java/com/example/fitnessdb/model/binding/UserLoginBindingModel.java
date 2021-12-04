package com.example.fitnessdb.model.binding;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserLoginBindingModel {
    public String username;
    public String password;

    public UserLoginBindingModel() {
    }

    @Size(min = 5, max = 20, message = "Username must be between 5 and 20 symbols.")
    @NotBlank
    public String getUsername() {
        return username;
    }

    public UserLoginBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    @NotBlank
    @Size(min = 3, message = "Password must be at lest 3 characters.")
    public String getPassword() {
        return password;
    }

    public UserLoginBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }
}
