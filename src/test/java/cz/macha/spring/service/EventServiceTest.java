package cz.macha.spring.service;

import cz.macha.spring.model.Category;
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
public class EventServiceTest {

    @Autowired
    EventService eventService;
    @Autowired
    UserService userService;
    @Autowired
    CategoryService categoryService;

    @Test
    public void getEventByOrganize(){
        User organizer = new User("org", "123", "org@gmail.com");

        userService.addUser(organizer);

        Event event = new Event("Box", "GGG vs Canelo", "23122020");
        event.setOrganizer(organizer);

        userService.createEvent(event);

        assertNotNull(eventService.getEventsByOrganizer(organizer));

    }
    @Test
    public void addEventToCategory(){
        Event event = new Event("Box", "GGG vs Canelo", "23122020");
        event.setOrganizer(new User("org", "123", "org@gmail.com"));
        userService.createEvent(event);

        Category category = new Category("Sport", new User("admin", "1212", "admin@hasha.e"));
        categoryService.addCategory(category);

        categoryService.addEvent(category, event);

        assertEquals(1, event.getCategory().size());
    }
}
