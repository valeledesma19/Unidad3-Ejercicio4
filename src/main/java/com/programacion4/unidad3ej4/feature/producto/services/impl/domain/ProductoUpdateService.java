package com.programacion4.unidad3ej4.feature.producto.services.impl.domain;

import org.springframework.stereotype.Service;

import com.programacion4.unidad3ej4.config.exceptions.NotFoundException;
import com.programacion4.unidad3ej4.feature.producto.dtos.request.ProductoUpdateRequestDto;
import com.programacion4.unidad3ej4.feature.producto.dtos.response.ProductoResponseDto;
import com.programacion4.unidad3ej4.feature.producto.mappers.ProductoMapper;
import com.programacion4.unidad3ej4.feature.producto.models.Categoria;
import com.programacion4.unidad3ej4.feature.producto.models.Producto;
import com.programacion4.unidad3ej4.feature.producto.repositories.ICategoriaRepository;
import com.programacion4.unidad3ej4.feature.producto.repositories.IProductoRepository;
import com.programacion4.unidad3ej4.feature.producto.services.interfaces.domain.IProductoUpdateService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductoUpdateService implements IProductoUpdateService {

    private final IProductoRepository productoRepository;
    private final ICategoriaRepository categoriaRepository;

    @Override
    public ProductoResponseDto update(Long id, ProductoUpdateRequestDto dto) {

        Producto producto = productoRepository
                .findByIdAndEstaEliminadoFalse(id)
                .orElseThrow(() -> new NotFoundException("Producto no encontrado"));

        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new NotFoundException("Categoría no encontrada"));

        producto.setNombre(capitalize(dto.getNombre()));
        producto.setDescripcion(capitalize(dto.getDescripcion()));

        producto.setCodigo(dto.getCodigo());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());
        producto.setCategoria(categoria);
        Producto actualizado = productoRepository.save(producto);

        return ProductoMapper.toResponseDto(actualizado);
    }

    private String capitalize(String texto) {
        if (texto == null || texto.isEmpty()) return texto;

        texto = texto.toLowerCase();
        return texto.substring(0,1).toUpperCase() + texto.substring(1);
    }
}