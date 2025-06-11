package com.ccstudent.msventa.service;

import com.ccstudent.msventa.dto.VentaDTO;

import java.util.List;

public interface VentaService {
    List<VentaDTO> getAll();
    VentaDTO getById(Long id);
    VentaDTO create(VentaDTO dto);
    VentaDTO update(Long id, VentaDTO dto);
    void delete(Long id);
}