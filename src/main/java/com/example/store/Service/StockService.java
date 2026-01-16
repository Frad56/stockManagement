package com.example.store.Service;

import com.example.store.Model.Stock;

import java.util.List;
import java.util.Optional;

public interface StockService {

    Stock saveStock(Stock stock);

    List<Stock> fetchStockList();

    void deleteStockById(Long stockId);

    Optional<Stock> findStockById(Long Stock);
}
