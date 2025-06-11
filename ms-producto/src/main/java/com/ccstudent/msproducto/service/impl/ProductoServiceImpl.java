package com.ccstudent.msproducto.service.impl;

import com.ccstudent.msproducto.dto.CategoriaDTO;
import com.ccstudent.msproducto.dto.ProductoDTO;
import com.ccstudent.msproducto.dto.ProveedorDTO;
import com.ccstudent.msproducto.entity.Producto;
import com.ccstudent.msproducto.feign.CategoriaFeignClient;
import com.ccstudent.msproducto.feign.ProveedorFeignClient;
import com.ccstudent.msproducto.repository.ProductoRepository;
import com.ccstudent.msproducto.service.ProductoService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository repository;
    private final CategoriaFeignClient categoriaClient;
    private final ProveedorFeignClient proveedorClient;

    @CircuitBreaker(name = "categoriaService", fallbackMethod = "fallbackCategoria")
    public CategoriaDTO fetchCategoria(Long id) {
        return categoriaClient.getById(id);
    }

    public CategoriaDTO fallbackCategoria(Long id, Throwable ex) {
        return new CategoriaDTO(id, "desconocido", null);
    }

    @CircuitBreaker(name = "proveedorService", fallbackMethod = "fallbackProveedor")
    public ProveedorDTO fetchProveedor(Long id) {
        return proveedorClient.getById(id);
    }

    public ProveedorDTO fallbackProveedor(Long id, Throwable ex) {
        return new ProveedorDTO(id, "desconocido");
    }

    private ProductoDTO toDTO(Producto e) {
        CategoriaDTO cat = fetchCategoria(e.getCategoriaId());
        ProveedorDTO prov = fetchProveedor(e.getProveedorId());
        return new ProductoDTO(
                e.getId(), e.getNombre(), e.getDescripcion(),
                e.getPrecio(), cat, prov
        );
    }

    private Producto toEntity(ProductoDTO dto) {
        Producto e = new Producto();
        e.setNombre(dto.getNombre());
        e.setDescripcion(dto.getDescripcion());
        e.setPrecio(dto.getPrecio());
        e.setCategoriaId(dto.getCategoria().getId());
        e.setProveedorId(dto.getProveedor().getId());
        return e;
    }

    @Override
    public List<ProductoDTO> getAll() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductoDTO getById(Long id) {
        Producto e = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        return toDTO(e);
    }

    @Override
    public ProductoDTO create(ProductoDTO dto) {
        return toDTO(repository.save(toEntity(dto)));
    }

    @Override
    public ProductoDTO update(Long id, ProductoDTO dto) {
        Producto existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        existing.setNombre(dto.getNombre());
        existing.setDescripcion(dto.getDescripcion());
        existing.setPrecio(dto.getPrecio());
        existing.setCategoriaId(dto.getCategoria().getId());
        existing.setProveedorId(dto.getProveedor().getId());
        return toDTO(repository.save(existing));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
