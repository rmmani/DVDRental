package com.strategicimperatives.entity.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "film")
public class Film {
    @Id
    @Column(name = "film_id")
    private Long id;

    private String title;
    private String description;
    private String rating;
    private Integer length;

    @OneToMany(mappedBy = "film")
    private List<FilmCategory> filmCategories;
}
