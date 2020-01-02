package cz.macha.spring.rest;

import cz.macha.spring.model.Place;

import cz.macha.spring.service.PlaceService;
import cz.macha.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@RestController
public class PlaceController {
    @Autowired
    UserService adminService;

    @Autowired
    PlaceService placeService;

    @RequestMapping("/places")
    public List<Place> getAllPlaces() {
        return placeService.getAllPlaces();
    }

    @RequestMapping("/places/name/{name}")
    public Place getPlaceByName(@PathVariable String name) {
        return placeService.getPlaceByName(name);
    }

    @PostMapping("/addplace")
    public ModelAndView addPlace(@RequestParam String name,
                                 @RequestParam String address,
                                 Authentication auth){
        Place place = new Place(name, adminService.getUserByLogin(auth.getName()), address);
        placeService.addPlace(place);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin");
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/places/{id}")
    public void updatePlace(@RequestBody Place place, @PathVariable Integer id){
        placeService.updatePlace(id, place);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/places/{id}")
    public void deletePlace(@PathVariable Integer id){
        placeService.deletePlace(id);
    }

}
