package com.northwind.backend.service.implementation;

import com.northwind.backend.dto.OrderDetailDto;
import com.northwind.backend.entities.OrderDetail;
import com.northwind.backend.entities.OrderProductId;
import com.northwind.backend.repository.OrderDetailRepository;
import com.northwind.backend.service.OrderDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {
    private final OrderDetailRepository repository;

    public static OrderDetailDto mapToDto(OrderDetail orderDetail){
        return new OrderDetailDto(
                orderDetail.getOrderId(),
                orderDetail.getProductId(),
                orderDetail.getUnitPrice(),
                orderDetail.getQuantity(),
                orderDetail.getDiscount()
        );
    }
    @Override
    public Set<OrderDetailDto> findAllOrderDetail() {
        return repository.findAll().stream().map(OrderDetailServiceImpl::mapToDto).collect(Collectors.toSet());
    }
}
