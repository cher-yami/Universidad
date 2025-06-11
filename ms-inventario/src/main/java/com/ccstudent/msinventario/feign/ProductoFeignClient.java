package com.ccstudent.msinventario.feign;

import com.ccstudent.msinventario.dto.ProductoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-producto", path = "/productos")
public interface ProductoFeignClient {
    @GetMapping("/{id}")
    ProductoDTO getById(@PathVariable("id") Long id);
}