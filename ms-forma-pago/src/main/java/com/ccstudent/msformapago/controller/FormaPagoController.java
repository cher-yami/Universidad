package com.ccstudent.msformapago.controller;

import com.ccstudent.msformapago.dto.FormaPagoDTO;
import com.ccstudent.msformapago.service.FormaPagoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/formas-pago")
@RequiredArgsConstructor
public class FormaPagoController {

    private final FormaPagoService service;

    @GetMapping
    public ResponseEntity<List<FormaPagoDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormaPagoDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<FormaPagoDTO> create(@Validated @RequestBody FormaPagoDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FormaPagoDTO> update(@PathVariable Long id,
                                               @Validated @RequestBody FormaPagoDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}