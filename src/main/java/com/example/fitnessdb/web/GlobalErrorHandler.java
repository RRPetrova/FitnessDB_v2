package com.example.fitnessdb.web;

import com.example.fitnessdb.exceptions.ObjectAlreadyExistsException;
import com.example.fitnessdb.exceptions.ResourceNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalErrorHandler {



    @ExceptionHandler({ResourceNotFoundException.class})
    public ModelAndView handleResourceNotFoundException(ResourceNotFoundException exception) {
        ModelAndView modelAndView = new ModelAndView("error-404");
        modelAndView.addObject("message", exception.getMessage());

        return modelAndView;
    }

    @ExceptionHandler({ObjectAlreadyExistsException.class})
    public ModelAndView handleDBObjectAlreadyExistsException(ObjectAlreadyExistsException exception) {
        ModelAndView modelAndView = new ModelAndView("error-500");
        modelAndView.addObject("message", exception.getMessage());

        return modelAndView;
    }




}
