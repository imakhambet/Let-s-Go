package cz.macha.spring.validation;

import cz.macha.spring.model.EventTicket;
import cz.macha.spring.service.EventService;
import cz.macha.spring.service.EventTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TicketValidation {

    private final EventService eventService;
    private final EventTicketService eventTicketService;
    private List<String> errors = new ArrayList<>();

    @Autowired
    public TicketValidation(EventTicketService eventTicketService, EventService eventService) {
        this.eventService = eventService;
        this.eventTicketService = eventTicketService;
    }


    public void validation(String name, String price, String quantity, int eventID) {
        name(name, eventID);
        price(price);
        quantity(quantity);
    }

    void name(String name, int eventID) {
        if (name.isEmpty()) {
            errors.add("empty name");
        } else {
            for (EventTicket ticket : eventService.getEvent(eventID).getEventTickets()) {
                if (ticket.getName().equals(name)) {
                    errors.add("you have ticket with this name");
                }
            }
        }
    }

    void price(String price) {
        if (price.isEmpty()) {
            errors.add("empty price");
        } else {
            try {
                float priceF = Float.parseFloat(price);
                if (priceF < 0)
                    errors.add("not correct price");
            } catch (NumberFormatException ex) {
                errors.add("not correct price");
            }
        }

    }

    void quantity(String quantity) {
        if(quantity.isEmpty()){
            errors.add("empty quantity");
        }else {
            try {
                float quantityF = Integer.parseInt(quantity);
                if (quantityF == 0)
                    errors.add("not correct quantity");
            } catch (NumberFormatException ex) {
                errors.add("not correct quantity");
            }
        }
    }


    public List<String> getErrors() {
        return errors;
    }
}
