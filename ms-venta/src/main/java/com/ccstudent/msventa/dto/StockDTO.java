package com.ccstudent.msventa.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockDTO {
    private Long productoId;
    private Integer cantidad;
}