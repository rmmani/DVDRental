package com.strategicimperatives.controller;

import com.strategicimperatives.dto.FilmRecommendationDTO;
import com.strategicimperatives.service.FilmRecommendationService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static java.lang.Long.valueOf;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/recommendations")
public class FilmRecommendationController {
    @Autowired
    private FilmRecommendationService recommendationService;

    //Get endpoint to handle customer recommendations.
    @GetMapping(value = "/{customerId}")
    public ResponseEntity<List<FilmRecommendationDTO>> getRecommendations(@PathVariable("customerId") @NotNull String customerId) {

        if (!StringUtils.isNumeric(customerId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Valid customerId is required");
        }
        List<FilmRecommendationDTO> recommendations = recommendationService.getRecommendations(valueOf(customerId));
        return ok(recommendations);
    }

    //Handle 400 bad request
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> handleEmptyCustomerId(ResponseStatusException responseStatusException) {
        return ResponseEntity.status(responseStatusException.getStatusCode()).body(responseStatusException.getMessage());
    }
}

