package com.example.store.Controller.stockMangment;


import com.example.store.DTO.stockManagment.ShelfDTO;
import com.example.store.Model.StockMangement.Shelf;
import com.example.store.Service.stockManagment.interfaces.ShelfService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/shelf")
public class ShelfController {
    private final ShelfService shelfService;

    public ShelfController(ShelfService shelfService) {
        this.shelfService = shelfService;
    }
    @PostMapping("/addShelf")
    public ResponseEntity<Shelf> saveShelf(@RequestBody  ShelfDTO shelfDTO) {
        return ResponseEntity.ok(shelfService.saveShelf(shelfDTO));
    }
    @GetMapping("/ListShelves")
    public ResponseEntity<List<Shelf>> fetchShelfList() {
        return ResponseEntity.ok(shelfService.fetchShelfList());
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Shelf> findShelfById(@PathVariable("id") Long shelfId) {
        return ResponseEntity.ok(shelfService.findShelfById(shelfId));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String,String>> deleteShelfById(@PathVariable("id") Long shelfId) {
        shelfService.deleteShelfById(shelfId);
        return ResponseEntity.ok(Map.of("message","Deleted Successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Shelf> updateShelf(@RequestBody  ShelfDTO shelfDTO,@PathVariable("id") Long shelfId) {
        return ResponseEntity.ok(shelfService.updateShelf(shelfDTO, shelfId));
    }


}
