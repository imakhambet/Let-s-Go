package cz.macha.spring.service;

import cz.macha.spring.repository.PlaceRepository;
import cz.macha.spring.model.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PlaceService {

    @Autowired
    private PlaceRepository placeRepository;

    @Transactional(readOnly = true)
    public List<Place> getAllPlaces(){
        return placeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Place getPlaceByName(String name) {
        return placeRepository.getPlaceByName(name);
    }

    @Transactional(readOnly = true)
    public Place getPlaceById(Integer id) {
        return placeRepository.findById(id).orElse(null); }

    @Transactional
    public void addPlace(Place place){
        placeRepository.save(place);
    }

    @Transactional
    public void updatePlace(Integer id, Place place){
        Place place1 = placeRepository.findById(id).orElse(null);
        assert place1 != null;
        place1.setName(place.getName());
        place1.setAddress(place.getAddress());

        placeRepository.save(place1);
    }

    @Transactional
    public void deletePlace(Integer id){
        placeRepository.deleteById(id);
    }
}
