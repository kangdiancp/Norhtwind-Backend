package com.northwind.backend.controller;

import com.northwind.backend.dto.OrderDto;
import com.northwind.backend.entities.Order;
import com.northwind.backend.service.OrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Tag(name = "Java Bootcamp", description = "Order Service API")
@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('FA')")
    public ResponseEntity<?> findAllOrder(){
        log.debug("Get all order resources");
        var ordersDto= orderService.findAllOrder();
        return new ResponseEntity<>(ordersDto, HttpStatus.OK);
    }
/*    @Async
    public CompletableFuture<ResponseEntity<?>> findAllOrder(){
        log.debug("Get all order resources");
        var ordersDto= orderService.findAllOrder();
        return CompletableFuture.completedFuture(new ResponseEntity<>(ordersDto, HttpStatus.OK));
    }*/

    @GetMapping("/{orderId}/details")
    @PreAuthorize("hasRole('ADMIN') or hasRole('FA')")
    @Async
    public CompletableFuture<ResponseEntity<?>> findAllOrderDetail(@PathVariable("orderId") Long orderId){
        log.debug("Get orderDetail resources");
        OrderDto orderDto = orderService.findOrderById(orderId);
        return CompletableFuture.completedFuture(new ResponseEntity<>(orderDto,HttpStatus.OK));
    }
}
