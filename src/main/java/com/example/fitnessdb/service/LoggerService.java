package com.example.fitnessdb.service;

import com.example.fitnessdb.web.listener.UserEvent;

import java.io.IOException;

public interface LoggerService {

    void log(UserEvent event);


}