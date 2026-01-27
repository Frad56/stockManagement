package com.example.store.Controller;


import com.example.store.Model.Category;
import com.example.store.Service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/categorys")
    public Category saveCategory(@Valid @RequestBody Category category){
        return  categoryService.saveCategory(category);
    }

    @GetMapping("/categorys")
    public List<Category> fetchCategoryList(){
        return categoryService.fetchCategoryList();
    }

    @DeleteMapping("/category/{id}")
    public String deleteCategoryByID(@PathVariable("id") Long categoryId){
        categoryService.deleteCategoryByID(categoryId);
        return  "Deleted Successfully";
    }

    @GetMapping("/categorys/find/{id}")
    public ResponseEntity<Category> findCategoryById(@PathVariable("id") Long categoryId){
        Category category = categoryService.findCategoryById(categoryId);
        return ResponseEntity.ok(category);
    }
}
