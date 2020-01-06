package cz.macha.spring.rest;


import cz.macha.spring.model.EventTicket;
import cz.macha.spring.model.Order;
import cz.macha.spring.service.EventTicketService;
import cz.macha.spring.service.OrderService;
import cz.macha.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
    public ModelAndView getOrdersByCustomer(Authentication authentication, Map<String, Object> model) {
        model.put("name", "<li><a href=\"#\" id=\"authName\">" + authentication.getName() + "</a></li>");
        model.put("logout", "<li id=\"logout\"><a href=\"/logout\">Logout</a></li>");

        model.put("link", "<li id=\"link\"><a href=\"/myorders\">My orders</a></li>");
        List<Order> orders =
                orderService.getOrdersByCustomerOrderByIdDesc(customerService.getUserByLogin(authentication.getName()));
        model.put("orders", orders);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/orders");
        return modelAndView;
    }

    @PostMapping("/buyticket/{ticket}")
    public ModelAndView addOrder(@RequestParam Integer quantity,
                                 @PathVariable Integer ticket, Authentication auth, Map<String, Object> model) {

        List<Order> ordersByTicket = orderService.getOrdersByTicket(eventTicketService.getEventTicketById(ticket));
        EventTicket ticket1 = eventTicketService.getEventTicketById(ticket);
        int notEmptyTickets = 0;
        for (Order order1 : ordersByTicket) {
            notEmptyTickets += order1.getQuantity();
        }

        int emptyTickets = ticket1.getQuantity() - notEmptyTickets;

        ModelAndView modelAndView = new ModelAndView();
        if (quantity > emptyTickets) {
            model.put("errors", "<p class=\"error\">Sorry, not enough tickets for your order. " + emptyTickets +
                    " tickets left with type " + ticket1.getName() + ".</p>" +
                    "<a class=\"error\" style=\"color: cornflowerblue; " +
                    "text-align: center; font-size: 18px; display: block\"  href=\"/event/"
                    + ticket1.getEvent().getId() + "\">Go back</a>");

            modelAndView.setViewName("event");
            return modelAndView;

        }
        Order order = new Order();
        order.setCustomer(customerService.getUserByLogin(auth.getName()));
        order.setEventTicket(ticket1);
        order.setQuantity(quantity);
        order.setTotalPrice(quantity * ticket1.getPrice());
        orderService.addOrders(order);
        modelAndView.setViewName("redirect:/myorders");
        return modelAndView;
    }

    @RequestMapping("/orders/{oid}")
    public void deleteOrder(@PathVariable Integer oid) {
        orderService.deleteOrders(oid);
    }

}

