package com.northwind.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class OrderProductIDDto {
    private Long orderId;
    private long productId;
}
