package com.ccstudent.msinventario.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "inventario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "producto_id", nullable = false)
    private Long productoId;

    @Column(nullable = false)
    private Integer cantidad;

    /** "ENTRADA" o "SALIDA" **/
    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false)
    private LocalDateTime fecha;
}