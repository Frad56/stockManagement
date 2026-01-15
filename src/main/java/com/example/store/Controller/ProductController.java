package com.example.store.Controller;


import com.example.store.Model.Product;
import com.example.store.Service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductServiceImpl productServiceImpl;

    @PostMapping("/products")
    public Product saveProduct(@Valid @RequestBody Product product) {
        return productServiceImpl.saveProduct(product);
    }

    @GetMapping("/products")
    public List<Product> fetchProductList(){
        return  productServiceImpl.fetchProductList();
    }

    @DeleteMapping("/products/{id}")
    public String deleteProductByID(@PathVariable("id") Long productId){
        productServiceImpl.deleteProductById(productId);
        return "Deleted Successfully";
    }

}
