package com.example.store.Controller;


import com.example.store.Model.Place;
import com.example.store.Service.PlaceServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlaceController {
    @Autowired
    private PlaceServiceImpl placeService;

    @PostMapping("/places")
    public Place savePlace(@Valid @RequestBody Place place){
        return placeService.savePlace(place);
    }

    @GetMapping("/places")
    public List<Place> fetchPlaceList(){
        return  placeService.fetchPlaceList();
    }

    @DeleteMapping("/places/{id}")
    public String deletePlaceById(@PathVariable("id") Long placeId){
        placeService.deletePlaceById(placeId);
        return  "Deleted Successfully";
    }
}
