package com.example.store.Service;


import com.example.store.Model.Product;
import com.example.store.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    //Add
    @Override
    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    //Read
    @Override
    public List<Product> fetchProductList(){
        return (List<Product>) productRepository.findAll();
    }

    //delete
    @Override
    public void deleteProductById(Long productId){
        productRepository.deleteById(productId);
    }
    //Update
  /*  @Override
    public Product updateProduct(Product product,Long productId){
        Product productDB = productRepository.findById(productId).get();

    }

   */



}
