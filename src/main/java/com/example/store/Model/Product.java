package com.example.store.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @NotNull
    @Size(max=100)
    @Column(name ="code")
    private String code;

    @NotNull
    @Column(name ="name")
    private String name;

    @NotNull
    @Column(name ="unityPrice",precision = 10, scale = 2)
    private BigDecimal unityPrice;


    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    @OneToOne
    @JoinColumn(name= "placeId")
    private  Place place;

    @ManyToOne
    @JoinColumn(name = "stockId")
    private  Stock stock;


    public Product(){}
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getUnityPrice() {
        return unityPrice;
    }

    public void setUnityPrice(BigDecimal unityPrice) {
        this.unityPrice = unityPrice;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }
}
