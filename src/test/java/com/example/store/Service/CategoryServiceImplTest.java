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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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

        Category input_Category = new Category();
        input_Category.setName("electronique");

        Category saved_Category =  new Category();
        input_Category.setName("electronique");

        when(categoryRepository.save(any(Category.class))).thenReturn(saved_Category);

        Category result = categoryService.saveCategory(input_Category);
        assertNotNull(result);

        verify(categoryRepository).save(input_Category);


    }

    @Test
    void deleteCategoryByID() {
        Category mock_category = new Category();
        Long category_id = mock_category.getId();

        when(categoryRepository.existsById(category_id)).thenReturn(true);
        doNothing().when(categoryRepository).deleteById(category_id);

        categoryService.deleteCategoryByID(category_id);

        verify(categoryRepository).deleteById(category_id);


    }

    @Test
    void findCategoryById() {
        Category mock_Category = new Category();

        Long mock_category_id = mock_Category.getId();
        when(categoryRepository.findById(mock_category_id)).thenReturn(java.util.Optional.of(mock_Category));

        Optional<Category> foundCategory = categoryService.findCategoryById(mock_category_id);

        assertEquals(foundCategory.get().getId(),mock_category_id);
    }


}