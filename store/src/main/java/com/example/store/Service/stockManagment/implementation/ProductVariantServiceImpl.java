package com.example.store.Service.stockManagment.implementation;


import com.example.store.DTO.stockManagment.ProductVariantDTO;
import com.example.store.Exception.ElementAlreadyExistException;
import com.example.store.Exception.ElementNotFoundException;
import com.example.store.Exception.ResourceInUseException;
import com.example.store.Model.StockMangement.Aisle;
import com.example.store.Model.StockMangement.Product;
import com.example.store.Model.StockMangement.ProductUnitSale;
import com.example.store.Model.StockMangement.ProductVariant;
import com.example.store.Repository.StockManagment.CharacteristicValueRepository;
import com.example.store.Repository.StockManagment.ProductVariantRepository;
import com.example.store.Service.stockManagment.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductVariantServiceImpl implements ProductVariantService {

    private final ProductVariantRepository productVariantRepository;
    private final ProductService productService;
    private final CharacteristicValueRepository characteristicValueRepository;




     public ProductVariantServiceImpl(ProductVariantRepository productVariantRepository,
                                      ProductService productService,
                                      CharacteristicValueRepository characteristicValueRepository){
         this.productVariantRepository = productVariantRepository;
         this.productService = productService;
         this.characteristicValueRepository = characteristicValueRepository;
     }



    private void mapDTOToVariant(ProductVariantDTO dto, ProductVariant variant) {
        variant.setCode(dto.getCode());
        variant.setSpecificPrice(dto.getSpecificPrice());
        variant.setQuantityInStock(dto.getQuantityInStock());
        variant.setProduct(productService.findProductById(dto.getProductId()));

    }
    @Override
    public ProductVariant saveProductVariant(ProductVariantDTO productVariantDTO) {
        String newProductReference = productVariantDTO.getCode().trim().toLowerCase();
        if(productVariantRepository.findByCode(newProductReference).isPresent()){
            throw new ElementAlreadyExistException("the Product Code ",productVariantDTO.getCode());
        }
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
         boolean productVariantInUse = characteristicValueRepository.existsByProductVariant_ProductVariantId(productVariantId);

         if(productVariantInUse){
             throw new ResourceInUseException("This Product Variant is already used and cannot be deleted");
        }
         productVariantRepository.deleteById(productVariantId);

    }

    @Override
    public boolean productHasVariants(Long productId){
        return  productVariantRepository.existsByProduct_ProductId(productId);
    }

    @Override
    public List<ProductVariant> findByProduct_ProductId(Long productId){
        if(!productVariantRepository.existsByProduct_ProductId(productId)){
            throw new ElementNotFoundException("No variants found for product id: " + productId );
        }
        return productVariantRepository.findByProduct_ProductId(productId);
    }



}
