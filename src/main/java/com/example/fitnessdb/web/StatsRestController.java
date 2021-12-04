package com.example.fitnessdb.web;

import com.example.fitnessdb.model.dto.StatsView;
import com.example.fitnessdb.service.StatsService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/statistics")
public class StatsRestController {

    private final StatsService statsService;
    private final ModelMapper modelMapper;

    public StatsRestController(StatsService statsService, ModelMapper modelMapper) {
        this.statsService = statsService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/search")
    public ResponseEntity<List<StatsView>> findAll() {
        List<StatsView> statsViews = this.statsService.statInfo();

        return ResponseEntity
                .ok()
                .body(statsViews);
    }


}

