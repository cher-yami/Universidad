package com.ccstudent.mscliente.util;

import com.ccstudent.mscliente.dto.ClienteDTO;
import com.ccstudent.mscliente.entity.Cliente;

public class ClienteMapper {
    public static ClienteDTO toDTO(Cliente e) {
        return new ClienteDTO(e.getId(), e.getNombre(),
                e.getApellido(), e.getEmail(), e.getTelefono());
    }
    public static Cliente toEntity(ClienteDTO dto) {
        return new Cliente(dto.getId(), dto.getNombre(),
                dto.getApellido(), dto.getEmail(), dto.getTelefono());
    }
}