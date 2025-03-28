package com.strategicimperatives.repository;

import com.strategicimperatives.entity.model.Film;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;
import org.springframework.data.repository.query.FluentQuery;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FilmRepositoryTest {

    @Mock
    private EntityManager entityManager;

    @Mock
    private Query query;

    private FilmRepository filmRepository;

    @BeforeEach
    void setUp() {
        filmRepository = new FilmRepositoryImpl(entityManager);
    }

    @Test
    void findRecommendedFilms_shouldReturnCorrectResult() {
        Long customerId = 1L;
        Pageable pageable = PageRequest.of(0, 10);

        Object[] film1 = {new Film(), "Action", "PG", 180};
        Object[] film2 = {new Film(), "Comedy", "12A", 160};
        Object[] film3 = {new Film(), "Drama", "U", 145};
        List<Object[]> expectedResult = Arrays.asList(film1, film2, film3);

        when(entityManager.createQuery(any(String.class))).thenReturn(query);
        when(query.setParameter("customerId", customerId)).thenReturn(query);
        when(query.setFirstResult(0)).thenReturn(query);
        when(query.setMaxResults(10)).thenReturn(query);
        when(query.getResultList()).thenReturn(expectedResult);

        List<Object[]> actualResult = filmRepository.findRecommendedFilms(customerId, pageable);

        assertEquals(expectedResult, actualResult);
    }

    // A simple implementation of FilmRepository for testing purposes
    private static class FilmRepositoryImpl implements FilmRepository {

        private final EntityManager entityManager;

        public FilmRepositoryImpl(EntityManager entityManager) {
            this.entityManager = entityManager;
        }

        @Override
        public List<Object[]> findRecommendedFilms(Long customerId, Pageable pageable) {
            Query query = entityManager.createQuery("SELECT f, c.name as category, " +
                    "(SELECT COUNT(r) FROM Rental r JOIN r.inventory i WHERE i.film = f AND r.customer.id = :customerId) as customerRentCount, " +
                    "(SELECT COUNT(r) FROM Rental r JOIN r.inventory i WHERE i.film = f) as totalRentCount " +
                    "FROM Film f " +
                    "JOIN f.filmCategories fc " +
                    "JOIN fc.category c " +
                    "WHERE f.id NOT IN (SELECT DISTINCT i.film.id FROM Rental r JOIN r.inventory i WHERE r.customer.id = :customerId) " +
                    "ORDER BY customerRentCount DESC, totalRentCount DESC, f.title ASC");
            query.setParameter("customerId", customerId);
            query.setFirstResult((int) pageable.getOffset());
            query.setMaxResults(pageable.getPageSize());
            return query.getResultList();
        }


        @Override
        public void flush() {

        }

        @Override
        public <S extends Film> S saveAndFlush(S entity) {
            return null;
        }

        @Override
        public <S extends Film> List<S> saveAllAndFlush(Iterable<S> entities) {
            return List.of();
        }

        @Override
        public void deleteAllInBatch(Iterable<Film> entities) {

        }

        @Override
        public void deleteAllByIdInBatch(Iterable<Long> longs) {

        }

        @Override
        public void deleteAllInBatch() {

        }

        @Override
        public Film getOne(Long aLong) {
            return null;
        }

        @Override
        public Film getById(Long aLong) {
            return null;
        }

        @Override
        public Film getReferenceById(Long aLong) {
            return null;
        }

        @Override
        public <S extends Film> Optional<S> findOne(Example<S> example) {
            return Optional.empty();
        }

        @Override
        public <S extends Film> List<S> findAll(Example<S> example) {
            return List.of();
        }

        @Override
        public <S extends Film> List<S> findAll(Example<S> example, Sort sort) {
            return List.of();
        }

        @Override
        public <S extends Film> Page<S> findAll(Example<S> example, Pageable pageable) {
            return null;
        }

        @Override
        public <S extends Film> long count(Example<S> example) {
            return 0;
        }

        @Override
        public <S extends Film> boolean exists(Example<S> example) {
            return false;
        }

        @Override
        public <S extends Film, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
            return null;
        }

        @Override
        public <S extends Film> S save(S entity) {
            return null;
        }

        @Override
        public <S extends Film> List<S> saveAll(Iterable<S> entities) {
            return List.of();
        }

        @Override
        public Optional<Film> findById(Long aLong) {
            return Optional.empty();
        }

        @Override
        public boolean existsById(Long aLong) {
            return false;
        }

        @Override
        public List<Film> findAll() {
            return List.of();
        }

        @Override
        public List<Film> findAllById(Iterable<Long> longs) {
            return List.of();
        }

        @Override
        public long count() {
            return 0;
        }

        @Override
        public void deleteById(Long aLong) {

        }

        @Override
        public void delete(Film entity) {

        }

        @Override
        public void deleteAllById(Iterable<? extends Long> longs) {

        }

        @Override
        public void deleteAll(Iterable<? extends Film> entities) {

        }

        @Override
        public void deleteAll() {

        }

        @Override
        public List<Film> findAll(Sort sort) {
            return List.of();
        }

        @Override
        public Page<Film> findAll(Pageable pageable) {
            return null;
        }
    }
}