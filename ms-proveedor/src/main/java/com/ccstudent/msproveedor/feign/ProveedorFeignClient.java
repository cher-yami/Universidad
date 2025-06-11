package com.ccstudent.msproveedor.feign;

import com.ccstudent.msproveedor.dto.ProveedorDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "ms-proveedor", path = "/proveedores")
public interface ProveedorFeignClient {

    @GetMapping
    List<ProveedorDTO> getAll();

    @GetMapping("/{id}")
    ProveedorDTO getById(@PathVariable("id") Long id);

    @PostMapping
    ProveedorDTO create(@RequestBody ProveedorDTO dto);

    @PutMapping("/{id}")
    ProveedorDTO update(@PathVariable("id") Long id, @RequestBody ProveedorDTO dto);

    @DeleteMapping("/{id}")
    void delete(@PathVariable("id") Long id);
}