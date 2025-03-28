package com.strategicimperatives.entity.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @Column(name = "customer_id")
    private Long id;
}
