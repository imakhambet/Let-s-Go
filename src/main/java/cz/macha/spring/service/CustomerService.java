package cz.macha.spring.service;

import cz.macha.spring.dao.CustomerDao;
import cz.macha.spring.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerDao customerDao;

    public List<Customer> getAllCustomers(){
        return customerDao.findAll();
    }

    public Customer getCustomer(Integer id){
        return customerDao.findOne(id);
    }

    public Customer getCustomerByLogin(String login) {
        return customerDao.findCustomerByLogin(login);
    }

    public void addCustomer(Customer customer){
        customerDao.save(customer);
    }

    public void updateCustomer(Integer id, Customer customer){

        customerDao.findOne(id).setEmail(customer.getEmail());
        customerDao.findOne(id).setLogin(customer.getLogin());
        customerDao.findOne(id).setPassword(customer.getPassword());
        customerDao.findOne(id).setPhone(customer.getPhone());
        customerDao.findOne(id).setName(customer.getName());

        customerDao.save(customerDao.findOne(id));
    }

    public void deleteOrganizer(Integer id){
        customerDao.delete(id);
    }



}
