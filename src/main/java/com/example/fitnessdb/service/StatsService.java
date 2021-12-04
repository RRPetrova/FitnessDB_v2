package com.example.fitnessdb.service;

import com.example.fitnessdb.model.dto.StatsView;
import org.springframework.data.domain.Page;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface StatsService {

    void onRequest(HttpServletRequest request);


    List<StatsView> statInfo();

//    Page<StatsView> getAllStats(Integer pageNumber, Integer pageSize, String sortBy);



}
