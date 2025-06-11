package com.ccstudent.msinventario.service;

import com.ccstudent.msinventario.dto.InventarioDTO;

import java.util.List;

public interface InventarioService {
    List<InventarioDTO> getAll();
    InventarioDTO getById(Long id);
    InventarioDTO create(InventarioDTO dto);
    InventarioDTO update(Long id, InventarioDTO dto);
    void delete(Long id);
}