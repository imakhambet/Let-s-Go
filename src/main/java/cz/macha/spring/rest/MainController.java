package cz.macha.spring.rest;

import cz.macha.spring.model.Category;
import cz.macha.spring.service.CategoryService;
import cz.macha.spring.service.EventService;
import cz.macha.spring.service.PlaceService;
import cz.macha.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class MainController {

    @Autowired
    EventService eventService;
    @Autowired
    UserService userService;
    @Autowired
    PlaceService placeService;
    @Autowired
    CategoryService categoryService;

    @RequestMapping("/")
    public String home(Map<String, Object> model, Authentication authentication) {
        if (authentication != null) {

            model.put("name", "<li><a href=\"#\" id=\"authName\">" + authentication.getName() + "</a></li>");
            model.put("logout", "<li id=\"logout\"><a href=\"/logout\">Logout</a></li>");

            if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ORGANIZER"))) {
                model.put("link", "<li id=\"link\"><a href=\"/createevent\">Add new event</a></li>");
                model.put("link2", "<li><a href=\"/myevents\" id=\"myEvents\">My events</a></li>");
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
        model.put("events", eventService.getAllEvents());
        return "index";
    }

    @PreAuthorize("hasRole('ROLE_ORGANIZER')")
    @RequestMapping("/createevent")
    public String addEvent(Map<String, Object> model, Authentication authentication) {
        model.put("name", "<li><a href=\"#\" id=\"authName\">" + authentication.getName() + "</a></li>");
        model.put("logout", "<li id=\"logout\"><a href=\"/logout\">Logout</a></li>");

        model.put("link", "<li id=\"link\"><a href=\"/createevent\">Add new event</a></li>");
        model.put("link2", "<li><a href=\"/myevents\" id=\"myEvents\">My events</a></li>");

        model.put("places", placeService.getAllPlaces());
        return "organizer/createevent";
    }

    @PreAuthorize("hasRole('ROLE_ORGANIZER')")
    @RequestMapping("/createticket/{id}")
    public String addTicket(Map<String, Object> model, Authentication authentication, @PathVariable Integer id) {
        model.put("name", "<li><a href=\"#\" id=\"authName\">" + authentication.getName() + "</a></li>");
        model.put("logout", "<li id=\"logout\"><a href=\"/logout\">Logout</a></li>");

        model.put("link", "<li id=\"link\"><a href=\"/createevent\">Add new event</a></li>");
        model.put("link2", "<li><a href=\"/myevents\" id=\"myEvents\">My events</a></li>");
        model.put("id", id);
        return "organizer/createticket";
    }

    @PreAuthorize("hasRole('ROLE_ORGANIZER')")
    @RequestMapping("/editcategoryEv/{id}")
    public String addCategoryEv(Map<String, Object> model, Authentication authentication, @PathVariable Integer id) {
        model.put("name", "<li><a href=\"#\" id=\"authName\">" + authentication.getName() + "</a></li>");
        model.put("logout", "<li id=\"logout\"><a href=\"/logout\">Logout</a></li>");

        model.put("link", "<li id=\"link\"><a href=\"/createevent\">Add new event</a></li>");
        model.put("link2", "<li><a href=\"/myevents\" id=\"myEvents\">My events</a></li>");

        model.put("category", eventService.getEvent(id).getCategory());
        model.put("categories", categoryService.getAllCategories());
        model.put("id", id);
        return "organizer/editcategory";
    }

    @RequestMapping("/admin")
    public String admin(Map<String, Object> model, Authentication auth) {
        model.put("name", "<li><a href=\"#\" id=\"authName\">" + auth.getName() + "</a></li>");
        model.put("logout", "<li id=\"logout\"><a href=\"/logout\">Logout</a></li>");

        model.put("link", "<li id=\"link\"><a href=\"/admin\">Admin page </a></li>");
        model.put("nameAdmin", auth.getName());
        return "admin/admin";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/createcategory")
    public String addCategory(Map<String, Object> model, Authentication auth) {

        model.put("name", "<li><a href=\"#\" id=\"authName\">" + auth.getName() + "</a></li>");
        model.put("logout", "<li id=\"logout\"><a href=\"/logout\">Logout</a></li>");

        model.put("link", "<li id=\"link\"><a href=\"/admin\">Admin page </a></li>");
        return "admin/createcategory";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/createplace")
    public String addPlace(Map<String, Object> model, Authentication auth) {

        model.put("name", "<li><a href=\"#\" id=\"authName\">" + auth.getName() + "</a></li>");
        model.put("logout", "<li id=\"logout\"><a href=\"/logout\">Logout</a></li>");

        model.put("link", "<li id=\"link\"><a href=\"/admin\">Admin page </a></li>");
        return "admin/createplace";
    }

    @GetMapping("/registration")
    public String registration(Map<String, Object> model, Authentication authentication) {
        if (authentication != null) {
            model.put("name", "<li><a href=\"#\" id=\"authName\">" + authentication.getName() + "</a></li>");
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
        return "registration";
    }

    @GetMapping("/succesorder")
    public String succesorder(Map<String, Object> model, Authentication auth) {
        return "user/succesorder";
    }
}
