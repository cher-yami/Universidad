package com.ccstudent.msanalisisventa.controller;

import com.ccstudent.msanalisisventa.dto.VentaDTO;
import com.ccstudent.msanalisisventa.service.AnalisisVentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/analisis")
@RequiredArgsConstructor
public class AnalisisVentaController {

    private final AnalisisVentaService service;

    @GetMapping("/cliente/{id}")
    public ResponseEntity<List<VentaDTO>> byCliente(@PathVariable Long id) {
        return ResponseEntity.ok(service.byCliente(id));
    }

    @GetMapping("/producto/{id}")
    public ResponseEntity<List<VentaDTO>> byProducto(@PathVariable Long id) {
        return ResponseEntity.ok(service.byProducto(id));
    }

    @GetMapping("/categoria/{id}")
    public ResponseEntity<List<VentaDTO>> byCategoria(@PathVariable Long id) {
        return ResponseEntity.ok(service.byCategoria(id));
    }

    @GetMapping("/fechas")
    public ResponseEntity<List<VentaDTO>> byFechaRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end
    ) {
        return ResponseEntity.ok(service.byFechaRange(start, end));
    }
}