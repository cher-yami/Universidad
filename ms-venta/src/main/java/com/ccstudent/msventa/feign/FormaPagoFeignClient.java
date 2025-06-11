package com.ccstudent.msventa.feign;

import com.ccstudent.msventa.dto.FormaPagoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-forma-pago", path = "/formas-pago")
public interface FormaPagoFeignClient {
    @GetMapping("/{id}")
    FormaPagoDTO getById(@PathVariable Long id);
}
