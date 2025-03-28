package com.strategicimperatives.service;

import com.strategicimperatives.dto.FilmRecommendationDTO;
import com.strategicimperatives.entity.model.Film;
import com.strategicimperatives.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.data.domain.PageRequest.of;

@Service
public class FilmRecommendationService {
    @Autowired
    private FilmRepository filmRepository;

    public List<FilmRecommendationDTO> getRecommendations(Long customerId) {
        List<Object[]> results = filmRepository.findRecommendedFilms(customerId, of(0, 10));

        return results.stream().map(result -> {
            Film film = (Film) result[0];
            String category = (String) result[1];
            return new FilmRecommendationDTO(
                    film.getId(),
                    film.getTitle(),
                    film.getDescription(),
                    category,
                    film.getRating(),
                    film.getLength()
            );
        }).collect(toList());
    }
}
