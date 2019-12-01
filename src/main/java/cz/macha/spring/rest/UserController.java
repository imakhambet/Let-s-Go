package cz.macha.spring.rest;

import cz.macha.spring.model.Answer;
import cz.macha.spring.model.Event;
import cz.macha.spring.model.Question;
import cz.macha.spring.model.User;
import cz.macha.spring.service.EventService;
import cz.macha.spring.service.PlaceService;
import cz.macha.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private EventService eventService;
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

    @RequestMapping(method = RequestMethod.POST, value = "/users")
    public void addUser(@RequestBody User user){
        userService.addUser(user);
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

    @RequestMapping(method = RequestMethod.POST,
            value = "/organizers/{id}/events")
    public void createEvent(@RequestBody Event event,
                         @PathVariable Integer id){
        event.setOrganizer(userService.getUser(id));
        eventService.addEvent(event);
    }

}
