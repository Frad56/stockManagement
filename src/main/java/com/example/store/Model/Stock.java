package com.example.store.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "stock")
public class Stock {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private  Long stockId;

    @NotNull
    @Column(name = "name")
    private String name;

    public Stock(){}
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }
}
