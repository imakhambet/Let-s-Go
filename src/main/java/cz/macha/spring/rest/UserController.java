package cz.macha.spring.rest;

import cz.macha.spring.model.*;
import cz.macha.spring.service.PlaceService;
import cz.macha.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
//    @Autowired
//    private EventService eventService;
    @Autowired
    private PlaceService placeService;


    @RequestMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping("/users/id{id}")
    public User getUserById(@PathVariable Integer id){
        return userService.getUser(id);
    }

    @RequestMapping("/users/{login}")
    public User getUserByName(@PathVariable String login){
        return userService.getUserByLogin(login);
    }

//    @RequestMapping(method = RequestMethod.POST, value = "/users")
//    public void addUser(@RequestBody User user){
//        userService.addUser(user);
//    }

//    @GetMapping("/registration")
//    public ModelAndView registration() {
////        return "registration";
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("registration");
//        return modelAndView;
//    }

    @PostMapping("/registration")
    public ModelAndView addUser(@RequestParam String username,
                                @RequestParam String email,
                                @RequestParam String password,
                                @RequestParam String role) {

        User user = new User(username,  password, email);
        System.out.println(username);
        user.setRole(role);
//        user.setRoles(Collections.singleton(Role.USER));
        userService.addUser(user);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/login");
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/users/{id}")
    public void updateUser(@RequestBody User user, @PathVariable Integer id){
        userService.updateUser(id, user);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{id}")
    public void deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
    }

    //pouze pro zakazniky
    @RequestMapping(method = RequestMethod.POST, value = "/users/{cId}/events/{eId}/questions")
    public void addQuestion(@PathVariable Integer cId, @PathVariable Integer eId, @RequestBody Question question){
        userService.addQuestion(cId, eId, question);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users/{oId}/questions/{qId}/answers")
    public void addAnswer(@PathVariable Integer oId, @PathVariable Integer qId, @RequestBody Answer answer){
        userService.addAnswer(oId, qId, answer);
    }

//    @RequestMapping(method = RequestMethod.POST,
//////            value = "/newevent")
//            value = "/organizers/{id}/events")
    @PostMapping("/addevent")
    public ModelAndView createEvent(@RequestParam String name,
                                    @RequestParam String description,
                                    @RequestParam String date,
                                    @RequestParam String place, Authentication authentication){
        System.out.println(name);
        Event event = new Event(name, description, date);
        event.setOrganizer(userService.getUserByLogin(authentication.getName()));
        event.setPlace(placeService.getPlaceByName(place));
        userService.createEvent(event);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }
}
