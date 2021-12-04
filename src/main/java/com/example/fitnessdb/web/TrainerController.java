package com.example.fitnessdb.web;


import com.example.fitnessdb.model.binding.AddNewTrainerBindingModel;
import com.example.fitnessdb.service.TrainerService;
import com.example.fitnessdb.service.WorkoutService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/trainers")
public class TrainerController {

    private final TrainerService trainerService;
    private final WorkoutService workoutService;

    public TrainerController(TrainerService trainerService, WorkoutService workoutService) {
        this.trainerService = trainerService;
        this.workoutService = workoutService;
    }


    @GetMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String addTrainerView(Model model) {
        if(!model.containsAttribute("addNewTrainerBindingModel")) {
            model.addAttribute("addNewTrainerBindingModel", new AddNewTrainerBindingModel());
            model.addAttribute("allWorkouts", this.workoutService.getAllWorkoutsNames());
            model.addAttribute("pageTitle", "Add trainer");
        }
        return "add-trainer";
    }


    @PostMapping("/add")
    public String addNewTrainer(@Valid AddNewTrainerBindingModel addNewTrainerBindingModel,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addNewTrainerBindingModel", addNewTrainerBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addNewTrainerBindingModel", bindingResult);
            return "redirect:/trainers/add";
        }

        if (this.trainerService.trainerAlreadyExists(addNewTrainerBindingModel.getName())) {
            redirectAttributes.addFlashAttribute("addNewTrainerBindingModel", addNewTrainerBindingModel);
            redirectAttributes.addFlashAttribute("trainerWithThisNameExists", true);
            return "redirect:/trainers/add";
        }

        this.trainerService.addTrainer(addNewTrainerBindingModel);
        return "redirect:/workouts/add";
    }
}
