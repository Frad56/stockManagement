package com.example.store.Repository;


import com.example.store.Model.Category;
import com.example.store.Model.Place;
import com.example.store.Model.Product;
import com.example.store.Model.Stock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private  ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private  StockRepository stockRepository;

    @Autowired
    private  PlaceRepository placeRepository;

    @Test
    void ProductRepository_ReturnSavedProduct(){

        Product product = new Product();

        Category category = new Category();
        category.setName("electro");
        categoryRepository.save(category);

        Place place = new Place();
        place.setName("A001-2");
        placeRepository.save(place);


        Stock stock = new Stock();
        stock.setName("A001");
        stock.setQuantity(30L);
        stockRepository.save(stock);

        product.setCode("112_Ta");
        product.setName("tel203");
        product.setUnityPrice(BigDecimal.valueOf(2003.300));
        product.setCategory(category);
        product.setPlace(place);
        product.setStock(stock);



        Product savedProduct = productRepository.save(product);

        assertThat(savedProduct).isNotNull();
        assertThat(savedProduct.getProduct_id()).isNotNull();
    //Representation State Transfer

    }

    @Test
    public void ProductRepository_GetALL(){

        //*************** Product 1 **********************
        Product product = new Product();

        Category category = new Category();
        category.setName("electro");

        Place place = new Place();
        place.setName("A001-2");

        Stock stock = new Stock();
        stock.setName("A001");
        stock.setQuantity(30L);

        product.setCode("112_Ta");
        product.setName("tel203");
        product.setUnityPrice(BigDecimal.valueOf(2003.300));
        product.setCategory(category);
        product.setPlace(place);
        product.setStock(stock);

        stockRepository.save(stock);
        categoryRepository.save(category);
        placeRepository.save(place);
        productRepository.save(product);
        //*************** Product 2 **********************
        Product product2 = new Product();

        Category category2 = new Category();
        category2.setName("electro");

        Place place2 = new Place();
        place2.setName("A001-2");

        Stock stock2 = new Stock();
        stock2.setName("A002");
        stock2.setQuantity(20L);

        product2.setCode("112_TB");
        product2.setName("tel205");
        product2.setUnityPrice(BigDecimal.valueOf(394.870));
        product2.setCategory(category2);
        product2.setPlace(place2);
        product2.setStock(stock2);


        stockRepository.save(stock2);
        categoryRepository.save(category2);
        placeRepository.save(place2);
        productRepository.save(product2);
        List<Product> productList = productRepository.findAll();

        assertThat(productList).isNotEmpty()
                .extracting(Product::getCode)
                .contains("112_Ta","112_TB");
    }

    @Test
    public void ProductRepository_findById_ReturnNotNull(){
        Product product = new Product();

        Category category = new Category();
        category.setName("electro");

        Place place = new Place();
        place.setName("A001-2");

        Stock stock = new Stock();
        stock.setName("A001");
        stock.setQuantity(30L);

        product.setCode("112_Ta");
        product.setName("tel203");
        product.setUnityPrice(BigDecimal.valueOf(2003.300));
        product.setCategory(category);
        product.setPlace(place);
        product.setStock(stock);

        stockRepository.save(stock);
        categoryRepository.save(category);
        placeRepository.save(place);
        productRepository.save(product);

        Optional<Product> productList= productRepository.findById(product.getProduct_id());

        assertThat(productList).isNotNull();
    }


    @Test
    public void ProductRepository_findByName_ReturnNotNull(){
        Product product = new Product();

        Category category = new Category();
        category.setName("electro");

        Place place = new Place();
        place.setName("A001-2");

        Stock stock = new Stock();
        stock.setName("A001");
        stock.setQuantity(30L);

        product.setCode("112_Ta");
        product.setName("tel203");
        product.setUnityPrice(BigDecimal.valueOf(2003.300));
        product.setCategory(category);
        product.setPlace(place);
        product.setStock(stock);

        stockRepository.save(stock);
        categoryRepository.save(category);
        placeRepository.save(place);
        productRepository.save(product);

        Optional<Product> productList= productRepository.findByName(product.getName());

        assertThat(productList).isNotNull();
    }

    @Test
    void ProductRepository_UpdateProduct(){

        Product product = new Product();

        Category category = new Category();
        category.setName("electro");
        categoryRepository.save(category);

        Place place = new Place();
        place.setName("A001-2");
        placeRepository.save(place);

        Stock stock = new Stock();
        stock.setName("A001");
        stock.setQuantity(30L);
        stockRepository.save(stock);

        product.setCode("112_Ta");
        product.setName("tel203");
        product.setUnityPrice(BigDecimal.valueOf(2003.300));
        product.setCategory(category);
        product.setPlace(place);
        product.setStock(stock);

         productRepository.save(product);

        Product productSave = productRepository.findById(product.getProduct_id()).get();
        productSave.setName("vetements");
        productSave.setCode("58L");

        Product updateProduct = productRepository.save(productSave);

        assertThat(updateProduct.getName()).isNotNull();
        assertThat(updateProduct.getCode()).isNotNull();

    }
    @Test
    void ProductRepository_DeleteProduct_ReturnProductIsEmpty(){

        Product product = new Product();

        Category category = new Category();
        category.setName("electro");
        categoryRepository.save(category);

        Place place = new Place();
        place.setName("A001-2");
        placeRepository.save(place);

        Stock stock = new Stock();
        stock.setName("A001");
        stock.setQuantity(30L);
        stockRepository.save(stock);

        product.setCode("112_Ta");
        product.setName("tel203");
        product.setUnityPrice(BigDecimal.valueOf(2003.300));
        product.setCategory(category);
        product.setPlace(place);
        product.setStock(stock);

        productRepository.save(product);

         productRepository.deleteById(product.getProduct_id());
        Optional<Product> productReturn = productRepository.findById(product.getProduct_id());
        assertThat(productReturn).isEmpty();

    }

}
