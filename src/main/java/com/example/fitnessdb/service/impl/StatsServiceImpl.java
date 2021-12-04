package com.example.fitnessdb.service.impl;

import com.example.fitnessdb.model.dto.StatsView;
import com.example.fitnessdb.model.entity.StatEntity;
import com.example.fitnessdb.repo.StatsRepo;
import com.example.fitnessdb.repo.UserRepo;
import com.example.fitnessdb.service.StatsService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.stream.Collectors;

@Service
public class StatsServiceImpl implements StatsService {

    private final UserRepo userRepo;
    private final StatsRepo statsRepo;
    private final ModelMapper modelMapper;


    public StatsServiceImpl(UserRepo userRepo, StatsRepo statsRepo,
                            ModelMapper modelMapper) {

        this.userRepo = userRepo;
        this.statsRepo = statsRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public void onRequest(HttpServletRequest request) {
        final String ip = HttpRequestResponseUtils.getClientIpAddress();
        final String url = HttpRequestResponseUtils.getRequestUrl();
        final String page = HttpRequestResponseUtils.getRequestUri();
        final String refererPage = HttpRequestResponseUtils.getRefererPage();
        final String queryString = HttpRequestResponseUtils.getPageQueryString();
        final String userAgent = HttpRequestResponseUtils.getUserAgent();
        final String requestMethod = HttpRequestResponseUtils.getRequestMethod();
        final LocalDateTime timestamp = LocalDateTime.now();

        StatEntity statEntity = new StatEntity();
        statEntity.setUser(HttpRequestResponseUtils.getLoggedInUser());
        statEntity.setIp(ip);
        statEntity.setRequestMethod(requestMethod);
        statEntity.setUrl(url);
        statEntity.setPage(page);
        statEntity.setRefererPage(refererPage);
        statEntity.setUserAgent(userAgent);
        statEntity.setTimestamp(timestamp);
        if (HttpRequestResponseUtils.getLoggedInUser() != null) {
            this.statsRepo.save(statEntity);
        }
    }

    @Override
    public List<StatsView> statInfo() {

        return this.statsRepo
                .findAll()
                .stream()
                .map(currLog ->
                        this.modelMapper
                                .map(currLog, StatsView.class))
                .collect(Collectors.toList());

    }
//    @Override
//    public Page<StatsView> getAllStats(Integer pageNumber, Integer pageSize, String sortBy) {
//
//        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
//        return this.statsRepo
//                .findAll(pageable)
//                .map(s-> this.modelMapper.map(s, StatsView.class));
//    }
}