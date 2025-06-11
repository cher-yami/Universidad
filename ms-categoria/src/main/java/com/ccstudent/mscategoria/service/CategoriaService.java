package com.ccstudent.mscategoria.service;

import com.ccstudent.mscategoria.dto.CategoriaDTO;

import java.util.List;

public interface CategoriaService {
    List<CategoriaDTO> getAll();
    CategoriaDTO getById(Long id);
    CategoriaDTO create(CategoriaDTO dto);
    CategoriaDTO update(Long id, CategoriaDTO dto);
    void delete(Long id);
}