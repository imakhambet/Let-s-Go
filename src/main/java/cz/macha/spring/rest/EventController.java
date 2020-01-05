package cz.macha.spring.rest;

import cz.macha.spring.model.Event;
//import cz.macha.spring.service.CategoryService;
import cz.macha.spring.service.EventService;
import cz.macha.spring.service.PlaceService;
import cz.macha.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@RestController
public class EventController {

    @Autowired
    private UserService organizerService;
    //    @Autowired
//    private CategoryService categoryService;
    @Autowired
    private PlaceService placeService;

    @Autowired
    private EventService eventService;

    @RequestMapping("/organizers/{id}/events")
    public List<Event> getEventsByOrganizer(@PathVariable Integer id) {
        return eventService.getEventsByOrganizer(organizerService.getUser(id));
    }

    @RequestMapping("/event/{id}")
    public ModelAndView getEvent(@PathVariable Integer id, Map<String, Object> model, Authentication authentication) {
        if(authentication != null) {
            model.put("name", "<h3>Username: " + authentication.getName() + "</h3>");
            model.put("logout", "<a href=\"/logout\">Logout</a>\n");

            if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ORGANIZER"))) {

                model.put("role", "<h3>Organizer page</h3>");
                if(organizerService.getUserByLogin(authentication.getName()).
                        equals(eventService.getEvent(id).getOrganizer()))
                    model.put("createticket", "<a href=\"/createticket/"+id+"\">Add ticket</a>");
            }
            if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))){
                model.put("userticket", "<label for=\"quantity\">Quantity</label>\n" +
                        "<input type=\"number\" id=\"quantity\" name=\"quantity\">" +
                        "<button type=\"submit\">Buy ticket</button>\n");
            }
        }else{
            model.put("login", "<a href=\"/login\">Login</a>\n");
            model.put("registration", "<a href=\"/registration\">Registration</a>\n");
            model.put("userticket", "<p style=\"color: red\">Please login as User</p>");
        }
        model.put("event", eventService.getEvent(id));
        model.put("tickets", eventService.getEvent(id).getEventTickets());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("event");
        return modelAndView;
    }
//    @RequestMapping("/categories/{name}/events")
//    public List<Event> getEventsByCategory(@PathVariable String name) {
//        return eventService.getEventsByCategory(categoryService.getCategoryByName(name));
//    }

    @RequestMapping("/place/{id}/events")
    public ModelAndView getEventsByPlace(@PathVariable int id,  Map<String, Object> model) {

        List<Event> events = eventService.getEventsByPlace(placeService.getPlaceById(id));
        model.put("place", placeService.getPlaceById(id).getName());
        model.put("events", events);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("eventsbyplace");
        return modelAndView;
    }

    @RequestMapping("/events/date/{date}")
    public List<Event> getEventsByDate(@PathVariable String date) {
        return eventService.getEventsByDate(date);
    }

    @RequestMapping("/events")
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }


    @RequestMapping("/events/name/{name}")
    public Event getEventByName(@PathVariable String name) {
        return eventService.getEventByName(name);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/events/{id}")
    public void updateEvent(@RequestBody Event event, @PathVariable Integer id) {
        eventService.updateEvent(id, event);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/events/{id}")
    public void deleteEvent(@PathVariable Integer id) {
        eventService.deleteEvent(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/events/{id}/places/{pId}")
    public void setPlaceForEvent(@PathVariable Integer id, @PathVariable Integer pId) {
        eventService.setPlace(id, placeService.getPlaceById(pId));
    }
}
