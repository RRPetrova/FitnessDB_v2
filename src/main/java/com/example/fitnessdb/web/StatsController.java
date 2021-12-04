package com.example.fitnessdb.web;

import com.example.fitnessdb.model.dto.StatsView;
import com.example.fitnessdb.service.StatsService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;

@Controller
public class StatsController {

    private final StatsService statsService;

    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping("/statistics")
    public String stats(Model model) {
        model.addAttribute("loggerList", this.statsService.statInfo());

        return "stats";
    }


}
