package com.northwind.backend.service;

import com.northwind.backend.dto.OrderDetailDto;
import com.northwind.backend.dto.OrderDto;

import java.util.Set;

public interface OrderDetailService {
    Set<OrderDetailDto> findAllOrderDetail();


}
