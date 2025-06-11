package com.ccstudent.msventa.feign;

import com.ccstudent.msventa.dto.StockDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "ms-stock", path = "/stock")
public interface StockFeignClient {
    @PostMapping("/decrementar")
    void decrementarStock(StockDTO dto);
}