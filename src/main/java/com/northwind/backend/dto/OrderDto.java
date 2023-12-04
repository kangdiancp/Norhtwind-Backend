package com.northwind.backend.dto;

import com.northwind.backend.entities.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@Data
public class OrderDto {
    private Long orderId;
    private CustomerDto customerDto;
    private LocalDateTime orderDate;
    private LocalDateTime requiredDate;
    private LocalDateTime shippedDate;
    private Double subTotal;
    private Double totalPrice;
    private Set<OrderDetailDto> orderDetailsDto;
}
