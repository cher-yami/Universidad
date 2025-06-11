package com.ccstudent.msproveedor.service.impl;

import com.ccstudent.msproveedor.dto.ProveedorDTO;
import com.ccstudent.msproveedor.entity.Proveedor;
import com.ccstudent.msproveedor.repository.ProveedorRepository;
import com.ccstudent.msproveedor.service.ProveedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProveedorServiceImpl implements ProveedorService {
    private final ProveedorRepository repository;

    private ProveedorDTO toDTO(Proveedor e) {
        return new ProveedorDTO(e.getId(), e.getNombre());
    }
    private Proveedor toEntity(ProveedorDTO dto) {
        return new Proveedor(dto.getId(), dto.getNombre());
    }

    @Override
    public List<ProveedorDTO> getAll() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public ProveedorDTO getById(Long id) {
        Proveedor e = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
        return toDTO(e);
    }

    @Override
    public ProveedorDTO create(ProveedorDTO dto) {
        return toDTO(repository.save(toEntity(dto)));
    }

    @Override
    public ProveedorDTO update(Long id, ProveedorDTO dto) {
        Proveedor existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
        existing.setNombre(dto.getNombre());
        return toDTO(repository.save(existing));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}