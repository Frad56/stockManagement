package com.example.store.Service.stockManagment.implementation;


import com.example.store.DTO.stockManagment.ProductDTO;
import com.example.store.Exception.ElementNotFoundException;
import com.example.store.Model.StockMangement.Aisle;
import com.example.store.Model.StockMangement.Category;
import com.example.store.Model.StockMangement.Product;
import com.example.store.Repository.StockManagment.ProductRepository;
import com.example.store.Service.stockManagment.interfaces.AisleService;
import com.example.store.Service.stockManagment.interfaces.CategoryService;
import com.example.store.Service.stockManagment.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final CategoryService categoryService;
    private  final AisleService aisleService;



    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,CategoryService categoryService,
                              AisleService aisleService){
        this.productRepository=productRepository;
        this.categoryService=categoryService;
        this.aisleService=aisleService;

    }

    private void mapDTOToProduct(ProductDTO dto, Product product) {
        product.setReference(dto.getReference());
        product.setDesignation(dto.getDesignation());
        product.setBrand(dto.getBrand());
        product.setDescription(dto.getDescription());
        product.setBasePrice(dto.getBasePrice());

        Category category =categoryService.findCategoryById(dto.getCategoryId());
        Aisle aisle = aisleService.findAisleById(dto.getAisleId());
        product.setCategory(category);
        product.setAisle(aisle);

    }

    @Override
    public Product saveProduct(ProductDTO dto){
        Product product = new Product();
        mapDTOToProduct(dto,product);
        return productRepository.save(product);
    }


    @Override
    public List<Product> fetchProductList(){
        return  productRepository.findAll();
    }


    @Override
    public void deleteProductById(Long productId){
        if(!productRepository.existsById(productId)){
            throw new ElementNotFoundException(productId);
        }
        productRepository.deleteById(productId);
    }


   @Override
    public Product updateProduct(ProductDTO product, Long productId){
        Product productDB = findProductById(productId);
        mapDTOToProduct(product,productDB);

        return productRepository.save(productDB);

    }


    @Override
    public Product findProductById(Long productId){
        return productRepository.findById(productId).orElseThrow(() ->
                new ElementNotFoundException(productId));
    }



}
