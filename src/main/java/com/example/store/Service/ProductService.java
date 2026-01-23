package com.example.store.Service;


import com.example.store.Model.Product;

import java.util.List;
import java.util.Optional;


public interface ProductService {

   Product saveProduct(Product product);

   List<Product> fetchProductList();

   Optional<Product> findProductById(Long idProduct);

   //Product updateProduct(Product product, Long productId);

   void deleteProductById(Long productID);

}
