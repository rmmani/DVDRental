package com.strategicimperatives.repository;

import com.strategicimperatives.entity.model.Film;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {
    @Query("SELECT f, c.name as category, " +
            "(SELECT COUNT(r) FROM Rental r JOIN r.inventory i WHERE i.film = f AND r.customer.id = :customerId) as customerRentCount, " +
            "(SELECT COUNT(r) FROM Rental r JOIN r.inventory i WHERE i.film = f) as totalRentCount " +
            "FROM Film f " +
            "JOIN f.filmCategories fc " +
            "JOIN fc.category c " +
            "WHERE f.id NOT IN (SELECT DISTINCT i.film.id FROM Rental r JOIN r.inventory i WHERE r.customer.id = :customerId) " +
            "ORDER BY customerRentCount DESC, totalRentCount DESC, f.title ASC")
    List<Object[]> findRecommendedFilms(@Param("customerId") Long customerId, Pageable pageable);
}
