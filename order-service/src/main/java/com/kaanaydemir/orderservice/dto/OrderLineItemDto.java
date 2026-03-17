package com.kaanaydemir.orderservice.dto;

import java.math.BigDecimal;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItemDto {

    @NotBlank
    private String skuCode;

    @NotNull
    private Integer quantity;

    @NotNull
    private BigDecimal price;
}