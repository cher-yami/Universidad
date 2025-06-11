package com.ccstudent.msproducto.feign;

import com.ccstudent.msproducto.dto.CategoriaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-categoria", path = "/categorias")
public interface CategoriaFeignClient {
    @GetMapping("/{id}")
    CategoriaDTO getById(@PathVariable("id") Long id);
}