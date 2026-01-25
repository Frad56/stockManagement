package com.example.store.Service;


import com.example.store.Model.Category;
import com.example.store.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    CategoryRepository categoryRepository;

    //add
    @Override
    public Category saveCategory(Category category){
        return categoryRepository.save(category);
    }

    //Read
    @Override
    public List<Category> fetchCategoryList(){
        return categoryRepository.findAll();
    }

    //delete
    @Override
    public void deleteCategoryByID(Long categoryId){
        if(categoryRepository.existsById(categoryId)){
            categoryRepository.deleteById(categoryId);
        }

    }

    //Search

    @Override
    public Optional<Category> findCategoryById(Long categoryId){
        return  categoryRepository.findById(categoryId);
    }
}

