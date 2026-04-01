package com.programacion4.unidad3ej4.feature.producto.services.impl.domain;

import org.springframework.stereotype.Service;

import com.programacion4.unidad3ej4.config.exceptions.NotFoundException;
import com.programacion4.unidad3ej4.feature.producto.dtos.request.ProductoPatchRequestDto;
import com.programacion4.unidad3ej4.feature.producto.dtos.response.ProductoResponseDto;
import com.programacion4.unidad3ej4.feature.producto.mappers.ProductoMapper;
import com.programacion4.unidad3ej4.feature.producto.models.Categoria;
import com.programacion4.unidad3ej4.feature.producto.models.Producto;
import com.programacion4.unidad3ej4.feature.producto.repositories.ICategoriaRepository;
import com.programacion4.unidad3ej4.feature.producto.repositories.IProductoRepository;
import com.programacion4.unidad3ej4.feature.producto.services.interfaces.domain.IProductoPatchService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductoPatchService implements IProductoPatchService {

    private final IProductoRepository productoRepository;
    private final ICategoriaRepository categoriaRepository;

    @Override
    public ProductoResponseDto patch(Long id, ProductoPatchRequestDto dto) {

        Producto producto = productoRepository
                .findByIdAndEstaEliminadoFalse(id)
                .orElseThrow(() -> new NotFoundException("Producto no encontrado"));


        if (dto.getPrecio() != null) {
            producto.setPrecio(dto.getPrecio());
        }

        if (dto.getStock() != null) {
            producto.setStock(dto.getStock());
        }

        if (dto.getCategoriaId() != null) {
            Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                    .orElseThrow(() -> new NotFoundException("Categoría no encontrada"));

            producto.setCategoria(categoria);
        }

        Producto actualizado = productoRepository.save(producto);

        return ProductoMapper.toResponseDto(actualizado);
    }
}