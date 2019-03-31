package com.restaurant.bill;

import com.restaurant.common.exception.OrderDetailNotFoundException;
import com.restaurant.common.exception.OrderNotFoundException;
import com.restaurant.common.model.Bill;
import com.restaurant.common.model.Order;
import com.restaurant.common.model.OrderDetail;
import com.restaurant.common.repository.BillRepository;
import com.restaurant.order.OrderDetailService;
import com.restaurant.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillService {

    @Autowired
    BillRepository billRepository;

    @Autowired
    OrderService orderService;

    /**
     * Get all Bill
     *
     * @return
     */
    public List<Bill> findAll() {
        return billRepository.findAll();
    }

    /**
     * Get bill by id
     *
     * @param id
     * @return
     */
    public Optional<Bill> findOne(int id) {
        return billRepository.findById(id);
    }


    /**
     * Create new bill
     *
     * @param bill
     * @return
     */
    public Bill save(Bill bill) {
        return billRepository.save(bill);
    }

    /**
     * Delete a bill by id
     *
     * @param bill
     */
    public void delete(Optional<Bill> bill) {
        billRepository.deleteById(bill.get().getId());
    }

    /**
     * Create bill
     *
     * @param bill
     * @return
     */
    public Bill saveBill(Bill bill) {

        Optional<Order> orderOptional = orderService.findOne(bill.getOrderId());
        if (!orderOptional.isPresent()) {
            throw new OrderNotFoundException("Not found order by id: " + bill.getOrder().getId());
        }
        Order order = orderOptional.get();
        List<OrderDetail> orderDetails = order.getOrderDetails();

        double billFinal = 0;

        for (OrderDetail detail : orderDetails) {
            double price = detail.getPrice() * detail.getQuantity();
            billFinal = billFinal + price;
        }
        bill.setAmount(billFinal);
        return billRepository.save(bill);
    }
}
