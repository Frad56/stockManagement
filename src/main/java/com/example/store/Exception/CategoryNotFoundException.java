package com.example.store.Exception;

public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException(Long id ){
        super("Category Not found with this id :"+id);
    }
}
