package com.example.store.Service;


import com.example.store.Model.Category;

import java.util.List;

public interface CategoryService {

    Category saveCategory(Category category);

    List<Category> fetchCategoryList();

    void deleteCategoryByID(Long categoryID);
}
