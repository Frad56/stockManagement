package com.example.store.Service.stockManagment.implementation;

import com.example.store.DTO.stockManagment.ShelfDTO;
import com.example.store.Model.StockMangement.Aisle;
import com.example.store.Model.StockMangement.Shelf;
import com.example.store.Repository.StockManagment.ShelfRepository;
import com.example.store.Service.stockManagment.interfaces.AisleService;
import com.example.store.Service.stockManagment.interfaces.ProductService;
import com.example.store.Service.stockManagment.interfaces.ShelfService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ShelfServiceImpl implements ShelfService {

    private final ShelfRepository shelfRepository;

    private final AisleService  aisleService;

    public ShelfServiceImpl(ShelfRepository shelfRepository,
                            AisleService aisleService) {
        this.shelfRepository = shelfRepository;
        this.aisleService = aisleService;
    }

    private void mapShelfDTOToShelf(ShelfDTO shelfDTO, Shelf shelf) {
        shelf.setShelfNameByAisle(shelfDTO.getShelfNameByAisle());
        shelf.setAisle(aisleService.findAisleById(shelfDTO.getAisleId()));
    }

    @Override
    public Shelf saveShelf(ShelfDTO shelfDTO) {
        Shelf shelf = new Shelf();
        mapShelfDTOToShelf(shelfDTO,shelf);

        return shelfRepository.save(shelf);
    }

    @Override
    public Shelf findShelfById(Long shelfId) {
        return shelfRepository.findById(shelfId).orElseThrow(()->
                new RuntimeException("Shelf not found with id: " + shelfId));
    }

    @Override
    public List<Shelf> fetchShelfList() {
        return shelfRepository.findAll();
    }

    @Override
    public Shelf updateShelf(ShelfDTO shelfDTO, Long shelfId) {
        Shelf shelf = findShelfById(shelfId);
        mapShelfDTOToShelf(shelfDTO,shelf);

        return shelfRepository.save(shelf);
    }

    @Override
    public void deleteShelfById(Long shelfId) {
        if(!shelfRepository.existsById(shelfId)){
            throw new RuntimeException("Shelf not found with id: " + shelfId);
        }
        shelfRepository.deleteById(shelfId);
    }



}
