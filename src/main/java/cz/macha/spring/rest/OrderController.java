package cz.macha.spring.rest;


import cz.macha.spring.model.Order;
import cz.macha.spring.service.EventTicketService;
import cz.macha.spring.service.OrderService;
import cz.macha.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    UserService customerService;

    @Autowired
    EventTicketService eventTicketService;

    @RequestMapping("/orders")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @RequestMapping("/customers/{id}/orders")
    public List<Order> getOrdersByCustomer(@PathVariable Integer id){
        return orderService.getOrdersByCustomer(customerService.getUser(id));
    }

    @RequestMapping("/customers/{id}/tickets/{ticket}/orders")
    public void addOrder(@RequestBody Order order, @PathVariable Integer id,
                         @PathVariable Integer ticket){
        order.setCustomer(customerService.getUser(id));
        order.setEventTicket(eventTicketService.getEventTicketById(ticket));
        orderService.addOrders(order);
    }

    @RequestMapping("/orders/{oid}")
    public void deleteOrder(@PathVariable Integer oid){
        orderService.deleteOrders(oid);
    }

}

