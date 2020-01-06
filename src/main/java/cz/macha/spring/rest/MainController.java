package cz.macha.spring.rest;

import cz.macha.spring.service.EventService;
import cz.macha.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping("/")
    public String home(Map<String, Object> model, Authentication authentication) {
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
        model.put("events", eventService.getAllEvents());
        return "index";
    }

    @RequestMapping("/createevent")
    public String addEvent(Map<String, Object> model, Authentication authentication) {
        model.put("name", "<li><a href=\"#\" id=\"authName\">" + authentication.getName() + "</a></li>");
        model.put("logout", "<li id=\"logout\"><a href=\"/logout\">Logout</a></li>");

        model.put("link", "<li id=\"link\"><a href=\"/createevent\">Add new event</a></li>");
        return "organizer/createevent";
    }

    @RequestMapping("/createticket/{id}")
    public String addTicket(Map<String, Object> model, Authentication authentication, @PathVariable Integer id) {
        model.put("name", "<li><a href=\"#\" id=\"authName\">" + authentication.getName() + "</a></li>");
        model.put("logout", "<li id=\"logout\"><a href=\"/logout\">Logout</a></li>");

        model.put("link", "<li id=\"link\"><a href=\"/createevent\">Add new event</a></li>");
        model.put("id", id);
        return "organizer/createticket";
    }

    @RequestMapping("/addcategory/{id}")
    public String addCategoryEv(Map<String, Object> model, Authentication authentication, @PathVariable Integer id) {
        model.put("name", "<li><a href=\"#\" id=\"authName\">" + authentication.getName() + "</a></li>");
        model.put("logout", "<li id=\"logout\"><a href=\"/logout\">Logout</a></li>");

        model.put("link", "<li id=\"link\"><a href=\"/createevent\">Add new event</a></li>");
        model.put("id", id);
        return "organizer/addcategory";
    }

    @RequestMapping("/admin")
    public String admin(Map<String, Object> model, Authentication auth) {
        model.put("name", "<li><a href=\"#\" id=\"authName\">" + auth.getName() + "</a></li>");
        model.put("logout", "<li id=\"logout\"><a href=\"/logout\">Logout</a></li>");

        model.put("link", "<li id=\"link\"><a href=\"/admin\">Admin page </a></li>");
        model.put("nameAdmin", auth.getName());
        return "admin/admin";
    }

    @RequestMapping("/createcategory")
    public String addCategory(Map<String, Object> model, Authentication auth) {

        model.put("name", "<li><a href=\"#\" id=\"authName\">" + auth.getName() + "</a></li>");
        model.put("logout", "<li id=\"logout\"><a href=\"/logout\">Logout</a></li>");

        model.put("link", "<li id=\"link\"><a href=\"/admin\">Admin page </a></li>");
        return "admin/createcategory";
    }

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
