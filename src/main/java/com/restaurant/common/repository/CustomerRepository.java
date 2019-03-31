package com.restaurant.common.repository;

import com.restaurant.common.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query("SELECT c FROM Customer c " +
            "WHERE (c.name LIKE CONCAT('%',:name,'%') OR :name = '')"
    )
    List<Customer> findCustomer(@Param("name") String name);

}
