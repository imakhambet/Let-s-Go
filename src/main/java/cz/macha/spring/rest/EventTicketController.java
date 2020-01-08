package cz.macha.spring.rest;

import cz.macha.spring.model.Category;
import cz.macha.spring.model.Event;
import cz.macha.spring.model.EventTicket;
import cz.macha.spring.service.EventService;
import cz.macha.spring.service.EventTicketService;
import cz.macha.spring.validation.TicketValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

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

    @PreAuthorize("hasRole('ROLE_ORGANIZER')")
    @PostMapping("/event/{id}/addticket")
    public ModelAndView addEventTicket(@RequestParam String name,
                                       @RequestParam String price,
                                       @RequestParam String quantity, @PathVariable Integer id,
                                       Map<String, Object> model) {

        ModelAndView modelAndView = new ModelAndView();

        TicketValidation ticketValidation = new TicketValidation(eventTicketService, eventService);
        ticketValidation.validation(name, price, quantity, id);
        List<String> errors = ticketValidation.getErrors();

        if (errors.size() == 0) {

            EventTicket eventTicket = new EventTicket(name, Float.parseFloat(price), Integer.parseInt(quantity));
            eventTicket.setEvent(eventService.getEvent(id));
            eventTicketService.addEventTicket(eventTicket);
            modelAndView.setViewName("redirect:/event/" + id);
            return modelAndView;
        }
        StringBuilder errorsS = new StringBuilder();
        for (String error : errors) {
            errorsS.append("<p class=\"error\">").append(error).append("</p>");
        }
        model.put("errors", errorsS.toString());
        modelAndView.setViewName("organizer/createticket");
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/tickets/{id}")
    public void updateEventTicket(@RequestBody EventTicket eventTicket, @PathVariable Integer id) {
        eventTicketService.updateEventTicket(id, eventTicket);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/tickets/{id}")
    public void deleteEventTicket(@PathVariable Integer id) {

        eventTicketService.deleteEventTicket(id);
    }
}
