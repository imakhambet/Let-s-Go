package cz.macha.spring.dao;

import cz.macha.spring.model.Customer;
import cz.macha.spring.model.EventTicket;
import cz.macha.spring.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDao extends JpaRepository<Order, Integer> {

    List<Order> findOrdersByCustomer(Customer customer);

}
