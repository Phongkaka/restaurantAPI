package com.restaurant.common.repository;

import com.restaurant.common.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("select o from Order o where o.hasDeleted=1")
    List<Order> findOrderByHasDeleted();

    @Query("select o from Order o where o.hasDeleted=0")
    List<Order> findAllOrder();
}
