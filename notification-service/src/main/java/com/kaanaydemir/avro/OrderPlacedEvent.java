package com.kaanaydemir.orderservice.event;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderPlacedEvent {

    @NotNull
    private UUID orderId;

    @NotNull
    private String productId;

    private int quantity;
}