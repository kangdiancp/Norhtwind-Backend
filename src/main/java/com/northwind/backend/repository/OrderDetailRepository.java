package com.northwind.backend.repository;

import com.northwind.backend.entities.OrderDetail;
import com.northwind.backend.entities.OrderProductId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderProductId> {
}
