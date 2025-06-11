package com.ccstudent.msinventario.feign;

import com.ccstudent.msinventario.dto.StockDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "ms-stock", path = "/stock")
public interface StockFeignClient {
    @PostMapping("/incrementar")
    void incrementarStock(StockDTO dto);

    @PostMapping("/decrementar")
    void decrementarStock(StockDTO dto);
}