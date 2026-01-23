package com.example.store.Service;

import com.example.store.Model.Place;
import com.example.store.Repository.PlaceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class PlaceServiceImplTest {

    @Mock
    private PlaceRepository placeRepository;

    @InjectMocks
    private PlaceServiceImpl placeService;

    @Test
    void fetchPlaceList() {

        Place place_1 = new Place();
        place_1.setName("Place_A");
       // placeRepository.save(place);

        Place place_2 = new Place();
        place_2.setName("Place_B");

        List<Place> mockPlace = Arrays.asList(place_1,place_2);
        when(placeRepository.findAll()).thenReturn(mockPlace);

        List<Place> result = placeService.fetchPlaceList();

        assertEquals(2,result.size());
        assertEquals("Place_A",result.get(0).getName());
        assertEquals("Place_B",result.get(1).getName());

        //pour verifier que cette method  " findAll" a été appelée
        verify(placeRepository).findAll();


    }

    @Test
    void whenSavePlaceFailed_ShouldThrowException(){
        Place place = new Place();
        place.setName("P001");

        when(placeRepository.save(any(Place.class))).thenThrow(
                new RuntimeException("DB error")
        );

        RuntimeException exception= assertThrows(RuntimeException.class,()-> placeService.savePlace(place));
        assertEquals("DB error",exception.getMessage());
        verify(placeRepository).save(place);
    }

    @Test
    void savePlace() {
    }

    @Test
    void deletePlaceById() {
    }

    @Test
    void findPlaceById() {
    }
}