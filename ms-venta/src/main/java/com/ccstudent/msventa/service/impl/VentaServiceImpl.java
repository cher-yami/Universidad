package com.ccstudent.msventa.service.impl;

import com.ccstudent.msventa.dto.*;
import com.ccstudent.msventa.entity.Venta;
import com.ccstudent.msventa.feign.ClienteFeignClient;
import com.ccstudent.msventa.feign.FormaPagoFeignClient;
import com.ccstudent.msventa.feign.ProductoFeignClient;
import com.ccstudent.msventa.feign.StockFeignClient;
import com.ccstudent.msventa.repository.VentaRepository;
import com.ccstudent.msventa.service.VentaService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VentaServiceImpl implements VentaService {

    private final VentaRepository repo;
    private final ClienteFeignClient cliClient;
    private final ProductoFeignClient prodClient;
    private final FormaPagoFeignClient fpClient;
    private final StockFeignClient stockClient;

    @CircuitBreaker(name = "clienteService", fallbackMethod = "fbCliente")
    private ClienteDTO fetchCliente(Long id) {
        return cliClient.getById(id);
    }
    private ClienteDTO fbCliente(Long id, Throwable ex) {
        return new ClienteDTO(id, null, null, null, null);
    }

    @CircuitBreaker(name = "productoService", fallbackMethod = "fbProducto")
    private ProductoDTO fetchProducto(Long id) {
        return prodClient.getById(id);
    }
    private ProductoDTO fbProducto(Long id, Throwable ex) {
        return new ProductoDTO(id, null, null, null);
    }

    @CircuitBreaker(name = "formaPagoService", fallbackMethod = "fbFormaPago")
    private FormaPagoDTO fetchFormaPago(Long id) {
        return fpClient.getById(id);
    }
    private FormaPagoDTO fbFormaPago(Long id, Throwable ex) {
        return new FormaPagoDTO(id, null);
    }

    @CircuitBreaker(name = "stockService", fallbackMethod = "fbStock")
    private void decrementarStock(StockDTO dto) {
        stockClient.decrementarStock(dto);
    }
    private void fbStock(StockDTO dto, Throwable ex) { }

    private VentaDTO toDTO(Venta v) {
        return new VentaDTO(
                v.getId(),
                fetchCliente(v.getClienteId()),
                fetchProducto(v.getProductoId()),
                fetchFormaPago(v.getFormaPagoId()),
                v.getCantidad(),
                v.getTotal(),
                v.getFecha()
        );
    }

    private Venta toEntity(VentaDTO dto) {
        Venta v = new Venta();
        v.setClienteId(dto.getCliente().getId());
        v.setProductoId(dto.getProducto().getId());
        v.setFormaPagoId(dto.getFormaPago().getId());
        v.setCantidad(dto.getCantidad());
        v.setTotal(dto.getTotal());
        v.setFecha(LocalDateTime.now());
        return v;
    }

    @Override
    public List<VentaDTO> getAll() {
        return repo.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public VentaDTO getById(Long id) {
        return toDTO(repo.findById(id).orElseThrow());
    }

    @Override
    public VentaDTO create(VentaDTO dto) {
        Venta saved = repo.save(toEntity(dto));
        decrementarStock(new StockDTO(saved.getProductoId(), saved.getCantidad()));
        return toDTO(saved);
    }

    @Override
    public VentaDTO update(Long id, VentaDTO dto) {
        Venta e = repo.findById(id).orElseThrow();
        e.setCantidad(dto.getCantidad());
        e.setTotal(dto.getTotal());
        return toDTO(repo.save(e));
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}