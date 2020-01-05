package cz.macha.spring.service;

import cz.macha.spring.repository.EventTicketRepository;
import cz.macha.spring.model.Event;
import cz.macha.spring.model.EventTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventTicketService {

    @Autowired
    EventTicketRepository eventTicketRepository;

    public List<EventTicket> getEventTicketsByEvent(Event event){
        return eventTicketRepository.findEventTicketByEvent(event);
    }

    public EventTicket getEventTicketById(Integer id){
        return eventTicketRepository.findById(id).orElse(null);
    }

    public void addEventTicket(EventTicket eventTicket){
        eventTicketRepository.save(eventTicket);
    }

    public void updateEventTicket(Integer id, EventTicket eventTicket){

        EventTicket ticket = eventTicketRepository.findById(id).orElse(null);
        assert ticket != null;
        ticket.setName(eventTicket.getName());
        ticket.setPrice(eventTicket.getPrice());
        ticket.setQuantity(eventTicket.getQuantity());

        eventTicketRepository.save(ticket);
    }

    public void deleteEventTicket(Integer id){
        eventTicketRepository.deleteById(id);
    }
}
