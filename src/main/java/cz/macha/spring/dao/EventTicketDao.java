package cz.macha.spring.dao;

import cz.macha.spring.model.Event;
import cz.macha.spring.model.EventTicket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventTicketDao extends JpaRepository<EventTicket, Integer> {
    List<EventTicket> findEventTicketByEvent(Event event);
}
