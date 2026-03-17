package com.kaanaydemir.orderservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public class OrderRequest {

    @NotBlank
    private String customerId;

    @NotEmpty
    private List<OrderLineItemDto> orderLineItems;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<OrderLineItemDto> getOrderLineItems() {
        return orderLineItems;
    }

    public void setOrderLineItems(List<OrderLineItemDto> orderLineItems) {
        this.orderLineItems = orderLineItems;
    }
}