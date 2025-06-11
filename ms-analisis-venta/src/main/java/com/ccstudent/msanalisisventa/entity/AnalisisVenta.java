package com.ccstudent.msanalisisventa.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "analisis_ventas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnalisisVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipo;        // CLIENTE | PRODUCTO | CATEGORIA | FECHAS
    private Long criterioId;    // id del cliente, producto o categoría (fecha codificada)
    private Long cantidadVentas;
    private java.math.BigDecimal montoTotal;
}