package com.example.store.Service;

import com.example.store.Model.Place;
import com.example.store.Model.Product;
import com.example.store.Repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceServiceImpl implements  PlaceService{

    @Autowired
    private PlaceRepository placeRepository;

    //add
    @Override
    public Place savePlace(Place place){
        return  placeRepository.save(place);
    }

    //Read
    @Override
    public List<Place> fetchPlaceList(){
        return  (List<Place>) placeRepository.findAll();
    }

    //delete
    @Override
    public void deletePlaceById(Long placeId){
        placeRepository.deleteById(placeId);
    }


}
