package com.example.store.Repository;


import com.example.store.Model.Place;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class PlaceRepositoryTest {

    @Autowired
    private PlaceRepository placeRepository;

    @Test
    void PlaceRepository_ReturnSavedPlace(){
        Place place = new Place();
        place.setName("A001-2");

        Place savedPlace = placeRepository.save(place);

        assertThat(savedPlace).isNotNull();
        assertThat(savedPlace.getPlace_id()).isNotNull();
        assertThat(savedPlace.getName()).isEqualTo("A001-2");
    }

    @Test
    public void  PlaceRepository_GetAll(){
        //********** Place 1 **********
        Place place = new Place();
        place.setName("A001-1");
        placeRepository.save(place);
        //********** Place 1 **********

        Place place2 = new Place();
        place2.setName("B001-2");
        placeRepository.save(place2);

        List<Place> placeList = placeRepository.findAll();

        assertThat(placeList).isNotEmpty().extracting(Place::getName)
                .contains("A001-1","B001-2");

    }
    @Test
    public void PlaceRepository_FindById_ReturnNotNull(){
        Place place = new Place();
        place.setName("A001-2");

        placeRepository.save(place);

        Optional<Place> placeList = placeRepository.findById(place.getPlace_id());
        assertThat(placeList).isNotNull();
    }
    @Test
    public void PlaceRepository_FindByName_ReturnNotNull(){
        Place place = new Place();
        place.setName("A001-2");

        placeRepository.save(place);

        Optional<Place> placeList = placeRepository.findById(place.getPlace_id());
        assertThat(placeList).isNotNull();
    }

    @Test
    void PlaceRepository_UpdatePlace(){
        Place place = new Place();
        place.setName("A001-2");

        placeRepository.save(place);

        Place placeSave = placeRepository.findPlaceByName(place.getName()).get();
        placeSave.setName("B847-1");

        Place updatePlace = placeRepository.save(placeSave);

        assertThat(updatePlace.getName()).isNotNull();
    }

    @Test
    void PlaceRepository_DeletePlace_ReturnPlaceIsEmpty(){
        Place place = new Place();
        place.setName("A001-2");

        placeRepository.save(place);

        placeRepository.deleteById(place.getPlace_id());

        Optional<Place> placeReturn = placeRepository.findById(place.getPlace_id());
        assertThat(placeReturn).isEmpty();

    }


}
