package com.northwind.backend.service;


import com.northwind.backend.dto.CategoryDto;
import com.northwind.backend.entities.Category;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.Optional;

public interface CategoryService extends BaseService{


    List<CategoryDto> findAllCategory();
    Optional<CategoryDto> findById(Long id);

    CategoryDto createCategory(Category category);

    Optional<CategoryDto> updateCategory(Long id, Category category);

    void deleteCategory(Long id);

    List<CategoryDto> findByDescriptionContaining(String name);



} 
