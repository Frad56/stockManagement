package com.example.store.Service;

import com.example.store.Exception.ElementNotFoundException;
import com.example.store.Model.Place;
import com.example.store.Repository.PlaceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


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
        Place input_place = new Place();
        input_place.setName("A002");

        Place saved_place = new Place();
        saved_place.setName("A002");
        when(placeRepository.save(any(Place.class))).thenReturn(saved_place);

        Place result = placeService.savePlace(input_place);
        assertNotNull(result);

        assertEquals("A002",result.getName());

        verify(placeRepository).save(input_place);

    }

    @Test
    void findPlaceById() {
        Place mock_place = new Place();
        mock_place.setName("A002");

        Long id_mock_place = mock_place.getPlace_id();
        when(placeRepository.findById(id_mock_place)).thenReturn(java.util.Optional.of(mock_place));

        Place result = placeService.findPlaceById(id_mock_place);
        assertEquals(id_mock_place,result.getPlace_id());


    }


    @Test
    void deletePlaceById_WhenCategoryDoesNotExist_ShouldThrowException() {
        Long id = 1L;
        when(placeRepository.existsById(id)).thenReturn(false);
        ElementNotFoundException exception = assertThrows(
                ElementNotFoundException.class ,() ->placeService.deletePlaceById(id)
        );
    }

    @Test
    void deletePlaceById_WhenCategoryExists_ShouldDeleteSuccessfully(){
        Long id = 1L;

        when(placeRepository.existsById(id)).thenReturn(true);

        placeService.deletePlaceById(id);

        verify(placeRepository).existsById(id);
        verify(placeRepository).deleteById(id);

    }


}