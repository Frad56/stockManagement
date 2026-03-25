package com.example.store.Repository.StockManagment;


import com.example.store.Model.StockMangement.CharacteristicValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CharacteristicValueRepository extends JpaRepository<CharacteristicValue, Long> {

}
