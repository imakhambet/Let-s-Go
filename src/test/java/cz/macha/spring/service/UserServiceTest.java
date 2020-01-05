package cz.macha.spring.service;

import cz.macha.spring.model.Event;
import cz.macha.spring.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @Test
    public void createUser(){
        User organizer = new User("org", "123", "org@gmail.com");
        userService.addUser(organizer);

        assertNotNull(userService.getUserByLogin("org"));
    }

    @Test
    public void updateUser(){
        User user = new User("org", "123", "org@gmail.com");
        user.setId(121212);
        userService.addUser(user);

        user.setEmail("org123@gmail.com");
        userService.updateUser(user.getId(), user);

        assertEquals("org123@gmail.com", userService.getUserByLogin("org").getEmail());

    }

    @Test
    public void deleteUser(){
        User user = new User("org", "123", "org@gmail.com");
        user.setId(121212);
        userService.addUser(user);
        assertNotNull(userService.getUserByLogin("org"));

        userService.deleteUser(userService.getUserByLogin("org").getId());

        assertNull(userService.getUserByLogin("org"));
    }

    @Test
    public void createEventByUser(){
        User organizer = new User("org", "123", "org@gmail.com");
        userService.addUser(organizer);

        Event event = new Event("Box", "GGG vs Canelo", "23122020");
        event.setOrganizer(organizer);

        userService.createEvent(event);

        assertNotNull(eventService.getEventByName("Box"));
    }
}
