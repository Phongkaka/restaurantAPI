package com.restaurant.bill;

import com.restaurant.common.exception.BillNotFoundException;
import com.restaurant.common.model.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class BillController {

    @Autowired
    BillService billService;

    /**
     * url
     */
    private static final String FIND_ALL_BILL_URL = "/bills";
    private static final String CREATE_ALL_BILL_URL = "/bills";
    private static final String FIND_BILL_BY_ID_URL = "/bill/{id}";
    private static final String UPDATE_BILL_BY_ID_URL = "/bill/{id}";
    private static final String DELETE_BILL_BY_ID_URL = "/bill/{id}";

    private static final String MSG_BILL_NOT_FOUND = "Not found bill by id: ";

    /**
     * Find all bill.
     *
     * @return
     */
    @GetMapping(FIND_ALL_BILL_URL)
    public List<Bill> findAll() {
        return billService.findAll();
    }

    /**
     * Find bill by id
     *
     * @param id
     * @return
     */
    @GetMapping(FIND_BILL_BY_ID_URL)
    public Bill findBillById(@PathVariable(value = "id") int id) {

        Optional<Bill> bill = billService.findOne(id);
        if (!bill.isPresent()) {
            throw new BillNotFoundException(MSG_BILL_NOT_FOUND + id);
        }
        return bill.get();
    }


    /**
     * Created new bill
     *
     * @param bill
     * @return
     */
    @PostMapping(CREATE_ALL_BILL_URL)
    public Bill createBill(@Valid @RequestBody Bill bill) {
        return billService.saveBill(bill);
    }

    /**
     * Update bill by id
     *
     * @param id
     * @param billDetail
     * @return
     */
    @PutMapping(UPDATE_BILL_BY_ID_URL)
    public Bill updateBill(@PathVariable(value = "id") int id,
                           @Valid @RequestBody Bill billDetail) {

        Optional<Bill> billOptional = billService.findOne(id);
        if (!billOptional.isPresent()) {
            throw new BillNotFoundException(MSG_BILL_NOT_FOUND + id);
        } else {
            Bill bill = billOptional.get();
            bill.setOrderId(billDetail.getOrderId());
            bill.setAmount(billDetail.getAmount());

            Bill updateBill = billService.save(bill);
            return updateBill;
        }
    }

    /**
     * Delete bill by id
     *
     * @param id
     * @return
     */
    @DeleteMapping(DELETE_BILL_BY_ID_URL)
    public Bill deleteBill(@PathVariable(value = "id") int id) {
        Optional<Bill> bill = billService.findOne(id);
        if (!bill.isPresent()) {
            throw new BillNotFoundException(MSG_BILL_NOT_FOUND + id);
        } else {
            billService.delete(bill);
            return bill.get();
        }
    }
}
