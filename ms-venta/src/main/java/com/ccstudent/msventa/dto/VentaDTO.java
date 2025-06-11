package com.ccstudent.msventa.dto;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VentaDTO {
    private Long id;
    private ClienteDTO cliente;
    private ProductoDTO producto;
    private FormaPagoDTO formaPago;
    private Integer cantidad;
    private BigDecimal total;
    private LocalDateTime fecha;
}