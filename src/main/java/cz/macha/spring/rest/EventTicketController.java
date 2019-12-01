package cz.macha.spring.rest;

import cz.macha.spring.model.Category;
import cz.macha.spring.model.Event;
import cz.macha.spring.model.EventTicket;
import cz.macha.spring.service.EventService;
import cz.macha.spring.service.EventTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventTicketController {

    @Autowired
    EventTicketService eventTicketService;
    @Autowired
    EventService eventService;

    @RequestMapping("/events/{id}/tickets")
    public List<EventTicket> getEventTicketsByEvent(@PathVariable Integer id) {
        return eventTicketService.getEventTicketsByEvent(eventService.getEvent(id));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/events/{id}/tickets")
    public void addEventTicket(@RequestBody EventTicket eventTicket, @PathVariable Integer id){
        eventTicket.setEvent(eventService.getEvent(id));
        eventTicketService.addEventTicket(eventTicket);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/tickets/{id}")
    public void updateEventTicket(@RequestBody EventTicket eventTicket, @PathVariable Integer id){
        eventTicketService.updateEventTicket(id, eventTicket);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/tickets/{id}")
    public void deleteEventTicket(@PathVariable Integer id){

        eventTicketService.deleteEventTicket(id);
    }
}
