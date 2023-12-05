package com.northwind.backend.controller;

import com.northwind.backend.dto.OrderDto;
import com.northwind.backend.service.CategoryService;
import com.northwind.backend.service.OrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@Tag(name = "Java Bootcamp", description = "Category Service API")
@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {
    private final CategoryService categoryService;

    /*@GetMapping
    @Async
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CompletableFuture<ResponseEntity<?>> findAllOrder(){
        log.debug("Get all category resources");
        var categoriesDto= categoryService.findAllCategory();
        return CompletableFuture.completedFuture(new ResponseEntity<>(categoriesDto, HttpStatus.OK));
    }*/

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_FA')")
    public ResponseEntity<?> findAllOrder(){
        log.debug("Get all category resources");
        var categoriesDto= categoryService.findAllCategory();
        return new ResponseEntity<>(categoriesDto, HttpStatus.OK);
    }


}
