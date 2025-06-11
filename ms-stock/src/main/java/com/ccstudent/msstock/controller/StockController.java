package com.ccstudent.msstock.controller;

import com.ccstudent.msstock.dto.StockDTO;
import com.ccstudent.msstock.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stock")
@RequiredArgsConstructor
public class StockController {

    private final StockService service;

    @GetMapping("/{productoId}")
    public ResponseEntity<StockDTO> getStock(@PathVariable Long productoId) {
        return ResponseEntity.ok(service.getStock(productoId));
    }

    @PostMapping("/incrementar")
    public ResponseEntity<Void> incrementar(@Validated @RequestBody StockDTO dto) {
        service.incrementarStock(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/decrementar")
    public ResponseEntity<Void> decrementar(@Validated @RequestBody StockDTO dto) {
        service.decrementarStock(dto);
        return ResponseEntity.ok().build();
    }
}