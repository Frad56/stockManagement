package com.example.store.Controller;


import com.example.store.Model.Stock;
import com.example.store.Service.StockServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class StockController {
    @Autowired
    private StockServiceImpl stockServiceImpl;

    @PostMapping("/stocks")
    public Stock saveStock(@Valid @RequestBody Stock stock){
        return  stockServiceImpl.saveStock(stock);
    }

    @GetMapping("/stocks")
    public List<Stock> fetchStockList(){
        return stockServiceImpl.fetchStockList();
    }

    @DeleteMapping("/stocks/{id}")
    public String deleteStockById(@PathVariable("id") Long stockId){
        stockServiceImpl.deleteStockById(stockId);
        return "Deleted Sucessfully";
    }
}
