package com.northwind.backend.repository;

import com.northwind.backend.dto.CategoryDto;
import com.northwind.backend.entities.Category;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long>{

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "delete from dbo.category where categoryid=:categoryId",
            countQuery = "select count(1) from dbo.category where categoryid=:categoryId",
            nativeQuery = true)
    int deleteCategoryById(Long categoryId);

    List<Category> findByDescriptionContaining(String description);
    
}
