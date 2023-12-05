package com.northwind.backend.dto;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@AllArgsConstructor
@Data
public class CategoryDto {
    private Long categoryId;
    private String categoryName;
    private String description;
}
