package cz.macha.spring.service;

import cz.macha.spring.model.User;
import cz.macha.spring.repository.EventRepository;
//import cz.macha.spring.model.Category;
import cz.macha.spring.model.Event;
import cz.macha.spring.model.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public List<Event> getAllEvents(){
        return eventRepository.findAll();
    }

    public List <Event> getEventsByOrganizer(User organizer) {
        return eventRepository.findEventByOrganizer(organizer);
    }

//    public List<Event> getEventsByCategory(Category category){
//        return eventRepository.findEventsByCategory(category);
//    }

    public List<Event> getEventsByPlace(Place place){
        return eventRepository.findEventsByPlace(place);
    }

    public List<Event> getEventsByDate(String date){
        return eventRepository.findEventsByDate(date);
    }

    public Event getEvent(Integer id){
        return eventRepository.findById(id).orElse(null);
    }

    public Event getEventByName(String name){
        return eventRepository.getEventByName(name);
    }

    public void updateEvent(Integer id, Event event){
        Event event1 = eventRepository.findById(id).orElse(null);
        assert event1 != null;
        event1.setName(event.getName());

        eventRepository.save(event1);
    }

    public void deleteEvent(Integer id){
        eventRepository.deleteById(id);
    }

    public void setPlace(Integer id, Place place){
        Event event = eventRepository.findById(id).orElse(null);
        assert event != null;
        event.setPlace(place);
        eventRepository.save(event);
    }
}
