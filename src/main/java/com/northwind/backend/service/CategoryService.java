package com.northwind.backend.service;


import com.northwind.backend.entities.Category;

import java.util.List;

public interface CategoryService extends BaseService{

    List<Category> findAllCategory();
} 
