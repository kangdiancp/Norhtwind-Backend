package com.northwind.backend.service;


import com.northwind.backend.dto.CategoryDto;
import com.northwind.backend.entities.Category;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface CategoryService extends BaseService{

    @PreAuthorize("hasRole('ADMIN')")
    List<CategoryDto> findAllCategory();
} 
