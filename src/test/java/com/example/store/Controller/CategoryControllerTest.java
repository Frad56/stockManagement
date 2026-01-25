package com.example.store.Controller;

import com.example.store.Model.Category;
import com.example.store.Service.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = CategoryController.class,
excludeAutoConfiguration = SecurityException.class)
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

//    @Test
//    void saveCategory() throws Exception{
//        Category category = new Category();
//        category.setName("C001");
//        when(categoryService.saveCategory(category)).thenReturn(category);
//
//        mockMvc.perform(post("/categorys").contenType(MediaType.APPLICATION_JSON))
//                .content(new ObjectMapper().writeValueAsString(category))
//                .andExpect(status().isOk());
//
//    }

    @Test
    void fetchCategoryList() {
    }

    @Test
    void deleteCategoryByID() {
    }
}