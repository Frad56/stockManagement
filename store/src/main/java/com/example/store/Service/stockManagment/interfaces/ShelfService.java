package com.example.store.Service.stockManagment.interfaces;

import com.example.store.DTO.stockManagment.ShelfDTO;
import com.example.store.Model.StockMangement.Aisle;
import com.example.store.Model.StockMangement.Shelf;

import java.util.List;

public interface ShelfService {
        Shelf saveShelf(ShelfDTO shelf);
        Shelf findShelfById(Long shelfId);
        List<Shelf> fetchShelfList();
        Shelf updateShelf(ShelfDTO shelf, Long shelfId);
        void deleteShelfById(Long shelfId);
}
