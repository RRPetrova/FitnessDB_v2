package com.example.fitnessdb.web.listener;

import org.springframework.context.ApplicationEvent;

public class UserEvent extends ApplicationEvent {

    private String username;
    private String email;

    public UserEvent(String username, String email) {
        super(username);
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public UserEvent setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserEvent setEmail(String email) {
        this.email = email;
        return this;
    }
}
