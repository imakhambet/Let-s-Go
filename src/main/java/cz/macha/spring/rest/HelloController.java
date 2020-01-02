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
public class HelloController {

    @Autowired
    EventService eventService;
    @Autowired
    UserService userService;

    @RequestMapping("/")
    public String home(Map<String, Object> model, Authentication authentication) {
        if(authentication != null) {
            model.put("name", "<h3>Username: " + authentication.getName() + "</h3>");
            model.put("logout", "<a href=\"/logout\">Logout</a>\n");

            if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ORGANIZER"))){
                model.put("role", "<h3>Organizer mode</h3>");
                model.put("link", "<a href=\"/createevent\">Add new event</a>\n");
            }
            if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))){
                model.put("role", "<h3>Admin mode</h3>");
                model.put("link", "<a href=\"/admin\">Go to admin page </a>\n");
            }
        }else{
            model.put("login", "<a href=\"/login\">Login</a>\n");
            model.put("registration", "<a href=\"/registration\">Registration</a>\n");
        }
        model.put("events", eventService.getAllEvents());
        return "index";
    }

    @RequestMapping("/createevent")
    public String addEvent() {
        return "organizer/createevent";
    }

    @RequestMapping("/createticket/{id}")
    public String addTicket(Map<String, Object> model, @PathVariable Integer id) {
        model.put("id", id);
        return "organizer/createticket";
    }

    @RequestMapping("/admin")
    public String admin() {
        return "admin/admin";
    }

    @RequestMapping("/createcategory")
    public String addCategory() {
        return "admin/createcategory";
    }

    @RequestMapping("/createplace")
    public String addPlace() {
        return "admin/createplace";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }
}
