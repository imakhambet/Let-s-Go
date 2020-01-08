package cz.macha.spring.rest;

import cz.macha.spring.model.Event;
//import cz.macha.spring.service.CategoryService;
import cz.macha.spring.service.CategoryService;
import cz.macha.spring.service.EventService;
import cz.macha.spring.service.PlaceService;
import cz.macha.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private PlaceService placeService;

    @Autowired
    private EventService eventService;

    @PreAuthorize("hasRole('ROLE_ORGANIZER')")
    @RequestMapping("/myevents")
    public ModelAndView getEventsByOrganizer(Map<String, Object> model,
                                         Authentication authentication) {
        if (authentication != null) {
            model.put("name", "<li><a href=\"#\" id=\"authName\">" + authentication.getName() + "</a></li>");
            model.put("logout", "<li id=\"logout\"><a href=\"/logout\">Logout</a></li>");

            if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ORGANIZER"))) {
                model.put("link2", "<li><a href=\"/myevents\" id=\"myEvents\">My events</a></li>");
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
        List<Event> events = eventService.getEventsByOrganizer(organizerService.getUser(organizerService.getUserByLogin(authentication.getName()).getId()));
        model.put("events", events);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("organizer/organizerevents");
        return modelAndView;
    }

    @RequestMapping("/event/{id}")
    public ModelAndView getEvent(@PathVariable Integer id, Map<String, Object> model, Authentication authentication) {
        if(authentication != null) {
            model.put("name", "<li><a href=\"#\" id=\"authName\">" + authentication.getName() + "</a></li>");
            model.put("logout", "<li id=\"logout\"><a href=\"/logout\">Logout</a></li>");

            if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ORGANIZER"))) {
                model.put("link2", "<li><a href=\"/myevents\" id=\"myEvents\">My events</a></li>");

                model.put("link", "<li id=\"link\"><a href=\"/createevent\">Add new event</a></li>");
                if(organizerService.getUserByLogin(authentication.getName()).
                        equals(eventService.getEvent(id).getOrganizer())) {
                    model.put("createticket", "<a href=\"/createticket/" + id + "\" id=\"addticket\">Add ticket</a>");
                    model.put("addCtgr", "<span id=\"addCatLink\"><a href=\"/addcategory/" + id + "\" id=\"addCtgr\">Add category</a></span>");
                }
            }
            if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
                model.put("link", "<li id=\"link\"><a href=\"/admin\">Admin page </a></li>");
            }

            if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))){
                model.put("link", "<li id=\"link\"><a href=\"/myorders\">My orders</a></li>");
                model.put("userticket", "<label for=\"quantity\">Quantity</label>\n" +
                        "<input type=\"number\" id=\"quantity\" name=\"quantity\">" +
                        "<button type=\"submit\" id=\"buyticket\">Buy</button>\n");
            }
        }else{
            model.put("login", "<li id=\"login\"><a href=\"/login\">Login</a></li>");
            model.put("registration", "<li id=\"registration\"><a href=\"/registration\">Registration</a></li>");
            model.put("userticket", "<p style=\"color: red\">Please login as User</p>");
        }
        model.put("event", eventService.getEvent(id));
        model.put("tickets", eventService.getEvent(id).getEventTickets());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("event");
        return modelAndView;
    }


    @RequestMapping("/place/{id}/events")
    public ModelAndView getEventsByPlace(@PathVariable int id,  Map<String, Object> model,
                                         Authentication authentication) {
        if (authentication != null) {
            model.put("name", "<li><a href=\"#\" id=\"authName\">" + authentication.getName() + "</a></li>");
            model.put("logout", "<li id=\"logout\"><a href=\"/logout\">Logout</a></li>");

            if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ORGANIZER"))) {
                model.put("link2", "<li><a href=\"/myevents\" id=\"myEvents\">My events</a></li>");
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
        List<Event> events = eventService.getEventsByPlace(placeService.getPlaceById(id));
        model.put("place", placeService.getPlaceById(id).getName());
        model.put("events", events);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("eventsbyplace");
        return modelAndView;
    }

    @RequestMapping("/category/{id}/events")
    public ModelAndView getEventsByCategory(@PathVariable int id,  Map<String, Object> model,
                                         Authentication authentication) {
        if (authentication != null) {
            model.put("name", "<li><a href=\"#\" id=\"authName\">" + authentication.getName() + "</a></li>");
            model.put("logout", "<li id=\"logout\"><a href=\"/logout\">Logout</a></li>");

            if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ORGANIZER"))) {
                model.put("link2", "<li><a href=\"/myevents\" id=\"myEvents\">My events</a></li>");
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
        List<Event> events = eventService.getEventsByCategory(categoryService.getCategory(id));
        model.put("category", categoryService.getCategory(id).getName().toLowerCase());
        model.put("events", events);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("eventsbycategory");
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

    @PreAuthorize("hasRole('ROLE_ORGANIZER')")
    @RequestMapping(value = "/deleteevent/{id}")
    public ModelAndView deleteEvent(@PathVariable Integer id) {
        eventService.deleteEvent(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/myevents");
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/events/{id}/places/{pId}")
    public void setPlaceForEvent(@PathVariable Integer id, @PathVariable Integer pId) {
        eventService.setPlace(id, placeService.getPlaceById(pId));
    }
}
