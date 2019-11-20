package cz.macha.spring.service;

import cz.macha.spring.dao.EventTicketDao;
import cz.macha.spring.model.Event;
import cz.macha.spring.model.EventTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventTicketService {

    @Autowired
    EventTicketDao eventTicketDao;

    public List<EventTicket> getEventTicketsByEvent(Event event){
        return eventTicketDao.findEventTicketByEvent(event);
    }

    public EventTicket getEventTicketById(Integer id){
        return eventTicketDao.findOne(id);
    }

    public void addEventTicket(EventTicket eventTicket){
        eventTicketDao.save(eventTicket);
    }

    public void updateEventTicket(Integer id, EventTicket eventTicket){

        eventTicketDao.findOne(id).setName(eventTicket.getName());
        eventTicketDao.findOne(id).setPrice(eventTicket.getPrice());
        eventTicketDao.findOne(id).setQuantity(eventTicket.getQuantity());

        eventTicketDao.save(eventTicketDao.findOne(id));
    }

    public void deleteEventTicket(Integer id){
        eventTicketDao.delete(id);
    }
}
