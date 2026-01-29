package com.example.store.Controller;


import com.example.store.Model.Place;
import com.example.store.Service.PlaceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlaceController {
    @Autowired
    private PlaceService placeService;

    @PostMapping("/places")
    public ResponseEntity<Place> savePlace(@Valid @RequestBody Place place){
        Place return_place  =placeService.savePlace(place);
        return ResponseEntity.ok(return_place);
    }

    @GetMapping("/places")
    public ResponseEntity < List<Place> > fetchPlaceList(){
        List<Place> places =placeService.fetchPlaceList();
        return ResponseEntity.ok(places);
    }

    @DeleteMapping("/places/{id}")
    public ResponseEntity<String> deletePlaceById(@PathVariable("id") Long placeId){
        placeService.deletePlaceById(placeId);
        return  ResponseEntity.ok("Deleted Successfully");
    }

    @GetMapping("/places/find/{id}")
    public ResponseEntity<Place> findPlaceById(@PathVariable("id") Long placeId){
        Place place = placeService.findPlaceById(placeId);
        return ResponseEntity.ok(place);
    }

}
