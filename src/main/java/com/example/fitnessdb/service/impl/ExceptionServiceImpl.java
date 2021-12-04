package com.example.fitnessdb.service.impl;

import com.example.fitnessdb.model.entity.ExceptionEntity;
import com.example.fitnessdb.repo.ExceptionRepo;
import com.example.fitnessdb.service.ExceptionService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class ExceptionServiceImpl implements ExceptionService {

    private final ExceptionRepo exceptionRepo;

    public ExceptionServiceImpl(ExceptionRepo exceptionRepo) {
        this.exceptionRepo = exceptionRepo;
    }


    @Override
    public void onRequest(HttpServletRequest request) {

        ExceptionEntity exceptionEntity = new ExceptionEntity();

        exceptionEntity.setUser(HttpRequestResponseUtils.getLoggedInUser());
    }
}
