package com.example.fitnessdb.web;

import com.example.fitnessdb.model.binding.UserRegisterBindingModel;
import com.example.fitnessdb.model.entity.UserEntity;
import com.example.fitnessdb.model.entity.UserRole;
import com.example.fitnessdb.model.entity.UserRoleEnum;
import com.example.fitnessdb.repo.UserRepo;
import com.example.fitnessdb.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;
import java.util.Set;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.util.AssertionErrors.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTests {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

    @Test
    public void registerPageStatusTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/register")).andExpect(status().isOk());
    }

    @Test
    public void loginPageStatusTest() throws Exception {
        mockMvc.perform(get("/users/login")).andExpect(status().isOk());
    }

    @Test
    public void registerNewUserTest() throws Exception {

        mockMvc.perform(post("/users/register")
                        .param("firstName", "TestName")
                        .param("username", "testtttt")
                        .param("email", "test@test.bg")
                        .param("password", "testP")
                        .param("confirmPassword", "testP")
                        .param("phoneNumber", "1234-45-67-88")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection());

        Assertions.assertEquals(3, userRepo.count());
        Optional<UserEntity> newlyCreatedUser = userRepo.findByUsername("testtttt");
        Assertions.assertTrue(newlyCreatedUser.isPresent());
        Assertions.assertEquals("TestName", newlyCreatedUser.get().getFirstName());
        Assertions.assertEquals("test@test.bg", newlyCreatedUser.get().getEmail());
        Assertions.assertEquals("1234-45-67-88", newlyCreatedUser.get().getPhoneNumber());

    }


    @Test
    public void signupWithWrongPasswordType() throws Exception {
        UserEntity user = new UserEntity();
        UserRegisterBindingModel userRegisterBindingModelTest = new UserRegisterBindingModel();
        userRegisterBindingModelTest
                .setFirstName("Testche")
                .setPhoneNumber("1232-45-67-38")
                .setUsername("test")
                .setPassword("test123")
                .setConfirmPassword("test123")
                .setEmail("test@test.bg");


  this.mockMvc.perform(post("/users/register")
                        .param("firstName", "TestName")
                        .param("username", "test")
                        .param("email", "test@test.bg")
                        .param("password", "testP")
                        .param("confirmPassword", "testP")
                        .param("phoneNumber", "1234-45-67-88")
                        .with(csrf()))
                .andExpect(view().name("redirect:/users/register"))
                .andExpect(model().attributeHasErrors())
                .andExpect(status().is3xxRedirection());
              //  .andReturn();
    }

    @Test
    public void registerUserWithUsernameAlreadyExisting() throws Exception {

        mockMvc.perform(post("/users/register")
                        .param("firstName", "TestName")
                        .param("username", "user1")
                        .param("email", "test@test.bg")
                        .param("password", "testP")
                        .param("confirmPassword", "testP")
                        .param("phoneNumber", "1234-45-67-88")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection());

        Assertions.assertTrue( userService.usernameAlreadyExists("user1"));
    }

}