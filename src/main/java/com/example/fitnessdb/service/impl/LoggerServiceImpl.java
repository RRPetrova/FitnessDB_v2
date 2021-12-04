package com.example.fitnessdb.service.impl;

import com.example.fitnessdb.service.LoggerService;
import com.example.fitnessdb.web.listener.UserEvent;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.*;

@Service
public class LoggerServiceImpl implements LoggerService {


    @Override
    public void log(UserEvent event) {
        Logger logger = Logger.getLogger("MyRegistrationLog");
        FileHandler fileHandler;

        try {
            // This block configures the logger with handler and formatter
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HHmm");
            fileHandler = new FileHandler("src/main/resources/output/event/event_"
                    +dateFormat.format(Calendar.getInstance().getTime()) + ".log");
            logger.addHandler(fileHandler);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);

            // the following statement is used to log any messages
            logger.info("My first log");
            logger.log(Level.INFO, String.format("Event happened: %s registered with email %s", event.getUsername(), event.getEmail()));


        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }


    }

}
