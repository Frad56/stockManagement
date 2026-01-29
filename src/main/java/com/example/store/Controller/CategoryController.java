package com.example.store.Controller;


import com.example.store.Model.Category;
import com.example.store.Service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/category")
    public ResponseEntity<Category> saveCategory(@Valid @RequestBody Category category){
        Category return_category =categoryService.saveCategory(category);
        return ResponseEntity.ok(return_category);
    }

    @GetMapping("/category")
    public ResponseEntity < List<Category> > fetchCategoryList(){
        List<Category> category_list =categoryService.fetchCategoryList();
        return ResponseEntity.ok(category_list);
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<String> deleteCategoryByID(@PathVariable("id") Long categoryId){
        categoryService.deleteCategoryByID(categoryId);
        return ResponseEntity.ok("Deleted Successfully");

    }

    @GetMapping("/category/find/{id}")
    public ResponseEntity<Category> findCategoryById(@PathVariable("id") Long categoryId){
        Category category = categoryService.findCategoryById(categoryId);
        return ResponseEntity.ok(category);
    }
}
