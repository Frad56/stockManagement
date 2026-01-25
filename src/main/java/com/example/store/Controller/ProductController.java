package com.example.store.Controller;


import com.example.store.DTO.ProductDTO;
import com.example.store.Model.Category;
import com.example.store.Model.Place;
import com.example.store.Model.Product;
import com.example.store.Model.Stock;
import com.example.store.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {


    private ProductService productService;
    private CategoryService categoryService;
    private StockService stockService;
    private PlaceService placeService;


    @Autowired
    public ProductController(ProductService productService,
                             CategoryService categoryService,
                             StockService stockService,
                             PlaceService placeService){
        this.productService =productService;
        this.categoryService = categoryService;
        this.stockService = stockService;
        this.placeService = placeService;
    }

    @PostMapping("/products")
    public Product saveProduct(@Valid @RequestBody ProductDTO dto) {
        Category category = categoryService.findCategoryById(dto.getCategory_id()).orElseThrow();
        Place place = placeService.findPlaceById(dto.getPlace_id()).orElseThrow();
        Stock stock = stockService.findStockById(dto.getStock_id()).orElseThrow();

            Product product = new Product();
            product.setCode(dto.getCode());
            product.setName(dto.getName());
            product.setUnityPrice(dto.getUnityPrice());
            product.setCategory(category);
            product.setPlace(place);
            product.setStock(stock);

       return productService.saveProduct(product);
    }

    @GetMapping("/products")
    public List<Product> fetchProductList(){
        return  productService.fetchProductList();
    }

    @GetMapping("/products/find/{id}")
    public Optional<Product> findProductById(@PathVariable("id") Long productId){
        return productService.findProductById(productId);
    }

    @DeleteMapping("/products/{id}")
    public String deleteProductByID(@PathVariable("id") Long productId){
        productService.deleteProductById(productId);
        return "Deleted Successfully";
    }

}
