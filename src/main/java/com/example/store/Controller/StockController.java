package com.example.store.Controller;


import com.example.store.Model.Stock;
import com.example.store.Service.StockService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class StockController {
    @Autowired
    private StockService stockService;

    @PostMapping("/stocks")
    public Stock saveStock(@Valid @RequestBody Stock stock){
        return  stockService.saveStock(stock);
    }

    @GetMapping("/stocks")
    public List<Stock> fetchStockList(){
        return stockService.fetchStockList();
    }

    @DeleteMapping("/stocks/{id}")
    public String deleteStockById(@PathVariable("id") Long stockId){
        stockService.deleteStockById(stockId);
        return "Deleted Sucessfully";
    }
}
