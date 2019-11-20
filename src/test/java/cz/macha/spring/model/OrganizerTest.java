package cz.macha.spring.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class OrganizerTest {

    @Test
    public void createNewOrganizer(){
        String login = "ear";
        String password = String.valueOf(new Random().nextInt(9999)+1000);
        String email = "imakha@gmail.com";
        String phone = "777 777 777";
        Organizer organizer = new Organizer(login, password, email, phone);

        assertEquals(organizer.getLogin(), login);
        assertEquals(organizer.getPassword(), password);
        assertEquals(organizer.getEmail(), email);
        assertEquals(organizer.getPhone(), phone);
    }

    @Test
    public void createNewEventsForOrganizer(){

        String login = "ear";
        String password = String.valueOf(new Random().nextInt(9999)+1000);
        String email = "imakha@gmail.com";
        String phone = "777 777 777";
        Organizer organizer = new Organizer(login, password, email, phone);
//        Category category = new Category("Concerts", new Admin("adminn"));

//        Event event = new Event(organizer);

    }


}