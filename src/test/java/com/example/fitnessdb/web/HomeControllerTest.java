package com.example.fitnessdb.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(value = "admin", roles = {"USER", "ADMIN"})
    public void viewHomePageWhenAuthenticated() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/home"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("allWorkouts", "mostlyChosen"));
    }


    @Test
    public void redirectToIndexFromHomePageWhenNotAuthenticated() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/home"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void viewIndexWhenNotAuthenticated() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/"))
                .andExpect(status().isOk());
    }


}
