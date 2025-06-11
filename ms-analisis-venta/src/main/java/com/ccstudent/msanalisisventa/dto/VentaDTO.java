package com.ccstudent.msanalisisventa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VentaDTO {
    private Long id;
    private Long clienteId;
    private Long productoId;
    private Integer cantidad;
    private BigDecimal total;
    private LocalDateTime fecha;
}