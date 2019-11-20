package cz.macha.spring.dao;

import cz.macha.spring.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceDao extends JpaRepository<Place, Integer> {
    Place getPlaceByName(String name);
}
