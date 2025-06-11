package com.ccstudent.msformapago.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "formas_pago")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormaPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
}