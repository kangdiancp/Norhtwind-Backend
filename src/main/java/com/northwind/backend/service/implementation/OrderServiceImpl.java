package com.northwind.backend.service.implementation;

import com.northwind.backend.dto.CustomerDto;
import com.northwind.backend.dto.OrderDetailDto;
import com.northwind.backend.dto.OrderDto;
import com.northwind.backend.entities.Order;
import com.northwind.backend.repository.CustomerRepository;
import com.northwind.backend.repository.OrderRepository;
import com.northwind.backend.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository repositoryOrder;

    public  static OrderDto mapToDto(Order order){
        Set<OrderDetailDto> ordersDetailDto = order.getOrderDetails().stream().map(OrderDetailServiceImpl::mapToDto).collect(Collectors.toSet());
        return new OrderDto(order.getOrderId(),
                CustomerServiceImpl.mapToDto(order.getCustomer()),
                order.getOrderDate(),
                order.getRequiredDate(),
                order.getShippedDate(),
                order.getSubTotal(),
                order.getTotalPrice(),
                ordersDetailDto
                );
    }

    @Override
    public Set<OrderDto> findAllOrder() {
        return repositoryOrder.findAll()
                .stream()
                .map(OrderServiceImpl::mapToDto)
                .collect(Collectors.toSet());
    }

    @Override
    public OrderDto findOrderById(Long orderId) {
        return repositoryOrder.findById(orderId).map(OrderServiceImpl::mapToDto).orElseThrow();
    }
}
