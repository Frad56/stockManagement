package com.example.store.Service;


import com.example.store.Model.Stock;
import com.example.store.Repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockServiceImpl implements  StockService {

    @Autowired
    StockRepository stockRepository;

    //add
    @Override
    public Stock saveStock(Stock stock){
        return stockRepository.save(stock);
    }

    //Read
    @Override
    public List<Stock> fetchStockList(){
        return (List<Stock>) stockRepository.findAll();
    }

    @Override
    public void deleteStockById(Long stockId){
        stockRepository.deleteById(stockId);
    }
}
