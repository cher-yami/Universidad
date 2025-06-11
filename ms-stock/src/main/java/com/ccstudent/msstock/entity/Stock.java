package com.ccstudent.msstock.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "stock")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "producto_id", unique = true, nullable = false)
    private Long productoId;

    @Column(nullable = false)
    private Integer cantidad;
}