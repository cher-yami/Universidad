package com.ccstudent.msformapago.service;

import com.ccstudent.msformapago.dto.FormaPagoDTO;

import java.util.List;

public interface FormaPagoService {
    List<FormaPagoDTO> getAll();
    FormaPagoDTO getById(Long id);
    FormaPagoDTO create(FormaPagoDTO dto);
    FormaPagoDTO update(Long id, FormaPagoDTO dto);
    void delete(Long id);
}