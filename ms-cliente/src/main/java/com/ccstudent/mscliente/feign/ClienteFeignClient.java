package com.ccstudent.mscliente.feign;

import com.ccstudent.mscliente.dto.ClienteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "ms-cliente", path = "/clientes")
public interface ClienteFeignClient {

    @GetMapping
    List<ClienteDTO> getAll();

    @GetMapping("/{id}")
    ClienteDTO getById(@PathVariable("id") Long id);

    @PostMapping
    ClienteDTO create(@RequestBody ClienteDTO dto);

    @PutMapping("/{id}")
    ClienteDTO update(@PathVariable("id") Long id, @RequestBody ClienteDTO dto);

    @DeleteMapping("/{id}")
    void delete(@PathVariable("id") Long id);
}