package com.strategicimperatives.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FilmRecommendationDTO {
    private Long id;
    private String title;
    private String description;
    private String category;
    private String rating;
    private Integer length;
}
