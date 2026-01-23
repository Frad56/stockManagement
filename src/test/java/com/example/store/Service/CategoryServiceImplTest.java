package com.example.store.Service;

import com.example.store.Model.Category;
import com.example.store.Repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private  CategoryServiceImpl categoryService;

    @Test
    void fetchCategoryList() {
        Category category_1 = new Category();
        category_1.setName("electronique");

        Category category_2 = new Category();
        category_2.setName("vetement");

        List<Category> mockList = Arrays.asList(category_1,category_2);

        when(categoryRepository.findAll()).thenReturn(mockList);
        List<Category> result = categoryService.fetchCategoryList();

        assertEquals("electronique",result.get(0).getName());
        assertEquals(2,result.size());

        verify(categoryRepository).findAll();
    }

    @Test
    void whenSaveCategoryFaild_ShouldThrrowException(){
        Category category_1 = new Category();
        category_1.setName("electronique");

        when(categoryRepository.save(any(Category.class)))
                .thenThrow(new RuntimeException("DB error"));

        RuntimeException exception=assertThrows(
                RuntimeException.class,()->categoryService.saveCategory(category_1)
        );
        assertEquals("DB error",exception.getMessage());

        verify(categoryRepository).save(category_1);

    }

    @Test
    void saveCategory() {

        Category inputCategory = new Category();
        inputCategory.setName("electronique");


    }

    @Test
    void deleteCategoryByID() {
    }

    @Test
    void findCategoryById() {
    }
}