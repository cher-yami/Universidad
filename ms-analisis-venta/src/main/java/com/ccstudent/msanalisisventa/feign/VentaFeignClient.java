package com.ccstudent.msanalisisventa.feign;

import com.ccstudent.msanalisisventa.dto.VentaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "ms-venta", path = "/ventas")
public interface VentaFeignClient {
    @GetMapping
    List<VentaDTO> getAll();
}