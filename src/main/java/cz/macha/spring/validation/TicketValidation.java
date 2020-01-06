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
            errors.add("TICKET NAME IS REQUIRED");
        } else {
            for (EventTicket ticket : eventService.getEvent(eventID).getEventTickets()) {
                if (ticket.getName().equals(name)) {
                    errors.add("DUPLICATE TICKET NAME");
                }
            }
        }
    }

    void price(String price) {
        if (price.isEmpty()) {
            errors.add("PRICE IS REQUIRED");
        } else {
            try {
                float priceF = Float.parseFloat(price);
                if (priceF < 0)
                    errors.add("PRICE IS NOT CORRECT");
            } catch (NumberFormatException ex) {
                errors.add("PRICE IS NOT CORRECT");
            }
        }

    }

    void quantity(String quantity) {
        if(quantity.isEmpty()){
            errors.add("TICKET QUANTITY IS REQUIRED");
        }else {
            try {
                float quantityF = Integer.parseInt(quantity);
                if (quantityF == 0)
                    errors.add("TICKET QUANTITY IS NOT CORRECT");
            } catch (NumberFormatException ex) {
                errors.add("TICKET QUANTITY IS NOT CORRECT");
            }
        }
    }


    public List<String> getErrors() {
        return errors;
    }
}
