package com.northwind.backend.controller;

import com.northwind.backend.dto.CategoryDto;
import com.northwind.backend.dto.OrderDto;
import com.northwind.backend.entities.Category;
import com.northwind.backend.exception.ResourceNotFoundException;
import com.northwind.backend.payload.response.MessageResponse;
import com.northwind.backend.service.CategoryService;
import com.northwind.backend.service.OrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.NullLiteral;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Tag(name = "Java Bootcamp", description = "Category Service API")
@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {
    private final CategoryService categoryService;

    //gunakan @Async & CompletableFuture pada sebuah method agar bisa diperlakukan Asyncronoush
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_FA')")
    @GetMapping("/async")
    @Async
    public CompletableFuture<ResponseEntity<?>> findAllOrderAsync(){
        log.debug("Get all category resources");
        var categoriesDto= categoryService.findAllCategory();
        return CompletableFuture.completedFuture(new ResponseEntity<>(categoriesDto, HttpStatus.OK));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('FA')")
    public ResponseEntity<?> findAllOrder(){
        log.debug("Get all category resources");

        try{
            var categoriesDto= categoryService.findAllCategory();

            if (categoriesDto.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(categoriesDto, HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search")
    @PreAuthorize("hasRole('ADMIN') or hasRole('FA')")
    public ResponseEntity<List<?>> findCategoriesByDescription(@RequestParam(required = false) String description) {
        try {
            List<CategoryDto> categories = new ArrayList<>();

            if (description == null)
                categoryService.findAllCategory().forEach(categories::add);
            else
                categoryService.findByDescriptionContaining(description).forEach(categories::add);

            if (categories.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(categories, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('FA')")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){
        try{
            Category category = Category.builder()
                    .categoryId(categoryDto.getCategoryId())
                    .categoryName(categoryDto.getCategoryName())
                    .description(categoryDto.getDescription()).build();

            categoryDto = categoryService.createCategory(category);
            return new ResponseEntity<>(categoryDto,HttpStatus.CREATED);

        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('FA')")
    public ResponseEntity<?> findCategoryById(@PathVariable("id")Long categoryId){
        CategoryDto category = categoryService.findById(categoryId).orElseThrow(()->
            new ResourceNotFoundException("CategoryId not found")
        );
        return new ResponseEntity<>(category,HttpStatus.OK);
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('FA')")
    public ResponseEntity<?> updateCategory(@PathVariable("id") long id, @RequestBody CategoryDto categoryUpdate) {
        Optional<CategoryDto> categoryDto = categoryService.findById(id);

        if (categoryDto.isPresent()) {
            Category categoryEntity = Category.builder()
                    .categoryId(categoryDto.get().getCategoryId())
                    .categoryName(categoryDto.get().getCategoryName())
                    .description(categoryDto.get().getDescription()).build();

            return new ResponseEntity<>(categoryService.createCategory(categoryEntity), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('FA')")
    public ResponseEntity<HttpStatus> deleteCategory(@PathVariable("id") long id) {
        try {
            categoryService.deleteCategory(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
