package com.northwind.backend;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.northwind.backend.dto.CategoryDto;
import com.northwind.backend.entities.Category;
import com.northwind.backend.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


@WebMvcTest(CategoryControllerTest.class)
public class CategoryControllerTest {
    @MockBean
    private CategoryRepository categoryRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;



    @Test
    //@WithMockUser(roles = {"ADMIN"})
    void shouldCreateCategory() throws Exception {
        CategoryDto category = new CategoryDto(0L,"Fashion","Female Fashion");

        mockMvc.perform(post("/api/category/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(category)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    //@WithMockUser(roles = {"ADMIN"})
    void shouldReturnCategory() throws Exception {
        long id = 11L;
        Category category = new Category(id, "Test", "Description TEST");

        when(categoryRepository.findById(id)).thenReturn(Optional.of(category));
        mockMvc.perform(get("/api/category/{id}", id)).andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryId").value(id))
                .andExpect(jsonPath("$.categoryName").value(category.getCategoryName()))
                .andExpect(jsonPath("$.description").value(category.getDescription()))
                .andDo(print());
    }

    @Test
   // @WithMockUser(roles={"ADMIN"})
    void shouldReturnNotFoundCategory() throws Exception {
        long id = 12L;

        when(categoryRepository.findById(id)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/category/{id}", id))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

}
