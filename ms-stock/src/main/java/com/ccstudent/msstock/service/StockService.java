package com.ccstudent.msstock.service;

import com.ccstudent.msstock.dto.StockDTO;

public interface StockService {
    StockDTO getStock(Long productoId);
    void incrementarStock(StockDTO dto);
    void decrementarStock(StockDTO dto);
}