package com.example.store.Repository.StockManagment;

import com.example.store.Model.StockMangement.Aisle;
import com.example.store.Model.StockMangement.Product;
import com.example.store.Model.StockMangement.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByAisle(Aisle aisle);

}
