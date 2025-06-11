package com.ccstudent.msinventario.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventarioDTO {
    private Long id;
    private ProductoDTO producto;
    private Integer cantidad;
    private String tipo;
    private LocalDateTime fecha;
}