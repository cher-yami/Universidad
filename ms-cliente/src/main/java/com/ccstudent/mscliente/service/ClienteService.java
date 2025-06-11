package com.ccstudent.mscliente.service;

import com.ccstudent.mscliente.dto.ClienteDTO;

import java.util.List;

public interface ClienteService {
    List<ClienteDTO> getAll();
    ClienteDTO getById(Long id);
    ClienteDTO create(ClienteDTO dto);
    ClienteDTO update(Long id, ClienteDTO dto);
    void delete(Long id);
}