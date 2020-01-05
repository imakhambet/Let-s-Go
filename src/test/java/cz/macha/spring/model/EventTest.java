package cz.macha.spring.model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class EventTest {

    @Test
    public void setOrganizerToEvent(){
        Event event = new Event("JoJoClub" ,
                "prosmotr jojo fleks kak v jojo." , "kogda umrem" );
        User organizer = new User("makha", "123",
                "imakhambet@gmail.com");
        event.setOrganizer(organizer);

        assertEquals(event.getOrganizer(), organizer);
    }

    @Test
    public void addEventToCategory(){
        Event event = new Event("JoJoClub" ,
                "prosmotr jojo fleks kak v jojo." , "kogda umrem" );

        User organizer = new User("makha", "123",
                "imakhambet@gmail.com");
        event.setOrganizer(organizer);

        User admin = new User("admin", "123",
                "admin@gmail.com");

        Category category = new Category( "Hentai" , admin);
        event.addCategory(category);

        assertEquals(1, event.getCategory().size());

    }

    @Test
    public void removeEventFromCategory(){
        Event event = new Event("JoJoClub" ,
                "prosmotr jojo fleks kak v jojo." , "kogda umrem" );

        User organizer = new User("makha", "123",
                "imakhambet@gmail.com");
        event.setOrganizer(organizer);

        User admin = new User("admin", "123",
                "admin@gmail.com");

        Category category = new Category( "Hentai" , admin);
        event.addCategory(category);

        assertEquals(1, event.getCategory().size());

        event.removeCategory(category);
        assertEquals(0, event.getCategory().size());
    }

    @Test
    public void addEventTo2Categories(){
        Event event = new Event("JoJoClub" ,
                "prosmotr jojo fleks kak v jojo." , "kogda umrem" );

        User organizer = new User("makha", "123",
                "imakhambet@gmail.com");
        event.setOrganizer(organizer);

        User admin = new User("admin", "123",
                "admin@gmail.com");

        Category category = new Category( "Hentai" , admin);
        Category category1 = new Category( "Gon" , admin);
        event.addCategory(category);
        event.addCategory(category1);

        assertEquals(2, event.getCategory().size());
    }


}
