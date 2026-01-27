package com.example.store.Controller;

import com.example.store.DTO.ProductDTO;
import com.example.store.Model.Category;
import com.example.store.Model.Place;
import com.example.store.Model.Product;
import com.example.store.Model.Stock;
import com.example.store.Service.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(controllers = ProductController.class,
        excludeAutoConfiguration = SecurityAutoConfiguration.class
)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @MockBean
    private CategoryService categoryService;

    @MockBean
    private PlaceService placeService;

    @MockBean
    private StockService stockService;

    @Test
    void givenProductId_whenGetProduct_ThenReturnJson() throws Exception {
      Long productId = 1L;
      Product mock_product = new Product();

        Category category = new Category();
        category.setName("electro");

        Place place = new Place();
        place.setName("A001-2");

        Stock stock = new Stock();
        stock.setName("A001");
        stock.setQuantity(30L);
        mock_product.setProduct_id(1L);
        mock_product.setCode("112_Ta");
        mock_product.setName("tel203");
        mock_product.setUnityPrice(BigDecimal.valueOf(2003.300));
        mock_product.setCategory(category);
        mock_product.setPlace(place);
        mock_product.setStock(stock);


        given(productService.findProductById(productId)).willReturn(Optional.of(mock_product));

         mockMvc.perform(get("/products/find/{id}",productId)
              .contentType(MediaType.APPLICATION_JSON))
              .andExpect(status().isOk())
              .andExpect(jsonPath("$.product_id",is(productId.intValue())))
              .andExpect(jsonPath("$.name",is(mock_product.getName())))
              //to see response
              .andDo(print());

    }


    @Test
    void fetchProductList() throws Exception {
        //Given

        //*************** Product 1 **********************
        Product product_1 = new Product();

        Category category = new Category();
        category.setName("electro");

        Place place = new Place();
        place.setName("A001-2");

        Stock stock = new Stock();
        stock.setName("A001");
        stock.setQuantity(30L);

        product_1.setCode("112_Ta");
        product_1.setName("tel203");
        product_1.setUnityPrice(BigDecimal.valueOf(2003.300));
        product_1.setCategory(category);
        product_1.setPlace(place);
        product_1.setStock(stock);

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

        //When
        List<Product> mockProducts = Arrays.asList(product_1,product2);
        given(productService.fetchProductList()).willReturn(mockProducts);

        //then
        mockMvc.perform(get("/products").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()",is(2)))
                .andExpect(jsonPath("$[0].name").value(product_1.getName()))
                .andExpect(jsonPath("$[1].name").value(product2.getName()))
                .andExpect(jsonPath("$[0].code").value(product_1.getCode()))
                .andExpect(jsonPath("$[1].code").value(product2.getCode()))
                .andExpect(jsonPath("$[0].unityPrice").value(product_1.getUnityPrice()))
                .andExpect(jsonPath("$[1].unityPrice").value(product2.getUnityPrice()))
                .andExpect(jsonPath("$[0].category.name").value(product_1.getCategory().getName()))
                .andExpect(jsonPath("$.[1].category.name").value(product2.getCategory().getName()))
                .andDo(print());


    }

    @Test
    void deleteProductByID() throws Exception {
        Product mock_Product = new Product();
        Long mock_Product_id = 1L;
        mock_Product.setProduct_id(1L);

        willDoNothing().given(productService).deleteProductById(mock_Product_id);
        mockMvc.perform(delete("/products/{id}",mock_Product_id).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Deleted Successfully"));

    }

    @Test
    void SaveProduct() throws Exception{

        Category category =new Category();
        category.setId(1L);
        Place place = new Place();
        place.setPlace_id(1L);
        Stock stock = new Stock();
        stock.setStock_id(1L);

        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("P01");
        productDTO.setPlace_id(1L);
        productDTO.setCode("23abc");
        productDTO.setStock_id(1L);
        productDTO.setCategory_id(1L);

        Product SavedProduct = new Product();
        SavedProduct.setPlace(place);
        SavedProduct.setCategory(category);
        SavedProduct.setStock(stock);
        SavedProduct.setName(productDTO.getName());

       when(categoryService.findCategoryById(1L)).thenReturn(category);
       when(placeService.findPlaceById(1L)).thenReturn(Optional.of(place));
       when(stockService.findStockById(1L)).thenReturn(Optional.of(stock));

       when(productService.saveProduct(any(Product.class))).thenReturn(SavedProduct);

        mockMvc.perform(post("/products").contentType(MediaType.APPLICATION_JSON)
                //transformer l'object java au json pour le envoyer vert le requete Http
                .content(new ObjectMapper().writeValueAsString(productDTO)))
                .andExpect(status().isOk());

    }
}