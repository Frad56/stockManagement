package com.example.store.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long category_id;

    @NotNull
    @Column(name = "name")
    private String name;

    public Category(){}


    public Long getId() {
        return category_id;
    }

    public void setId(Long id) {
        this.category_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
