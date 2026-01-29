package com.example.store.Controller;

import com.example.store.Model.Place;
import com.example.store.Service.PlaceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(controllers = PlaceController.class,
excludeAutoConfiguration = SecurityAutoConfiguration.class)
class PlaceControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PlaceService placeService;
    @Test
    void savePlace() throws Exception {
        Place place =new Place();
        place.setPlace_id(1L);
        place.setName("P001");
        when(placeService.savePlace(any(Place.class))).thenReturn(place);

        mockMvc.perform(post("/places")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(place)))
                        .andExpect(status().isOk());
    }

    @Test
    void fetchPlaceList() throws Exception {
        //****** Place_1 *******
        Place place_1 =new Place();
        place_1.setPlace_id(1L);
        place_1.setName("P001");
        //  ****** Place_2 *****
        Place place_2 = new Place();
        place_2.setPlace_id(2L);
        place_2.setName("P002");
        List<Place> mockPlaceList = Arrays.asList(place_1,place_2);
        when(placeService.fetchPlaceList()).thenReturn(mockPlaceList);

        mockMvc.perform(get("/places").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()",is(2)))
                .andExpect(jsonPath("$[0].name").value(place_1.getName()))
                .andExpect(jsonPath("$[1].name").value(place_2.getName()));
    }

    @Test
    void deletePlaceById() throws Exception {
        Place place = new Place();
        place.setPlace_id(1L);

        willDoNothing().given(placeService).deletePlaceById(place.getPlace_id());

        mockMvc.perform(delete("/places/{id}",place.getPlace_id()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Deleted Successfully"));
    }
}