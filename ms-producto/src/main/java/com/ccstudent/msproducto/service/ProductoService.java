package com.ccstudent.msproducto.service;

import com.ccstudent.msproducto.dto.ProductoDTO;

import java.util.List;

public interface ProductoService {
    List<ProductoDTO> getAll();
    ProductoDTO getById(Long id);
    ProductoDTO create(ProductoDTO dto);
    ProductoDTO update(Long id, ProductoDTO dto);
    void delete(Long id);
}