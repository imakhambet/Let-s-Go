package cz.macha.spring.model;

import cz.macha.spring.repository.CategoryRepository;
import cz.macha.spring.repository.EventRepository;
import cz.macha.spring.repository.PlaceRepository;
import cz.macha.spring.repository.UserRepository;

import cz.macha.spring.service.EventService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.image.PackedColorModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    EventRepository eventRepository;
    @Autowired
    PlaceRepository placeRepository;
    @Autowired
    CategoryRepository categoryRepository;


    @Test
    public void saveAndGetCustomer() {
        User user = new User("makha", "123",
                "imakhambet@gmail.com");

        userRepository.save(user);

        User user1 = userRepository.findCustomerByLogin("makha");

        assertNotNull(user);
        assertEquals(user1.getEmail(), user.getEmail());
        assertEquals(user1.getLogin(), user.getLogin());

    }

    @Test
    public void testDeleteCustomer() {
        User user = new User("makha", "123",
                "imakhambet@gmail.com");

        userRepository.save(user);
        userRepository.delete(user);
    }

    @Test
    public void findAllEmployees() {
        User user = new User("makha", "123",
                "imakhambet@gmail.com");

        userRepository.save(user);
        assertNotNull(userRepository.findAll());
    }

    @Test
    public void createEvent(){
        User organizer = new User("makha", "123",
                "imakhambet@gmail.com");
        userRepository.save(organizer);
        Place place = new Place("Kazahstan" , organizer , "NurSulTan");
        placeRepository.save(place);
        Event create = new Event(organizer , place , "JoJoClub" , "prosmotr jojo fleks kak v jojo . Tolko v topovych shmotkach" , "kogda umrem" );
        eventRepository.save(create);
        Category category = new Category( "Hentai" , organizer );
        categoryRepository.save(category);
        create.addCategory(category);
        assertNotNull(categoryRepository.getCategoryByName("Hentai"));
    }
}
