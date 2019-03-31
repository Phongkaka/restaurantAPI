package com.restaurant.order;

import com.restaurant.common.exception.OrderNotFoundException;
import com.restaurant.common.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    /**
     * url
     */
    private static final String FIND_ALL_ORDER_URL = "/orders";
    private static final String CREATE_ORDER_URL = "/orders";
    private static final String FIND_ORDER_BY_ID_URL = "/order/{id}";
    private static final String UPDATE_ORDER_BY_ID_URL = "/order/{id}";
    private static final String DELETE_ORDER_BY_ID_URL = "/order/{id}";

    private static final String MSG_ORDER_NOT_FOUND = "Not found order by id: ";

    /**
     * Get all order.
     *
     * @return
     */
    @GetMapping(FIND_ALL_ORDER_URL)
    public List<Order> findAll() {
        return orderService.findAll();
    }

    @GetMapping("/orderBin")
    public List<Order> recycleBin() {
        return orderService.recycleBin();
    }

    /**
     * Get order by id
     *
     * @param id
     * @return
     */
    @GetMapping(FIND_ORDER_BY_ID_URL)
    public Order findOrderById(@PathVariable(value = "id") int id) {

        Optional<Order> order = orderService.findOne(id);
        if (!order.isPresent()) {
            throw new OrderNotFoundException(MSG_ORDER_NOT_FOUND + id);
        }
        return order.get();
    }


    /**
     * Created new order
     * @param order
     * @return
     */
    @PostMapping(CREATE_ORDER_URL)
    public Order createOrder(@Valid @RequestBody Order order) {
        return orderService.save(order);
    }

    /**
     * Update order by id
     *
     * @param id
     * @param orderDetail
     * @return
     */
    @PutMapping(UPDATE_ORDER_BY_ID_URL)
    public Order updateOrder(@PathVariable(value = "id") int id,
                           @Valid @RequestBody Order orderDetail) {

        Optional<Order> orderOptional = orderService.findOne(id);
        if (!orderOptional.isPresent()) {
            throw new OrderNotFoundException(MSG_ORDER_NOT_FOUND + id);
        } else {
            Order order = orderOptional.get();
            order.setCustomerId(orderDetail.getCustomerId());

            Order updateOrder = orderService.save(order);
            return updateOrder;
        }
    }

    /**
     * Delete order by id
     *
     * @param id
     * @return
     */
    @DeleteMapping(DELETE_ORDER_BY_ID_URL)
    public Order deleteOrder(@PathVariable(value = "id") int id) {

        Optional<Order> order = orderService.findOne(id);
        if (!order.isPresent()) {
            throw new OrderNotFoundException(MSG_ORDER_NOT_FOUND + id);
        } else {
            orderService.delete(order.get());
            return order.get();
        }
    }
}
