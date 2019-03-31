package com.restaurant.order;

import com.restaurant.common.exception.OrderDetailNotFoundException;
import com.restaurant.common.model.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class OrderDetailController {

    @Autowired
    OrderDetailService orderDetailService;

    /**
     * url
     */
    private static final String FIND_ALL_ORDER_DETAIL_URL = "/orderDetails";
    private static final String CREATE_ORDER_DETAIL_URL = "/orderDetails";
    private static final String FIND_ORDER_DETAIL_BY_ID_URL = "/orderDetail/{id}";
    private static final String UPDATE_ORDER_DETAIL_BY_ID_URL = "/orderDetail/{id}";
    private static final String DELETE_ORDER_DETAIL_BY_ID_URL = "/orderDetail/{id}";

    private static final String MSG_ORDER_DETAIL_NOT_FOUND = "Not found orderDetail by id: ";

    /**
     * Get all orderDetail.
     *
     * @return
     */
    @GetMapping(FIND_ALL_ORDER_DETAIL_URL)
    public List<OrderDetail> findAll() {

        return orderDetailService.findAll();
    }

    /**
     * Get orderDetail by id
     *
     * @param id
     * @return
     */
    @GetMapping(FIND_ORDER_DETAIL_BY_ID_URL)
    public OrderDetail findOrderDetailById(@PathVariable(value = "id") int id) {

        Optional<OrderDetail> orderDetail = orderDetailService.findOne(id);
        if (!orderDetail.isPresent()) {
            throw new OrderDetailNotFoundException(MSG_ORDER_DETAIL_NOT_FOUND + id);
        }
        return orderDetail.get();
    }

    /**
     * Created new orderDetail
     *
     * @param orderDetail
     * @return
     */
    @PostMapping(CREATE_ORDER_DETAIL_URL)
    public OrderDetail createOrderDetail(@Valid @RequestBody OrderDetail orderDetail) {

        return orderDetailService.createOrder(orderDetail);
    }

    /**
     * Update orderDetail by id
     *
     * @param id
     * @param detailUpdate
     * @return
     */
    /*@PutMapping(UPDATE_ORDER_DETAIL_BY_ID_URL)
    public OrderDetail updateOrderDetail(@PathVariable(value = "id") int id,
                           @Valid @RequestBody OrderDetail detailUpdate) {

        Optional<OrderDetail> orderDetailOptional = orderDetailService.findOne(id);
        if (!orderDetailOptional.isPresent()) {
            throw new OrderDetailNotFoundException(MSG_ORDER_DETAIL_NOT_FOUND + id);
        } else {
            OrderDetail orderDetail = orderDetailOptional.get();
            orderDetail.setOrderId(detailUpdate.getOrderId());
            orderDetail.setFoodId(detailUpdate.getFoodId());
            orderDetail.setQuantity(detailUpdate.getQuantity());

            OrderDetail updateOrderDetail = orderDetailService.createOrder(orderDetail);
            return updateOrderDetail;
        }
    }*/

    /**
     * Delete orderDetail by id
     *
     * @param id
     * @return
     */
    @DeleteMapping(DELETE_ORDER_DETAIL_BY_ID_URL)
    public OrderDetail deleteOrderDetail(@PathVariable(value = "id") int id) {

        Optional<OrderDetail> orderDetail = orderDetailService.findOne(id);
        if (!orderDetail.isPresent()) {
            throw new OrderDetailNotFoundException(MSG_ORDER_DETAIL_NOT_FOUND + id);
        } else {
            orderDetailService.delete(orderDetail);
            return orderDetail.get();
        }
    }

}
