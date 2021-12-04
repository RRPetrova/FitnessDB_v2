package com.example.fitnessdb.web;

import com.example.fitnessdb.model.binding.AddNewPostBindingModel;
import com.example.fitnessdb.model.dto.*;
import com.example.fitnessdb.model.binding.AddNewWorkoutBindingModel;
import com.example.fitnessdb.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/workouts")
public class WorkoutController {

    private final ModelMapper modelMapper;
    private final WorkoutService workoutService;
    private final UserService userService;
    private final TrainerService trainerService;
    private final PostService postService;
    private final TimeCourseService timeCourseService;

    public WorkoutController(ModelMapper modelMapper, WorkoutService workoutService,
                             UserService userService, TrainerService trainerService,
                             PostService postService, TimeCourseService timeCourseService) {
        this.modelMapper = modelMapper;
        this.workoutService = workoutService;
        this.userService = userService;
        this.trainerService = trainerService;
        this.postService = postService;

        this.timeCourseService = timeCourseService;
    }


    @GetMapping("/all")
    public String allWorkouts(Model model) {
        List<WorkoutsWithDetailsDto> allWorkouts = this.workoutService.getAllWorkouts()
                .stream()
                .map(currWorkout -> this.modelMapper.map(currWorkout, WorkoutsWithDetailsDto.class))
                .collect(Collectors.toList());
        model.addAttribute("workouts", allWorkouts);

        return "/home";
    }


    @GetMapping("/myWorkouts")
    public String myWorkouts(Principal principal, Model model) {
        String name = principal.getName();
        List<WorkoutDetailsViewDto> userWorkouts = this.workoutService.getUserWorkouts(principal.getName());
        model.addAttribute("workouts", this.workoutService.getUserWorkouts(principal.getName()));
        model.addAttribute("pageTitle", "My Workouts");
        return "my-workouts";
    }



    @PostMapping("/addToWorkoutsList/{id}")
    public String addToMyWorkoutsList(@PathVariable Long id, Principal principal) {

        this.userService.addToMyWorkoutsList(principal.getName(), id);


        return "redirect:/workouts/myWorkouts";
    }


    @PostMapping("/details/{id}")
    public String save(@PathVariable Long id,
                       AddNewPostBindingModel addNewPostBindingModel,
                       Model model,
                       Principal principal) {

        model.addAttribute("posts", addNewPostBindingModel);
        PostDetailsDto postDetailsDto = this.modelMapper.map(addNewPostBindingModel, PostDetailsDto.class);
        UserCredentialsDto userCredentialsDto = this.userService.findByUsername(principal.getName());
        postDetailsDto.setUserCredentialsDto(userCredentialsDto);
        postDetailsDto.setWorkoutsWithDetailsDto(this.workoutService.findById(id));
        this.postService.savePost(postDetailsDto);

        return "redirect:/workouts/details/" + id;
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        WorkoutsWithDetailsDto workoutsWithDetailsDto = this.workoutService.findById(id);
        model.addAttribute("workout", workoutsWithDetailsDto);
//        PostDetailsDto postDetailsDto = new PostDetailsDto();
//        postDetailsDto.setWorkoutsWithDetailsDto(workoutsWithDetailsDto);

        // model.addAttribute("posts", postDetailsDto);
        model.addAttribute("posts", new AddNewPostBindingModel());
        model.addAttribute("allPostsForCurrWorkout", this.postService.lastFivePostsForCurrWorkout(id));
        return "workout-details";
    }

    @GetMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String addNewWorkoutView(Model model) {
        if (!model.containsAttribute("addNewWorkoutBindingModel")) {
            model.addAttribute("addNewWorkoutBindingModel", new AddNewWorkoutBindingModel());
            model.addAttribute("allFreeTrainers", this.trainerService.getAllFreeTrainersNames());
            model.addAttribute("pageTitle", "Add workout");
        }
        return "add-workout";
    }

    @PostMapping("/add")
    public String addNewWorkout(@Valid AddNewWorkoutBindingModel addNewWorkoutBindingModel,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addNewWorkoutBindingModel", addNewWorkoutBindingModel);
            redirectAttributes.addFlashAttribute("allFreeTrainers", this.trainerService.getAllFreeTrainersNames());
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addNewWorkoutBindingModel", bindingResult);
            return "redirect:/workouts/add";
        }

        if (this.workoutService.workoutNameAlreadyExists(addNewWorkoutBindingModel.getName())) {
            redirectAttributes.addFlashAttribute("addNewWorkoutBindingModel", addNewWorkoutBindingModel);
            redirectAttributes.addFlashAttribute("workoutWithThisNameExists", true);
            return "redirect:/workouts/add";

        }


        //TimeCourseDto timeCourseDto = ;
        this.timeCourseService.addNewSchedule(this.modelMapper.map(addNewWorkoutBindingModel, TimeCourseDto.class));
        this.workoutService.addWorkout(addNewWorkoutBindingModel);
        return "redirect:/home";

    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteWorkout(@PathVariable Long id) {
        this.workoutService.deleteWorkoutById(id);
        return "redirect:/";
    }

    @GetMapping("/removeWorkout/{id}")
    public String removeFromMyWorkouts(@PathVariable Long id, Principal principal) {
        this.userService.removeFromMyWorkoutsList(principal.getName(), id);
        return "redirect:/workouts/myWorkouts";
    }

}
