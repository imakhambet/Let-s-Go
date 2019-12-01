package cz.macha.spring.repository;

import cz.macha.spring.model.Event;
import cz.macha.spring.model.EventTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventTicketRepository extends JpaRepository<EventTicket, Integer> {
    List<EventTicket> findEventTicketByEvent(Event event);
}
