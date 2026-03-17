package com.kaanaydemir.inventoryservice.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private Long productId;

    @PositiveOrZero
    private Integer quantity;

    private Boolean inStock;
}