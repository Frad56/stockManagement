package com.example.store.Service;


import com.example.store.Model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    Category saveCategory(Category category);

    Optional<Category> findCategoryById(Long categoryId);

    List<Category> fetchCategoryList();

    void deleteCategoryByID(Long categoryID);
}
