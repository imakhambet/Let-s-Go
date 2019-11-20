package cz.macha.spring.rest;

import cz.macha.spring.model.Event;
import cz.macha.spring.model.Place;
import cz.macha.spring.service.CategoryService;
import cz.macha.spring.service.EventService;
import cz.macha.spring.service.OrganizerService;
import cz.macha.spring.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventController {

    @Autowired
    private OrganizerService organizerService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private PlaceService placeService;

    @Autowired
    private EventService eventService;

    @RequestMapping("/organizers/{id}/events")
    public List<Event> getEventsByOrganizer(@PathVariable Integer id) {
        return eventService.getEventsByOrganizer(organizerService.getOrganizer(id));
    }

    @RequestMapping("/categories/{name}/events")
    public List<Event> getEventsByCategory(@PathVariable String name) {
        return eventService.getEventsByCategory(categoryService.getCategoryByName(name));
    }

    @RequestMapping("/places/{name}/events")
    public List<Event> getEventsByPlace(@PathVariable String name) {
        return eventService.getEventsByPlace(placeService.getPlaceByName(name));
    }

    @RequestMapping("/events/{date}")
    public List<Event> getEventsByDate(@PathVariable String date) {
        return eventService.getEventsByDate(date);
    }

    @RequestMapping("/events")
    public List<Event> getAllEvents(){
        return eventService.getAllEvents();
    }

    @RequestMapping("/events/{id}")
    public Event getEvent(@PathVariable Integer id){
        return eventService.getEvent(id);
    }

    @RequestMapping("/events/name/{name}")
    public Event getEventByName(@PathVariable String name){
        return eventService.getEventByName(name);
    }

    @RequestMapping(method = RequestMethod.POST,
            value = "/organizers/{id}/categories/{name}/places/{place}/events")
    public void addEvent(@RequestBody Event event,
                         @PathVariable Integer id,
                         @PathVariable String name,
                         @PathVariable String place){
        event.setOrganizer(organizerService.getOrganizer(id));
        event.setCategory(categoryService.getCategoryByName(name));
        event.setPlace(placeService.getPlaceByName(place));
        eventService.addEvent(event);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/events/{id}")
    public void updateUser(@RequestBody Event event, @PathVariable Integer id){
        eventService.updateEvent(id, event);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/events/{id}")
    public void deleteUser(@PathVariable Integer id){
        eventService.deleteEvent(id);
    }
}
