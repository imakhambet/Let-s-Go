package cz.macha.spring.service;

import cz.macha.spring.model.User;
import cz.macha.spring.repository.OrderRepository;
import cz.macha.spring.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public List<Order> getOrdersByCustomerOrderByIdDesc(User customer){
        return orderRepository.findOrdersByCustomerOrderByIdDesc(customer);
    }

    public void addOrders(Order order){
        orderRepository.save(order);
    }

    public void deleteOrders(Integer id) {
        orderRepository.deleteById(id);
    }

}
