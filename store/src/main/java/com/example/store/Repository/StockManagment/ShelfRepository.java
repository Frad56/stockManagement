package com.example.store.Repository.StockManagment;


import com.example.store.Model.StockMangement.Shelf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShelfRepository extends JpaRepository<Shelf, Long> {
}
