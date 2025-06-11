package com.ccstudent.msanalisisventa.service;

import com.ccstudent.msanalisisventa.dto.VentaDTO;

import java.time.LocalDate;
import java.util.List;

public interface AnalisisVentaService {
    List<VentaDTO> byCliente(Long clienteId);
    List<VentaDTO> byProducto(Long productoId);
    List<VentaDTO> byCategoria(Long categoriaId);
    List<VentaDTO> byFechaRange(LocalDate start, LocalDate end);
}