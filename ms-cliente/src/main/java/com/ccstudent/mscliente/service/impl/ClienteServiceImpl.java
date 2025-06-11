package com.ccstudent.mscliente.service.impl;

import com.ccstudent.mscliente.dto.ClienteDTO;
import com.ccstudent.mscliente.entity.Cliente;
import com.ccstudent.mscliente.repository.ClienteRepository;
import com.ccstudent.mscliente.service.ClienteService;
import com.ccstudent.mscliente.util.ClienteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository repository;

    @Override
    public List<ClienteDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(ClienteMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ClienteDTO getById(Long id) {
        Cliente e = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        return ClienteMapper.toDTO(e);
    }

    @Override
    public ClienteDTO create(ClienteDTO dto) {
        Cliente saved = repository.save(ClienteMapper.toEntity(dto));
        return ClienteMapper.toDTO(saved);
    }

    @Override
    public ClienteDTO update(Long id, ClienteDTO dto) {
        Cliente existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        existing.setNombre(dto.getNombre());
        existing.setApellido(dto.getApellido());
        existing.setEmail(dto.getEmail());
        existing.setTelefono(dto.getTelefono());
        Cliente updated = repository.save(existing);
        return ClienteMapper.toDTO(updated);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}