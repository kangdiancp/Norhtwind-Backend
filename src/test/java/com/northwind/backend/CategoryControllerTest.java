package com.northwind.backend;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.northwind.backend.controller.CategoryController;
import com.northwind.backend.dto.CategoryDto;
import com.northwind.backend.entities.Category;
import com.northwind.backend.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ImportAutoConfiguration(classes = {SecurityConfig.class})
@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CategoryService categoryService;



    @Test
    @WithMockUser(roles = {"ADMIN"})
    void shouldCreateCategory() throws Exception {
        CategoryDto category = new CategoryDto(0l, "Music", "K-Pop");

        mockMvc.perform(post("/api/category").contentType(MediaType.APPLICATION_JSON)
                        .with(user("kangdian").roles("ADMIN"))
                        .with(csrf())
                        .content(objectMapper.writeValueAsString(category)))
                .andExpect(status().isCreated())
                .andDo(print());
    }


    @Test
    @WithMockUser(roles = {"ADMIN"})
    void shouldReturnCategory() throws Exception {
        long id = 1L;
        CategoryDto category = new CategoryDto(id, "Musics", "K-Pop, J-Pop, West");

        when(categoryService.findById(id)).thenReturn(Optional.of(category));
        mockMvc.perform(get("/api/category/{id}", id)).andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryId").value(id))
                .andExpect(jsonPath("$.categoryName").value(category.getCategoryName()))
                .andExpect(jsonPath("$.description").value(category.getDescription()))
                .andDo(print());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void shouldReturnNotFoundCategory() throws Exception {
        long id = 1L;

        when(categoryService.findById(id)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/category/{id}", id))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void shouldReturnListOfCategories() throws Exception {
        List<CategoryDto> categories = new ArrayList<>(
                Arrays.asList(new CategoryDto(1l, "Test 1", "Desc 1"),
                        new CategoryDto(2l, "Test 2", "Desc 2"),
                        new CategoryDto(3l, "Test 3", "Desc 3")));

        when(categoryService.findAllCategory()).thenReturn(categories);
        mockMvc.perform(get("/api/category"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(categories.size()))
                .andDo(print());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void shouldReturnListOfCategoriesWithFilter() throws Exception {
        List<CategoryDto> categories = new ArrayList<>(
                Arrays.asList(new CategoryDto(1l, "Confections", "Desserts, candies, and sweet breads"),
                        new CategoryDto(2l, "Meat/Poultry", "Prepared meats"),
                        new CategoryDto(3l, "Musics", "K-Pop, J-Pop, West")));

        String cateName = "Pop";
        MultiValueMap<String, String> paramsMap = new LinkedMultiValueMap<>();
        paramsMap.add("description", cateName);

        when(categoryService.findByDescriptionContaining(cateName)).thenReturn(categories);
        mockMvc.perform(get("/api/category/search?").params(paramsMap))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(categories.size()))
                .andDo(print());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void shouldReturnNoContentWhenFilter() throws Exception {
        String search = "Pop";
        MultiValueMap<String, String> paramsMap = new LinkedMultiValueMap<>();
        paramsMap.add("description", search);

        List<CategoryDto> categories = Collections.emptyList();

        when(categoryService.findByDescriptionContaining(search)).thenReturn(categories);
        mockMvc.perform(get("/api/category/search?").params(paramsMap))
                .andExpect(status().isNoContent())
                .andDo(print());
    }


    @Test
    @WithMockUser(roles = {"ADMIN"})
    void shouldUpdateCategory() throws Exception {
        long id = 1L;

        CategoryDto category = new CategoryDto(id, "Musics", "K-Pop, J-Pop, West");
        CategoryDto categoryUpdate = new CategoryDto(id, "Musics", "Bollywood");

        when(categoryService.findById(id)).thenReturn(Optional.of(category));
        when(categoryService.createCategory(any(Category.class))).thenReturn(categoryUpdate);

        mockMvc.perform(put("/api/category/{id}", id).contentType(MediaType.APPLICATION_JSON)
                        .with(user("kangdian").roles("ADMIN"))
                        .with(csrf())
                        .content(objectMapper.writeValueAsString(categoryUpdate)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryName").value(categoryUpdate.getCategoryName()))
                .andExpect(jsonPath("$.description").value(categoryUpdate.getDescription()))
                .andDo(print());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void shouldReturnNotFoundUpdateCategory() throws Exception {
        long id = 1L;

        CategoryDto categoryUpdate = new CategoryDto(id, "Musics", "Bollywood");

        when(categoryService.findById(id)).thenReturn(Optional.empty());
        when(categoryService.createCategory(any(Category.class))).thenReturn(categoryUpdate);

        mockMvc.perform(put("/api/category/{id}", id).contentType(MediaType.APPLICATION_JSON)
                        .with(user("kangdian").roles("ADMIN"))
                        .with(csrf())
                        .content(objectMapper.writeValueAsString(categoryUpdate)))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void shouldDeleteCategory() throws Exception {
        long id = 1L;

        CategoryDto categoryDelete = new CategoryDto(id, "Musics", "Bollywood");

        doNothing().when(categoryService).deleteCategory(id);

        mockMvc.perform(delete("/api/category/{id}", id).contentType(MediaType.APPLICATION_JSON)
                        .with(user("kangdian").roles("ADMIN"))
                        .with(csrf())
                        .content(objectMapper.writeValueAsString(categoryDelete)))
                .andExpect(status().isNoContent())
                .andDo(print());
    }


}
