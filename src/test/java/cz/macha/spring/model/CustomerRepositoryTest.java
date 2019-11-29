package cz.macha.spring.model;

import cz.macha.spring.repository.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    public void saveAndGetCustomer() {
        Customer customer = new Customer("makha", "123",
                "imakhambet@gmail.com", "5555", "ndjf");

        customerRepository.save(customer);

        Customer customer1 = customerRepository.findCustomerByLogin("makha");

        assertNotNull(customer);
        assertEquals(customer1.getName(), customer.getName());
        assertEquals(customer1.getEmail(), customer1.getEmail());

    }

    @Test
    public void testDeleteCustomer() {
        Customer customer = new Customer("makha", "123",
                "imakhambet@gmail.com", "5555", "ndjf");
        customerRepository.save(customer);
        customerRepository.delete(customer);
    }

    @Test
    public void findAllEmployees() {
        Customer customer = new Customer("makha", "123",
                "imakhambet@gmail.com", "5555", "ndjf");
        customerRepository.save(customer);
        assertNotNull(customerRepository.findAll());
    }

}
