package com.northwind.backend.service;

import com.northwind.backend.dto.OrderDto;
import com.northwind.backend.entities.Order;

import java.util.Set;

public interface OrderService {
    Set<OrderDto> findAllOrder();
    OrderDto findOrderById(Long orderId);

}
