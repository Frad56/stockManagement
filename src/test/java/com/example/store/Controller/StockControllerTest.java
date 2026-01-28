package com.example.store.Controller;

import com.example.store.Model.Stock;
import com.example.store.Service.StockService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = StockController.class,
excludeAutoConfiguration = SecurityAutoConfiguration.class)
class StockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StockService stockService;

    @Test
    void saveStock() throws Exception{
        Stock stock = new Stock();
        stock.setStock_id(1L);
        stock.setName("S00");
        stock.setQuantity(20L);

        when(stockService.saveStock(any(Stock.class))).thenReturn(stock);

        mockMvc.perform(post("/stocks").contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(stock)))
                .andExpect(status().isOk());


    }

    @Test
    void fetchStockList() throws Exception{
        // ****** Stock_1 *****
        Stock stock_1 = new Stock();
        stock_1.setStock_id(1L);
        // ****** Stock_2 *****
        Stock stock_2 = new Stock();
        stock_2.setStock_id(1L);
        List<Stock> mockStockList = Arrays.asList(stock_1,stock_2);
        when(stockService.fetchStockList()).thenReturn(mockStockList);
        mockMvc.perform(get("/stocks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()",is(2)));

    }

    @Test
    void deleteStockById() throws Exception{
        Stock stock_1 = new Stock();
        stock_1.setStock_id(1L);
        willDoNothing().given(stockService).deleteStockById(stock_1.getStock_id());
        mockMvc.perform(delete("/stocks/{id}",1L).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Deleted Successfully"));
            }
}