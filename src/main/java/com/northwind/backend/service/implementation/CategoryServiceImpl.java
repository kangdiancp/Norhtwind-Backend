package com.northwind.backend.service.implementation;

import com.northwind.backend.dto.CategoryDto;
import com.northwind.backend.entities.Category;
import com.northwind.backend.exception.ResourceNotFoundException;
import com.northwind.backend.repository.CategoryRepository;
import com.northwind.backend.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
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
    public Optional<CategoryDto> findById(Long id) {
        Optional<Category> categoryEntity = Optional.ofNullable(repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("CategoryId not found")
        ));

        CategoryDto categoryDto = CategoryServiceImpl.mapToDto(categoryEntity.get());
        return Optional.of(categoryDto);
    }

    @Override
    public CategoryDto createCategory(Category category) {
        return CategoryServiceImpl.mapToDto(repository.save(category));
    }

    @Override
    public Optional<CategoryDto> updateCategory(Long id, Category category) {
        Optional<Category> categoryEntity = repository.findById(id);
        if (categoryEntity.isPresent()){
            return Optional.of(CategoryServiceImpl.mapToDto(repository.save(category)));
        }
        return Optional.empty();
    }

    @Override
    public void deleteCategory(Long id) {
        Optional<Category> category = repository.findById(id);
        if (category.isPresent()){
            repository.deleteCategoryById(id);
        }
    }

    @Override
    public List<CategoryDto> findByDescriptionContaining(String name) {
        return repository.findByDescriptionContaining(name)
                .stream()
                .map(CategoryServiceImpl::mapToDto)
                .collect(Collectors.toList());
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
