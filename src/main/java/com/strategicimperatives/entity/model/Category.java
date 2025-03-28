package com.strategicimperatives.entity.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "category")
public class Category {
    @Id
    @Column(name = "category_id")
    private Long id;

    private String name;
}
