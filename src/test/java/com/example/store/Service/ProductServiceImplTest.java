package com.example.store.Service;

import com.example.store.Model.Product;
import com.example.store.Repository.CategoryRepository;
import com.example.store.Repository.PlaceRepository;
import com.example.store.Repository.ProductRepository;
import com.example.store.Repository.StockRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private StockRepository stockRepository;

    @Mock
    private PlaceRepository placeRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private ProductServiceImpl productService;


    @Test
    void fetchProductList() {

        //*************** Product 1 **********************
        Product product_1 = new Product();
        product_1.setName("tel203");
        //*************** Product 2 **********************
        Product product2 = new Product();
        product2.setName("tel205");


        List<Product> mockProducts = Arrays.asList(product_1,product2);
        when(productRepository.findAll()).thenReturn(mockProducts);

        List<Product> result = productService.fetchProductList();

        assertEquals(2,result.size());
        assertEquals("tel203",result.get(0).getName());

        verify(productRepository).findAll();

    }

    @Test
    void whenSaveProductFailed_ShouldThrowException(){
        Product mock_Product = new Product();
        mock_Product.setName("tel203");

        when(productRepository.save(any(Product.class)))
                .thenThrow(new RuntimeException("DB error"));

        RuntimeException exception = assertThrows(
                RuntimeException.class,() -> productService.saveProduct(mock_Product));

        assertEquals("DB error",exception.getMessage());

        verify(productRepository).save(mock_Product);
    }

    @Test
    void saveProduct() {
        Product input_Product = new Product();
        input_Product.setName("Laptop");

        Product savedProduct = new Product();
        savedProduct.setName("Laptop");

        when(productRepository.save(any(Product.class))).thenReturn(savedProduct);

        Product result = productService.saveProduct(input_Product);
        assertNotNull(result);
        assertEquals("Laptop",result.getName());

        verify(productRepository).save(input_Product);



    }

    @Test
    void findProductById(){

        Product mock_Product = new Product();
        mock_Product.setName("tel203");

        Long productId= mock_Product.getProduct_id();
        when(productRepository.findById(productId)).thenReturn(java.util.Optional.of(mock_Product));

        //when
        Optional<Product> foundProduct = productService.findProductById(productId);

        assertEquals(productId,foundProduct.get().getProduct_id());
    }
    @Test
    void deleteProductById() {
        Product mock_Product = new Product();
        Long productId = mock_Product.getProduct_id();

        //verifie que le produit existe et la suppression ne fait rien réel
        when(productRepository.existsById(productId)).thenReturn(true);
        doNothing().when(productRepository).deleteById(productId);

        productService.deleteProductById(productId);

        verify(productRepository).deleteById(productId);
    }
}