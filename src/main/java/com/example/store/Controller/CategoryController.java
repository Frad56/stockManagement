package com.example.store.Controller;


import com.example.store.Model.Category;
import com.example.store.Service.CategoryServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryServiceImpl categoryServiceImpl;

    @PostMapping("/categorys")
    public Category saveCategory(@Valid @RequestBody Category category){
        return  categoryServiceImpl.saveCategory(category);
    }

    @GetMapping("/categorys")
    public List<Category> fetchCategoryList(){
        return categoryServiceImpl.fetchCategoryList();
    }

    @DeleteMapping("/category/{id}")
    public String deleteCategoryByID(@PathVariable("id") Long categoryId){
        categoryServiceImpl.deleteCategoryByID(categoryId);
        return  "Deleted Successfully";
    }
}
