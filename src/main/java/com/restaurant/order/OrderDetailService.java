package com.restaurant.order;

import com.restaurant.common.exception.FoodNotFoundException;
import com.restaurant.common.exception.OrderNotFoundException;
import com.restaurant.common.model.Food;
import com.restaurant.common.model.OrderDetail;
import com.restaurant.common.repository.OrderDetailRepository;
import com.restaurant.food.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailService {

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    FoodService foodService;

    /**
     * Find all orderDetail
     *
     * @return
     */
    public List<OrderDetail> findAll() {
        return orderDetailRepository.findAll();
    }

    /**
     * Find orderDetail by id
     *
     * @param id
     * @return
     */
    public Optional<OrderDetail> findOne(int id) {
        return orderDetailRepository.findById(id);
    }

    /**
     * Delete a orderDetail by id
     *
     * @param orderDetail
     */
    public void delete(Optional<OrderDetail> orderDetail) {
        orderDetailRepository.deleteById(orderDetail.get().getId());
    }

    /**
     * Create order from orderDetail
     *
     * @param order
     * @return
     */
    public OrderDetail createOrder(OrderDetail order) {

        Optional<Food> foodOptional = foodService.findOne(order.getFoodId());

        if (!foodOptional.isPresent()) {
            throw new FoodNotFoundException("Not found food by id: " + order.getFoodId());
        }

        Food food = foodOptional.get();
        int orderQuantity = order.getQuantity();
        int foodQuantity = food.getQuantity();

        if (orderQuantity <= foodQuantity) {

            food.setQuantity(foodQuantity - orderQuantity);
            foodService.save(food);

            order.setPrice(food.getPrice());
            order.setNameFood(food.getName());

            return orderDetailRepository.save(order);
        } else {
            throw new OrderNotFoundException("The quantity is not correct");
        }

    }

}
