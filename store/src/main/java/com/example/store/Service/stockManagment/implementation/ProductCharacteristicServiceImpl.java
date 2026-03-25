package com.example.store.Service.stockManagment.implementation;


import com.example.store.DTO.stockManagment.ProductCharacteristicDTO;
import com.example.store.Model.StockMangement.ProductCharacteristic;
import com.example.store.Repository.StockManagment.ProductCharacteristicRepository;
import com.example.store.Service.stockManagment.interfaces.CharacteristicService;
import com.example.store.Service.stockManagment.interfaces.ProductCharacteristicService;
import com.example.store.Service.stockManagment.interfaces.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCharacteristicServiceImpl implements ProductCharacteristicService {

    private final ProductCharacteristicRepository productCharacteristicRepository;
    private final ProductService productService;
    private final CharacteristicService characteristicService;

    public ProductCharacteristicServiceImpl(ProductCharacteristicRepository productCharacteristicRepository,
                                            ProductService productService
                                , CharacteristicService characteristicService) {
        this.productCharacteristicRepository = productCharacteristicRepository;
        this.productService = productService;
        this.characteristicService = characteristicService;
    }

    private void mapDTOToProductCharacteristic(ProductCharacteristicDTO productCharacteristicDTO, ProductCharacteristic productCharacteristic) {
        productCharacteristic.setProduct(productService.findProductById(productCharacteristicDTO.getProductId()));
        productCharacteristic.setCharacteristic(characteristicService.findCharacteristicById(productCharacteristicDTO.getCharacteristicId()));
    }
    @Override
    public ProductCharacteristic saveProductCharacteristic(ProductCharacteristicDTO productCharacteristic) {
        ProductCharacteristic productCharacteristicDB = new ProductCharacteristic();
        mapDTOToProductCharacteristic(productCharacteristic, productCharacteristicDB);
        return productCharacteristicRepository.save(productCharacteristicDB);
    }

    @Override
    public ProductCharacteristic findProductCharacteristicById(Long productCharacteristicId) {
        return productCharacteristicRepository.findById(productCharacteristicId).orElseThrow(() ->
                new RuntimeException("ProductCharacteristic not found with id: " + productCharacteristicId));
    }

    @Override
    public List<ProductCharacteristic> fetchProductCharacteristicList() {
        return productCharacteristicRepository.findAll();
    }

    @Override
    public ProductCharacteristic updateProductCharacteristic(ProductCharacteristicDTO productCharacteristic, Long productCharacteristicId) {
        ProductCharacteristic existingProductCharacteristic = findProductCharacteristicById(productCharacteristicId);
        mapDTOToProductCharacteristic(productCharacteristic, existingProductCharacteristic);
        return productCharacteristicRepository.save(existingProductCharacteristic);
    }

    @Override
    public void deleteProductCharacteristicById(Long productCharacteristicId) {
        if (!productCharacteristicRepository.existsById(productCharacteristicId)) {
            throw new RuntimeException("ProductCharacteristic not found with id: " + productCharacteristicId);
        }
        productCharacteristicRepository.deleteById(productCharacteristicId);
    }

}
