package com.kaanaydemir.inventoryservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryResponse {

    @NotNull
    private Long productId;

    @Min(0)
    private Integer quantity;

    private Boolean inStock;
}