package cz.macha.spring.service;

import cz.macha.spring.dao.EventDao;
import cz.macha.spring.model.Category;
import cz.macha.spring.model.Event;
import cz.macha.spring.model.Organizer;
import cz.macha.spring.model.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventDao eventDao;

    public List<Event> getAllEvents(){
        return eventDao.findAll();
    }

    public List <Event> getEventsByOrganizer(Organizer organizer) {
        return eventDao.findEventByOrganizer(organizer);
    }

    public List<Event> getEventsByCategory(Category category){
        return eventDao.findEventsByCategory(category);
    }

    public List<Event> getEventsByPlace(Place place){
        return eventDao.findEventsByPlace(place);
    }

    public List<Event> getEventsByDate(String date){
        return eventDao.findEventsByDate(date);
    }

    public Event getEvent(Integer id){
        return eventDao.findOne(id);
    }

    public Event getEventByName(String name){
        return eventDao.getEventByName(name);
    }

    public void addEvent(Event event){
        eventDao.save(event);
    }

    public void updateEvent(Integer id, Event event){
        eventDao.findOne(id).setName(event.getName());

        eventDao.save(eventDao.findOne(id));
    }

    public void deleteEvent(Integer id){
        eventDao.delete(id);
    }
}
