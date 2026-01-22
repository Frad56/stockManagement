package com.example.store.Repository;


import com.example.store.Model.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void CategoryRepository_ReturnSavedCategory() {
        Category category = new Category();
        category.setName("electro");

        Category savedCategory = categoryRepository.save(category);
        assertThat(savedCategory).isNotNull();
        assertThat(savedCategory.getId()).isNotNull();
        assertThat(savedCategory.getName()).isEqualTo("electro");
    }

    @Test
    public  void CategoryRepositor_GetAll(){
        //****** Category1 *******
        Category category = new Category();
        category.setName("electronique");
        categoryRepository.save(category);

        //****** Category2 *******
        Category category2 = new Category();
        category2.setName("vetement");
        categoryRepository.save(category2);

        List<Category> categoryList = categoryRepository.findAll();

        assertThat(categoryList)
                .isNotEmpty()
                .extracting(Category::getName)
                .contains("electronique","vetement" );
    }

    @Test
    public  void CategoryRepositor_findById_ReturnNotNull(){
        Category category = new Category();
        category.setName("electronique");
        categoryRepository.save(category);

        Optional<Category> categoryList = categoryRepository.findById(category.getId());
        assertThat(categoryList).isNotNull();
    }

    @Test
    public void CategoryRepositor_findByName_ReturnNotNull(){
        Category category = new Category();
        category.setName("electronique");
        categoryRepository.save(category);

        Optional<Category> categoryList = categoryRepository.findByName(category.getName());

        assertThat(categoryList).isNotNull();
    }

    @Test
    void CategoryRepository_UpdateCategory(){
        Category category = new Category();
        category.setName("electronique");
        categoryRepository.save(category);

        Category categorySave = categoryRepository.findByName(category.getName()).get();
        categorySave.setName("vetement");

        Category updateCategory = categoryRepository.save(categorySave);

        assertThat(updateCategory).isNotNull();
    }

    @Test
    void CategoryRepository_DeleteCategory_ReturnCategoryISEmpty(){
        Category category = new Category();
        category.setName("electronique");
        categoryRepository.save(category);

        categoryRepository.deleteById(category.getId());

        Optional<Category> categoryReturn = categoryRepository.findById(category.getId());
        assertThat(categoryReturn).isEmpty();
    }
}
