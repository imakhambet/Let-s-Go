package cz.macha.spring.repository;

import cz.macha.spring.model.User;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void saveAndGetCustomer() {
        User user = new User("makha", "123",
                "imakhambet@gmail.com");

        userRepository.save(user);

        User user1 = userRepository.findCustomerByLogin("makha");

        assertNotNull(user);
        assertEquals(user1.getEmail(), user.getEmail());
        assertEquals(user1.getLogin(), user.getLogin());

    }

    @Test
    public void testDeleteCustomer() {
        User user = new User("makha", "123",
                "imakhambet@gmail.com");

        userRepository.save(user);
        userRepository.delete(user);

    }

    @Test
    public void findAllEmployees() {
        User user = new User("makha", "123",
                "imakhambet@gmail.com");

        userRepository.save(user);
        assertNotNull(userRepository.findAll());
    }

}
