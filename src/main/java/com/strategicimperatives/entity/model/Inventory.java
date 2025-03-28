package com.strategicimperatives.entity.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Column;
import lombok.Data;

@Data
@Entity
@Table(name = "inventory")
public class Inventory {
    @Id
    @Column(name = "inventory_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;
}
