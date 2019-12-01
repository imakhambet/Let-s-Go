package cz.macha.spring.repository;

//import cz.macha.spring.model.Category;
import cz.macha.spring.model.Event;
import cz.macha.spring.model.Place;
import cz.macha.spring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
    List<Event> findEventByOrganizer(User organizer);
//    List<Event> findEventsByCategory(Category category);
    List<Event> findEventsByPlace(Place place);
    List<Event> findEventsByDate(String date);
    Event getEventByName(String name);
}
