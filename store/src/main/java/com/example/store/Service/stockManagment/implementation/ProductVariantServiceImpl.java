package com.example.store.Service.stockManagment.implementation;


import com.example.store.DTO.stockManagment.ProductVariantDTO;
import com.example.store.Exception.ElementNotFoundException;
import com.example.store.Model.StockMangement.Aisle;
import com.example.store.Model.StockMangement.Product;
import com.example.store.Model.StockMangement.ProductVariant;
import com.example.store.Repository.StockManagment.ProductVariantRepository;
import com.example.store.Service.stockManagment.interfaces.AisleService;
import com.example.store.Service.stockManagment.interfaces.ProductService;
import com.example.store.Service.stockManagment.interfaces.ProductVariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductVariantServiceImpl implements ProductVariantService {

    private final ProductVariantRepository productVariantRepository;
    private final ProductService productService;



    @Autowired
     public ProductVariantServiceImpl(ProductVariantRepository productVariantRepository,
                                      ProductService productService){
         this.productVariantRepository = productVariantRepository;
         this.productService = productService;
     }



    private void mapDTOToVariant(ProductVariantDTO dto, ProductVariant variant) {
        variant.setCode(dto.getCode());
        variant.setSpecificPrice(dto.getSpecificPrice());
        variant.setQuantityInStock(dto.getQuantityInStock());
        variant.setProduct(productService.findProductById(dto.getProductId()));

    }
    @Override
    public ProductVariant saveProductVariant(ProductVariantDTO productVariantDTO) {
        ProductVariant productVariant = new ProductVariant();
        mapDTOToVariant(productVariantDTO,productVariant);

        return productVariantRepository.save(productVariant);

    }


    @Override
    public List<ProductVariant> fetchProductVariantList() {
        return  productVariantRepository.findAll();
    }


    @Override
    public ProductVariant findProductVariantById(Long idProductVariant) {
        return productVariantRepository.findById(idProductVariant).orElseThrow(()->
                new ElementNotFoundException(idProductVariant));
    }


    @Override
    public ProductVariant updateProductVariant(ProductVariantDTO productVariantDTO, Long productVariantId) {
        ProductVariant productVariantToUpdate = findProductVariantById(productVariantId);
        mapDTOToVariant(productVariantDTO, productVariantToUpdate);
        return productVariantRepository.save(productVariantToUpdate);

    }


    @Override
    public void deleteProductVariantById(Long productVariantId) {
         if(!productVariantRepository.existsById(productVariantId)){
             throw new ElementNotFoundException(productVariantId);
         }
         productVariantRepository.deleteById(productVariantId);

    }


}
