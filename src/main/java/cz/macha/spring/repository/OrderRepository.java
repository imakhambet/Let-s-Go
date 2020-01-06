package cz.macha.spring.repository;

import cz.macha.spring.model.EventTicket;
import cz.macha.spring.model.Order;
import cz.macha.spring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findOrdersByCustomerOrderByIdDesc(User customer);

    int countByEventTicket(EventTicket ticket);

    List<Order> findOrdersByEventTicket(EventTicket eventTicket);

}
