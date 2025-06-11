package com.ccstudent.msstock.service.impl;

import com.ccstudent.msstock.dto.StockDTO;
import com.ccstudent.msstock.entity.Stock;
import com.ccstudent.msstock.repository.StockRepository;
import com.ccstudent.msstock.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final StockRepository repository;

    @Override
    public StockDTO getStock(Long productoId) {
        Stock entity = repository.findByProductoId(productoId)
                .orElse(new Stock(null, productoId, 0));
        return new StockDTO(entity.getProductoId(), entity.getCantidad());
    }

    @Override
    @Transactional
    public void incrementarStock(StockDTO dto) {
        Stock stock = repository.findByProductoId(dto.getProductoId())
                .orElse(new Stock(null, dto.getProductoId(), 0));
        stock.setCantidad(stock.getCantidad() + dto.getCantidad());
        repository.save(stock);
    }

    @Override
    @Transactional
    public void decrementarStock(StockDTO dto) {
        Stock stock = repository.findByProductoId(dto.getProductoId())
                .orElseThrow(() -> new RuntimeException("Stock no encontrado para producto " + dto.getProductoId()));
        int nueva = stock.getCantidad() - dto.getCantidad();
        if (nueva < 0) {
            throw new RuntimeException("Stock insuficiente para producto " + dto.getProductoId());
        }
        stock.setCantidad(nueva);
        repository.save(stock);
    }
}