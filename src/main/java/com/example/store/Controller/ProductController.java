package com.example.store.Controller;


import com.example.store.DTO.ProductDTO;
import com.example.store.Model.Category;
import com.example.store.Model.Place;
import com.example.store.Model.Product;
import com.example.store.Model.Stock;
import com.example.store.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {


    private final ProductService productService;
    private final CategoryService categoryService;
    private final StockService stockService;
    private  final PlaceService placeService;


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
    public ResponseEntity<Product> saveProduct(@Valid @RequestBody ProductDTO dto) {
        Category category = categoryService.findCategoryById(dto.getCategory_id());
        Place place = placeService.findPlaceById(dto.getPlace_id());
        Stock stock = stockService.findStockById(dto.getStock_id());

            Product product = new Product();
            product.setCode(dto.getCode());
            product.setName(dto.getName());
            product.setUnityPrice(dto.getUnityPrice());
            product.setCategory(category);
            product.setPlace(place);
            product.setStock(stock);
        Product return_product = productService.saveProduct(product);
       return ResponseEntity.ok(return_product);
    }

    @GetMapping("/products")
    public ResponseEntity< List<Product> >fetchProductList(){
        List<Product> products =productService.fetchProductList();;
        return ResponseEntity.ok(products);
    }

    @GetMapping("/products/find/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable("id") Long productId){
        Product product =  productService.findProductById(productId);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProductByID(@PathVariable("id") Long productId){
        productService.deleteProductById(productId);
        return ResponseEntity.ok("Deleted Successfully");
    }

}
