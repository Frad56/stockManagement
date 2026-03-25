package com.example.store.Service.stockManagment.interfaces;


import com.example.store.DTO.stockManagment.ProductDTO;
import com.example.store.DTO.stockManagment.ProductVariantDTO;
import com.example.store.Model.StockMangement.Product;
import com.example.store.Model.StockMangement.ProductVariant;

import java.util.List;

public interface ProductVariantService {

    ProductVariant saveProductVariant(ProductVariantDTO productVariantDTO);

    List<ProductVariant> fetchProductVariantList();

    ProductVariant findProductVariantById(Long idProductVariant);

    ProductVariant updateProductVariant(ProductVariantDTO productVariantDTO, Long ProductVariantId);

    void deleteProductVariantById(Long ProductVariantId);


}
