package com.ccstudent.mscliente.service.impl;

import com.ccstudent.mscliente.dto.ClienteDTO;
import com.ccstudent.mscliente.entity.Cliente;
import com.ccstudent.mscliente.repository.ClienteRepository;
import com.ccstudent.mscliente.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {
    private final ClienteRepository repository;

    private ClienteDTO toDTO(Cliente e) {
        return new ClienteDTO(e.getId(), e.getNombre(), e.getApellido(), e.getEmail(), e.getTelefono());
    }
    private Cliente toEntity(ClienteDTO dto) {
        return new Cliente(dto.getId(), dto.getNombre(), dto.getApellido(), dto.getEmail(), dto.getTelefono());
    }

    @Override
    public List<ClienteDTO> getAll() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public ClienteDTO getById(Long id) {
        Cliente e = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        return toDTO(e);
    }

    @Override
    public ClienteDTO create(ClienteDTO dto) {
        return toDTO(repository.save(toEntity(dto)));
    }

    @Override
    public ClienteDTO update(Long id, ClienteDTO dto) {
        Cliente existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        existing.setNombre(dto.getNombre());
        existing.setApellido(dto.getApellido());
        existing.setEmail(dto.getEmail());
        existing.setTelefono(dto.getTelefono());
        return toDTO(repository.save(existing));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}