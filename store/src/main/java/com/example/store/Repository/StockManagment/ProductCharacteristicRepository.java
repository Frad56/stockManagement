package com.example.store.Repository.StockManagment;


import com.example.store.Model.StockMangement.ProductCharacteristic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductCharacteristicRepository  extends JpaRepository<ProductCharacteristic,Long> {
}
