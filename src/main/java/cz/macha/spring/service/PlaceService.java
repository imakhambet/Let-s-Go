package cz.macha.spring.service;

import cz.macha.spring.repository.PlaceRepository;
import cz.macha.spring.model.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceService {

    @Autowired
    private PlaceRepository placeRepository;

    public List<Place> getAllPlaces(){
        return placeRepository.findAll();
    }

    public Place getPlaceByName(String name) {
        return placeRepository.getPlaceByName(name);
    }
    public Place getPlaceById(Integer id) {
        return placeRepository.findById(id).orElse(null); }

    public void addPlace(Place place){
        placeRepository.save(place);
    }

    public void updatePlace(Integer id, Place place){
        Place place1 = placeRepository.findById(id).orElse(null);
        assert place1 != null;
        place1.setName(place.getName());
        place1.setAddress(place.getAddress());

        placeRepository.save(place1);
    }

    public void deletePlace(Integer id){
        placeRepository.deleteById(id);
    }
}
