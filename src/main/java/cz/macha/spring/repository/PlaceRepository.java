package cz.macha.spring.repository;

import cz.macha.spring.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Integer> {
    Place getPlaceByName(String name);
}
