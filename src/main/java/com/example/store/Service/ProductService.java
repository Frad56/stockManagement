package com.example.store.Service;


import com.example.store.Model.Product;

import java.util.List;


public interface ProductService {

   Product saveProduct(Product product);

   List<Product> fetchProductList();

   //Product updateProduct(Product product, Long productId);

   void deleteProductById(Long productID);

}
