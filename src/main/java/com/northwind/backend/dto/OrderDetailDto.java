package com.northwind.backend.dto;

import com.northwind.backend.entities.OrderProductId;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class OrderDetailDto {
    //private OrderProductIDDto orderProductIDDto;
    private Long orderId;
    private Long productId;
    private Double unitPrice;
    private int quantity;
    private float discount;
}
