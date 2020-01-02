package cz.macha.spring.rest;


import cz.macha.spring.model.Order;
import cz.macha.spring.service.EventTicketService;
import cz.macha.spring.service.OrderService;
import cz.macha.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

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

    @RequestMapping("/myorders")
    public ModelAndView getOrdersByCustomer(Authentication authentication, Map<String, Object> model){
        List<Order> orders =
                orderService.getOrdersByCustomerOrderByIdDesc(customerService.getUserByLogin(authentication.getName()));
        model.put("name", "<h3>Username: " + authentication.getName() + "</h3>");
        model.put("orders", orders);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/orders");
        return modelAndView;
    }

    @PostMapping("/buyticket/{ticket}")
    public ModelAndView addOrder(@RequestParam Integer quantity,
                                 @PathVariable Integer ticket, Authentication auth){
        Order order = new Order();
        order.setCustomer(customerService.getUserByLogin(auth.getName()));
        order.setEventTicket(eventTicketService.getEventTicketById(ticket));
        order.setQuantity(quantity);
        order.setTotalPrice(quantity*eventTicketService.getEventTicketById(ticket).getPrice());
        orderService.addOrders(order);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/myorders");
        return modelAndView;
    }

    @RequestMapping("/orders/{oid}")
    public void deleteOrder(@PathVariable Integer oid){
        orderService.deleteOrders(oid);
    }

}

