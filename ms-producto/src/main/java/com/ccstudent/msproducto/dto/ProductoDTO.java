package com.ccstudent.msproducto.dto;

import lombok.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private CategoriaDTO categoria;
    private ProveedorDTO proveedor;
}