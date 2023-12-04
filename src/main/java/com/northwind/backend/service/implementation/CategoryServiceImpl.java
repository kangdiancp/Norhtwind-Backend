package com.northwind.backend.service.implementation;

import com.northwind.backend.entities.Category;
import com.northwind.backend.repository.CategoryRepository;
import com.northwind.backend.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repository;

    @Override
    public List<Category> findAllCategory() {
        return repository.findAll();
    }

    @Override
    public <T> Optional<T> createEntity(T entity) {
        Category category = (Category) entity;
        repository.save(category);
        return (Optional<T>) Optional.of(category);
    }

    @Override
    public <T> List<T> findAllEntity() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllEntity'");
    }

    @Override
    public <T> T findEntityById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findEntityById'");
    }

    @Override
    public void deleteEntityById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteEntityById'");
    }

}
