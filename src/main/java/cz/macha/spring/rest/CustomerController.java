package cz.macha.spring.rest;

import cz.macha.spring.model.Customer;
import cz.macha.spring.model.Organizer;
import cz.macha.spring.service.CustomerService;
import cz.macha.spring.service.OrganizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/customers")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @RequestMapping("/customers/id{id}")
    public Customer getUserById(@PathVariable Integer id){
        return customerService.getCustomer(id);
    }

    @RequestMapping("/customers/{login}")
    public Customer getUserByName(@PathVariable String login){
        return customerService.getCustomerByLogin(login);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/customers")
    public void addUser(@RequestBody Customer customer){
        customerService.addCustomer(customer);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/customers/{id}")
    public void updateUser(@RequestBody Customer customer, @PathVariable Integer id){
        customerService.updateCustomer(id, customer);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/customers/{id}")
    public void deleteUser(@PathVariable Integer id){
        customerService.deleteOrganizer(id);
    }
}
