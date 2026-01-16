package com.example.store.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "stock")
public class Stock {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private  Long stock_id;

    @NotNull
    @Column(name = "name")
    private String name;


    @NotNull
    @Column(name = "quantity")
    private Long quantity;

  //  @ManyToOne
    //@JoinColumn(name = "productId")
    //private  Product product;

    public Stock(){}
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getStock_id() {
        return stock_id;
    }

    public void setStock_id(Long stock_id) {
        this.stock_id = stock_id;
    }

    public @NotNull Long getQuantity() {
        return quantity;
    }

    public void setQuantity(@NotNull Long quantity) {
        this.quantity = quantity;
    }

    //  public Product getProduct() {
    //    return product;
    //}

   // public void setProduct(Product product) {
     //   this.product = product;
    //}
}
