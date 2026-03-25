package com.example.store.Controller.stockMangment;


import com.example.store.DTO.stockManagment.ProductVariantDTO;
import com.example.store.Model.StockMangement.ProductVariant;
import com.example.store.Service.stockManagment.interfaces.ProductVariantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/productVariant")
public class ProductVariantController {
    private final ProductVariantService productVariantService;
    public ProductVariantController(ProductVariantService productVariantService) {
        this.productVariantService = productVariantService;
    }

    @PostMapping("/addProductVariant")
    public ResponseEntity<ProductVariant> saveProductVariant(@RequestBody  ProductVariantDTO productVariantDTO) {
        return ResponseEntity.ok(productVariantService.saveProductVariant(productVariantDTO));
    }
    @GetMapping("/ListProductVariants")
    public ResponseEntity<List<ProductVariant>> fetchProductVariantList() {
        return ResponseEntity.ok(productVariantService.fetchProductVariantList());
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ProductVariant> findProductVariantById(@PathVariable("id") Long productVariantId) {
        return ResponseEntity.ok(productVariantService.findProductVariantById(productVariantId));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String,String>> deleteProductVariantById(@PathVariable("id") Long productVariantId) {
        productVariantService.deleteProductVariantById(productVariantId);
        return ResponseEntity.ok(Map.of("message","Deleted Successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductVariant> updateProductVariant(@RequestBody ProductVariantDTO productVariantDTO, @PathVariable("id") Long productVariantId) {
        return ResponseEntity.ok(productVariantService.updateProductVariant(productVariantDTO, productVariantId));
    }
}
