package com.restaurant.customer;

import com.restaurant.common.exception.CustomerNotFoundException;
import com.restaurant.common.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    /**
     * url
     */
    private static final String FIND_ALL_CUSTOMER_URL = "/customers";
    private static final String CREATE_CUSTOMER_URL = "/customers";
    private static final String FIND_CUSTOMER_BY_ID_URL = "/customer/{id}";
    private static final String UPDATE_CUSTOMER_BY_ID_URL = "/customer/{id}";
    private static final String DELETE_CUSTOMER_BY_ID_URL = "/customer/{id}";
    private static final String FIND_CUSTOMER_URL = "/customer/search";

    private static final String MSG_CUSTOMER_NOT_FOUND = "Not found customer by id: ";

    /**
     * Find all customer
     *
     * @return
     */
    @GetMapping(FIND_ALL_CUSTOMER_URL)
    public List<Customer> findAll() {
        return customerService.findAll();
    }

    /**
     * Find customer by id
     *
     * @param id
     * @return
     */
    @GetMapping(FIND_CUSTOMER_BY_ID_URL)
    public Customer findCustomerById(
            @PathVariable(value = "id") int id) {
        Optional<Customer> customer = customerService.findOne(id);
        if (!customer.isPresent()) {
            throw new CustomerNotFoundException(MSG_CUSTOMER_NOT_FOUND + id);
        }
        return customer.get();
    }

    /**
     * Find customer by name
     *
     * @param name
     * @return
     */
    @GetMapping(FIND_CUSTOMER_URL)
    public List<Customer> findCustomer(
            @RequestParam(value = "name", required = false) String name) {
        return customerService.findCustomer(name);
    }

    /**
     * Create customer
     *
     * @param customer
     * @return
     */
    @PostMapping(CREATE_CUSTOMER_URL)
    public Customer createCustomer(@Valid @RequestBody Customer customer) {
        return customerService.save(customer);
    }

    /**
     * Update customer by id
     *
     * @param id
     * @param customerDetail
     * @return
     */
    @PutMapping(UPDATE_CUSTOMER_BY_ID_URL)
    public Customer updateCustomer(@PathVariable(value = "id") int id,
                                   @Valid @RequestBody Customer customerDetail) {
        Optional<Customer> customerOptional = customerService.findOne(id);
        if (!customerOptional.isPresent()) {
            throw new CustomerNotFoundException(MSG_CUSTOMER_NOT_FOUND + id);
        } else {
            Customer customer = customerOptional.get();
            customer.setName(customerDetail.getName());
            customer.setPhone(customerDetail.getPhone());

            Customer updateCustomer = customerService.save(customer);
            return updateCustomer;
        }

    }

    /**
     * Delete customer by id
     *
     * @param id
     * @return
     */
    @DeleteMapping(DELETE_CUSTOMER_BY_ID_URL)
    public Customer deleteCustomer(@PathVariable(value = "id") int id) {
        Optional<Customer> customer = customerService.findOne(id);
        if (!customer.isPresent()) {
            throw new CustomerNotFoundException(MSG_CUSTOMER_NOT_FOUND + id);
        } else {
            customerService.delete(customer);
            return customer.get();
        }
    }
}
