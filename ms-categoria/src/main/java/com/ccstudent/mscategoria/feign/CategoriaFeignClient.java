package com.ccstudent.mscategoria.feign;

import com.ccstudent.mscategoria.dto.CategoriaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "ms-categoria", path = "/categorias")
public interface CategoriaFeignClient {

    @GetMapping
    List<CategoriaDTO> getAll();

    @GetMapping("/{id}")
    CategoriaDTO getById(@PathVariable("id") Long id);

    @PostMapping
    CategoriaDTO create(@RequestBody CategoriaDTO dto);

    @PutMapping("/{id}")
    CategoriaDTO update(@PathVariable("id") Long id, @RequestBody CategoriaDTO dto);

    @DeleteMapping("/{id}")
    void delete(@PathVariable("id") Long id);
}