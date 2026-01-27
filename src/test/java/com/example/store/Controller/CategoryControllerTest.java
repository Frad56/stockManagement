package com.example.store.Controller;

import com.example.store.Model.Category;
import com.example.store.Service.CategoryService;
import static org.hamcrest.Matchers.is;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = CategoryController.class,
excludeAutoConfiguration = SecurityAutoConfiguration.class)
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @Test
    void saveCategory() throws Exception{
        Category category = new Category();
        category.setName("C001");
        when(categoryService.saveCategory(any(Category.class))).thenReturn(category);

        mockMvc.perform(post("/categorys").contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(category)))
                .andExpect(status().isOk());

   }

    @Test
    void fetchCategoryList() throws Exception{
        // *********** Category_1 ************
        Category category_1 =new Category();
        category_1.setName("C001");
        // *********** Category_2 ************
        Category category_2 = new Category();
        category_2.setName("C002");

        List<Category> mockCategoryList = Arrays.asList(category_1,category_2);
        when(categoryService.fetchCategoryList()).thenReturn(mockCategoryList);
        mockMvc.perform(get("/categorys").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()",is(2)))
                .andExpect(jsonPath("$[0].name").value(category_1.getName()))
                .andExpect(jsonPath("$[1].name").value(category_2.getName()));



    }

    @Test
    void deleteCategoryByID() throws Exception {
        Long id = 1L;
        Category category_1 =new Category();
        category_1.setName("C001");
        category_1.setId(1L);

        willDoNothing().given(categoryService).deleteCategoryByID(category_1.getId());
        mockMvc.perform(delete("/category/{id}",id).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Deleted Successfully"));
               // .andExpect(category_1.getId(),is(id));
    }

}