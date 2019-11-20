package cz.macha.spring.service;

import cz.macha.spring.dao.PlaceDao;
import cz.macha.spring.model.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceService {

    @Autowired
    private PlaceDao placeDao;

    public List<Place> getAllPlaces(){
        return placeDao.findAll();
    }

    public Place getPlaceByName(String name) {
        return placeDao.getPlaceByName(name);
    }

    public void addPlace(Place place){
        placeDao.save(place);
    }

    public void updatePlace(Integer id, Place place){
        placeDao.findOne(id).setName(place.getName());
        placeDao.findOne(id).setAddress(place.getAddress());

        placeDao.save(placeDao.findOne(id));
    }

    public void deletePlace(Integer id){
        placeDao.delete(id);
    }
}
