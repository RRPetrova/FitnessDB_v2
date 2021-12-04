package com.example.fitnessdb.web;

import com.example.fitnessdb.service.UserService;
import com.example.fitnessdb.service.WorkoutService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {

    private final WorkoutService workoutService;
    private final UserService userService;

    public HomeController(WorkoutService workoutService, UserService userService) {
        this.workoutService = workoutService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String index(Principal principal, Model model) {
        if (principal != null) {
            return "redirect:/home";
        }
        model.addAttribute("pageTitle", "Welcome to Fitness DB");
        return "index";
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("allWorkouts", this.workoutService.getAllWorkouts());
        model.addAttribute("mostlyChosen", this.userService.mostlyChosen());
        model.addAttribute("pageTitle", "Welcome Home!");
        return "home";
    }
}
