package com.example.store.Service;

import com.example.store.Exception.ElementNotFoundException;
import com.example.store.Model.Place;
import com.example.store.Repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceServiceImpl implements  PlaceService{


    private PlaceRepository placeRepository;

    @Autowired
    private PlaceServiceImpl(PlaceRepository placeRepository){
        this.placeRepository=placeRepository;
    }

    //add
    @Override
    public Place savePlace(Place place){
        return  placeRepository.save(place);
    }

    //Read
    @Override
    public List<Place> fetchPlaceList(){
        return  placeRepository.findAll();
    }

    //delete
    @Override
    public void deletePlaceById(Long placeId){
        if (!placeRepository.existsById(placeId)){
            throw new ElementNotFoundException(placeId);
        }
        placeRepository.deleteById(placeId);

    }

    @Override
    public Place findPlaceById(Long place_id){
        return placeRepository.findById(place_id).orElseThrow(()
                -> new ElementNotFoundException(place_id));
    }


}
