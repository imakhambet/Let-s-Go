package cz.macha.spring.repository;

import cz.macha.spring.model.Event;
import cz.macha.spring.model.User;
import cz.macha.spring.repository.CategoryRepository;
import cz.macha.spring.repository.EventRepository;
import cz.macha.spring.repository.PlaceRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EventRepositoryTest {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    public void createEvent(){
        User organizer = new User("makha", "123",
                "imakhambet@gmail.com");
        Event event = new Event("JoJoClub" , "prosmotr jojo fleks kak v jojo . Tolko v topovych shmotkach" , "kogda umrem" );
        event.setOrganizer(organizer);
        eventRepository.save(event);

        assertNotNull(event);
    }

    @Test
    public void getEvent(){
        User organizer = new User("makha", "123",
                "imakhambet@gmail.com");
        Event event = new Event("JoJoClub" ,
                "prosmotr jojo fleks kak v jojo." , "kogda umrem" );
        event.setOrganizer(organizer);
        eventRepository.save(event);

        Event event1 = eventRepository.getEventByName("JoJoClub");

        assertEquals(event.getDate(), event1.getDate());
        assertEquals(event.getName(), event1.getName());
        assertEquals(event, event1);
    }

}
