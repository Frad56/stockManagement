package com.example.store.Repository;

import com.example.store.Model.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {

    Optional<Place> findPlaceByName(String name);
}
