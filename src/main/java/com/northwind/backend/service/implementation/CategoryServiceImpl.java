package com.northwind.backend.service.implementation;

import com.northwind.backend.dto.CategoryDto;
import com.northwind.backend.entities.Category;
import com.northwind.backend.repository.CategoryRepository;
import com.northwind.backend.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repository;

    public static CategoryDto mapToDto(Category category){
        return new CategoryDto(category.getCategoryId(),
                category.getCategoryName(),
                category.getDescription());
    }

    @Override
    public List<CategoryDto> findAllCategory() {

        return repository.findAll().stream().map(CategoryServiceImpl::mapToDto).collect(Collectors.toList());

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
