package com.ccstudent.mscategoria.service.impl;

import com.ccstudent.mscategoria.dto.CategoriaDTO;
import com.ccstudent.mscategoria.entity.Categoria;
import com.ccstudent.mscategoria.repository.CategoriaRepository;
import com.ccstudent.mscategoria.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {
    private final CategoriaRepository repository;

    private CategoriaDTO toDTO(Categoria e) {
        return new CategoriaDTO(e.getId(), e.getNombre(), e.getDescripcion());
    }
    private Categoria toEntity(CategoriaDTO dto) {
        return new Categoria(dto.getId(), dto.getNombre(), dto.getDescripcion());
    }

    @Override
    public List<CategoriaDTO> getAll() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public CategoriaDTO getById(Long id) {
        Categoria e = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        return toDTO(e);
    }

    @Override
    public CategoriaDTO create(CategoriaDTO dto) {
        return toDTO(repository.save(toEntity(dto)));
    }

    @Override
    public CategoriaDTO update(Long id, CategoriaDTO dto) {
        Categoria existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        existing.setNombre(dto.getNombre());
        existing.setDescripcion(dto.getDescripcion());
        return toDTO(repository.save(existing));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
