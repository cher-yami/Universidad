package com.ccstudent.msinventario.service.impl;

import com.ccstudent.msinventario.dto.InventarioDTO;
import com.ccstudent.msinventario.dto.ProductoDTO;
import com.ccstudent.msinventario.dto.StockDTO;
import com.ccstudent.msinventario.entity.Inventario;
import com.ccstudent.msinventario.feign.ProductoFeignClient;
import com.ccstudent.msinventario.feign.StockFeignClient;
import com.ccstudent.msinventario.repository.InventarioRepository;
import com.ccstudent.msinventario.service.InventarioService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventarioServiceImpl implements InventarioService {

    private final InventarioRepository repository;
    private final ProductoFeignClient productoClient;
    private final StockFeignClient stockClient;

    @CircuitBreaker(name = "stockService", fallbackMethod = "fallbackStock")
    private void callIncrementar(StockDTO dto) {
        stockClient.incrementarStock(dto);
    }
    private void callDecrementar(StockDTO dto) {
        stockClient.decrementarStock(dto);
    }
    private void fallbackStock(StockDTO dto, Throwable ex) {
        // log o manejar error
    }

    private InventarioDTO toDTO(Inventario e) {
        ProductoDTO prod = productoClient.getById(e.getProductoId());
        return new InventarioDTO(
                e.getId(), prod,
                e.getCantidad(), e.getTipo(), e.getFecha()
        );
    }

    private Inventario toEntity(InventarioDTO dto) {
        Inventario e = new Inventario();
        e.setProductoId(dto.getProducto().getId());
        e.setCantidad(dto.getCantidad());
        e.setTipo(dto.getTipo());
        e.setFecha(LocalDateTime.now());
        return e;
    }

    @Override
    public List<InventarioDTO> getAll() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public InventarioDTO getById(Long id) {
        Inventario e = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro de inventario no encontrado"));
        return toDTO(e);
    }

    @Override
    public InventarioDTO create(InventarioDTO dto) {
        Inventario saved = repository.save(toEntity(dto));
        StockDTO stockDto = new StockDTO(saved.getProductoId(), saved.getCantidad());
        if ("ENTRADA".equalsIgnoreCase(dto.getTipo())) {
            callIncrementar(stockDto);
        } else {
            callDecrementar(stockDto);
        }
        return toDTO(saved);
    }

    @Override
    public InventarioDTO update(Long id, InventarioDTO dto) {
        Inventario existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro de inventario no encontrado"));
        existing.setCantidad(dto.getCantidad());
        existing.setTipo(dto.getTipo());
        return toDTO(repository.save(existing));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}