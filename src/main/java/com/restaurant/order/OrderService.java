package com.restaurant.order;

import com.restaurant.common.model.Order;
import com.restaurant.common.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    /**
     * Get all order
     *
     * @return
     */
    public List<Order> findAll() {
        return orderRepository.findAllOrder();
    }

    /**
     * Get order by id
     *
     * @param id
     * @return
     */
    public Optional<Order> findOne(int id) {
        return orderRepository.findById(id);
    }


    /**
     * Create new order
     *
     * @param order
     * @return
     */
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    /**
     * Soft delete order
     *
     * @param order
     */
    public void delete(Order order) {
        order.setHasDeleted(true);
        orderRepository.save(order);
    }

    /**
     * Find all order by has deleted
     *
     * @return
     */
    public List<Order> recycleBin() {
        return orderRepository.findOrderByHasDeleted();
    }
}
