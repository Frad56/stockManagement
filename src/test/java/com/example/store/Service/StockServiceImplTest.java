package com.example.store.Service;

import com.example.store.Model.Stock;
import com.example.store.Repository.StockRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StockServiceImplTest {

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private StockServiceImpl stockService;

    @Test
    void fetchStockList(){
        Stock stock_1 = new Stock();
        stock_1.setName("S001");
        stock_1.setQuantity(22L);


        Stock stock_2 = new Stock();
        stock_2.setName("S002");
        stock_2.setQuantity(12L);

        List<Stock>  mockStocks= Arrays.asList(stock_1,stock_2);
        when(stockRepository.findAll()).thenReturn(mockStocks);

        List<Stock> result = stockService.fetchStockList();

        assertEquals(2,result.size());
        assertEquals("S001",result.get(0).getName());
        assertEquals("S002",result.get(1).getName());

        verify(stockRepository).findAll();



    }

    @Test
    void whenSaveStockFailed_ShouldThrowException(){
        Stock stock = new Stock();

        when(stockRepository.save(any(Stock.class))).thenThrow(
                new RuntimeException("DB error")
        );
        RuntimeException exception = assertThrows(RuntimeException.class,()->stockService.saveStock(stock));
        assertEquals("DB error",exception.getMessage());

        verify(stockRepository).save(stock);
    }

}