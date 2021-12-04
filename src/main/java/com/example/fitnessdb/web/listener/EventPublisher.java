package com.example.fitnessdb.web.listener;


import com.example.fitnessdb.model.dto.UserCredentialsDto;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class EventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public EventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }



    public void publishEvent(UserCredentialsDto userCredentialsDto) {
        UserEvent userEvent = new UserEvent(userCredentialsDto.getUsername(), userCredentialsDto.getEmail());

        this.applicationEventPublisher.publishEvent(userEvent);
    }


}