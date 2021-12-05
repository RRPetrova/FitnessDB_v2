package com.example.fitnessdb.web;

import com.example.fitnessdb.model.dto.UserCredentialsDto;
import com.example.fitnessdb.model.binding.UserLoginBindingModel;
import com.example.fitnessdb.model.binding.UserRegisterBindingModel;
import com.example.fitnessdb.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final ModelMapper modelMapper;
    private final UserService userService;

    public UserController(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginView(Model model) {
        if (!model.containsAttribute("userLoginBindingModel")) {
            model.addAttribute("userLoginBindingModel", new UserLoginBindingModel());
            model.addAttribute("notFound", false);
            model.addAttribute("wrongCredentials", false);
            model.addAttribute("pageTitle", "Login");
        }

        return "login";
    }

    @PostMapping("/login-error")
    public ModelAndView loginError(@ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                                           String username, RedirectAttributes redirectAttributes) {
//TODO  58:42 ot lekciq hateoas without model and view
        ModelAndView modelAndView = new ModelAndView();

        redirectAttributes.addFlashAttribute("wrongCredentials", true);
        redirectAttributes.addFlashAttribute("username", username);


        modelAndView.setViewName("redirect:/users/login");
        return modelAndView;
    }


    @GetMapping("/register")
    public String registerView(Model model) {
        if (!model.containsAttribute("userRegisterBindingModel")) {
            model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
            model.addAttribute("userAlreadyExists", false);
            model.addAttribute("pageTitle", "Register");
        }

        return "register";
    }

    @PostMapping("/register")
    public String registerNewUser(@Valid UserRegisterBindingModel userRegisterBindingModel,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !userRegisterBindingModel.getPassword()
                .equals(userRegisterBindingModel.getConfirmPassword())) {

            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel",
                    bindingResult);


            return "redirect:/users/register";
        }

        if (this.userService.usernameAlreadyExists(userRegisterBindingModel.getUsername())) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("userAlreadyExists", true);
            return "redirect:/users/register";
        }


        UserCredentialsDto userCredentialsDto = this.modelMapper.map(userRegisterBindingModel, UserCredentialsDto.class);
        this.userService.registerUser(userCredentialsDto);
        return "redirect:/users/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/";
    }


}
