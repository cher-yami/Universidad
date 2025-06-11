package com.ccstudent.msanalisisventa.service.impl;

import com.ccstudent.msanalisisventa.dto.ClienteDTO;
import com.ccstudent.msanalisisventa.dto.ProductoDTO;
import com.ccstudent.msanalisisventa.dto.VentaDTO;
import com.ccstudent.msanalisisventa.feign.ClienteFeignClient;
import com.ccstudent.msanalisisventa.feign.ProductoFeignClient;
import com.ccstudent.msanalisisventa.feign.VentaFeignClient;
import com.ccstudent.msanalisisventa.service.AnalisisVentaService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnalisisVentaServiceImpl implements AnalisisVentaService {

    private final VentaFeignClient ventaClient;
    private final ClienteFeignClient clienteClient;
    private final ProductoFeignClient productoClient;

    @CircuitBreaker(name = "ventaService", fallbackMethod = "fallbackVentas")
    private List<VentaDTO> fetchVentas() {
        return ventaClient.getAll();
    }
    private List<VentaDTO> fallbackVentas(Throwable ex) {
        return List.of();
    }

    @CircuitBreaker(name = "clienteService", fallbackMethod = "fallbackCliente")
    private ClienteDTO fetchCliente(Long id) {
        return clienteClient.getById(id);
    }
    private ClienteDTO fallbackCliente(Long id, Throwable ex) {
        return new ClienteDTO(id, "desconocido", null, null, null);
    }

    @CircuitBreaker(name = "productoService", fallbackMethod = "fallbackProducto")
    private ProductoDTO fetchProducto(Long id) {
        return productoClient.getById(id);
    }
    private ProductoDTO fallbackProducto(Long id, Throwable ex) {
        return new ProductoDTO(id, "desconocido", null, null);
    }

    @Override
    public List<VentaDTO> byCliente(Long clienteId) {
        return fetchVentas().stream()
                .filter(v -> v.getClienteId().equals(clienteId))
                .peek(v -> v.setCliente(fetchCliente(v.getClienteId())))
                .collect(Collectors.toList());
    }

    @Override
    public List<VentaDTO> byProducto(Long productoId) {
        return fetchVentas().stream()
                .filter(v -> v.getProductoId().equals(productoId))
                .peek(v -> v.setProducto(fetchProducto(v.getProductoId())))
                .collect(Collectors.toList());
    }

    @Override
    public List<VentaDTO> byCategoria(Long categoriaId) {
        return fetchVentas().stream()
                .filter(v -> {
                    var prod = fetchProducto(v.getProductoId());
                    return prod != null && prod.getId().equals(categoriaId);
                })
                .peek(v -> {
                    v.setProducto(fetchProducto(v.getProductoId()));
                    v.setCliente(fetchCliente(v.getClienteId()));
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<VentaDTO> byFechaRange(LocalDate start, LocalDate end) {
        return fetchVentas().stream()
                .filter(v -> {
                    var f = v.getFecha().toLocalDate();
                    return !f.isBefore(start) && !f.isAfter(end);
                })
                .peek(v -> {
                    v.setProducto(fetchProducto(v.getProductoId()));
                    v.setCliente(fetchCliente(v.getClienteId()));
                })
                .collect(Collectors.toList());
    }
}