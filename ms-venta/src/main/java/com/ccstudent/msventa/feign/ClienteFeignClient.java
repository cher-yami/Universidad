package com.ccstudent.msventa.feign;

import com.ccstudent.msventa.dto.ClienteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-cliente", path = "/clientes")
public interface ClienteFeignClient {
    @GetMapping("/{id}")
    ClienteDTO getById(@PathVariable Long id);
}