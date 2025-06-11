package com.ccstudent.msformapago.service.impl;

import com.ccstudent.msformapago.dto.FormaPagoDTO;
import com.ccstudent.msformapago.entity.FormaPago;
import com.ccstudent.msformapago.repository.FormaPagoRepository;
import com.ccstudent.msformapago.service.FormaPagoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FormaPagoServiceImpl implements FormaPagoService {

    private final FormaPagoRepository repository;

    private FormaPagoDTO toDTO(FormaPago e) {
        return new FormaPagoDTO(e.getId(), e.getNombre());
    }

    private FormaPago toEntity(FormaPagoDTO dto) {
        return new FormaPago(dto.getId(), dto.getNombre());
    }

    @Override
    public List<FormaPagoDTO> getAll() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FormaPagoDTO getById(Long id) {
        FormaPago e = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Forma de pago no encontrada"));
        return toDTO(e);
    }

    @Override
    public FormaPagoDTO create(FormaPagoDTO dto) {
        return toDTO(repository.save(toEntity(dto)));
    }

    @Override
    public FormaPagoDTO update(Long id, FormaPagoDTO dto) {
        FormaPago existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Forma de pago no encontrada"));
        existing.setNombre(dto.getNombre());
        return toDTO(repository.save(existing));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}