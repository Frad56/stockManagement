package com.example.store.Controller;


import com.example.store.DTO.ProductDTO;
import com.example.store.Model.Category;
import com.example.store.Model.Place;
import com.example.store.Model.Product;
import com.example.store.Model.Stock;
import com.example.store.Service.CategoryServiceImpl;
import com.example.store.Service.PlaceServiceImpl;
import com.example.store.Service.ProductServiceImpl;
import com.example.store.Service.StockServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
public class ProductController {


    private ProductServiceImpl productServiceImpl;
    private CategoryServiceImpl categoryServiceImpl;
    private StockServiceImpl stockServiceImpl;
    private PlaceServiceImpl placeServiceImpl;


    @Autowired
    public ProductController(ProductServiceImpl productServiceImpl,
                             CategoryServiceImpl categoryServiceImp,
                             StockServiceImpl stockServiceImpl,
                             PlaceServiceImpl placeServiceImpl){
        this.productServiceImpl=productServiceImpl;
        this.categoryServiceImpl =categoryServiceImp;
        this.stockServiceImpl=stockServiceImpl;
        this.placeServiceImpl=placeServiceImpl;
    }

    @PostMapping("/products")
    public Product saveProduct(@Valid @RequestBody ProductDTO dto) {
        Category category = categoryServiceImpl.findCategoryById(dto.getCategory_id()).orElseThrow();
        Place place = placeServiceImpl.findPlaceById(dto.getPlace_id()).orElseThrow();
        Stock stock = stockServiceImpl.findStockById(dto.getStock_id()).orElseThrow();

            Product product = new Product();
            product.setCode(dto.getCode());
            product.setName(dto.getName());
            product.setUnityPrice(dto.getUnityPrice());
            product.setCategory(category);
            product.setPlace(place);
            product.setStock(stock);

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
