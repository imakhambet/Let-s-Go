package cz.macha.spring.rest;

import cz.macha.spring.model.Category;
import cz.macha.spring.model.Event;
import cz.macha.spring.model.EventTicket;
import cz.macha.spring.service.EventService;
import cz.macha.spring.service.EventTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class EventTicketController {

    @Autowired
    EventTicketService eventTicketService;
    @Autowired
    EventService eventService;

    @RequestMapping("/event/{id}/tickets")
    public List<EventTicket> getEventTicketsByEvent(@PathVariable Integer id) {
        return eventTicketService.getEventTicketsByEvent(eventService.getEvent(id));
    }

    @PostMapping("/event/{id}/addticket")
    public ModelAndView addEventTicket(@RequestParam String name,
                                       @RequestParam float price,
                                       @RequestParam int quantity, @PathVariable Integer id){
        EventTicket eventTicket = new EventTicket(name, price, quantity);
        eventTicket.setEvent(eventService.getEvent(id));
        eventTicketService.addEventTicket(eventTicket);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/event/"+ id);
        return modelAndView;
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
