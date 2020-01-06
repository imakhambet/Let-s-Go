package cz.macha.spring.rest;

import cz.macha.spring.model.*;
import cz.macha.spring.service.PlaceService;
import cz.macha.spring.service.UserService;
import cz.macha.spring.validation.EventValidation;
import cz.macha.spring.validation.RegValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    public User getUserById(@PathVariable Integer id) {
        return userService.getUser(id);
    }

    @RequestMapping("/users/{login}")
    public User getUserByName(@PathVariable String login) {
        return userService.getUserByLogin(login);
    }

    @PostMapping("/registration")
    public ModelAndView addUser(@RequestParam String username,
                                @RequestParam String email,
                                @RequestParam String password,
                                @RequestParam String role, Map<String, Object> model) {

        ModelAndView modelAndView = new ModelAndView();

        RegValidation regValidation = new RegValidation(userService);
        regValidation.validation(username, email, password);

        if (regValidation.getErrors().size() == 0) {
            User user = new User(username, password, email);
            user.setRole(role);
            userService.addUser(user);
            modelAndView.setViewName("redirect:/login");
            return modelAndView;
        }
        StringBuilder errors = new StringBuilder();
        for (String error: regValidation.getErrors())
            errors.append("<p class=\"error\">").append(error).append("</p>");

        model.put("errors", errors.toString());
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/users/{id}")
    public void updateUser(@RequestBody User user, @PathVariable Integer id) {
        userService.updateUser(id, user);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }

    //pouze pro zakazniky
    @RequestMapping(method = RequestMethod.POST, value = "/users/{cId}/events/{eId}/questions")
    public void addQuestion(@PathVariable Integer cId, @PathVariable Integer eId, @RequestBody Question question) {
        userService.addQuestion(cId, eId, question);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users/{oId}/questions/{qId}/answers")
    public void addAnswer(@PathVariable Integer oId, @PathVariable Integer qId, @RequestBody Answer answer) {
        userService.addAnswer(oId, qId, answer);
    }


    @PostMapping("/addevent")
    public ModelAndView createEvent(@RequestParam String name,
                                    @RequestParam String description,
                                    @RequestParam String date,
                                    @RequestParam String place, Authentication authentication,
                                    Map<String, Object> model) {

        EventValidation validation = new EventValidation(placeService);
        validation.validation(name, description, date, place);
        List<String> eventErrors = validation.getErrors();

        ModelAndView modelAndView = new ModelAndView();

        if (eventErrors.size() == 0) {
            Event event = new Event(name, description, date);
            event.setOrganizer(userService.getUserByLogin(authentication.getName()));
            event.setPlace(placeService.getPlaceByName(place));
            userService.createEvent(event);
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }

        StringBuilder errors = new StringBuilder();
        for (String error: eventErrors)
            errors.append("<p class=\"error\">").append(error).append("</p>");

        model.put("errors", errors.toString());
        modelAndView.setViewName("organizer/createevent");
        return modelAndView;
    }
}
