package com.example.store.Service;

import com.example.store.Model.Place;

import java.util.List;

public interface PlaceService {

    Place savePlace(Place place);

    List<Place> fetchPlaceList();

    void deletePlaceById(Long placeId);
}
