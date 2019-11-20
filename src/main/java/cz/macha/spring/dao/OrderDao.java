package cz.macha.spring.dao;

import cz.macha.spring.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDao extends JpaRepository<Order, Integer> {

}
