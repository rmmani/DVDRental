package com.strategicimperatives.controller;

import com.strategicimperatives.dto.FilmRecommendationDTO;
import com.strategicimperatives.service.FilmRecommendationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static java.lang.Integer.valueOf;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FilmRecommendationController.class)
class FilmRecommendationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    FilmRecommendationService filmRecommendationService;
    FilmRecommendationDTO filmRecommendationDTO;

    @BeforeEach
    public void setUp() {
        filmRecommendationDTO = new FilmRecommendationDTO(2L, "Die Hard", "Power packed action movie",
                "Action", "PF-13" , valueOf(180));
    }

    @Test
    void shouldGetSuccessfulRecommendations() throws Exception {
        when(filmRecommendationService.getRecommendations(2L)).thenReturn(asList(filmRecommendationDTO));
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/recommendations/2"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Action")));
    }

    @Test
    void getReturnExceptionRecommendationsGivenInvalidCustomerIdInput() throws Exception {
        when(filmRecommendationService.getRecommendations(2L)).thenReturn(asList(filmRecommendationDTO));
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/recommendations/dffefsd"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Valid customerId is required")));
    }

    @Test
    void getReturnExceptionRecommendationsGivenNullCustomerIdInput() throws Exception {
        when(filmRecommendationService.getRecommendations(2L)).thenReturn(asList(filmRecommendationDTO));
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/recommendations/"))
                .andExpect(status().isNotFound());
    }
}