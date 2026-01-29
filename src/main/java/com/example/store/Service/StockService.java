package com.example.store.Service;

import com.example.store.Model.Stock;

import java.util.List;

public interface StockService {

    Stock saveStock(Stock stock);

    List<Stock> fetchStockList();

    void deleteStockById(Long stockId);

    Stock findStockById(Long Stock);
}
