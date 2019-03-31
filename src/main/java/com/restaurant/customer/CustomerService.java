package com.restaurant.customer;

import com.restaurant.common.model.Customer;
import com.restaurant.common.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    /**
     * Get all customer
     *
     * @return
     */
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    /**
     * Get customer by id
     * @param id
     * @return
     */
    public Optional<Customer> findOne(int id) {
        return customerRepository.findById(id);
    }

    /**
     * Get customer by name and id
     *
     * @param name
     * @return
     */
    public List<Customer> findCustomer(String name) {
        return customerRepository.findCustomer(name);
    }

    /**
     * Update customer
     *
     * @param customer
     * @return
     */
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    /**
     * Delete customer by id.
     *
     * @param customer
     */
    public void delete(Optional<Customer> customer) {
        customerRepository.deleteById(customer.get().getId());
    }
}
