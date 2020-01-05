package cz.macha.spring.repository;

import cz.macha.spring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findCustomerByLogin(String login);
}
