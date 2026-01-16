package com.example.store.Service;

import com.example.store.Model.Place;

import java.util.List;
import java.util.Optional;

public interface PlaceService {

    Place savePlace(Place place);

    List<Place> fetchPlaceList();

    Optional<Place> findPlaceById(Long place_id);
    void deletePlaceById(Long placeId);
}
