package com.northwind.backend.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name="categories",schema="dbo")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="categoryid",updatable = false, nullable = false)
    private Long categoryId;
    
    @Column(name="categoryname")
    private String categoryName;

    @Column(name="description")
    private String description;

    //@NoArgsConstructor
    //public Category()

    //@AllArgsConstructor
    //public Category(Integer categoryId,String categoryName, String description)

}
