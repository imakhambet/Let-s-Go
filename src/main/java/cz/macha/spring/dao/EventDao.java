package cz.macha.spring.dao;

import cz.macha.spring.model.Category;
import cz.macha.spring.model.Event;
import cz.macha.spring.model.Organizer;
import cz.macha.spring.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventDao extends JpaRepository<Event, Integer> {
    List<Event> findEventByOrganizer(Organizer organizer);
    List<Event> findEventsByCategory(Category category);
    List<Event> findEventsByPlace(Place place);
    List<Event> findEventsByDate(String date);
    Event getEventByName(String name);
}
