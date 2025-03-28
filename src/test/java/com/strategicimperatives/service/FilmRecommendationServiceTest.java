package com.strategicimperatives.service;

import com.strategicimperatives.dto.FilmRecommendationDTO;
import com.strategicimperatives.entity.model.Film;
import com.strategicimperatives.repository.FilmRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.data.domain.Pageable.ofSize;

@ExtendWith(MockitoExtension.class)
class FilmRecommendationServiceTest {

    @Mock
    private FilmRepository filmRepository;
    private List<Object[]> filmRecommendationDTOList = new ArrayList<>(15);
    @InjectMocks
    private FilmRecommendationService filmRecommendationService;

    @BeforeEach
    public void setUp() {
        Object[] objects = new Object[10];

        IntStream.rangeClosed(0, 9)
                .forEach(x-> {

                    Film film = new Film();
                    film.setId(891L);
                    film.setTitle("Timberland Sky");
                    film.setDescription("A Boring Display of a Man And a Dog who must Redeem a Girl in A U-Boat");
                    film.setRating("G");
                    film.setLength(69);

                    String category = "Classics";
                    Integer customerRentCount = 5;
                    Integer totalRentCount = 23;

                    Object[] resultArray = new Object[]{
                            film,
                            category,
                            customerRentCount,
                            totalRentCount
                    };
                    filmRecommendationDTOList.add(resultArray);
                });
    }

    @Test
    public void shouldReturnFirstTenItemsGivenCustomerId() {
        when(filmRepository.findRecommendedFilms(1L, ofSize(10))).thenReturn(filmRecommendationDTOList);
        List<FilmRecommendationDTO> filmRecommendationDTOS = filmRecommendationService.getRecommendations(1L);
        assertEquals(10, filmRecommendationDTOS.size());
    }

    @Test
    public void shouldThrowExceptionGivenIncorrectCustomerIdFormat() {
        when(filmRepository.findRecommendedFilms(1L, ofSize(10))).thenReturn(filmRecommendationDTOList);
        List<FilmRecommendationDTO> filmRecommendationDTOS = filmRecommendationService.getRecommendations(1L);
        assertEquals(10, filmRecommendationDTOS.size());
    }
}