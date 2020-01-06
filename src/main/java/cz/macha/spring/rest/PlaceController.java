package cz.macha.spring.rest;

import cz.macha.spring.model.Place;

import cz.macha.spring.service.PlaceService;
import cz.macha.spring.service.UserService;
import cz.macha.spring.validation.PlaceValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;


@RestController
public class PlaceController {
    @Autowired
    UserService adminService;

    @Autowired
    PlaceService placeService;

    @RequestMapping("/places")
    public ModelAndView getAllPlaces(Authentication authentication, Map<String, Object> model) {
        List<Place> places = placeService.getAllPlaces();
        if (authentication != null) {
            model.put("name", "<li><a href=\"#\">" + authentication.getName() + "</a></li>");
            model.put("logout", "<li id=\"logout\"><a href=\"/logout\">Logout</a></li>");

            if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ORGANIZER"))) {
                model.put("link", "<li id=\"link\"><a href=\"/createevent\">Add new event</a></li>");
            }
            if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
                model.put("link", "<li id=\"link\"><a href=\"/admin\">Admin page </a></li>");
            }
            if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))) {
                model.put("link", "<li id=\"link\"><a href=\"/myorders\">My orders</a></li>");
            }
        } else {
            model.put("login", "<li id=\"login\"><a href=\"/login\">Login</a></li>");
            model.put("registration", "<li id=\"registration\"><a href=\"/registration\">Registration</a></li>");
        }
        model.put("places", places);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("places");
        return modelAndView;
    }

    @RequestMapping("/places/name/{name}")
    public Place getPlaceByName(@PathVariable String name) {
        return placeService.getPlaceByName(name);
    }

    @PostMapping("/addplace")
    public ModelAndView addPlace(@RequestParam String name,
                                 @RequestParam String address,
                                 Authentication auth, Map<String, Object> model){
        PlaceValidation validation = new PlaceValidation(placeService);
        validation.validation(name, address);
        List<String> placeErrors = validation.getErrors();

        ModelAndView modelAndView = new ModelAndView();

        if (placeErrors.size() == 0) {
            name = name.toLowerCase();
            address = address.toLowerCase();
            Place place = new Place(name, adminService.getUserByLogin(auth.getName()), address);
            placeService.addPlace(place);
            modelAndView.setViewName("redirect:/admin");
            return modelAndView;
        }
        StringBuilder errors = new StringBuilder();
        for (String error: placeErrors)
            errors.append("<p>").append(error).append("</p>");

        model.put("errors", errors.toString());
        modelAndView.setViewName("admin/createplace");
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
