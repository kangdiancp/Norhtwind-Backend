package com.northwind.backend.controller;

import com.northwind.backend.dto.OrderDto;
import com.northwind.backend.entities.Order;
import com.northwind.backend.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Set;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/")
    @Async
    public CompletableFuture<ResponseEntity<?>> findAllOrder(){
        log.debug("Get all order resources");
        var ordersDto= orderService.findAllOrder();
        return CompletableFuture.completedFuture(new ResponseEntity<>(ordersDto, HttpStatus.OK));
    }

    @GetMapping("/{orderId}/details")
    @Async
    public CompletableFuture<ResponseEntity<?>> findAllOrderDetail(@PathVariable("orderId") Long orderId){
        log.debug("Get orderDetail resources");
        OrderDto orderDto = orderService.findOrderById(orderId);
        return CompletableFuture.completedFuture(new ResponseEntity<>(orderDto,HttpStatus.OK));
    }
}