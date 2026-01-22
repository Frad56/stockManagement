package com.example.store.Repository;



import com.example.store.Model.Stock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
public class StockRepositoryTest {

    @Autowired
    private StockRepository stockRepository;

    //Arrange
    @Test
    void StockRepository_ReturnSavedStock () {
         Stock stock = new Stock();
         stock.setName("A001");
         stock.setQuantity(30L);
        //Act
        Stock savedStock = stockRepository.save(stock);

        //Assert
        assertThat(savedStock).isNotNull();
        assertThat(savedStock.getStock_id()).isNotNull();
        assertThat(savedStock.getName()).isEqualTo("A001");

    }
    @Test
    public void StockRepository_GetAll(){
        // ******* stock_1 *********
        Stock stock_1 = new Stock();
        stock_1.setName("A001");
        stock_1.setQuantity(30L);
        stockRepository.save(stock_1);

        // ******* stock_2 *********
        Stock stock_2 = new Stock();
        stock_2.setName("B001");
        stock_2.setQuantity(10L);

        stockRepository.save(stock_2);

        List<Stock> stockList = stockRepository.findAll();

        assertThat(stockList)
                .isNotEmpty()
                .extracting(Stock :: getName)
                .contains("A001","B001");
    }

    @Test
    public void StockRepository_findById_ReturNotNull(){
        Stock stock = new Stock();
        stock.setName("A001");
        stock.setQuantity(30L);
        stockRepository.save(stock);

        Optional<Stock> stockList = stockRepository.findById(stock.getStock_id());

        assertThat(stockList).isNotNull();

    }

    @Test
    void StockRepository_findbyName_ReturnNotNull(){
        Stock stock = new Stock();
        stock.setName("A001");
        stock.setQuantity(30L);
        stockRepository.save(stock);

        Optional<Stock> stockList = stockRepository.findByName(stock.getName());

        assertThat(stockList).isNotNull();

    }

    @Test
    void  StockRepository_UpdateStock(){

        Stock stock = new Stock();
        stock.setName("A001");
        stock.setQuantity(30L);
        stockRepository.save(stock);

        Stock stockSave = stockRepository.findByName(stock.getName()).get();
        stockSave.setName("A002");

        Stock updateStock = stockRepository.save(stockSave);
        assertThat(updateStock).isNotNull();

    }

    @Test
    void StockRepository_DeleteStock_ReturnStockIsEmpty(){
        Stock stock = new Stock();
        stock.setName("A001");
        stock.setQuantity(30L);
        stockRepository.save(stock);

        stockRepository.deleteById(stock.getStock_id());

        Optional<Stock> stockReturn =stockRepository.findById(stock.getStock_id());
        assertThat(stockReturn).isEmpty();
        }

}
