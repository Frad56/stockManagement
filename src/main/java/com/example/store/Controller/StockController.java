package com.example.store.Controller;


import com.example.store.Model.Stock;
import com.example.store.Service.StockService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class StockController {
    @Autowired
    private StockService stockService;

    @PostMapping("/stocks")
    public ResponseEntity<Stock> saveStock(@Valid @RequestBody Stock stock){
        Stock return_stock =stockService.saveStock(stock);
        return  ResponseEntity.ok(return_stock);
    }

    @GetMapping("/stocks")
    public ResponseEntity < List<Stock> > fetchStockList(){
        List<Stock> stock_list =stockService.fetchStockList();;
        return ResponseEntity.ok(stock_list);
    }

    @DeleteMapping("/stocks/{id}")
    public ResponseEntity<String> deleteStockById(@PathVariable("id") Long stockId){
        stockService.deleteStockById(stockId);
        return ResponseEntity.ok("Deleted Successfully");
    }
    @GetMapping("/stocks/find/{id}")
    public ResponseEntity<Stock> findStockById(@PathVariable("id") Long stockId){
        Stock stock =stockService.findStockById(stockId);
        return ResponseEntity.ok(stock);
    }

}
