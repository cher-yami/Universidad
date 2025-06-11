package com.ccstudent.msproducto.feign;

import com.ccstudent.msproducto.dto.ProveedorDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-proveedor", path = "/proveedores")
public interface ProveedorFeignClient {
    @GetMapping("/{id}")
    ProveedorDTO getById(@PathVariable("id") Long id);
}