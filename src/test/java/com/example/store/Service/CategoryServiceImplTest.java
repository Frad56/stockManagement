package com.example.store.Service;

import com.example.store.Exception.ElementNotFoundException;
import com.example.store.Model.Category;
import com.example.store.Repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

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
    void whenSaveCategoryFailed_ShouldThrowException(){
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
    void deleteCategoryById_WhenCategoryExists_ShouldDeleteSuccessfully(){
        Long id = 1L;

        when(categoryRepository.existsById(id)).thenReturn(true);

        categoryService.deleteCategoryByID(id);

        verify(categoryRepository).existsById(id);
        verify(categoryRepository).deleteById(id);

    }

    @Test
    void deleteCategoryById_WhenCategoryDoesNotExist_ShouldThrowException(){
        Long id = 1L;
        when(categoryRepository.existsById(id)).thenReturn(false);
        ElementNotFoundException exception = assertThrows(
                ElementNotFoundException.class ,() -> categoryService.deleteCategoryByID(id) );


    }


    @Test
    void findCategoryById() {
        Category mock_Category = new Category();

        Long mock_category_id = mock_Category.getId();
        when(categoryRepository.findById(mock_category_id)).thenReturn(java.util.Optional.of(mock_Category));
       Category foundCategory = categoryService.findCategoryById(mock_category_id);

        assertEquals(foundCategory.getId(),mock_category_id);
    }


}