package cz.macha.spring.service;

import cz.macha.spring.dao.OrderDao;
import cz.macha.spring.model.Customer;
import cz.macha.spring.model.EventTicket;
import cz.macha.spring.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderDao orderDao;

    public List<Order> getAllOrders(){
        return orderDao.findAll();
    }

    public List<Order> getOrdersByCustomer(Customer customer){
        return orderDao.findOrdersByCustomer(customer);
    }

    public void addOrders(Order order){
        orderDao.save(order);
    }

    public void deleteOrders(Integer id) {
        orderDao.delete(id);
    }

}
