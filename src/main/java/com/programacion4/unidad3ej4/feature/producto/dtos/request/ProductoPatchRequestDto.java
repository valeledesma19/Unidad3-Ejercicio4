package com.programacion4.unidad3ej4.feature.producto.dtos.request;

import lombok.Data;

@Data
public class ProductoPatchRequestDto {

    private Double precio;
    private Integer stock;
    private Long categoriaId;

}