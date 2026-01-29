package com.example.store.Service;


import com.example.store.Exception.ElementNotFoundException;
import com.example.store.Model.Stock;
import com.example.store.Repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockServiceImpl implements  StockService {

    StockRepository stockRepository;

    @Autowired
    private StockServiceImpl(StockRepository stockRepository){
        this.stockRepository =stockRepository;
    }

    //add
    @Override
    public Stock saveStock(Stock stock){
        return stockRepository.save(stock);
    }

    //Read
    @Override
    public List<Stock> fetchStockList(){
        return stockRepository.findAll();
    }

    @Override
    public void deleteStockById(Long stockId){
        if(!stockRepository.existsById(stockId)){
            throw  new ElementNotFoundException(stockId);
        }
        stockRepository.deleteById(stockId);
    }

    @Override
    public Stock findStockById(Long stock_id){
        return stockRepository.findById(stock_id).orElseThrow(() -> new ElementNotFoundException(stock_id));
    }
}
